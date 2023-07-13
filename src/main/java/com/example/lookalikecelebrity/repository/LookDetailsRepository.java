package com.example.lookalikecelebrity.repository;

import com.example.lookalikecelebrity.entity.LookDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LookDetailsRepository extends JpaRepository<LookDetails, Long> {
    LookDetails findByCelebIdAndCelebId(Long celebId, Long lookId);

    List<LookDetails> findAllByCelebId(Long celebId);

}