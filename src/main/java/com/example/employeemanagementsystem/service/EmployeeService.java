package com.example.employeemanagementsystem.service;

import com.example.employeemanagementsystem.model.Employee;
import java.util.List;

public interface EmployeeService {

    List<Employee> getAllEmployees();

    Employee getEmployeeById(Long id);

    Employee saveEmployee(Employee employee);

    void deleteEmployee(Long id);

    // Diğer metotlar
}
