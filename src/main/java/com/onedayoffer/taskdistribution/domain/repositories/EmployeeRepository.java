package com.onedayoffer.taskdistribution.domain.repositories;

import com.onedayoffer.taskdistribution.domain.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Sviridov_V_A
 * @version 1.0.0-SNAPSHOT
 * @since 2024-03-23
 */
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}
