package com.example.cloud_work.service;

import com.example.cloud_work.dto.MemberDetail;
import com.example.cloud_work.dto.MemberRequest;
import com.example.cloud_work.entity.Member;
import com.example.cloud_work.exception.MemberNotFoundException;
import com.example.cloud_work.repository.MemberRespository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRespository memberRespository;

    @Transactional
    public MemberDetail saveMember(MemberRequest request) {
        Member member = Member.from(request);
        memberRespository.save(member);

        return MemberDetail.from(member);
    }

    @Transactional(readOnly = true)
    public MemberDetail getOneMember(Long memberId) {
        Member member = memberRespository.findById(memberId).orElseThrow(
                () -> new MemberNotFoundException("해당 팀원은 존재하지 않습니다.")
        );

        return MemberDetail.from(member);
    }
}
