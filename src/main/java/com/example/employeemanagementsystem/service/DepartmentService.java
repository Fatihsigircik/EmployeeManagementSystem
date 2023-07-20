package com.example.employeemanagementsystem.service;

import com.example.employeemanagementsystem.model.Department;
import java.util.List;

public interface DepartmentService {

    List<Department> getAllDepartments();

    Department getDepartmentById(Long id);

    Department saveDepartment(Department department);

    void deleteDepartment(Long id);

    // Diğer metotlar
}