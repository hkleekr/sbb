package com.example.sbb.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@Data
public class QuestionRequestDto {

    private String subject;
    private String content;
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;
    private List<Answer> answerList;

//    Dto에서 entity로 요청하는 부분
    public Question toEntity() {
        return Question.builder()
                .subject(subject)
                .content(content)
                .createDate(createDate)
                .modifyDate(modifyDate)
                .build();
    }
}
