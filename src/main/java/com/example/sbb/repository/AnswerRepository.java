package com.example.sbb.repository;

import com.example.sbb.domain.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerRepository extends JpaRepository <Answer, Integer> {
}
