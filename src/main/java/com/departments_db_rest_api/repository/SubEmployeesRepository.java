package com.departments_db_rest_api.repository;

import com.departments_db_rest_api.entities.SubEmployee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

//@Service("SubEmplRep")
@Repository//("SubEmplRep")
public interface SubEmployeesRepository extends JpaRepository<SubEmployee, Long> {
    List<SubEmployee> findBySubDepartmentId(Long subDepId);

}
