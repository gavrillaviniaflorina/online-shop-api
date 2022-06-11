package com.example.onlineshopapi.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PieceDto {


    private Long idPerson;
    private String name;
    private String category;
    private String color;
}
