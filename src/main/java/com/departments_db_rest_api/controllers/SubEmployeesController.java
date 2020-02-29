package com.departments_db_rest_api.controllers;

import com.departments_db_rest_api.entities.SubEmployee;
import com.departments_db_rest_api.services.SubEmployeesService;
import com.departments_db_rest_api.web_services.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/departments_app")
public class SubEmployeesController {

    private SubEmployeesService subEmployeesService;

    @Autowired
    public SubEmployeesController(SubEmployeesService subEmployeesService) {
        this.subEmployeesService = subEmployeesService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/sub_employees")
    public List<SubEmployee> findAllSubEmpl() {
        return subEmployeesService.findAll();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/sub_employees/{id:\\d+}")
    public SubEmployee findSubEmplById(@PathVariable Long id) {
        Optional<SubEmployee> subEmployee = subEmployeesService.findById(id);
        if (subEmployee.isPresent()) {
            return subEmployee.get();
        } else throw new NotFoundException("Employee with Id: " + id + " Not Found!");
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/sub_employees")
    public SubEmployee createSubEmployee(@Valid @RequestBody SubEmployee subEmployee) {
        return  subEmployeesService.save(subEmployee);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/sub_employees/{id:\\d+}")
    public SubEmployee updateSubEmployee(@PathVariable Long id, @Valid @RequestBody SubEmployee updateSubEmpl){
        return subEmployeesService.findById(id).map(subEmpl -> {
            subEmpl.setFirstName(updateSubEmpl.getFirstName());
            subEmpl.setMiddleName(updateSubEmpl.getMiddleName());
            subEmpl.setLastName(updateSubEmpl.getLastName());
            subEmpl.setBirthDate(updateSubEmpl.getBirthDate());
            subEmpl.setPassport(updateSubEmpl.getPassport());
            subEmpl.setSubDepartment(updateSubEmpl.getSubDepartment());
            return subEmployeesService.save(subEmpl);
        }).get();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value =  "/sub_employees/{id:\\d+}")
    public void deleteMainEmpl(@PathVariable Long id) {
        subEmployeesService.deleteById(id);
    }
}