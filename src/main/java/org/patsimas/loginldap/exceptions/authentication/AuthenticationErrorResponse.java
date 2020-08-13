package org.patsimas.loginldap.exceptions.authentication;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationErrorResponse {

    private int errorCode;

    private HttpStatus status;

    private String message;
}
