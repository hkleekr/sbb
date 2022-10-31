package com.example.sbb.Service;

import com.example.sbb.domain.*;
import com.example.sbb.repository.AnswerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import java.time.LocalDateTime;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AnswerService {

    private final AnswerRepository answerRepository;

    @Transactional
    public void save(AnswerRequestDto requestDto, AnswerResponseDto responseDto) {
        Integer getId = answerRepository.save(requestDto.toEntity()).getId();
        String result = ""; //responseCode
        boolean answerSave = answerRepository.existsById(getId);
        if (answerSave == true) {
            result = "답변저장성공";
        }
        responseDto.setResponseCode(result);
    }

    @Transactional
    public void modify(Integer id, AnswerRequestDto requestDto, AnswerResponseDto responseDto) {
        Optional<Answer> oa = answerRepository.findById(id);
        Answer a = oa.get();
        a.setContent(requestDto.getContent());
        a.setModifyDate(LocalDateTime.now());
        answerRepository.save(a);
        String result = "";
        boolean answerModify = answerRepository.existsById(id);
        if (answerModify == true) {
            result = "답변수정성공";
        }
        responseDto.setResponseCode(result);
    }

    @Transactional
    public void delete(Integer id, AnswerResponseDto responseDto) {
        answerRepository.deleteById(id);
        String result = "";
        if (answerRepository.existsById(id) == false) {
            result = "답변삭제성공";
        }
        responseDto.setResponseCode(result);
    }
}
