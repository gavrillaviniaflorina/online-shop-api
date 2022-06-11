package com.example.onlineshopapi.service;


import com.example.onlineshopapi.exceptions.PieceNotFoundException;
import com.example.onlineshopapi.model.PieceOfClothing;
import com.example.onlineshopapi.repository.PieceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PieceService {

    private PieceRepository pieceRepository;

    public PieceService(PieceRepository pieceRepository) {
        this.pieceRepository = pieceRepository;
    }

    public List<PieceOfClothing> getAll(){
        return pieceRepository.findAll();
    }

    public List<PieceOfClothing> getCardOfAPerson(Long person_id){
        return  pieceRepository.allPiecesOfAPerson(person_id);

    }

    public void updatePieceFromCard(PieceOfClothing updatePiece){


        Boolean updatedId=this.pieceRepository.existsById(updatePiece.getId());

        if(!updatedId){
            throw new PieceNotFoundException(
                    "Piece not found"
            );
        }

        this.pieceRepository.findById(updatePiece.getId()).map(note -> {

            note.setName(updatePiece.getName());
            note.setCategory(updatePiece.getCategory());
            note.setColor(updatePiece.getColor());

            return pieceRepository.save(note);
        });
    }



}
