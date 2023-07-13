package com.example.lookalikecelebrity.repository;

import com.example.lookalikecelebrity.entity.CelebrityDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CelebrityDetailsRepository extends JpaRepository<CelebrityDetails, Long> {

    List<CelebrityDetails> findAllByCelebNameIn(List<String> celebNames);
}