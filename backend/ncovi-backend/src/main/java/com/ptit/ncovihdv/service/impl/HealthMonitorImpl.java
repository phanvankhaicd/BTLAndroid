package com.ptit.ncovihdv.service.impl;

import com.ptit.ncovihdv.dto.request.HealthMonitorRequest;
import com.ptit.ncovihdv.dto.response.GetListHealthMonitorResponse;
import com.ptit.ncovihdv.dto.response.HealthMonitorResponse;
import com.ptit.ncovihdv.dto.response.SendHealthMonitorRespone;
import com.ptit.ncovihdv.dto.response.UserResponse;
import com.ptit.ncovihdv.model.Account;
import com.ptit.ncovihdv.model.HealthMonitor;
import com.ptit.ncovihdv.model.User;
import com.ptit.ncovihdv.repository.AccountRepository;
import com.ptit.ncovihdv.repository.HealthMonitorRepository;
import com.ptit.ncovihdv.service.HealthMonitorService;
import com.ptit.ncovihdv.util.common.Constant;
import com.ptit.ncovihdv.util.common.DateUtil;
import com.ptit.ncovihdv.util.common.type.HealthType;
import com.ptit.ncovihdv.util.common.type.StatusEnum;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HealthMonitorImpl implements HealthMonitorService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private HealthMonitorRepository healthMonitorRepository;

    @Override
    public SendHealthMonitorRespone sendHealthMonitor(HealthMonitorRequest request) {
        HealthMonitor healthMonitor = new HealthMonitor();
        UserResponse userRespone = new UserResponse();
        HealthMonitorResponse healthMonitorResponse = new HealthMonitorResponse();
        String username = Constant.getCurrentUser();
        User user = getCurrentUser(username);
        healthMonitor.setUserByUser(user);
        healthMonitor.setHealthMonitorCreateAt(DateUtil.getCreateAt());
        BeanUtils.copyProperties(user, userRespone);
        BeanUtils.copyProperties(request, healthMonitor);
        if (request.getHealthMonitorGood() == 0) {
            healthMonitor.setHealthMonitorStatus(HealthType.BAD.getValue());
            StringBuilder sb = new StringBuilder();
            if (checkStatus(request.getHealthMonitorSick())) {
                sb.append("Sốt, ");
            }
            if (checkStatus(request.getHealthMonitorCough())) {
                sb.append("Ho, ");
            }
            if (checkStatus(request.getHealthMonitorSultry())) {
                sb.append("Khó thở, ");
            }
            if (checkStatus(request.getHealthMonitorTired())) {
                sb.append("Đau người, mệt mỏi, ");
            }
            healthMonitor.setHealthMonitorDescription(sb.substring(0, sb.length() - 2).toString());
        } else if (request.getHealthMonitorGood() == 1) {
            healthMonitor.setHealthMonitorStatus(HealthType.SAFE.getValue());
            healthMonitor.setHealthMonitorDescription("Bình thường");
        }
        BeanUtils.copyProperties(healthMonitor, healthMonitorResponse);
        healthMonitorRepository.save(healthMonitor);
        return new SendHealthMonitorRespone(healthMonitorResponse, userRespone);
    }

    @Override
    public GetListHealthMonitorResponse getListHealthMonitorByUser(Pageable pageable) {
        UserResponse userRespone = new UserResponse();

        String username = Constant.getCurrentUser();
        User user = getCurrentUser(username);
        BeanUtils.copyProperties(user, userRespone);
        List<HealthMonitor> healthMonitor =
                healthMonitorRepository.findByUserByUser(user, pageable);
        List<HealthMonitorResponse> listHeathiMonitorResponse =
                new ArrayList<HealthMonitorResponse>();
        for (HealthMonitor health : healthMonitor) {
            HealthMonitorResponse healthMonitorResponse = new HealthMonitorResponse();
            BeanUtils.copyProperties(health, healthMonitorResponse);
            listHeathiMonitorResponse.add(healthMonitorResponse);
        }
        return new GetListHealthMonitorResponse(listHeathiMonitorResponse, userRespone);
    }

    public Boolean checkStatus(Integer status) {
        if (status == 0) {
            return false;
        } else {
            return true;
        }
    }

    public User getCurrentUser(String username) {
        Account account = accountRepository.findAccountByUsernameAndStatus(username,
                StatusEnum.ACTIVE.getValue());
        return account.getUserByUserId();
    }
}
