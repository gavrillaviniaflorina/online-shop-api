package com.example.onlineshopapi.web;

import com.example.onlineshopapi.dto.LoginDto;
import com.example.onlineshopapi.dto.PieceDto;
import com.example.onlineshopapi.model.Person;
import com.example.onlineshopapi.service.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("api/v1/persons")
@RestController
@CrossOrigin
public class PersonController {

    private PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public ResponseEntity<List<Person>> getPersons(){
        return new ResponseEntity<>(this.personService.getAll(), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<Optional<Person>> validPerson(@RequestBody LoginDto loginDto) {

        return new ResponseEntity<>(this.personService.searchPerson(loginDto.getEmail(), loginDto.getPassword()),HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Person> addPerson(@RequestBody Person person){
        this.personService.addPerson(person);
        return  new ResponseEntity<>(person,HttpStatus.OK);
    }


    @DeleteMapping("delete/{id}")

    @ResponseStatus(value = HttpStatus.OK)
    public void  deletePerson(@PathVariable Long id){

        this.personService.deletePerson(id);

    }

    @PostMapping("/addPieceToCard")
    public ResponseEntity<PieceDto> addPiece(@RequestBody PieceDto pieceDto){
        this.personService.addPieceToCard(pieceDto);
        return new ResponseEntity<>(pieceDto,HttpStatus.OK);
    }

    @DeleteMapping("deletePieceFromCard/{id}")

    @ResponseStatus(value = HttpStatus.OK)
    public void deletePiece(@PathVariable Long id){
        this.personService.deletePerson(id);
    }

    @PostMapping("getPersonId")
    public ResponseEntity<Long> getPersonId(@RequestBody LoginDto loginDto){

        return new ResponseEntity<>(this.personService.getActualPersonId(loginDto.getEmail(), loginDto.getPassword()),HttpStatus.OK);
    }


}
