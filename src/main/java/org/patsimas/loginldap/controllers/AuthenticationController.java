package org.patsimas.loginldap.controllers;

import lombok.extern.slf4j.Slf4j;
import org.patsimas.loginldap.dto.AuthenticationRequestDto;
import org.patsimas.loginldap.exceptions.authentication.AuthenticationErrorResponse;
import org.patsimas.loginldap.exceptions.authentication.AuthenticationFailedException;
import org.patsimas.loginldap.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping(value = "/authenticate")
public class AuthenticationController {

    @Autowired
    AuthenticationService authenticationService;

    @PostMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> createAuthenticateToken(@RequestBody AuthenticationRequestDto authenticationRequest) throws Exception{

        log.info("Authenticate user {}", authenticationRequest.getUsername());

        return ResponseEntity.ok(authenticationService.authenticate(authenticationRequest));
    }

    @ExceptionHandler(AuthenticationFailedException.class)
    public ResponseEntity<AuthenticationErrorResponse> exceptionHandler(Exception ex) {

        AuthenticationErrorResponse authenticationErrorResponse = AuthenticationErrorResponse.builder()
                .errorCode(HttpStatus.NOT_ACCEPTABLE.value())
                .status(HttpStatus.NOT_ACCEPTABLE)
                .message(ex.getMessage())
                .build();

        return new ResponseEntity<AuthenticationErrorResponse>(authenticationErrorResponse, HttpStatus.NOT_ACCEPTABLE);
    }

}
