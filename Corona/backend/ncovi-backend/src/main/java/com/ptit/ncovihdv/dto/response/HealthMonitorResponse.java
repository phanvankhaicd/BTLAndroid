package com.ptit.ncovihdv.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class HealthMonitorResponse {
    private Integer healthMonitorId;
    private Integer healthMonitorSick;
    private Integer healthMonitorCough;
    private Integer healthMonitorSultry;
    private Integer healthMonitorTired;
    private Integer healthMonitorGood;
    private Integer healthMonitorStatus;
    private String healthMonitorDescription;
    private LocalDateTime healthMonitorCreateAt;
}
