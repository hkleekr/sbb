package com.example.sbb.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Data
public class AnswerResponseDto {

    private Integer id;
    private String subject;
    private String content;
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;
    private Question question;
    private String responseCode;
}
