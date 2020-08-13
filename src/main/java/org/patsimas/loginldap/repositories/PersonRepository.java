package org.patsimas.loginldap.repositories;

import org.patsimas.loginldap.dto.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.query.LdapQuery;
import org.springframework.ldap.query.SearchScope;
import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;
import org.springframework.security.ldap.LdapUtils;

import javax.naming.NamingException;
import javax.naming.directory.Attributes;

import static org.springframework.ldap.query.LdapQueryBuilder.query;

//https://www.baeldung.com/spring-ldap
//https://memorynotfound.com/spring-boot-spring-ldap-advanced-ldap-queries-example/

@Repository
public class PersonRepository {

    @Autowired
    private LdapTemplate ldapTemplate;

    public Person findPersonByUsername(String username) {

        LdapQuery query = query()
                .searchScope(SearchScope.SUBTREE)
                .timeLimit(5000)
                .countLimit(10)
                .attributes("sn", "givenName", "mail", "sAMAccountName", "pwdLastSet")
                //.base(LdapUtils.emptyLdapName())
                .where("objectclass").is("user")
                .and("sAMAccountName").is(username)
                .and("sAMAccountName").isPresent();

        return ldapTemplate.search(query, new PersonAttributesMapper()).get(0);
    }

    private class PersonAttributesMapper implements AttributesMapper<Person> {
        public Person mapFromAttributes(Attributes attrs) throws NamingException {

            String password = null;

            Object passwordValue = attrs.get("pwdLastSet").get();

            if (!ObjectUtils.isEmpty(passwordValue))
                password = LdapUtils.convertPasswordToString(passwordValue);

            return Person.builder()
                    .surName((String)attrs.get("sn").get())
                    .givenName((String)attrs.get("givenName").get())
                    .email((String)attrs.get("mail").get())
                    .username((String)attrs.get("sAMAccountName").get())
                    .password(password)
                    .build();
        }
    }
}
