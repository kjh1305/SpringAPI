package com.boot.springapi.domain.user;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class User {
    long id;

    @NotNull
    String email;

    @NotNull
    String password;

    @NotNull
    String name;

    LocalDateTime det;

    public User(){

    }
    public User(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }
}
