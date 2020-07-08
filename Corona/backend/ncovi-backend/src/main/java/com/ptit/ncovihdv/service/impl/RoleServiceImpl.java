package com.ptit.ncovihdv.service.impl;

import com.ptit.ncovihdv.model.Role;
import com.ptit.ncovihdv.repository.RoleRepository;
import com.ptit.ncovihdv.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role getRoleByCode(String code) {
        return roleRepository.findByRoleCode(code);
    }

}
