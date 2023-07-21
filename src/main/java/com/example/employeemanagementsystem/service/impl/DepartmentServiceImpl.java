package com.example.employeemanagementsystem.service.impl;

import com.example.employeemanagementsystem.model.Department;
import com.example.employeemanagementsystem.model.DepartmentFilter;
import com.example.employeemanagementsystem.repository.DepartmentRepository;
import com.example.employeemanagementsystem.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import jakarta.persistence.criteria.Predicate;

import javax.swing.text.html.HTMLDocument;
import java.util.List;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public Page<Department> getAllDepartments(DepartmentFilter departmentFilter, Pageable pageable) {
        Specification<Department> spec = (root, query, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.conjunction();
            if (departmentFilter.getDepartmentName() != null) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("departmentName"), departmentFilter.getDepartmentName()));
            }

            return predicate;
        };

        return departmentRepository.findAll(spec, pageable);

    }

    @Override
    public Department getDepartmentById(Long id) {
        Optional<Department> optionalDepartment = departmentRepository.findById(id);
        return optionalDepartment.orElse(null);
    }

    @Override
    public Department saveDepartment(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public void deleteDepartment(Long id) {
        departmentRepository.deleteById(id);
    }

}