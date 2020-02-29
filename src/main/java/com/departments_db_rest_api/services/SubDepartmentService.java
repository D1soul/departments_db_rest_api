package com.departments_db_rest_api.services;

import com.departments_db_rest_api.entities.SubDepartment;
import java.util.List;
import java.util.Optional;

public interface SubDepartmentService {
    List<SubDepartment> findAll();
    Optional<SubDepartment> findById(Long id);
    Optional<SubDepartment> findByName(String name);
    SubDepartment save(SubDepartment subDepartment);
    void deleteById(Long id);

}
