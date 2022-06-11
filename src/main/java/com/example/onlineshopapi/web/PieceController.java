package com.example.onlineshopapi.web;

import com.example.onlineshopapi.model.PieceOfClothing;
import com.example.onlineshopapi.service.PieceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public class PieceController {

    private PieceService pieceService;

    public PieceController(PieceService pieceService) {
        this.pieceService = pieceService;
    }

    @PutMapping("update")
    public ResponseEntity<PieceOfClothing> updatePiece(@RequestBody PieceOfClothing pieceOfClothing){
        this.pieceService.updatePieceFromCard(pieceOfClothing);
        return new ResponseEntity<>(pieceOfClothing, HttpStatus.OK);
    }

    @GetMapping("cardOfAPerson/{idPerson}")
    public ResponseEntity<List<PieceOfClothing>> getCardOfAPerson(@PathVariable Long idPerson){

        return  new ResponseEntity<>(this.pieceService.getCardOfAPerson(idPerson), HttpStatus.OK);
    }
}
