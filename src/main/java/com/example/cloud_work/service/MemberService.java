package com.example.cloud_work.service;

import com.example.cloud_work.repository.MemberRespository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRespository memberRespository;



}
