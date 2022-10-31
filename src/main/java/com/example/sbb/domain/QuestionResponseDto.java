package com.example.sbb.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@Data
public class QuestionResponseDto {

    private Integer id;
    private String subject;
    private String content;
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;
    private List<Answer> answerList;
    private String responseCode;

}
