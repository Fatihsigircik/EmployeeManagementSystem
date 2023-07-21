package com.example.employeemanagementsystem.service;

import com.example.employeemanagementsystem.model.Department;
import java.util.List;

import com.example.employeemanagementsystem.model.DepartmentFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DepartmentService {

    Page<Department> getAllDepartments(DepartmentFilter departmentFilter, Pageable pageable);

    Department getDepartmentById(Long id);

    Department saveDepartment(Department department);

    void deleteDepartment(Long id);

    // Diğer metotlar
}