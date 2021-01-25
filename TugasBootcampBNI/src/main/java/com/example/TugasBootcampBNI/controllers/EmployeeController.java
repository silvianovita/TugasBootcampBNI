/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.TugasBootcampBNI.controllers;

import com.example.TugasBootcampBNI.entities.Department;
import com.example.TugasBootcampBNI.entities.Employee;
import com.example.TugasBootcampBNI.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author User
 */
@Controller
@RequestMapping("employee")
public class EmployeeController {
    @Autowired
    EmployeeRepository employeeRepository;

    @GetMapping("/")
    public String main(Model model){
        model.addAttribute("employees",employeeRepository.findAll());
        model.addAttribute("employee",new Employee());
        return "employee-view";
    }
    @PostMapping("/saveEmployee")
    public String saveEmp(Employee employee){
        employeeRepository.save(employee);
        return "redirect:/employee/";
    }
    
    @GetMapping("/get/{id}")
    public String getById(Model model, @PathVariable("id") String id){
        model.addAttribute("employees",employeeRepository.findAll());
        model.addAttribute("employee",employeeRepository.findById(id).get());
        return "employee-view";
    }
    
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    private String delete(@RequestParam String id){
        employeeRepository.delete(new Employee(id));
        return "redirect:/employee/";       
    }

    @GetMapping("/test")
    public String test(){
        System.out.println("Coba");

        Employee e = new Employee();
        //Saat Save harus di set departmentnya
        e.setId("E03");
        e.setName("Silvia");
        e.setDepartment(new Department("D01"));
        
        employeeRepository.save(e);
        for (Employee employee : employeeRepository.findAll()) {
            System.out.println(employee.getId());           
            System.out.println(employee.getName());
            System.out.println(employee.getDepartment().getName()); 
        }
        return "employee-view";
    }
    
}
