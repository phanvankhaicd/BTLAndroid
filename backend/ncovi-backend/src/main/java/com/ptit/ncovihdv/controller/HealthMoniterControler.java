package com.ptit.ncovihdv.controller;

import com.ptit.ncovihdv.dto.request.HealthMonitorRequest;
import com.ptit.ncovihdv.dto.response.BaseResponseData;
import com.ptit.ncovihdv.dto.response.GetListHealthMonitorResponse;
import com.ptit.ncovihdv.dto.response.SendHealthMonitorRespone;
import com.ptit.ncovihdv.exception.ApplicationException;
import com.ptit.ncovihdv.service.HealthMonitorService;
import com.ptit.ncovihdv.util.message.ApplicationCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/health-monitor")
public class HealthMoniterControler {

    @Autowired
    private HealthMonitorService healthMonitorService;

    @PostMapping("/send")
    public ResponseEntity<?> sendHealth(@RequestBody HealthMonitorRequest request) {
        BaseResponseData<SendHealthMonitorRespone> responeData = new BaseResponseData<>();
        try {
            if (!request.isValid()) {
                throw new ApplicationException(ApplicationCode.ERROR,
                        ApplicationCode.DATA_INVALID);
            }

            SendHealthMonitorRespone response = healthMonitorService.sendHealthMonitor(request);
            responeData.setData(response);
            responeData.setMessage(ApplicationCode.SEND_DATA_HEALTH_SUCCESS);
            responeData.setErrorCode(ApplicationCode.SUCCESS);
        } catch (ApplicationException e) {
            responeData.setErrorCode(e.getCode());
            responeData.setMessage(e.getMessage());
        }
        return new ResponseEntity<>(responeData, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<?> getListHealth(
            @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(name = "size", required = false, defaultValue = "5") Integer size) {
        BaseResponseData<GetListHealthMonitorResponse> responeData = new BaseResponseData<>();
        Sort sort = Sort.by("healthMonitorCreateAt").descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        GetListHealthMonitorResponse response =
                healthMonitorService.getListHealthMonitorByUser(pageable);
        responeData.setData(response);
        responeData.setMessage(ApplicationCode.GET_DATA_HEATH_SUCCESS);
        responeData.setErrorCode(ApplicationCode.SUCCESS);
        return new ResponseEntity<>(responeData, HttpStatus.OK);
    }

}
