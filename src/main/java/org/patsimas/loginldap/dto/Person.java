package org.patsimas.loginldap.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Person {

    String surName;

    String givenName;

    String email;

    String username;

    String password;
}
