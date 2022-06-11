package com.example.onlineshopapi;

import com.example.onlineshopapi.model.Person;
import com.example.onlineshopapi.model.PieceOfClothing;
import com.example.onlineshopapi.repository.PersonRepository;
import com.example.onlineshopapi.repository.PieceRepository;
import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class OnlineShopApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(OnlineShopApiApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(
            PersonRepository personRepository, PieceRepository pieceRepository
    ){
        return args->{

            Faker faker=new Faker();


//
//                String firstName="andrei";
//                String lastName="popescu";
//                String email=String.format("%s.%s@mycode.edu",firstName,lastName);
//
//
//
//                Person person = new Person( firstName+" "+lastName,email,new BCryptPasswordEncoder().encode("12345"));
//
//                personRepository.save(person);


//
            Person person=personRepository.findById(2L).get();


            for(int i=0;i<10;i++){


                PieceOfClothing pieceOfClothing=new PieceOfClothing(faker.name().firstName(),faker.book().genre(),faker.color().name());

                person.addNote(pieceOfClothing);

            }

            personRepository.save(person);



        };
    }


}
