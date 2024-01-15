package com.example.rekrutacja.service;

import com.example.rekrutacja.entity.users.ActivityStatus;
import com.example.rekrutacja.entity.users.Employee;
import com.example.rekrutacja.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public void changeActiveStatus(ActivityStatus status, String name) {
        Employee user = getEmployeeByUsername(name);
        user.setActivityStatus(status);
        employeeRepository.save(user);
    }

    public Employee getEmployeeByUsername(String username) {
        return employeeRepository.findEmployeeByLogin(username).orElseThrow(
                () -> new UsernameNotFoundException("Employee with username " + username + " not found")
        );
    }
}
