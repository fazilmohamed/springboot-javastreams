package com.learn.springbootjavastreams.repository;

import com.learn.springbootjavastreams.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
