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
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

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
        when(employeeRepository.findAll()).thenReturn(employees);

        // Call the service method
        List<Employee> result = employeeService.getAllEmployees();

        // Verify the result
        assertEquals(2, result.size());
        assertEquals("Ali", result.get(0).getName());
        assertEquals("Bilgi İşlem", result.get(1).getDepartment().getDepartmentName());
    }

    // Add other test methods for other service methods
}