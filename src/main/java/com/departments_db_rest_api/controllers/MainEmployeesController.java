package com.departments_db_rest_api.controllers;

import com.departments_db_rest_api.entities.MainEmployee;
import com.departments_db_rest_api.repository.MainDepartmentRepository;
import com.departments_db_rest_api.repository.MainEmployeesRepository;
import com.departments_db_rest_api.web_services.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/departments/main_departments")
public class MainEmployeesController {

    private MainEmployeesRepository mainEmployeesRepository;

    private MainDepartmentRepository mainDepartmentRepository;

    @Autowired
    public MainEmployeesController(MainDepartmentRepository mainDepartmentRepository,
                                   MainEmployeesRepository mainEmployeesRepository) {
          this.mainDepartmentRepository = mainDepartmentRepository;
          this.mainEmployeesRepository = mainEmployeesRepository;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/main_employees")
    public List<MainEmployee> findAllMainEmpl() {
        return mainEmployeesRepository.findAll();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{mainDepId:\\d+}/employees")
    public List<MainEmployee>findMainEmplByMainDepId(@PathVariable Long mainDepId) {
        if (mainDepartmentRepository.existsById(mainDepId)) {
            return mainEmployeesRepository.findByMainDepartmentId(mainDepId);
        }  else throw new NotFoundException("Main Department with Id: " + mainDepId + " Not Found!");
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{mainDepId:\\d+}/employees/{id:\\d+}")
    public MainEmployee findMeById(@PathVariable Long mainDepId,
                                   @PathVariable Long id) {
        if(mainDepartmentRepository.existsById(mainDepId)) {
            Optional<MainEmployee> mainEmployee = mainEmployeesRepository.findById(id);
            if (mainEmployee.isPresent()) {
                return mainEmployee.get();
            }
            else throw new NotFoundException("Main Employee with Id: " + id
                    + " in Main Department with Id: " + mainDepId + " Not Found!");
        }
        else throw new NotFoundException("Main Department with Id: " + mainDepId + " Not Found!");
    }


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/main_employees")
    public MainEmployee createMainEmployee(@Valid @RequestBody MainEmployee mainEmployee) {
        return  mainEmployeesRepository.save(mainEmployee);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/{mainDepId:\\d+}/employees/{id:\\d+}")
    public MainEmployee updateMainEmployee(@PathVariable Long mainDepId,
                   @PathVariable Long id, @Valid @RequestBody MainEmployee updateMainEmpl){
        if(mainDepartmentRepository.existsById(mainDepId)){
            return mainEmployeesRepository.findById(id)
                    .map(mainEmpl -> {
                        mainEmpl.setFirstName(updateMainEmpl.getFirstName());
                        mainEmpl.setMiddleName(updateMainEmpl.getMiddleName());
                        mainEmpl.setLastName(updateMainEmpl.getLastName());
                        mainEmpl.setBirthDate(updateMainEmpl.getBirthDate());
                        mainEmpl.setPassport(updateMainEmpl.getPassport());
                        mainEmpl.setMainDepartment(updateMainEmpl.getMainDepartment());
                        return mainEmployeesRepository.save(mainEmpl);
                    }).get();
        }
        else throw new NotFoundException("Main Department with Id: " + mainDepId + " Not Found!");
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/{mainDepId:\\d+}/employees/{id:\\d+}")
    public void deleteMainEmpl(@PathVariable Long mainDepId, @PathVariable Long id) {
        if(mainDepartmentRepository.existsById(mainDepId)) {
            MainEmployee mainEmpl =  mainEmployeesRepository.findById(id).get();
                mainEmployeesRepository.delete(mainEmpl);
        }
        else throw new NotFoundException("Main Department with Id: " + mainDepId + " Not Found!");
    }
}

