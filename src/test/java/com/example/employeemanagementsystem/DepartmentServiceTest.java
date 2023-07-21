package com.example.employeemanagementsystem;

import com.example.employeemanagementsystem.model.Department;
import com.example.employeemanagementsystem.repository.DepartmentRepository;
import com.example.employeemanagementsystem.service.impl.DepartmentServiceImpl;
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
import static org.mockito.ArgumentMatchers.any;
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
        Page<Department> page = new PageImpl<>(departments);
        when(departmentRepository.findAll(any(Specification.class), any(Pageable.class))).thenReturn(page);

        // Call the service method
        Page<Department> result = departmentService.getAllDepartments(null, Pageable.unpaged());

        // Verify the result
        assertEquals(2, result.getContent().size());
        assertEquals("Finans", result.getContent().get(1).getDepartmentName());
    }

}