import com.departments_db_rest_api.SpringRestApiRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = SpringRestApiRunner.class)
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application.yaml")
public class CreateAllTogether {

    @Autowired
    private MockMvc mockMvc;
    private static final String URL_FIND_ALL_MAIN_DEPARTMENTS = "http://localhost:8080/departments/main_departments";
    private static final String URL_CREATE_MAIN_DEP = "http://localhost:8080/departments/main_departments/";
    private static final String URL_CREATE_MAIN_EMPL = "http://localhost:8080/departments/main_departments/main_employees";
    private static final String URL_CREATE_SUB_DEP = "http://localhost:8080/departments/main_departments/sub_departments";
    private static final String URL_CREATE_SUB_EMPL = "http://localhost:8080/departments/main_departments/sub_employees";


    @Test
    public  void createMainDepTest() throws Exception{
        String createMainDep = "{"
                + "\"Name\": \"Department_of_Culture\""
                + "}";

        mockMvc.perform(MockMvcRequestBuilders
                .post(URL_CREATE_MAIN_DEP)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(createMainDep));

        String createMainEmpl = "{"
                          + "\"Last Name\": \"Kulichin\","
                          + "\"First Name\": \"Ignat\","
                          + "\"Middle Name\": \"Asanovich\","
                          + "\"Birth Date\": \"1959-01-03\","
                          + "\"Passport\": \"52 13 653214\","
                          + "\"Main Department\": \"Department_of_Culture\""
                          + "}";

        mockMvc.perform(MockMvcRequestBuilders
                .post(URL_CREATE_MAIN_EMPL)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(createMainEmpl));

        String createSubDep = "{"
                + "\"Name\": \"Department of Music\","
                + "\"Main Department\": \"Department_of_Culture\""
                + "}";

        mockMvc.perform(MockMvcRequestBuilders
                .post(URL_CREATE_SUB_DEP)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(createSubDep));

        String createSubEmpl = "{"
                + "\"Last Name\": \"Grachova\","
                + "\"First Name\": \"Natalia\","
                + "\"Middle Name\": \"Yurievna\","
                + "\"Birth Date\": \"1978-09-19\","
                + "\"Passport\": \"64 14 723214\","
                + "\"Sub-Department\": \"Department of Music\""
                + "}";

        mockMvc.perform(MockMvcRequestBuilders
                .post(URL_CREATE_SUB_EMPL)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(createSubEmpl));

        mockMvc.perform(MockMvcRequestBuilders
                .get(URL_FIND_ALL_MAIN_DEPARTMENTS)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }
}
