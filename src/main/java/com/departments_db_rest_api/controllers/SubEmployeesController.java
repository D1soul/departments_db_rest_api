package com.departments_db_rest_api.controllers;

import com.departments_db_rest_api.entities.SubEmployee;
import com.departments_db_rest_api.repository.MainDepartmentRepository;
import com.departments_db_rest_api.repository.SubDepartmentRepository;
import com.departments_db_rest_api.repository.SubEmployeesRepository;
import com.departments_db_rest_api.web_services.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/departments/main_departments")
public class SubEmployeesController {

    private MainDepartmentRepository mainDepartmentRepository;

    private SubDepartmentRepository subDepartmentRepository;

    private SubEmployeesRepository subEmployeesRepository;

    @Autowired
    public SubEmployeesController(MainDepartmentRepository mainDepartmentRepository,
                                  SubDepartmentRepository subDepartmentRepository,
                                  SubEmployeesRepository subEmployeesRepository) {
          this.mainDepartmentRepository = mainDepartmentRepository;
          this.subDepartmentRepository = subDepartmentRepository;
          this.subEmployeesRepository = subEmployeesRepository;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/sub_employees")
    public List<SubEmployee> findAllSubEmpl() {
        return subEmployeesRepository.findAll();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{mainDepId:\\d+}/sub_departments/{subDepId:\\d+}/employees")
    public List<SubEmployee>findSubEmplBySubDepId(@PathVariable Long mainDepId,
                                                     @PathVariable Long subDepId) {
        if (mainDepartmentRepository.existsById(mainDepId)) {
            if(subDepartmentRepository.existsById(subDepId)){
                return subEmployeesRepository.findBySubDepartmentId(subDepId);
            }
            else throw new NotFoundException("Sub-Department with Id: " + subDepId
                                   + " in Main Department with Id: " + mainDepId + " Not Found!");
        }
        else throw new NotFoundException("Main Department with Id: " + mainDepId + " Not Found!");
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{mainDepId:\\d+}/sub_departments/{subDepId:\\d+}/employees/{id:\\d+}")
    public SubEmployee findSubEmplById(@PathVariable Long mainDepId,
                             @PathVariable Long subDepId, @PathVariable Long id) {
        if(mainDepartmentRepository.existsById(mainDepId)) {
            if (subDepartmentRepository.existsById(subDepId)) {
                Optional<SubEmployee> subEmployee = subEmployeesRepository.findById(id);
                if (subEmployee.isPresent()) {
                    return subEmployee.get();
                } else throw new NotFoundException("Employee with Id: " + id
                        + " in Sub-Department with Id: " + subDepId + " Not Found!");
            }
            else throw new NotFoundException("Sub-Department with Id: " + subDepId
                    + " in Main Department with Id: " + mainDepId + " Not Found!");
        }
        else throw new NotFoundException("Main Department with Id: " + mainDepId + " Not Found!");
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/sub_employees")
    public SubEmployee createSubEmployee(@Valid @RequestBody SubEmployee subEmployee) {
        return  subEmployeesRepository.save(subEmployee);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/{mainDepId:\\d+}/sub_departments/{subDepId:\\d+}/employees/{id:\\d+}")
    public SubEmployee updateSubEmployee(@PathVariable Long mainDepId, @PathVariable Long subDepId,
                                @PathVariable Long id, @Valid @RequestBody SubEmployee updateSubEmpl){
        if(mainDepartmentRepository.existsById(mainDepId)) {
            if (subDepartmentRepository.existsById(subDepId)) {
                return subEmployeesRepository.findById(id)
                        .map(subEmpl -> {
                            subEmpl.setFirstName(updateSubEmpl.getFirstName());
                            subEmpl.setMiddleName(updateSubEmpl.getMiddleName());
                            subEmpl.setLastName(updateSubEmpl.getLastName());
                            subEmpl.setBirthDate(updateSubEmpl.getBirthDate());
                            subEmpl.setPassport(updateSubEmpl.getPassport());
                            subEmpl.setSubDepartment(updateSubEmpl.getSubDepartment());
                            return subEmployeesRepository.save(subEmpl);
                        }).get();
            }
            else throw new NotFoundException("Sub-Department with Id: " + subDepId
                                   + " in Main Department with Id: " + mainDepId + " Not Found!");
        }
        else throw new NotFoundException("Main Department with Id: " + mainDepId + " Not Found!");
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value =  "/{mainDepId:\\d+}/sub_departments/{subDepId:\\d+}/employees/{id:\\d+}")
    public void deleteMainEmpl(@PathVariable Long mainDepId,
                               @PathVariable Long subDepId, @PathVariable Long id) {
        if (mainDepartmentRepository.existsById(mainDepId)) {
            if (subDepartmentRepository.existsById(subDepId)) {
                SubEmployee subEmpl = subEmployeesRepository.findById(id).get();
                subEmployeesRepository.delete(subEmpl);
            }
            else throw new NotFoundException("Sub-Department with Id: " + subDepId
                    + " in Main Department with Id: " + mainDepId + " Not Found!");
        }
        else throw new NotFoundException("Main Department with Id: " + mainDepId + " Not Found!");
    }
}