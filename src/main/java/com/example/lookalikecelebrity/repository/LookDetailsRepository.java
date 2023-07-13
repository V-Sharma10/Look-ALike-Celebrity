package com.example.lookalikecelebrity.repository;

import com.example.lookalikecelebrity.DTO.LookDetailsDto;
import com.example.lookalikecelebrity.entity.CelebrityDetails;
import com.example.lookalikecelebrity.entity.LookDetails;
import com.example.lookalikecelebrity.responses.CelebSpecificLooksResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LookDetailsRepository extends JpaRepository<LookDetails, Long> {

    LookDetails getLookDetailsByCelebIdAndAndLookId(Long celebId, Long lookId);

}