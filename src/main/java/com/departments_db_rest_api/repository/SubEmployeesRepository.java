package com.departments_db_rest_api.repository;

import com.departments_db_rest_api.entities.SubEmployee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SubEmployeesRepository extends JpaRepository<SubEmployee, Long> {

    List<SubEmployee> findBySubDepartmentId(Long subDepartmentId);

    void deleteById(Long id);
}
