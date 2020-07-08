package com.ptit.ncovihdv.repository;

import com.ptit.ncovihdv.model.HealthMonitor;
import com.ptit.ncovihdv.model.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HealthMonitorRepository extends CrudRepository<HealthMonitor, Integer> {
    List<HealthMonitor> findByUserByUser(User user, Pageable pageable);
}
