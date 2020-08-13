package org.patsimas.loginldap.services;

import lombok.extern.slf4j.Slf4j;
import org.patsimas.loginldap.dto.MyUserDetails;
import org.patsimas.loginldap.dto.Person;
import org.patsimas.loginldap.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
@Slf4j
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    PersonRepository personRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        log.info("Load person process [username: {}] start", username);

        Person person = personRepository.findPersonByUsername(username);

        if (ObjectUtils.isEmpty(person))
            throw new UsernameNotFoundException("Not found " + username);

        return new MyUserDetails(person);
    }
}
