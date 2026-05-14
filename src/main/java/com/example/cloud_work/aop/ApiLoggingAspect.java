package com.example.cloud_work.aop;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import tools.jackson.databind.ObjectMapper;

import java.time.LocalDateTime;
import java.util.Objects;

@Aspect
@Component
@Slf4j
@RequiredArgsConstructor
public class ApiLoggingAspect {

    private final ObjectMapper objectMapper;

    @Around("execution(* com.example.cloud_work.controller.MemberController.addMember(..)) || " +
            "execution(* com.example.cloud_work.controller.MemberController.getMember(..))")
    public Object memberApi(ProceedingJoinPoint joinPoint) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) Objects
                .requireNonNull(RequestContextHolder.getRequestAttributes()))
                .getRequest();

        String requestUrl = request.getRequestURI();
        LocalDateTime requestTime = LocalDateTime.now();

        String requestBody = objectMapper.writeValueAsString(joinPoint.getArgs());

        log.info("[API 요청] : requestTime = {}, url = {}, requestBody = {}",
                requestTime, requestUrl, requestBody);

        Object result = joinPoint.proceed();

        String responseBody = objectMapper.writeValueAsString(result);
        log.info("[API 응답] url={}, responseBody={}", requestUrl, responseBody);

        return result;
    }


}
