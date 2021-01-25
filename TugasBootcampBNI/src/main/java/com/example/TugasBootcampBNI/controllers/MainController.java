/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.TugasBootcampBNI.controllers;

import com.example.TugasBootcampBNI.entities.Department;
import com.example.TugasBootcampBNI.repositories.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author User
 */
@Controller
public class MainController {
    @Autowired
    DepartmentRepository departmentRepository;

    @GetMapping("/")
    public String main(Model model){
//        System.out.println("Coba Cetak");
//        List<Department> departments = departmentRepository.findAll();
//        for (Department department: departments) {
//            System.out.println("Department Id   : "+department.getId());
//            System.out.println("Department Name : "+department.getName());
//        }
        model.addAttribute("departments",departmentRepository.findAll());
        model.addAttribute("department",new Department());
        return "index";
    }
    @PostMapping("/save")
    public String save(Department department){
        departmentRepository.save(department);
        return "redirect:/";
    }
    
    @GetMapping("/get/{id}")
    public String getById(Model model, @PathVariable("id") String id){
        model.addAttribute("departments",departmentRepository.findAll());
        model.addAttribute("department",departmentRepository.findById(id).get());
        return "index";
    }

    @GetMapping("/test")
    public String test(){
        System.out.println("Coba cetak");
        //Cara 1 Set Data
        Department d = new Department();
        d.setId("D03");
        d.setName("OPR");
        departmentRepository.save(d);

        departmentRepository.save(new Department("D03","ORM"));

        for (Department department : departmentRepository.findAll()) {
            System.out.println("Department Id   : "+department.getId());
            System.out.println("Department Name : "+department.getName());
        }
        return "index";
    }
    
}
