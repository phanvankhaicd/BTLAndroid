package com.ptit.ncovihdv.service.impl;

import com.ptit.ncovihdv.dto.request.SendReflectionContactResquest;
import com.ptit.ncovihdv.dto.request.SendReflectionInfoRequest;
import com.ptit.ncovihdv.dto.response.ReflectionContactResponse;
import com.ptit.ncovihdv.dto.response.ReflectionInfoResponse;
import com.ptit.ncovihdv.dto.response.UserResponse;
import com.ptit.ncovihdv.model.Account;
import com.ptit.ncovihdv.model.Reflection;
import com.ptit.ncovihdv.model.User;
import com.ptit.ncovihdv.repository.AccountRepository;
import com.ptit.ncovihdv.repository.ReflectionRepository;
import com.ptit.ncovihdv.service.ReflectionService;
import com.ptit.ncovihdv.util.common.Constant;
import com.ptit.ncovihdv.util.common.DateUtil;
import com.ptit.ncovihdv.util.common.type.ReflectionType;
import com.ptit.ncovihdv.util.common.type.StatusEnum;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReflectionServiceImpl implements ReflectionService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ReflectionRepository reflectionRepository;

    @Override
    public ReflectionContactResponse sendReflectionContact(
            SendReflectionContactResquest request) {
        Reflection reflection = new Reflection();
        UserResponse userRespone = new UserResponse();
        ReflectionContactResponse reflectionContactResponse = new ReflectionContactResponse();
        String username = Constant.getCurrentUser();
        User user = getCurrentUser(username);
        BeanUtils.copyProperties(request, reflection);
        BeanUtils.copyProperties(user, userRespone);
        reflection.setUserByUser(user);
        reflection.setReflectionCreatedAt(DateUtil.getCreateAt());
        reflection.setReflectionType(ReflectionType.CONTACT.getValue());
        reflectionRepository.save(reflection);
        BeanUtils.copyProperties(reflection, reflectionContactResponse);
        reflectionContactResponse.setUser(userRespone);
        return reflectionContactResponse;
    }

    @Override
    public ReflectionInfoResponse sendReflectionInfo(SendReflectionInfoRequest request) {
        Reflection reflection = new Reflection();
        UserResponse userRespone = new UserResponse();
        ReflectionInfoResponse reflectionResponse = new ReflectionInfoResponse();
        String username = Constant.getCurrentUser();
        User user = getCurrentUser(username);
        BeanUtils.copyProperties(request, reflection);
        BeanUtils.copyProperties(user, userRespone);
        reflection.setUserByUser(user);
        reflection.setReflectionCreatedAt(DateUtil.getCreateAt());
        reflection.setReflectionType(ReflectionType.INFO.getValue());
        reflectionRepository.save(reflection);
        BeanUtils.copyProperties(reflection, reflectionResponse);
        reflectionResponse.setUser(userRespone);
        return reflectionResponse;
    }

    public User getCurrentUser(String username) {
        Account account = accountRepository.findAccountByUsernameAndStatus(username,
                StatusEnum.ACTIVE.getValue());
        return account.getUserByUserId();
    }

}
