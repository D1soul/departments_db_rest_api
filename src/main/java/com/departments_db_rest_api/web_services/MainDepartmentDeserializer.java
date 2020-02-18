package com.departments_db_rest_api.web_services;

import com.departments_db_rest_api.entities.MainDepartment;
import com.departments_db_rest_api.repository.MainDepartmentRepository;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonComponent;
import java.io.IOException;

@JsonComponent
public class MainDepartmentDeserializer extends JsonDeserializer<MainDepartment> {

    private MainDepartmentRepository mainDepartmentRepository;

    public MainDepartmentDeserializer(){
    }

    @Autowired
    public MainDepartmentDeserializer(MainDepartmentRepository mainDepartmentRepository){
        this.mainDepartmentRepository = mainDepartmentRepository;
    }

    @Override
    public MainDepartment deserialize(JsonParser jsonParser, DeserializationContext ctxt) throws IOException, JsonProcessingException {

        String name = jsonParser.getValueAsString();

        MainDepartment mainDepartment = mainDepartmentRepository.findByName(name).get();

        return mainDepartment;
    }
}