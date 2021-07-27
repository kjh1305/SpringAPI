package com.boot.springapi.domain.user;


import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class InsertUser {
    @NotNull
    private String email;

    @NotNull
    private String password;

    @NotNull
    private String name;
}
