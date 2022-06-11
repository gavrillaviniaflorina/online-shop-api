package com.example.onlineshopapi.security;


import com.example.onlineshopapi.model.Person;
import com.example.onlineshopapi.repository.PersonRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {
    private PersonRepository personRepository;

    public UserDetailsServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Person person=personRepository.selectedEmailExists(s).get();
        if(person!=null){
            return person;
        }

        throw  new UsernameNotFoundException(
                "User "+s+"not found"
        );
    }
}
