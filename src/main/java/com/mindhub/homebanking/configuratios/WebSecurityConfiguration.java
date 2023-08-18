package com.mindhub.homebanking.configuratios;


import com.mindhub.homebanking.models.Client;
import com.mindhub.homebanking.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Configuration
public class WebSecurityConfiguration extends GlobalAuthenticationConfigurerAdapter {
    @Autowired
    private ClientRepository clientRepository;



    @Override
    public void init(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(email-> {

            Client client = clientRepository.findByEmail(email);

            if (client != null) {

                return new User(client.getEmail(), client.getPassword(), 

                        AuthorityUtils.createAuthorityList("CLIENT"));


            } else {

                throw new UsernameNotFoundException("Unknown user: " + email);

            }

        });

    }


}
