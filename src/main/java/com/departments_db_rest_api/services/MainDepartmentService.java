package com.departments_db_rest_api.services;

import com.departments_db_rest_api.entities.MainDepartment;
import java.util.List;
import java.util.Optional;

public interface MainDepartmentService {
    List<MainDepartment> findAll();
    Optional<MainDepartment> findById(Long id);
    Optional<MainDepartment> findByName(String name);
    MainDepartment save(MainDepartment mainDepartment);
    void deleteById(Long id);
}
