package com.bridgelabz.repository;

import com.bridgelabz.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Purpose : To implement the interface which extends database operations
 *
 * @author SREELIPTA
 * @since 06-12-2021
 */

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Integer> {

}
