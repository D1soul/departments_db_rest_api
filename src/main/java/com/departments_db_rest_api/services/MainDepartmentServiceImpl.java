package com.departments_db_rest_api.services;

import com.departments_db_rest_api.entities.MainDepartment;
import com.departments_db_rest_api.repository.MainDepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class MainDepartmentServiceImpl implements MainDepartmentService{

    private MainDepartmentRepository mainDepartmentRepository;

    @Autowired
    public MainDepartmentServiceImpl(MainDepartmentRepository mainDepartmentRepository) {
        this.mainDepartmentRepository = mainDepartmentRepository;
    }

    @Override
    public List<MainDepartment> findAll() {
        return mainDepartmentRepository.findAll();
    }

    @Override
    public Optional<MainDepartment> findById(Long id) {
        return mainDepartmentRepository.findById(id);
    }

    @Override
    public Optional<MainDepartment> findByName(String name) {
        return mainDepartmentRepository.findByName(name);
    }

    @Override
    public MainDepartment save(MainDepartment mainDepartment) {
        return mainDepartmentRepository.save(mainDepartment);
    }

    @Override
    public void deleteById(Long id) {
        mainDepartmentRepository.deleteById(id);
    }
}
