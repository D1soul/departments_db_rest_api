package com.departments_db_rest_api.entities;

import com.departments_db_rest_api.web_services.MainDepartmentDeserializer;
import com.departments_db_rest_api.web_services.MainDepartmentSerializer;
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "sub_departments")
public class SubDepartment implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @JsonProperty("Id")
    @Column(name = "id")
    private Long id;

    @JsonProperty("Name")
    @Column(name = "name")
    private String name;


    @JsonSerialize(using = MainDepartmentSerializer.class)
    @JsonDeserialize(using = MainDepartmentDeserializer.class)
    @JsonProperty("Main Department")
    @ManyToOne
    @JoinColumn(name = "md_id", nullable = false)
    private MainDepartment mainDepartment;


    @JsonProperty("Employees")
    @OneToMany(mappedBy = "subDepartment", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval=true)
    private Set<SubEmployee> subEmployees;

    public String toString(){
        return "Id: " + id + "\n" + "Name: " + name + "\n"
             + "Main Dep id: " + mainDepartment.getId() + "\n";
    }
}
