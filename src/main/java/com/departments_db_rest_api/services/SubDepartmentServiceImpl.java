package com.departments_db_rest_api.services;

import com.departments_db_rest_api.entities.SubDepartment;
import com.departments_db_rest_api.repository.SubDepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class SubDepartmentServiceImpl implements SubDepartmentService {

    private SubDepartmentRepository subDepartmentRepository;

    @Autowired
    public SubDepartmentServiceImpl(SubDepartmentRepository subDepartmentRepository) {
        this.subDepartmentRepository = subDepartmentRepository;
    }

    @Override
    public List<SubDepartment> findAll() {
        return subDepartmentRepository.findAll();
    }

    @Override
    public Optional<SubDepartment> findById(Long id) {
        return subDepartmentRepository.findById(id);
    }

    @Override
    public Optional<SubDepartment> findByName(String name) {
        return subDepartmentRepository.findByName(name);
    }

    @Override
    public SubDepartment save(SubDepartment subDepartment) {
        return subDepartmentRepository.save(subDepartment);
    }

    @Override
    public void deleteById(Long id) {
        subDepartmentRepository.deleteById(id);
    }
}
