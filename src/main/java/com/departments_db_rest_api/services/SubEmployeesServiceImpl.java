package com.departments_db_rest_api.services;

import com.departments_db_rest_api.entities.SubEmployee;
import com.departments_db_rest_api.repository.SubEmployeesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class SubEmployeesServiceImpl implements SubEmployeesService {

    private SubEmployeesRepository subEmployeesRepository;

    @Autowired
    public SubEmployeesServiceImpl(SubEmployeesRepository subEmployeesRepository) {
        this.subEmployeesRepository = subEmployeesRepository;
    }

    @Override
    public List<SubEmployee> findAll() {
        return subEmployeesRepository.findAll();
    }

    @Override
    public Optional<SubEmployee> findById(Long id) {
        return subEmployeesRepository.findById(id);
    }

    @Override
    public SubEmployee save(SubEmployee subEmployee) {
        return subEmployeesRepository.save(subEmployee);
    }

    @Override
    public void deleteById(Long id) {
        subEmployeesRepository.deleteById(id);
    }
}
