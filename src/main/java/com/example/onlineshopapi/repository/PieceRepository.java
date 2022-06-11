package com.example.onlineshopapi.repository;

import com.example.onlineshopapi.model.PieceOfClothing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PieceRepository extends JpaRepository<PieceOfClothing,Long> {

    @Query(value = "select * from note where person_id like ?1",nativeQuery = true)
    List<PieceOfClothing> allPiecesOfAPerson(Long person_id);
}
