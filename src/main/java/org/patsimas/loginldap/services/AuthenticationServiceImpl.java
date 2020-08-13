package org.patsimas.loginldap.services;

import lombok.extern.slf4j.Slf4j;
import org.patsimas.loginldap.config.security.JwtTokenProvider;
import org.patsimas.loginldap.dto.AuthenticationRequestDto;
import org.patsimas.loginldap.dto.AuthenticationResponseDto;
import org.patsimas.loginldap.exceptions.authentication.AuthenticationFailedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Override
    public AuthenticationResponseDto authenticate(AuthenticationRequestDto authenticationRequest) {

        log.info("Authentication processs begins");

        Authentication authentication = authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),
                authenticationRequest.getPassword()));

        if (ObjectUtils.isEmpty(authentication))
            throw new AuthenticationFailedException("Incorrect username or password");

        final String token = jwtTokenProvider.generateToken(authentication);

        log.info("Authentication processs completed");

        return AuthenticationResponseDto.builder()
                .jwt(token)
                .username(authenticationRequest.getUsername())
                .build();
    }

    private Authentication authenticate(UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken){

        try {

            return authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        }
        catch (BadCredentialsException e){

            e.printStackTrace();

            return null;
        }
    }
}
