package com.boot.springapi.domain.user;

import lombok.Data;

@Data
public class OauthUser {
    private Long id;
    private String email;
    private String password;
}
