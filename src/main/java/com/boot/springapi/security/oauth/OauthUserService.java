package com.boot.springapi.security.oauth;

import com.boot.springapi.domain.user.CustomUserDetails;
import com.boot.springapi.domain.user.OauthUser;
import com.boot.springapi.domain.user.User;
import com.boot.springapi.repository.user.UserRepository;
import com.boot.springapi.repository.user.UserRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OauthUserService implements UserDetailsService {

    private UserRepositoryImpl userRepository;
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findUserEmail(username);
        return new org.springframework.security.core.userdetails.User(user.get().getEmail(), user.get().getPassword(), getAuthorities());
    }

    private Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER")); }

}
