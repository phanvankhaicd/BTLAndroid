package com.ptit.ncovihdv.service;

import com.ptit.ncovihdv.dto.request.HealthMonitorRequest;
import com.ptit.ncovihdv.dto.response.GetListHealthMonitorResponse;
import com.ptit.ncovihdv.dto.response.SendHealthMonitorRespone;
import org.springframework.data.domain.Pageable;

public interface HealthMonitorService {

    public SendHealthMonitorRespone sendHealthMonitor(HealthMonitorRequest request);

    public GetListHealthMonitorResponse getListHealthMonitorByUser(Pageable pageable);
}
