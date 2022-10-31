package com.example.sbb.Service;

import com.example.sbb.domain.Question;
import com.example.sbb.domain.QuestionRequestDto;
import com.example.sbb.domain.QuestionResponseDto;
import com.example.sbb.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class QuestionService {

    private final QuestionRepository questionRepository;

    public List<Question> getList() {
        return this.questionRepository.findAll();
    }

    @Transactional
    public Object getDetail(Integer id) {
        Optional<Question> oq = questionRepository.findById(id);
        return oq.get();
    }

    @Transactional
    public void save(QuestionRequestDto requestDto, QuestionResponseDto responseDto) {
        Integer getId = questionRepository.save(requestDto.toEntity()).getId();
        String result = ""; //responseCode
        boolean questionSave = questionRepository.existsById(getId);
        if (questionSave == true) {
            result = "질문저장성공";
        }
        responseDto.setResponseCode(result);
    }

    @Transactional
    public void modify(Integer id, QuestionRequestDto requestDto, QuestionResponseDto responseDto) {
        Optional<Question> oq = questionRepository.findById(id);
        Question q = oq.get();
        q.setSubject(requestDto.getSubject());
        q.setContent(requestDto.getContent());
        q.setModifyDate(LocalDateTime.now());
        questionRepository.save(q);
        String result ="";
        boolean questionModify = questionRepository.existsById(id);
        if(questionModify == true) {
            result = "질문수정성공";
        }
        responseDto.setResponseCode(result);
    }

    @Transactional
    public void delete(Integer id, QuestionResponseDto responseDto) {
        questionRepository.deleteById(id);
        String result = "";
        if(questionRepository.existsById(id) == false) {
            result = "질문삭제성공";
        }
        responseDto.setResponseCode(result);
    }
}
