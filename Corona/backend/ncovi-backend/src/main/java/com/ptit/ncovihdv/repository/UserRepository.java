package com.ptit.ncovihdv.repository;

import com.ptit.ncovihdv.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 04-Jun-2020
 */
@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    @Query("Select u From User u Where u.userDeviceToken IS NOT NULL")
    List<User> findUserHaveDeviceToken();
}
