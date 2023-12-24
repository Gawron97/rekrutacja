package com.example.rekrutacja.repository;

import com.example.rekrutacja.entity.users.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
