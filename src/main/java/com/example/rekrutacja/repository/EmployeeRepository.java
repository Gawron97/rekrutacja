package com.example.rekrutacja.repository;

import com.example.rekrutacja.entity.AppUserRole;
import com.example.rekrutacja.entity.users.ActivityStatus;
import com.example.rekrutacja.entity.users.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Optional<Employee> findEmployeeByLogin(String login);

    Set<Employee> findEmployeeByActivityStatusAndRole(ActivityStatus status, AppUserRole role);
}
