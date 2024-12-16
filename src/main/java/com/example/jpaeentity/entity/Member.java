package com.example.jpaeentity.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "member")
@Getter
public class Member extends BaseEntity {

    @Id
    //MYSQL로 만들었으니까 GenerationType.IDENTITY 사용
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    private Integer age;

    public Member(String username, String password, Integer age) {
        this.username = username;
        this.password = password;
        this.age = age;
    }

    public Member() {

    }

    public void updatePassword(String password) {
        this.password = password;
    }
}
