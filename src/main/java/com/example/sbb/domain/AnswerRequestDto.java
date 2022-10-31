package com.example.sbb.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Data
public class AnswerRequestDto {

    private String content;
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;
    private Question question;

//    Dto에서 Entity로 요청하는 부분
    public Answer toEntity() {
        return Answer.builder()
                .content(content)
                .createDate(createDate)
                .modifyDate(modifyDate)
                .question(question)
                .build();
    }
}
