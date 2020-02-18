package com.departments_db_rest_api.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "main_departments")
public class MainDepartment implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @JsonProperty("Id")
    @Column(name = "id")
    private Long id;

    @JsonProperty("Name")
    @Column(name = "name")
    private String name;

    @OrderBy("id asc ")
    @JsonProperty("Employees")
    @OneToMany(mappedBy = "mainDepartment", cascade=CascadeType.ALL)
    private Set<MainEmployee> mainEmployees;

    @OrderBy("id asc ")
    @JsonProperty("Sub-Departments")
    @OneToMany(mappedBy = "mainDepartment", cascade=CascadeType.ALL)
    private Set<SubDepartment> subDepartment;

    public String toString(){
        return "\n Id: " + id + "\n Name: " + name;
    }
}

