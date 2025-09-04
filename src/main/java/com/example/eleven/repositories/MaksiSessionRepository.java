package com.example.eleven.repositories;

import com.example.eleven.models.MaksiSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaksiSessionRepository extends JpaRepository<MaksiSession, Long> {
}
