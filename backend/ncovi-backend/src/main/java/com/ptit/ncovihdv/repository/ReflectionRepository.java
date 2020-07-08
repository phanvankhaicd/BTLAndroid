package com.ptit.ncovihdv.repository;

import com.ptit.ncovihdv.model.Reflection;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * 04-Jun-2020
 */
@Repository
public interface ReflectionRepository extends CrudRepository<Reflection, Integer> {
}
