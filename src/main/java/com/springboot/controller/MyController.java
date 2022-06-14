package com.springboot.controller;

import com.springboot.model.Employee;
import com.springboot.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MyController {

    private final EmployeeService employeeService;

    @Autowired
    public MyController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    @GetMapping("/")
    public String showAllEmployees(Model model) {
        List<Employee> empl = employeeService.findAll();
        model.addAttribute("allEmp", empl);
        return "all-employees";
    }

    @GetMapping("/employee-save")
    public String saveEmployeeForm(Employee employee) {
        return "employee-save";
    }

    @PostMapping("/employee-save")
    public String saveEmployee(Employee employee) {
        employeeService.saveEmployee(employee);
        return "redirect:/";
    }

    @GetMapping("/employee-delete/{id}")
    public String deleteEmployee(@PathVariable("id") Long id) {
        employeeService.deleteById(id);
        return "redirect:/";
    }

    @GetMapping("/employee-update/{id}")
    public String updateEmployeeForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("employee", employeeService.findById(id));
        return "employee-update";
    }

    @PostMapping("/employee-update")
    public String updateEmployee(Employee employee) {
        employeeService.saveEmployee(employee);
        return "redirect:/";
    }
}
