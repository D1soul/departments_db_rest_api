package com.departments_db_rest_api.services;

import com.departments_db_rest_api.entities.MainEmployee;
import com.departments_db_rest_api.repository.MainEmployeesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class MainEmployeesServiceImpl implements MainEmployeesService{

    private MainEmployeesRepository mainEmployeesRepository;

    @Autowired
    public MainEmployeesServiceImpl(MainEmployeesRepository mainEmployeesRepository){
        this.mainEmployeesRepository = mainEmployeesRepository;
    }

    @Override
    public List<MainEmployee> findAll() {
        return mainEmployeesRepository.findAll();
    }

    @Override
    public Optional<MainEmployee> findById(Long id) {
        return mainEmployeesRepository.findById(id);
    }

    @Override
    public MainEmployee save(MainEmployee mainEmployee) {
        return mainEmployeesRepository.save(mainEmployee);
    }

    @Override
    public void deleteById(Long id) {
        mainEmployeesRepository.deleteById(id);
    }
}
