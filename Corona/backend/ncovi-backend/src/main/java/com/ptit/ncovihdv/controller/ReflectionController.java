package com.ptit.ncovihdv.controller;

import com.ptit.ncovihdv.dto.request.SendReflectionContactResquest;
import com.ptit.ncovihdv.dto.request.SendReflectionInfoRequest;
import com.ptit.ncovihdv.dto.response.BaseResponseData;
import com.ptit.ncovihdv.dto.response.ReflectionContactResponse;
import com.ptit.ncovihdv.dto.response.ReflectionInfoResponse;
import com.ptit.ncovihdv.service.ReflectionService;
import com.ptit.ncovihdv.util.message.ApplicationCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/reflection")
public class ReflectionController {

    @Autowired
    private ReflectionService reflectionService;

    @PostMapping("/contact")
    public ResponseEntity<?> sendContact(@RequestBody SendReflectionContactResquest request) {
        BaseResponseData<ReflectionContactResponse> responeData = new BaseResponseData<>();
        ReflectionContactResponse response = reflectionService.sendReflectionContact(request);
        responeData.setData(response);
        responeData.setMessage(ApplicationCode.SEND_DATA_REFLECTION_CONTACT_SUCCESS);
        responeData.setErrorCode(ApplicationCode.SUCCESS);
        return new ResponseEntity<>(responeData, HttpStatus.OK);
    }

    @PostMapping("/info")
    public ResponseEntity<?> sendInfo(@RequestBody SendReflectionInfoRequest request) {
        BaseResponseData<ReflectionInfoResponse> responeData = new BaseResponseData<>();
        ReflectionInfoResponse response = reflectionService.sendReflectionInfo(request);
        responeData.setData(response);
        responeData.setMessage(ApplicationCode.SEND_DATA_REFLECTION_INFO_SUCCESS);
        responeData.setErrorCode(ApplicationCode.SUCCESS);
        return new ResponseEntity<>(responeData, HttpStatus.OK);
    }
}
