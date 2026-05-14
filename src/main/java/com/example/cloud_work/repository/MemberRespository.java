package com.example.cloud_work.repository;

import com.example.cloud_work.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRespository extends JpaRepository<Member, Long> {
}
