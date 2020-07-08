package com.ptit.ncovihdv.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetListHealthMonitorResponse {

    private List<HealthMonitorResponse> list;
    private UserResponse user;
}
