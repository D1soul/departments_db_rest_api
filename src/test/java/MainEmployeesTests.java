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
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = SpringRestApiRunner.class)
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application.yaml")
public class MainEmployeesTests {

    @Autowired
    private MockMvc mockMvc;

    private static final String URL_FIND_MAIN_EMPL_BY_ID = "http://localhost:8080/departments/main_departments/{mainDepId}/employees/{id}";
    private static final String URL_FIND_MAIN_EMPLS_BY_MD_ID = "http://localhost:8080/departments/main_departments/{mainDepId}/employees";
    private static final String URL_FIND_ALL_MAIN_EMPL = "http://localhost:8080/departments/main_departments/main_employees";
    private static final String URL_UPDATE_MAIN_EMPL = "http://localhost:8080/departments/main_departments/{mainDepId}/employees/{id}";
    private static final String URL_CREATE_MAIN_EMPL = "http://localhost:8080/departments/main_departments/main_employees";
    private static final String URL_DELETE_MAIN_EMPL = "http://localhost:8080/departments/main_departments/{mainDepId}/employees/{id}";


    @Test
    public void findAllMainEmplTest() throws Exception
    {        mockMvc.perform(MockMvcRequestBuilders
                .get(URL_FIND_ALL_MAIN_EMPL)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void findMainEmplsByMDIdTest() throws Exception
    {        mockMvc.perform(MockMvcRequestBuilders
            .get(URL_FIND_MAIN_EMPLS_BY_MD_ID, 1)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andDo(print());
    }

    @Test
    public void findEmplByIdTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get(URL_FIND_MAIN_EMPL_BY_ID, 1, 2)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
    }

    @Test
    public  void createMainEmplTest() throws Exception{
        String createEmpl = "{"
                          + "\"Last Name\": \"Kulichin\","
                          + "\"First Name\": \"Ignat\","
                          + "\"Middle Name\": \"Asanovich\","
                          + "\"Birth Date\": \"1959-01-03\","
                          + "\"Passport\": \"52 13 653214\","
                          + "\"Main Department\": \"Department of Health\""
                          + "}";

        mockMvc.perform(MockMvcRequestBuilders
                .post(URL_CREATE_MAIN_EMPL)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(createEmpl))
                .andExpect(status().isCreated())
                .andDo(print());
    }

    @Test
    public  void updateMainEmplTest() throws Exception{
        String updateEmpl = "{"
                          + "\"Last Name\": \"Kuzmin\","
                          + "\"First Name\": \"Alexey\","
                          + "\"Middle Name\": \"Alexandrovich\","
                          + "\"Birth Date\": \"1949-05-11\","
                          + "\"Passport\": \"12 43 532214\","
                          + "\"Main Department\": \"Department of Health\""
                          + "}";

        mockMvc.perform(MockMvcRequestBuilders
                .put(URL_UPDATE_MAIN_EMPL, 1, 2)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(updateEmpl))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void deleteMainEmplTest() throws Exception
    {
        mockMvc.perform( MockMvcRequestBuilders
                .delete(URL_DELETE_MAIN_EMPL, 1, 2))
                .andExpect(status().isNoContent());

        mockMvc.perform(MockMvcRequestBuilders
                .get(URL_FIND_ALL_MAIN_EMPL)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print());
    }
}
