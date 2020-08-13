package org.patsimas.loginldap.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
public class AuthenticationResponseDto {

    private final String jwt;

    private String username;
}
