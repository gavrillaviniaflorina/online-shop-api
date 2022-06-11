package com.example.onlineshopapi.service;

import com.example.onlineshopapi.dto.PieceDto;
import com.example.onlineshopapi.exceptions.BadRequestException;
import com.example.onlineshopapi.exceptions.InvalidEmailOrPasswordException;
import com.example.onlineshopapi.exceptions.PersonNotFoundException;
import com.example.onlineshopapi.exceptions.PieceNotFoundException;
import com.example.onlineshopapi.model.Person;
import com.example.onlineshopapi.model.PieceOfClothing;
import com.example.onlineshopapi.repository.PersonRepository;
import com.example.onlineshopapi.repository.PieceRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class PersonService {

    private PersonRepository personRepository;
    private PieceRepository pieceRepository;
    private ModelMapper modelMapper;

    public PersonService(PersonRepository personRepository, PieceRepository pieceRepository, ModelMapper modelMapper) {
        this.personRepository = personRepository;
        this.pieceRepository = pieceRepository;
        this.modelMapper = modelMapper;
    }
    public List<Person> getAll(){
        return  this.personRepository.findAll();
    }

    public void addPerson(Person person){

        Boolean existsEmail=this.personRepository.selectedEmailExists(person.getEmail()).isPresent();

        if(existsEmail){
            throw  new BadRequestException(
                    "Email "+person.getEmail()+" taken"
            );
        }
        personRepository.save(new Person(person.getName(),person.getEmail(),person.getPassword()));
    }

    public void deletePerson(Long id){
        Boolean existsId=this.personRepository.selectedIdExists(id).isEmpty();

        if(existsId){
            throw new PersonNotFoundException(
                    "Person not found"
            );
        }
        personRepository.deleteById(id);
    }

    public void updatePerson(Person updatePerson){


        Boolean updateId=this.personRepository.selectedIdExists(updatePerson.getId()).isEmpty();

        if(updateId){
            throw new PersonNotFoundException(
                    "Person not found"
            );
        }

        this.personRepository.findById(updatePerson.getId()).map(person->{
            person.setEmail(updatePerson.getEmail());
            person.setName(updatePerson.getName());
            person.setPassword(updatePerson.getPassword());

            return personRepository.save(person);
        });



    }

    public void addPieceToCard(PieceDto pieceDto){
        Optional<Person> person=this.personRepository.selectedIdExists(pieceDto.getIdPerson());


        if(person.isPresent()){

            Person person1=person.get();

            modelMapper.getConfiguration()
                    .setMatchingStrategy(MatchingStrategies.LOOSE);

            PieceOfClothing piece= new PieceOfClothing();
            modelMapper.map(pieceDto,piece);
            person1.addNote(piece);
            personRepository.save(person.get());

        }else{

            throw new PersonNotFoundException(
                    "Person not found"
            );
        }

    }

    public void removePieceFromCard(Long id){

        Optional<PieceOfClothing> note= pieceRepository.findById(id);
        if (note.isPresent()) {


            pieceRepository.deleteById(id);


        }else{
            throw new PieceNotFoundException(
                    "Piece not found"
            );
        }

    }

    public Optional<Person> searchPerson(String email,String password){


        Optional<Person> person=personRepository.selectedEmailExists(email);
        if(person.isEmpty()){
            throw new InvalidEmailOrPasswordException("Invalid email or password");
        }

        return person;

    }

    public Long  getActualPersonId(String email, String password){
        Optional<Person> person=personRepository.selectedEmailExists(email);
        if(person.isEmpty()){
            throw new InvalidEmailOrPasswordException("Invalid email or password");
        }

        return person.get().getId();
    }

}
