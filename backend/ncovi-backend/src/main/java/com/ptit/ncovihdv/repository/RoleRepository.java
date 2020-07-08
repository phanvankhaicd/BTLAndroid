package com.ptit.ncovihdv.repository;

import com.ptit.ncovihdv.model.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role, Integer> {
    Role findByRoleCode(String roleCode);

}
