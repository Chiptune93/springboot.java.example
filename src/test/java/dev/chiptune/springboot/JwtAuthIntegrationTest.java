package dev.chiptune.springboot;

import dev.chiptune.springboot.util.JwtTokenUtil;
import dev.chiptune.springboot.util.RefreshTokenStore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class JwtAuthIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private RefreshTokenStore refreshTokenStore;

    private final String username = "testuser";
    private String validAccessToken;
    private String expiredAccessToken;
    private String validRefreshToken;
    private final String invalidRefreshToken = "invalid.refresh.token";

    @BeforeEach
    void setUp() {
        validAccessToken = jwtTokenUtil.generateToken(username);
        expiredAccessToken = jwtTokenUtil.generateExpiredToken(username);
        validRefreshToken = jwtTokenUtil.generateRefreshToken(username);
        refreshTokenStore.saveToken(username, validRefreshToken);
    }

    @Test
    void testAccessWithValidAccessToken() throws Exception {
        mockMvc.perform(get("/api/secure")
                        .header("Authorization", "Bearer " + validAccessToken))
                .andExpect(status().isOk())
                .andExpect(content().string("You are authenticated!"));
    }

    @Test
    void testAccessWithExpiredAccessTokenAndValidRefreshToken() throws Exception {
        mockMvc.perform(get("/api/secure")
                        .header("Authorization", "Bearer " + expiredAccessToken)
                        .header("X-Refresh-Token", validRefreshToken))
                .andExpect(status().isOk())
                .andExpect(header().exists("X-New-Access-Token"));
    }

    @Test
    void testAccessWithExpiredAccessTokenAndInvalidRefreshToken() throws Exception {
        mockMvc.perform(get("/api/secure")
                        .header("Authorization", "Bearer " + expiredAccessToken)
                        .header("X-Refresh-Token", invalidRefreshToken))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void testAccessWithExpiredAccessTokenAndNoRefreshToken() throws Exception {
        mockMvc.perform(get("/api/secure")
                        .header("Authorization", "Bearer " + expiredAccessToken))
                .andExpect(status().isUnauthorized());
    }
}
