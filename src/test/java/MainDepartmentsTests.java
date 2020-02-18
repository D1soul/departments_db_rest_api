import com.departments_db_rest_api.SpringRestApiRunner;
import com.departments_db_rest_api.controllers.MainDepartmentsController;
import com.departments_db_rest_api.controllers.MainEmployeesController;
import com.departments_db_rest_api.entities.MainDepartment;
import com.departments_db_rest_api.entities.MainEmployee;
import com.departments_db_rest_api.repository.MainDepartmentRepository;
import com.departments_db_rest_api.repository.MainEmployeesRepository;
import com.departments_db_rest_api.repository.SubDepartmentRepository;
import com.departments_db_rest_api.repository.SubEmployeesRepository;
import com.departments_db_rest_api.web_services.NotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.Date;
import java.util.GregorianCalendar;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = SpringRestApiRunner.class)
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application.yaml")
public class MainDepartmentsTests {

    @Autowired
    private MockMvc mockMvc;

    private static final String URL_FIND_ALL_MAIN_DEPARTMENTS = "http://localhost:8080/departments/main_departments";
    private static final String URL_FIND_MAIN_DEP_BY_ID = "http://localhost:8080/departments/main_departments/{id}";
    private static final String URL_FIND_MAIN_DEP_BY_NAME = "http://localhost:8080/departments/main_departments/name/{name}";
    private static final String URL_FIND_MAIN_DEP_BY_EMPL_FIRST_NAME =
                          "http://localhost:8080/departments/main_departments/findByEmplFirstName/{firstName}";
    private static final String URL_UPDATE_MAIN_DEP = "http://localhost:8080/departments/main_departments/{id}";
    private static final String URL_CREATE_MAIN_DEP = "http://localhost:8080/departments/main_departments/";
    private static final String URL_DELETE_MAIN_DEP = "http://localhost:8080/departments/main_departments/{id}";


    @Test
    public void getAllMainDepTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get(URL_FIND_ALL_MAIN_DEPARTMENTS)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void findMainDepByIdTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get(URL_FIND_MAIN_DEP_BY_ID, 1)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void findMaiDepByName() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders
                .get(URL_FIND_MAIN_DEP_BY_NAME, "Department_of_Education_and_Science")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void findMaiDepByEmplFirstName() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders
                .get(URL_FIND_MAIN_DEP_BY_EMPL_FIRST_NAME, "Valeria")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public  void updateMainDepTest() throws Exception{
        String updateMainDep = "{"
                             +  "\"Name\": \"Department_of_Education\""
                             +  "}";

        mockMvc.perform(MockMvcRequestBuilders
                .put(URL_UPDATE_MAIN_DEP, 1)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(updateMainDep))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public  void createMainDepTest() throws Exception{
        String createMainDep = "{"
                             + "\"Name\": \"Department_of_Agriculture\""
                             + "}";

        mockMvc.perform(MockMvcRequestBuilders
                .post(URL_CREATE_MAIN_DEP)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(createMainDep))
                .andExpect(status().isCreated())
                .andDo(print());
    }

    @Test
    public void deleteMainDepTest() throws Exception
    {
        mockMvc.perform( MockMvcRequestBuilders
                .delete(URL_DELETE_MAIN_DEP, 2) )
                .andExpect(status().isNoContent());

        mockMvc.perform(MockMvcRequestBuilders
                .get(URL_FIND_ALL_MAIN_DEPARTMENTS)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print());
    }

}