package com.departments_db_rest_api.controllers;

import com.departments_db_rest_api.entities.MainDepartment;
import com.departments_db_rest_api.repository.MainDepartmentRepository;
import com.departments_db_rest_api.web_services.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/departments")
public class MainDepartmentsController {

    private MainDepartmentRepository mainDepartmentRepository;

    @Autowired
    public MainDepartmentsController(MainDepartmentRepository mainDepartmentRepository) {
        this.mainDepartmentRepository = mainDepartmentRepository;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/main_departments")
    public List<MainDepartment> findAll() {
        return mainDepartmentRepository.findAll();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/main_departments/{id:\\d+}")
    public MainDepartment findMdById(@PathVariable Long id) {
        Optional<MainDepartment> mainDepartments = mainDepartmentRepository.findById(id);
        if(mainDepartments.isPresent()){
            return mainDepartments.get();
        }
       else throw new NotFoundException("Main Department with Id: " + id + " Not Found!");
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/main_departments/name/{name:[\\w\\s]+}")
    public MainDepartment findByName(@PathVariable String name) {
        Optional<MainDepartment> mainDepartments = mainDepartmentRepository.findByName(name);
         if(mainDepartments.isPresent()) {
             return mainDepartments.get();
         }
         else throw new NotFoundException("Main Department with Name: " + name + " Not Found!");
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/main_departments/findByEmplFirstName/{firstName:[\\w\\S]+}")
    public MainDepartment findByEmplFirstName(@PathVariable String firstName) {
        Optional<MainDepartment> mainDepartments = mainDepartmentRepository.findMDByEmplFirstName(firstName);
        if(mainDepartments.isPresent()) {
            return mainDepartments.get();
        }
        else throw new NotFoundException("Main Department with Employees First name: " + firstName + " Not Found!");
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/main_departments")
    public MainDepartment createMainDep(@Valid @RequestBody MainDepartment mainDepartment) {
        return  mainDepartmentRepository.save(mainDepartment);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/main_departments/{id:\\d+}")
    public MainDepartment updateMainDep(@PathVariable Long id, @Valid @RequestBody MainDepartment mainDepUpdate) {
        return mainDepartmentRepository.findById(id).map(mainDep -> {
               mainDep.setName(mainDepUpdate.getName());
               return mainDepartmentRepository.save(mainDep);
        }).get();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/main_departments/{id:\\d+}")
    public void deleteMainDep(@PathVariable Long id) {
        MainDepartment mainDep= mainDepartmentRepository.findById(id).get();
               mainDepartmentRepository.delete(mainDep);
    }




}

