package com.example.cloud_work.entity;

import com.example.cloud_work.dto.MemberRequest;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "members")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Long age;
    private String mbti;
    private String imageurl;

    public Member(String name, Long age, String mbti) {
        this.name = name;
        this.age = age;
        this.mbti = mbti;
        this.imageurl = null;
    }

    public static Member from(MemberRequest request) {
        return new Member(
                request.name(),
                request.age(),
                request.mbti()
        );
    }

    public void updateImageUrl(String imageurl) {
        this.imageurl = imageurl;
    }

}
