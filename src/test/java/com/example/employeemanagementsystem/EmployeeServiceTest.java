package com.example.employeemanagementsystem;

import com.example.employeemanagementsystem.model.Department;
import com.example.employeemanagementsystem.model.Employee;
import com.example.employeemanagementsystem.repository.EmployeeRepository;
import com.example.employeemanagementsystem.service.impl.EmployeeServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @Test
    public void testGetAllEmployees() {
        // Mock the repository response
        Employee employee1 = new Employee();
        employee1.setId(1L);
        employee1.setName("Ali");
        employee1.setSurname("Ak");
        employee1.setPosition("Müdür");
        employee1.setSalary(5000.0);
        Department department1 = new Department();
        department1.setId(1L);
        department1.setDepartmentName("İnsan Kaynakları");
        employee1.setDepartment(department1);

        Employee employee2 = new Employee();
        employee2.setId(2L);
        employee2.setName("Veli");
        employee2.setSurname("Pak");
        employee2.setPosition("Bilişim Uzmanı");
        employee2.setSalary(4000.0);
        Department department2 = new Department();
        department2.setId(3L);
        department2.setDepartmentName("Bilgi İşlem");
        employee2.setDepartment(department2);

        List<Employee> employees = Arrays.asList(employee1, employee2);
        Page<Employee> page = new PageImpl<>(employees);
        when(employeeRepository.findAll(any(Specification.class), any(Pageable.class))).thenReturn(page);


        // Call the service method
        Page<Employee> result = employeeService.getAllEmployees(null, Pageable.unpaged());

        // Verify the result
        assertEquals(2, result.getContent().size());
        assertEquals("Ali", result.getContent().get(0).getName());
        assertEquals("Bilgi İşlem", result.getContent().get(1).getDepartment().getDepartmentName());
    }

}