package com.example.lookalikecelebrity.repository;

import com.example.lookalikecelebrity.entity.CelebrityDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CelebrityDetailsRepository extends JpaRepository<CelebrityDetails, Long> {
    @Query(value = "select c from CelebrityDetails c where c.celebName in ?1", nativeQuery = true)
    List<CelebrityDetails> getAllByCelebNames(List<String> celebNames);
}