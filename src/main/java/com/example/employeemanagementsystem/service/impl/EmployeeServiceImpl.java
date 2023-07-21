package com.example.employeemanagementsystem.service.impl;

import com.example.employeemanagementsystem.model.Employee;
import com.example.employeemanagementsystem.model.EmployeeFilter;
import com.example.employeemanagementsystem.repository.EmployeeRepository;
import com.example.employeemanagementsystem.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import jakarta.persistence.criteria.Predicate;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Page<Employee> getAllEmployees(EmployeeFilter employeeFilter, Pageable pageable) {

        Specification<Employee> spec = (root, query, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.conjunction();
            if (employeeFilter.getName() != null) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("name"), employeeFilter.getName()));
            }
            if (employeeFilter.getSurname() != null) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("surname"), employeeFilter.getSurname()));
            }
            if (employeeFilter.getSalary() >=0) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("salary"), employeeFilter.getSalary()));
            }

            if (employeeFilter.getPosition() != null) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("position"), employeeFilter.getPosition()));
            }

            return predicate;
        };
        return employeeRepository.findAll(spec, pageable);
    }

    @Override
    public Employee getEmployeeById(Long id) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        return optionalEmployee.orElse(null);
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }



}