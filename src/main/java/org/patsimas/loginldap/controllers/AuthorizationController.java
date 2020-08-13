package org.patsimas.loginldap.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class AuthorizationController {

    @GetMapping(value = "/hello")
    public String all(){

        log.info("page for all");

        return "Aris Thessaloniki!!!";
    }
}
