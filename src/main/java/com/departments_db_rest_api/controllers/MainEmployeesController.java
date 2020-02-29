package com.departments_db_rest_api.controllers;

import com.departments_db_rest_api.entities.MainEmployee;
import com.departments_db_rest_api.services.MainEmployeesService;
import com.departments_db_rest_api.web_services.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/departments_app/")
public class MainEmployeesController {

    private MainEmployeesService mainEmployeesService;

    @Autowired
    public MainEmployeesController(MainEmployeesService mainEmployeesService) {
        this.mainEmployeesService = mainEmployeesService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/main_employees")
    public List<MainEmployee> findAllMainEmpl() {
        return mainEmployeesService.findAll();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/main_employees/{id:\\d+}")
    public MainEmployee findMeById(@PathVariable Long id) {
        Optional<MainEmployee> mainEmployee = mainEmployeesService.findById(id);
        if (mainEmployee.isPresent()) {
            return mainEmployee.get();
        }
        else throw new NotFoundException("Main Employee with Id: " + id + " Not Found!");
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/main_employees")
    public MainEmployee createMainEmployee(@Valid @RequestBody MainEmployee mainEmployee) {
        return  mainEmployeesService.save(mainEmployee);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/main_employees/{id:\\d+}")
    public MainEmployee updateMainEmployee(@PathVariable Long id, @Valid @RequestBody MainEmployee updateMainEmpl){
        return mainEmployeesService.findById(id).map(mainEmpl -> {
            mainEmpl.setFirstName(updateMainEmpl.getFirstName());
            mainEmpl.setMiddleName(updateMainEmpl.getMiddleName());
            mainEmpl.setLastName(updateMainEmpl.getLastName());
            mainEmpl.setBirthDate(updateMainEmpl.getBirthDate());
            mainEmpl.setPassport(updateMainEmpl.getPassport());
            mainEmpl.setMainDepartment(updateMainEmpl.getMainDepartment());
            return mainEmployeesService.save(mainEmpl);
        }).get();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/main_employees/{id:\\d+}")
    public void deleteMainEmpl(@PathVariable Long id) {
        mainEmployeesService.deleteById(id);
    }
}

