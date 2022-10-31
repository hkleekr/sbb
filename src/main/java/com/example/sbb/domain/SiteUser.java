package com.example.sbb.domain;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

@NoArgsConstructor
@Data
@Entity
public class SiteUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "사용자 ID는 필수항목입니다.")
    private String username;

    @NotEmpty(message = "password는 필수항목입니다.")
    private String password;

    @NotEmpty(message = "email은 필수항목입니다.")
    private String email;

    @Builder
    public SiteUser(Long id, String username, String password, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
    }
}
