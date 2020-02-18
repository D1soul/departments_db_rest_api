package com.departments_db_rest_api.repository;

import com.departments_db_rest_api.entities.SubDepartment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface SubDepartmentRepository extends JpaRepository<SubDepartment, Long> {

    Optional<SubDepartment> findByName(@Param("name") String name);

    List<SubDepartment> findByMainDepartmentId (Long mainDepartmentId);

}
