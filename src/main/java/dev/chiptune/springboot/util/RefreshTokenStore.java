package dev.chiptune.springboot.util;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class RefreshTokenStore {
    private final Map<String, String> store = new ConcurrentHashMap<>();

    public void saveToken(String username, String refreshToken) {
        store.put(username, refreshToken);
    }

    public String getToken(String username) {
        return store.get(username);
    }

    public void removeToken(String username) {
        store.remove(username);
    }
}

