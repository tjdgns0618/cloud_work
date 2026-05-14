package com.example.cloud_work.dto;

import com.example.cloud_work.entity.Member;

public record MemberDetail(String name, Long age, String mbti) {
    public static MemberDetail from(Member member) {
        return new MemberDetail(
                member.getName(),
                member.getAge(),
                member.getMbti()
        );
    }
}
