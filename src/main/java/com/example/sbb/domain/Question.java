package com.example.sbb.domain;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@Data
@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 200)
    private String subject;

    @Column(columnDefinition = "text")
    private String content;

    private LocalDateTime createDate;
    private LocalDateTime modifyDate;

    @ManyToOne(fetch = FetchType.LAZY)
    private SiteUser siteUser;

    @OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE)
    private List<Answer> answerList;

    @Builder
    public Question(Integer id, String subject, String content, LocalDateTime createDate, LocalDateTime modifyDate, SiteUser siteUser, List<Answer> answerList) {
        this.id = id;
        this.subject = subject;
        this.content = content;
        this.createDate = createDate;
        this.modifyDate = modifyDate;
        this.siteUser = siteUser;
        this.answerList = answerList;
    }

    @PrePersist
    void createDate() {this.createDate = LocalDateTime.now();}
    void modifyDate() {this.modifyDate = LocalDateTime.now();}
}
