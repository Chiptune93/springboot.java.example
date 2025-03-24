package dev.chiptune.springboot.controller;

import dev.chiptune.springboot.util.JwtTokenUtil;
import dev.chiptune.springboot.util.RefreshTokenStore;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final JwtTokenUtil jwtTokenUtil;
    private final RefreshTokenStore refreshTokenStore;

    public AuthController(JwtTokenUtil jwtTokenUtil, RefreshTokenStore refreshTokenStore) {
        this.jwtTokenUtil = jwtTokenUtil;
        this.refreshTokenStore = refreshTokenStore;
    }

    @PostMapping("/login")
    public Map<String, String> login(@RequestParam String username, HttpServletResponse response) {
        // ✅ 액세스 토큰 & 리프레시 토큰 생성
        String accessToken = jwtTokenUtil.generateToken(username);
        String refreshToken = jwtTokenUtil.generateRefreshToken(username);

        // ✅ 액세스 토큰을 HttpOnly 쿠키에 저장
        Cookie accessCookie = new Cookie("access_token", accessToken);
        accessCookie.setHttpOnly(true);
        accessCookie.setSecure(true);
        accessCookie.setPath("/");
        accessCookie.setMaxAge(60 * 30); // 30분

        // ✅ 리프레시 토큰을 HttpOnly 쿠키에 저장
        Cookie refreshCookie = new Cookie("refresh_token", refreshToken);
        refreshCookie.setHttpOnly(true);
        refreshCookie.setSecure(true);
        refreshCookie.setPath("/");
        refreshCookie.setMaxAge(60 * 60 * 24 * 7); // 7일

        response.addCookie(accessCookie);
        response.addCookie(refreshCookie);

        return Map.of(
                "accessToken", accessToken,
                "refreshToken", refreshToken
        );
    }

    @PostMapping("/refresh")
    public Map<String, String> refresh(@RequestParam String username, @RequestParam String refreshToken) {
        String storedToken = refreshTokenStore.getToken(username);
        if (storedToken == null || !storedToken.equals(refreshToken)) {
            throw new RuntimeException("Invalid refresh token");
        }

        if (!jwtTokenUtil.validateToken(refreshToken)) {
            throw new RuntimeException("Expired refresh token");
        }

        String newAccessToken = jwtTokenUtil.generateToken(username);
        return Map.of("accessToken", newAccessToken);
    }
}
