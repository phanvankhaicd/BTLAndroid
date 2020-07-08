package com.ptit.ncovihdv.dto.request;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SendReflectionInfoRequest {
    private Integer reflectionInfoQuestion1;
    private Integer reflectionInfoQuestion2;
    private Integer reflectionInfoQuestion3;
    private String reflectionInfoDescription;
    private LocalDateTime reflectionInfoTime;
    private String reflectionInfoAddress;
}
