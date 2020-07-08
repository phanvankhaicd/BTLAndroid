package com.ptit.ncovihdv.service;

import com.ptit.ncovihdv.dto.request.SendReflectionContactResquest;
import com.ptit.ncovihdv.dto.request.SendReflectionInfoRequest;
import com.ptit.ncovihdv.dto.response.ReflectionContactResponse;
import com.ptit.ncovihdv.dto.response.ReflectionInfoResponse;

public interface ReflectionService {

    public ReflectionContactResponse sendReflectionContact(
            SendReflectionContactResquest request);

    public ReflectionInfoResponse sendReflectionInfo(SendReflectionInfoRequest request);
}
