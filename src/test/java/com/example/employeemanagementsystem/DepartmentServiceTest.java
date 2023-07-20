package com.example.employeemanagementsystem;

import com.example.employeemanagementsystem.model.Department;
import com.example.employeemanagementsystem.repository.DepartmentRepository;
import com.example.employeemanagementsystem.service.impl.DepartmentServiceImpl;
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
public class DepartmentServiceTest {

    @Mock
    private DepartmentRepository departmentRepository;

    @InjectMocks
    private DepartmentServiceImpl departmentService;

    @Test
    public void testGetAllDepartments() {
        // Mock the repository response
        Department department1 = new Department();
        department1.setId(1L);
        department1.setDepartmentName("İnsan Kaynakları");
        Department department2 = new Department();
        department2.setId(2L);
        department2.setDepartmentName("Finans");
        List<Department> departments = Arrays.asList(department1, department2);
        when(departmentRepository.findAll()).thenReturn(departments);

        // Call the service method
        List<Department> result = departmentService.getAllDepartments();

        // Verify the result
        assertEquals(2, result.size());
        assertEquals("Finans", result.get(1).getDepartmentName());
    }

}