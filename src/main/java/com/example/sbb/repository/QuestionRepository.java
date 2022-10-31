package com.example.sbb.repository;

import com.example.sbb.domain.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository <Question, Integer> {
    Question findBySubject(String subject);
    Question findBySubjectAndContent(String subject, String content);
    Page<Question> findBySubjectContainingOrContentContaining(String subject, String content, Pageable pageable);
    List<Question> findBySubjectLike(String subject);
}
