package com.departments_db_rest_api.services;

import com.departments_db_rest_api.entities.SubEmployee;
import java.util.List;
import java.util.Optional;

public interface SubEmployeesService {
    List<SubEmployee> findAll();
    Optional<SubEmployee> findById(Long id);
    SubEmployee save(SubEmployee subEmployee);
    void deleteById(Long id);

}
