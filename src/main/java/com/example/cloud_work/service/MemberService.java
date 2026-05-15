package com.example.cloud_work.service;

import com.example.cloud_work.dto.MemberDetail;
import com.example.cloud_work.dto.MemberRequest;
import com.example.cloud_work.entity.Member;
import com.example.cloud_work.exception.MemberNotFoundException;
import com.example.cloud_work.repository.MemberRespository;
import io.awspring.cloud.s3.S3Template;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRespository memberRespository;

    private static final Duration PRESIGNED_URL_EXPIRATION = Duration.ofDays(7);

    private final S3Template s3Template;

    @Value("${spring.cloud.aws.s3.bucket}")
    private String bucket;

    private Member findMember(Long memberId) {
        return memberRespository.findById(memberId).orElseThrow(
                () -> new MemberNotFoundException("해당 팀원은 존재하지 않습니다.")
        );
    }

    @Transactional
    public MemberDetail saveMember(MemberRequest request) {
        Member member = Member.from(request);
        memberRespository.save(member);

        return MemberDetail.from(member);
    }

    @Transactional(readOnly = true)
    public MemberDetail getOneMember(Long memberId) {
        Member member = findMember(memberId);

        return MemberDetail.from(member);
    }

    @Transactional
    public void upload(Long memberId, MultipartFile file) {
        Member member = findMember(memberId);

        try {
            String key = "uploads/" + UUID.randomUUID() + "_" + file.getOriginalFilename();
            s3Template.upload(bucket, key, file.getInputStream());
            member.updateImageUrl(key);
        } catch (IOException e) {
            // 적절한 커스텀 예외로 바꾸고, GlobalExceptionHandler로 핸들링 필요
            throw new RuntimeException("파일 업로드 실패", e);
        }
    }

    public URL getDownloadUrl(Long memberId) {
        Member member = findMember(memberId);

        return s3Template.createSignedGetURL(bucket, member.getImageurl(), PRESIGNED_URL_EXPIRATION);
    }
}
