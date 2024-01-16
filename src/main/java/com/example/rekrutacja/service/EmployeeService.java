package com.example.rekrutacja.service;

import com.example.rekrutacja.entity.users.ActivityStatus;
import com.example.rekrutacja.entity.users.Employee;
import com.example.rekrutacja.repository.EmployeeRepository;
import com.example.rekrutacja.utils.exception.EmployeeNotAvailableException;
import com.example.rekrutacja.utils.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    @Value("${employee.availability-timeout.minutes}")
    private Integer AVAILABILITY_TIMEOUT_MINUTES;

    private final EmployeeRepository employeeRepository;

    public void changeActiveStatus(ActivityStatus status, String name) {
        Employee user = getEmployeeByUsername(name);
        user.setLastActivity(LocalDateTime.now());
        user.setActivityStatus(status);
        employeeRepository.save(user);
    }

    public Employee getEmployeeByUsername(String username) {
        return employeeRepository.findEmployeeByLogin(username).orElseThrow(
                () -> new ResourceNotFoundException("Employee with username " + username + " not found")
        );
    }

    public Employee getAvailableEmployee() {
        return employeeRepository.findEmployeeByActivityStatus(ActivityStatus.ACTIVE).stream()
                .filter(this::checkEmployeeAvailable)
                .findFirst()
                .orElseThrow(() -> new EmployeeNotAvailableException("No available employees found"));
    }

    private boolean checkEmployeeAvailable(Employee employee) {
        boolean isEmployeeAvailable =
                employee.getActivityStatus().equals(ActivityStatus.ACTIVE) &&
                employee.getLastActivity().isAfter(LocalDateTime.now().minusMinutes(AVAILABILITY_TIMEOUT_MINUTES));

        if(!isEmployeeAvailable) {
            employee.setActivityStatus(ActivityStatus.INACTIVE);
            employeeRepository.save(employee);
            return false;
        }

        return true;
    }
}
