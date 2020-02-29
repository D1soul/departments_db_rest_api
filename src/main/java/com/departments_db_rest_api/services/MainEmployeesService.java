package com.departments_db_rest_api.services;

import com.departments_db_rest_api.entities.MainEmployee;
import java.util.List;
import java.util.Optional;

public interface MainEmployeesService {
    List<MainEmployee> findAll();
    Optional<MainEmployee> findById(Long id);
    MainEmployee save(MainEmployee mainEmployee);
    void deleteById(Long id);
}
