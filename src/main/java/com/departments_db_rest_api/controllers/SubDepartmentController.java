package com.departments_db_rest_api.controllers;

import com.departments_db_rest_api.entities.SubDepartment;
import com.departments_db_rest_api.repository.MainDepartmentRepository;
import com.departments_db_rest_api.repository.SubDepartmentRepository;
import com.departments_db_rest_api.web_services.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/departments/main_departments")
public class SubDepartmentController {

    private SubDepartmentRepository subDepartmentRepository;

    private MainDepartmentRepository mainDepartmentRepository;

    @Autowired
    public SubDepartmentController(MainDepartmentRepository mainDepartmentRepository,
                                   SubDepartmentRepository subDepartmentRepository) {
          this.mainDepartmentRepository = mainDepartmentRepository;
          this.subDepartmentRepository = subDepartmentRepository;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/sub_departments")
    public List<SubDepartment> findAllSubDep() {
        return subDepartmentRepository.findAll();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{mainDepId:\\d+}/sub_departments")
    public List<SubDepartment>findSubDepByMainDepId(@PathVariable Long mainDepId) {
        if (mainDepartmentRepository.existsById(mainDepId)) {
            return subDepartmentRepository.findByMainDepartmentId(mainDepId);
        }  else throw new NotFoundException("Main Department with Id: " + mainDepId + " Not Found!");
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{mainDepId:\\d+}/sub_departments/{id:\\d+}")
    public SubDepartment findSubDepById(@PathVariable Long mainDepId,
                                        @PathVariable Long id) {
        if(mainDepartmentRepository.existsById(mainDepId)) {
            Optional<SubDepartment> subDepartment = subDepartmentRepository.findById(id);
            if (subDepartment.isPresent()) {
                return subDepartment.get();
            }
            else throw new NotFoundException("Sub-Department with Id: " + id
                    + " in Main Department with Id: " + mainDepId + " Not Found!");
        }
        else throw new NotFoundException("Main Department with Id: " + mainDepId + " Not Found!");
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{mainDepId:\\d+}/sub_departments/name/{name:[\\w\\s]+}")
    public SubDepartment findSubDepByName(@PathVariable Long mainDepId,
                                          @PathVariable String name) {
        if(mainDepartmentRepository.existsById(mainDepId)) {
            Optional<SubDepartment> subDepartment = subDepartmentRepository.findByName(name);
            if (subDepartment.isPresent()) {
                return subDepartment.get();
            }
            else throw new NotFoundException("Sub-Department with Name: " + name
                    + " in Main Department with Id: " + mainDepId + " Not Found!");
        }
        else throw new NotFoundException("Main Department with Id: " + mainDepId + " Not Found!");
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/sub_departments")
    public SubDepartment createSubDep(@Valid @RequestBody SubDepartment subDepartment) {
        return  subDepartmentRepository.save(subDepartment);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/{mainDepId:\\d+}/sub_departments/{id:\\d+}")
    public SubDepartment updateSubDep(@PathVariable Long mainDepId,
                   @PathVariable Long id, @Valid @RequestBody SubDepartment updateSubDep){
        if(mainDepartmentRepository.existsById(mainDepId)) {
            return subDepartmentRepository.findById(id)
                    .map(subDep -> {
                        subDep.setName(updateSubDep.getName());
                        subDep.setMainDepartment(updateSubDep.getMainDepartment());
                        return subDepartmentRepository.save(subDep);
                    }).get();
        }
        else throw new NotFoundException("Main Department with Id: " + mainDepId + " Not Found!");
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/{mainDepId:\\d+}/sub_departments/{id:\\d+}")
    public void deleteSubDep(@PathVariable Long mainDepId, @PathVariable Long id) {
        if(mainDepartmentRepository.existsById(mainDepId)) {
            SubDepartment subDepartment =  subDepartmentRepository.findById(id).get();
                subDepartmentRepository.delete(subDepartment);
        }
        else throw new NotFoundException("Main Department with Id: " + mainDepId + " Not Found!");
    }
}