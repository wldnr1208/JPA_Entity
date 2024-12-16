package com.example.jpaeentity.entity;


import jakarta.persistence.*;
import lombok.Getter;

//1
@Getter
@Entity
@Table(name = "board")
public class Board extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "longtext")
    private String contents;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    public Board(String contents, String title) {
        this.contents = contents;
        this.title = title;
    }

    public Board() {

    }

    public void setMember(Member member) {
        this.member = member;
    }
}
