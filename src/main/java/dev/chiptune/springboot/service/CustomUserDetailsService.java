package dev.chiptune.springboot.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 실제 서비스에서는 DB 조회 후 UserDetails 구현체 리턴
        return User.builder()
                .username(username)
                .password("") // JWT 기반에서는 비밀번호 필요 없음
                .authorities(Collections.emptyList())
                .build();
    }
}
