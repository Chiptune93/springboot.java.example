package dev.chiptune.springboot.config;

import dev.chiptune.springboot.service.CustomUserDetailsService;
import dev.chiptune.springboot.util.JwtTokenUtil;
import dev.chiptune.springboot.util.RefreshTokenStore;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenUtil jwtTokenUtil;
    private final RefreshTokenStore refreshTokenStore;
    private final CustomUserDetailsService userDetailsService;

    public JwtAuthenticationFilter(JwtTokenUtil jwtTokenUtil,
                                   RefreshTokenStore refreshTokenStore,
                                   CustomUserDetailsService userDetailsService) {
        this.jwtTokenUtil = jwtTokenUtil;
        this.refreshTokenStore = refreshTokenStore;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        // JWT 검사를 하지 않을 경로 예외 처리
        String requestURI = request.getRequestURI();
        if (isPublicPath(requestURI)) {
            filterChain.doFilter(request, response); // 인증 로직 생략
            return;
        }

        String header = request.getHeader("Authorization");

        if (header != null && header.startsWith("Bearer ")) {
            String token = header.substring(7);

            // Step 1: Access Token 유효한 경우
            if (jwtTokenUtil.validateToken(token)) {
                String username = jwtTokenUtil.getUsernameFromToken(token);
                authenticateUser(username, request);
            } else {
                // Step 2: Refresh Token 검사
                String refreshToken = request.getHeader("X-Refresh-Token");

                if (refreshToken != null) {
                    try {
                        String username = jwtTokenUtil.getUsernameFromToken(token); // expired token에서도 claim 추출 가능
                        String stored = refreshTokenStore.getToken(username);

                        if (stored != null && stored.equals(refreshToken) && jwtTokenUtil.validateToken(refreshToken)) {
                            // 새 Access Token 발급
                            String newAccessToken = jwtTokenUtil.generateToken(username);
                            response.setHeader("X-New-Access-Token", newAccessToken);

                            authenticateUser(username, request);
                        } else {
                            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid refresh token");
                            return;
                        }
                    } catch (Exception e) {
                        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid refresh token");
                        return;
                    }
                } else {
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token expired and no refresh token provided");
                    return;
                }
            }
        }

        filterChain.doFilter(request, response);
    }

    private void authenticateUser(String username, HttpServletRequest request) {
        var userDetails = userDetailsService.loadUserByUsername(username);
        var auth = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(auth);
    }

    // 필터를 생략할 공개 경로 목록
    private boolean isPublicPath(String uri) {
        return uri.startsWith("/api/public/") ||
                uri.equals("/login") ||
                uri.equals("/signup") ||
                uri.equals("/favicon.ico");
    }
}
