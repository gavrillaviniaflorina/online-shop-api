package com.example.onlineshopapi.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.GenerationType.SEQUENCE;

@Table(name = "piceOfClothing")
@Entity(name = "PiceOfClothing")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class PieceOfClothing {

    @Id
    @SequenceGenerator(
            name="pieces_sequence",
            sequenceName = "pieces_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "pieces_sequence"
    )
    @Column(
            name="id"
    )

    private Long id;

    @Column(
            name="name",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String name;

    @Column(
            name="category",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String category;

    @Column(
            name="color",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String color;

    public PieceOfClothing(String name, String category, String color) {
        this.name = name;
        this.category = category;
        this.color = color;
    }
    @ManyToOne(
            fetch = FetchType.LAZY
    )

    @JoinColumn(
            name="person_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name="person_id_fk"
            )

    )
    @JsonBackReference
    private Person person;

    @Override
    public boolean equals(Object obj) {

        PieceOfClothing piece=(PieceOfClothing) obj;
        return piece.id==this.id;
    }
}



