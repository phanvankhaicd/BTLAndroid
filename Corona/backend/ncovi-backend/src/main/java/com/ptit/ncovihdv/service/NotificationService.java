package com.ptit.ncovihdv.service;

import com.ptit.ncovihdv.dto.request.UpdateTokenDeviceRequest;
import com.ptit.ncovihdv.exception.ApplicationException;

/**
 * 04-Jun-2020
 */
public interface NotificationService {
    void updateTokenDevice(UpdateTokenDeviceRequest request) throws ApplicationException;

    void notificationJob() throws ApplicationException;

    void crawlDataJob();

}
