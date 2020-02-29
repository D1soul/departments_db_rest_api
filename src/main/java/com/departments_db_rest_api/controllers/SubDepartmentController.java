package com.departments_db_rest_api.controllers;

import com.departments_db_rest_api.entities.SubDepartment;
import com.departments_db_rest_api.services.SubDepartmentService;
import com.departments_db_rest_api.web_services.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/departments_app")
public class SubDepartmentController {

    private SubDepartmentService subDepartmentService;

    @Autowired
    public SubDepartmentController(SubDepartmentService subDepartmentService) {
        this.subDepartmentService = subDepartmentService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/sub_departments")
    public List<SubDepartment> findAllSubDep() {
        return subDepartmentService.findAll();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/sub_departments/{id:\\d+}")
    public SubDepartment findSubDepById(@PathVariable Long id) {
        Optional<SubDepartment> subDepartment = subDepartmentService.findById(id);
        if (subDepartment.isPresent()) {
            return subDepartment.get();
        }
        else throw new NotFoundException("Sub-Department with Id: " + id + " Not Found!");
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/sub_departments/name/{name:[\\w\\s]+}")
    public SubDepartment findSubDepByName(@PathVariable String name) {
        Optional<SubDepartment> subDepartment = subDepartmentService.findByName(name);
        if (subDepartment.isPresent()) {
            return subDepartment.get();
        }
        else throw new NotFoundException("Sub-Department with Name: " + name + " Not Found!");
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/sub_departments")
    public SubDepartment createSubDep(@Valid @RequestBody SubDepartment subDepartment) {
        return  subDepartmentService.save(subDepartment);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/sub_departments/{id:\\d+}")
    public SubDepartment updateSubDep(@PathVariable Long id, @Valid @RequestBody SubDepartment updateSubDep){
        return subDepartmentService.findById(id).map(subDep -> {
            subDep.setName(updateSubDep.getName());
            subDep.setMainDepartment(updateSubDep.getMainDepartment());
            return subDepartmentService.save(subDep);
        }).get();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/sub_departments/{id:\\d+}")
    public void deleteSubDep(@PathVariable Long id) {
            subDepartmentService.deleteById(id);
    }
}