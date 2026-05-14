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

}
