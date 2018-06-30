package com.redhat.springboot.vacationleave;

import com.redhat.springboot.vacationleave.model.Employee;
import com.redhat.springboot.vacationleave.repository.EmployeeRepository;
import com.redhat.springboot.vacationleave.service.EmployeeService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EmployeeServiceTest {

    @Autowired
    private EmployeeService employeeService;

    @Before
    public void beforeTest() { }

    @Test
    public void list() {
        PageRequest pageRequest = new PageRequest(0, 100);
        Page<Employee> page = employeeService.list(pageRequest);
        assertNotNull(page);
    }

    @Test
    public void leavesNotEmpty() {
        PageRequest pageRequest = new PageRequest(0, 100);
        Page<Employee> page = employeeService.list(pageRequest);
        assertNotNull(page);
        page.getContent().forEach(e -> assertNotNull(e.getLeaves()));
        for(Employee e: page.getContent()) {
            System.out.println(e);
        }
    }



}