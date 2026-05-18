package com.example.cloud_work.controller;

import com.example.cloud_work.dto.MemberDetail;
import com.example.cloud_work.dto.MemberRequest;
import com.example.cloud_work.exception.MemberNotFoundException;
import com.example.cloud_work.service.MemberService;
import jakarta.servlet.ServletException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

import java.net.URL;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.BDDMockito.willThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(
        controllers = MemberController.class,
        excludeAutoConfiguration = {
                io.awspring.cloud.autoconfigure.s3.S3AutoConfiguration.class,
                io.awspring.cloud.autoconfigure.config.parameterstore.ParameterStoreAutoConfiguration.class,
                io.awspring.cloud.autoconfigure.core.AwsAutoConfiguration.class,
                io.awspring.cloud.autoconfigure.core.CredentialsProviderAutoConfiguration.class,
                io.awspring.cloud.autoconfigure.core.RegionProviderAutoConfiguration.class
        }
)
@ActiveProfiles("test")
class MemberControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private MemberService memberService;

    @Autowired
    private ObjectMapper objectMapper;

    private MemberDetail sampleDetail() {
        return new MemberDetail("홍길동", 25L, "INTJ");
    }

    private MemberRequest validRequest() {
        return new MemberRequest("홍길동", 25L, "INTJ");
    }

    @Nested
    @DisplayName("POST /api/members - 회원 생성")
    class AddMember {

        @Test
        @DisplayName("성공: 유효한 요청으로 201 Created와 MemberDetail 반환")
        void addMember_success() throws Exception {
            given(memberService.saveMember(any(MemberRequest.class)))
                    .willReturn(sampleDetail());

            mockMvc.perform(post("/api/members")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(validRequest())))
                    .andDo(print())
                    .andExpect(status().isCreated())
                    .andExpect(jsonPath("$.name").value("홍길동"))
                    .andExpect(jsonPath("$.age").value(25))
                    .andExpect(jsonPath("$.mbti").value("INTJ"));
        }

        @Test
        @DisplayName("실패: name이 빈 문자열이면 400 Bad Request")
        void addMember_fail_blankName() throws Exception {
            MemberRequest request = new MemberRequest("", 25L, "INTJ");

            mockMvc.perform(post("/api/members")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(request)))
                    .andDo(print())
                    .andExpect(status().isBadRequest());
        }

        @Test
        @DisplayName("실패: age가 null이면 400 Bad Request")
        void addMember_fail_nullAge() throws Exception {
            MemberRequest request = new MemberRequest("홍길동", null, "INTJ");

            mockMvc.perform(post("/api/members")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(request)))
                    .andDo(print())
                    .andExpect(status().isBadRequest());
        }

        @Test
        @DisplayName("실패: mbti가 빈 문자열이면 400 Bad Request")
        void addMember_fail_blankMbti() throws Exception {
            MemberRequest request = new MemberRequest("홍길동", 25L, "");

            mockMvc.perform(post("/api/members")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(request)))
                    .andDo(print())
                    .andExpect(status().isBadRequest());
        }

        @Test
        @DisplayName("실패: 요청 본문이 비어있으면(빈 JSON) 400 Bad Request")
        void addMember_fail_emptyBody() throws Exception {
            mockMvc.perform(post("/api/members")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("{}"))
                    .andDo(print())
                    .andExpect(status().isBadRequest());
        }

        @Test
        @DisplayName("실패: Content-Type이 text/plain이면 415 Unsupported Media Type")
        void addMember_fail_unsupportedMediaType() throws Exception {
            mockMvc.perform(post("/api/members")
                            .contentType(MediaType.TEXT_PLAIN)
                            .content("name=홍길동"))
                    .andDo(print())
                    .andExpect(status().isUnsupportedMediaType());
        }
    }

    @Nested
    @DisplayName("GET /api/members/{memberId} - 회원 단건 조회")
    class GetMember {

        @Test
        @DisplayName("성공: 존재하는 memberId로 200 OK와 MemberDetail 반환")
        void getMember_success() throws Exception {
            given(memberService.getOneMember(1L))
                    .willReturn(sampleDetail());

            mockMvc.perform(get("/api/members/{memberId}", 1L))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.name").value("홍길동"))
                    .andExpect(jsonPath("$.age").value(25))
                    .andExpect(jsonPath("$.mbti").value("INTJ"));
        }

        @Test
        @DisplayName("실패: 존재하지 않는 memberId → 404 Not Found + 오류 메시지")
        void getMember_fail_notFound() throws Exception {
            given(memberService.getOneMember(999L))
                    .willThrow(new MemberNotFoundException("해당 팀원은 존재하지 않습니다."));

            mockMvc.perform(get("/api/members/{memberId}", 999L))
                    .andDo(print())
                    .andExpect(status().isNotFound())
                    .andExpect(content().string("해당 팀원은 존재하지 않습니다."));
        }

        @Test
        @DisplayName("실패: memberId가 숫자가 아니면 400 Bad Request")
        void getMember_fail_invalidPathVariable() throws Exception {
            mockMvc.perform(get("/api/members/{memberId}", "abc"))
                    .andDo(print())
                    .andExpect(status().isBadRequest());
        }
    }

    @Nested
    @DisplayName("POST /api/members/{memberId}/profile-image - 프로필 이미지 업로드")
    class SetProfileImage {

        @Test
        @DisplayName("성공: 유효한 이미지 파일 업로드 → 200 OK + '프로필 이미지 설정 성공'")
        void setProfileImage_success() throws Exception {
            willDoNothing().given(memberService).upload(anyLong(), any());

            MockMultipartFile mockFile = new MockMultipartFile(
                    "file",
                    "profile.png",
                    MediaType.IMAGE_PNG_VALUE,
                    "fake-image-bytes".getBytes()
            );

            mockMvc.perform(multipart("/api/members/{memberId}/profile-image", 1L)
                            .file(mockFile))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(content().string("프로필 이미지 설정 성공"));
        }

        @Test
        @DisplayName("실패: 존재하지 않는 memberId → 404 Not Found + 오류 메시지")
        void setProfileImage_fail_memberNotFound() throws Exception {
            willThrow(new MemberNotFoundException("해당 팀원은 존재하지 않습니다."))
                    .given(memberService).upload(anyLong(), any());

            MockMultipartFile mockFile = new MockMultipartFile(
                    "file", "profile.png",
                    MediaType.IMAGE_PNG_VALUE, "bytes".getBytes()
            );

            mockMvc.perform(multipart("/api/members/{memberId}/profile-image", 999L)
                            .file(mockFile))
                    .andDo(print())
                    .andExpect(status().isNotFound())
                    .andExpect(content().string("해당 팀원은 존재하지 않습니다."));
        }

        @Test
        @DisplayName("실패: IOException 래핑 RuntimeException → GlobalExceptionHandler 미처리 → ServletException 전파")
        void setProfileImage_fail_runtimeException() {
            willThrow(new RuntimeException("파일 업로드 실패"))
                    .given(memberService).upload(anyLong(), any());

            MockMultipartFile mockFile = new MockMultipartFile(
                    "file", "profile.png",
                    MediaType.IMAGE_PNG_VALUE, "bytes".getBytes()
            );

            // GlobalExceptionHandler가 RuntimeException을 처리하지 않아 ServletException으로 전파됨
            assertThatThrownBy(() ->
                    mockMvc.perform(multipart("/api/members/{memberId}/profile-image", 1L)
                            .file(mockFile)))
                    .isInstanceOf(ServletException.class)
                    .hasRootCauseInstanceOf(RuntimeException.class)
                    .hasRootCauseMessage("파일 업로드 실패");
        }
    }

    @Nested
    @DisplayName("GET /api/members/{memberId}/profile-image - Presigned URL 조회")
    class GetProfileImage {

        @Test
        @DisplayName("성공: 존재하는 회원의 Presigned URL 반환 → 200 OK")
        void getProfileImage_success() throws Exception {
            URL presignedUrl = new URL(
                    "https://test-bucket.s3.amazonaws.com/uploads/uuid_profile.png"
                    + "?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Expires=604800"
            );
            given(memberService.getDownloadUrl(1L))
                    .willReturn(presignedUrl);

            mockMvc.perform(get("/api/members/{memberId}/profile-image", 1L))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(content().string(
                            org.hamcrest.Matchers.containsString("test-bucket.s3.amazonaws.com")
                    ));
        }

        @Test
        @DisplayName("실패: 존재하지 않는 memberId → 404 Not Found + 오류 메시지")
        void getProfileImage_fail_memberNotFound() throws Exception {
            given(memberService.getDownloadUrl(999L))
                    .willThrow(new MemberNotFoundException("해당 팀원은 존재하지 않습니다."));

            mockMvc.perform(get("/api/members/{memberId}/profile-image", 999L))
                    .andDo(print())
                    .andExpect(status().isNotFound())
                    .andExpect(content().string("해당 팀원은 존재하지 않습니다."));
        }
    }
}
