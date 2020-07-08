package com.ptit.ncovihdv.dto.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReflectionInfoResponse {
    private Integer reflectionId;
    private Integer reflectionInfoQuestion1;
    private Integer reflectionInfoQuestion2;
    private Integer reflectionInfoQuestion3;
    private String reflectionInfoDescription;
    private String reflectionInfoAddress;
    private LocalDateTime reflectionInfoTime;
    private LocalDateTime reflectionCreatedAt;
    private Integer reflectionType;
    private UserResponse user;
}
