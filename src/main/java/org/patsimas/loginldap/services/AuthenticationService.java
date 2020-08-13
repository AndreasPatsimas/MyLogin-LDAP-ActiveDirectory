package org.patsimas.loginldap.services;

import org.patsimas.loginldap.dto.AuthenticationRequestDto;
import org.patsimas.loginldap.dto.AuthenticationResponseDto;
import org.springframework.stereotype.Service;

@Service
public interface AuthenticationService {

    AuthenticationResponseDto authenticate(AuthenticationRequestDto authenticationRequest);

}
