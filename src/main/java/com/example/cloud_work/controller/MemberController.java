package com.example.cloud_work.controller;

import com.example.cloud_work.dto.MemberDetail;
import com.example.cloud_work.dto.MemberRequest;
import com.example.cloud_work.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URL;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class MemberController {

    private final MemberService memberService;

    @PostMapping
    public ResponseEntity<MemberDetail> addMember(@Valid @RequestBody MemberRequest request) {
        MemberDetail response = memberService.saveMember(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{memberId}")
    public ResponseEntity<MemberDetail> getMember(@PathVariable Long memberId) {
        MemberDetail response = memberService.getOneMember(memberId);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/{memberId}/profile-image")
    public ResponseEntity<String> setProfileIamge(@PathVariable Long memberId, MultipartFile file) {
        memberService.upload(memberId, file);

        return ResponseEntity.status(HttpStatus.OK).body("프로필 이미지 설정 성공");
    }

    @GetMapping("/{memberId}/profile-image")
    public ResponseEntity<URL> getProfileIamge(@PathVariable Long memberId) {
        URL presignedUrl = memberService.getDownloadUrl(memberId);

        return ResponseEntity.status(HttpStatus.OK).body(presignedUrl);
    }

}
