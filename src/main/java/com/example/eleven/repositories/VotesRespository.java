package com.example.eleven.repositories;

import com.example.eleven.models.Votes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VotesRespository extends JpaRepository<Votes, Long> {
}
