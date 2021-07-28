package com.boot.springapi.domain.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.ElementCollection;
import javax.persistence.FetchType;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


@Getter
@Setter
@NoArgsConstructor
@ToString
/**
 * Spring Security 에서 사용자의 정보를 담는 인터페이스, 사용자의 정보를 불러오기 위해서 구현해야한다.
 */
public class User implements UserDetails {


    private long id;

    @NotNull
    private String email;

    @NotNull
    private String password;

    @NotNull
    private String name;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime det;

    public User(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    private List<String> roles = new ArrayList<>(Arrays.asList("ROLE_USER")); //enum으로 바꾸기

    /**
     *
     * @return 계정의 권한을 리턴
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    /**
     *
     * @return 계정의 고유한 값을 리턴 (DB PK값, 중복 없는 이메일 등)
     */
    @Override
    public String getUsername() {
        return email;
    }

    /**
     * true = 만료 안됨
     * @return  계정의 만료 여부 리턴
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * true = 잠기지 않음
     * @return  계정의 잠김 여부 리턴
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * true = 만료 안됨
     * @return  비밀번호 만료 여부 리턴
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * true = 활성화 됨
     * @return  계정의 활성화 여부 리턴턴
     */
   @Override
    public boolean isEnabled() {
        return true;
    }

    /**
     *
     * @return 계정의 비밀번호를 리턴
     */
    public String getPassword() {
        return ("{noop}" + password);
    }
}
