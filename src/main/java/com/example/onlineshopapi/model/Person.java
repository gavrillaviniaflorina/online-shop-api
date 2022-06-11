package com.example.onlineshopapi.model;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.example.onlineshopapi.security.UserRole.PERSON;
import static javax.persistence.GenerationType.SEQUENCE;

@Entity(name="Person")
@Table(name="person")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person implements UserDetails {

    @Id
    @SequenceGenerator(
            name="person_sequence",
            sequenceName = "person_sequence",
            allocationSize = 1
    )

    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "person_sequence"
    )

    @Column(
            name="id"
    )

    private long id;

    @Column(
            name="name",
            nullable = false,
            columnDefinition = "TEXT"
    )
    @NotEmpty
    private String name;


    @Column(
            name="email",
            nullable = false,
            unique = true
    )
    @Email
    @NotEmpty
    private String email;

    @Column(
            name="password",
            nullable = false


    )
    @NotEmpty
    private String password;


    @OneToMany(
            mappedBy = "person",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    @JsonManagedReference
    List<PieceOfClothing> cos=new ArrayList<>();

    public Person(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;

    }

    public void addNote(PieceOfClothing piece){
        this.cos.add(piece);
        piece.setPerson(this);
    }

    public void deleteNote(Long id){


        PieceOfClothing piece = new PieceOfClothing();
        piece.setId(id);
        this.cos.remove(piece);

    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

   @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return PERSON.getGrantedAuthrities();
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
