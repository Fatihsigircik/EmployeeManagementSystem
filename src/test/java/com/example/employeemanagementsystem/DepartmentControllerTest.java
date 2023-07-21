package com.example.employeemanagementsystem;

import com.example.employeemanagementsystem.controller.DepartmentController;
import com.example.employeemanagementsystem.model.Department;
import com.example.employeemanagementsystem.model.DepartmentFilter;
import com.example.employeemanagementsystem.service.DepartmentService;
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
public class DepartmentControllerTest {

    @Mock
    private DepartmentService departmentService;

    @InjectMocks
    private DepartmentController departmentController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(departmentController).build();
    }

    @Test
    public void testGetAllDepartments() throws Exception {
        // Mock the service response
        Department department1 = new Department();
        department1.setId(1L);
        department1.setDepartmentName("İnsan Kaynakları");
        Department department2 = new Department();
        department2.setId(2L);
        department2.setDepartmentName("Finans");
        List<Department> departments = Arrays.asList(department1, department2);
        Page<Department> page = new PageImpl<>(departments);
        when(departmentService.getAllDepartments(null,Pageable.unpaged())).thenReturn(page);

        // Perform the GET request and verify the result
        mockMvc.perform(MockMvcRequestBuilders.get("/api/departments")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].departmentName").value("İnsan Kaynakları"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].departmentName").value("Finans"))
                .andDo(print());
    }

}
