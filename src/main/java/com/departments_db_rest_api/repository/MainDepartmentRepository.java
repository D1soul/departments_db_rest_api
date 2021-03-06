package com.departments_db_rest_api.repository;

import com.departments_db_rest_api.entities.MainDepartment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface MainDepartmentRepository extends JpaRepository<MainDepartment, Long> {
    Optional<MainDepartment> findByName(String name);
    void deleteById(Long id);
}
