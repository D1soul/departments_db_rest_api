package com.departments_db_rest_api.entities;

import com.departments_db_rest_api.web_services.SubDepartmentDeserializer;
import com.departments_db_rest_api.web_services.SubDepartmentSerializer;
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "sub_employees")
public class SubEmployee implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @JsonProperty("Id")
    @Column(name = "id")
    private Long id;

    @JsonProperty("Last Name")
    @Column(name = "last_name")
    private String lastName;

    @JsonProperty("First Name")
    @Column(name = "first_name")
    private String firstName;

    @JsonProperty("Middle Name")
    @Column(name = "middle_name")
    private String middleName;

    @JsonProperty("Birth Date")
    @Temporal(TemporalType.DATE)
    @Column(name = "birth_date")
    private Date birthDate;

    @JsonProperty("Passport")
    @Column(name = "passport")
    private String passport;

    @JsonSerialize(using = SubDepartmentSerializer.class)
    @JsonDeserialize(using = SubDepartmentDeserializer.class)
    @JsonProperty("Sub-Department")
    @ManyToOne
    @JoinColumn(name = "sd_id", nullable = false)
    private SubDepartment subDepartment;

    public String toString(){
        return "Id: " + id + "\n" + "First Name: " + firstName + "\n"
                + "Middle Name: " + middleName + "\n" + "Last Name: " + lastName + "\n"
                + "Birth Date: " + birthDate + "\n" + "Passport: " + passport + "\n"
                + "SD Id" + subDepartment.getId() + "\n";
    }
}
