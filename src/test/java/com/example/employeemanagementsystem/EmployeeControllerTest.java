package com.example.employeemanagementsystem;

import com.example.employeemanagementsystem.controller.EmployeeController;
import com.example.employeemanagementsystem.model.Department;
import com.example.employeemanagementsystem.model.Employee;
import com.example.employeemanagementsystem.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@ExtendWith(MockitoExtension.class)
public class EmployeeControllerTest {

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private EmployeeController employeeController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();
    }

    @Test
    public void testGetAllEmployees() throws Exception {
        // Mock the service response
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
        when(employeeService.getAllEmployees(null, Pageable.unpaged())).thenReturn(page);

        // Perform the GET request and verify the result
        mockMvc.perform(MockMvcRequestBuilders.get("/api/employees")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Ali"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].department.departmentName").value("Bilgi İşlem"))
                .andDo(print());
    }

}
