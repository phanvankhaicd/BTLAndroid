package com.ptit.ncovihdv.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SendHealthMonitorRespone {
    private HealthMonitorResponse healthMonitor;
    private UserResponse user;
}
