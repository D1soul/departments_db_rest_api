package com.departments_db_rest_api.controllers;

import com.departments_db_rest_api.entities.MainDepartment;
import com.departments_db_rest_api.services.MainDepartmentService;
import com.departments_db_rest_api.web_services.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/departments_app")
public class MainDepartmentsController {

   private MainDepartmentService mainDepartmentService;

    @Autowired
    public MainDepartmentsController(MainDepartmentService mainDepartmentService) {
        this.mainDepartmentService = mainDepartmentService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/main_departments")
    public List<MainDepartment> findAll() {
        return mainDepartmentService.findAll();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/main_departments/{id:\\d+}")
    public MainDepartment findMdById(@PathVariable Long id) {
        Optional<MainDepartment> mainDepartments = mainDepartmentService.findById(id);
        if(mainDepartments.isPresent()){
            return mainDepartments.get();
        }
       else throw new NotFoundException("Main Department with Id: " + id + " Not Found!");
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/main_departments/name/{name:[\\w\\s]+}")
    public MainDepartment findByName(@PathVariable String name) {
        Optional<MainDepartment> mainDepartments = mainDepartmentService.findByName(name);
         if(mainDepartments.isPresent()) {
             return mainDepartments.get();
         }
         else throw new NotFoundException("Main Department with Name: " + name + " Not Found!");
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/main_departments")
    public MainDepartment createMainDep(@Valid @RequestBody MainDepartment mainDepartment) {
        return  mainDepartmentService.save(mainDepartment);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/main_departments/{id:\\d+}")
    public MainDepartment updateMainDep(@PathVariable Long id, @Valid @RequestBody MainDepartment mainDepUpdate) {
        return mainDepartmentService.findById(id).map(mainDep -> {
               mainDep.setName(mainDepUpdate.getName());
               return mainDepartmentService.save(mainDep);
        }).get();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/main_departments/{id:\\d+}")
    public void deleteMainDep(@PathVariable Long id) {
        mainDepartmentService.deleteById(id);
    }




}

