package com.departments_db_rest_api.web_services;

import com.departments_db_rest_api.entities.SubDepartment;
import com.departments_db_rest_api.repository.SubDepartmentRepository;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonComponent;
import java.io.IOException;

@JsonComponent
public class SubDepartmentDeserializer extends JsonDeserializer<SubDepartment> {

    public SubDepartmentDeserializer(){
    }

    private SubDepartmentRepository subDepartmentRepository;

    @Autowired
    public SubDepartmentDeserializer(SubDepartmentRepository subDepartmentRepository){
        this.subDepartmentRepository = subDepartmentRepository;
    }

    @Override
    public SubDepartment deserialize(JsonParser jsonParser, DeserializationContext ctxt) throws IOException, JsonProcessingException {

        String name = jsonParser.getValueAsString();

        SubDepartment subDepartment = subDepartmentRepository.findByName(name).get();

        return subDepartment;
    }
}