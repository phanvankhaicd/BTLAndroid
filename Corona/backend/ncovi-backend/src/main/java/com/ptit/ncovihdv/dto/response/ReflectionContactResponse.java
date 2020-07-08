package com.ptit.ncovihdv.dto.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReflectionContactResponse {
    private Integer reflectionId;
    private Integer reflectionContactQuestion1;
    private Integer reflectionContactQuestion2;
    private Integer reflectionContactQuestion3;
    private LocalDateTime reflectionCreatedAt;
    private Integer reflectionType;
    private UserResponse user;
}
