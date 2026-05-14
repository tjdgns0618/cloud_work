package com.example.cloud_work.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record MemberRequest(
        @NotBlank
        String name,

        @NotNull
        Long age,

        @NotBlank
        String mbti
) {
}
