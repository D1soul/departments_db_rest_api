package com.departments_db_rest_api.repository;

import com.departments_db_rest_api.entities.MainEmployee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MainEmployeesRepository extends JpaRepository<MainEmployee, Long> {

    List<MainEmployee> findByMainDepartmentId (Long mainDepartmentId);
}
