package com.boot.springapi.domain.user;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class InsertUser {
    @NotNull
    private String email;

    @NotNull
    private String password;

    @NotNull
    private String name;
}
