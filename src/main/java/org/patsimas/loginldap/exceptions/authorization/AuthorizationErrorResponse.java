package org.patsimas.loginldap.exceptions.authorization;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthorizationErrorResponse {

    private int errorCode;

    private HttpStatus status;

    private String message;
}
