package com.example.sbb.domain;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@Data
@Entity
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "text")
    private String content;

    private LocalDateTime createDate;
    private LocalDateTime modifyDate;

    @ManyToOne  
    private Question question;

    @Builder

    public Answer(Integer id, String content, LocalDateTime createDate, LocalDateTime modifyDate, Question question) {
        this.id = id;
        this.content = content;
        this.createDate = createDate;
        this.modifyDate = modifyDate;
        this.question = question;
    }

    @PrePersist
    void createDate() {this.createDate = LocalDateTime.now();}
    void modifyDate() {this.modifyDate = LocalDateTime.now();}
}
