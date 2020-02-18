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
public class SubEmployeesTests {

    @Autowired
    private MockMvc mockMvc;

    private static final String URL_FIND_ALL_SUB_EMPL = "http://localhost:8080/departments/main_departments/sub_employees";
    private static final String URL_FIND_SUB_EMPLS_BY_SD_ID =
                "http://localhost:8080/departments/main_departments/{mainDepId}/sub_departments/{subDepId}";
    private static final String URL_FIND_SUB_EMPL_BY_ID =
                "http://localhost:8080/departments/main_departments/{mainDepId}/sub_departments/{subDepId}/employees/{id}";
    private static final String URL_UPDATE_SUB_EMPL =
                "http://localhost:8080/departments/main_departments/{mainDepId}/sub_departments/{subDepId}/employees/{id}";
    private static final String URL_CREATE_SUB_EMPL = "http://localhost:8080/departments/main_departments/sub_employees";
    private static final String URL_DELETE_SUB_EMPL =
                "http://localhost:8080/departments/main_departments/{mainDepId}/sub_departments/{subDepId}/employees/{id}";

    @Test
    public void findAllSubEmplsTest() throws Exception
    {        mockMvc.perform(MockMvcRequestBuilders
                .get(URL_FIND_ALL_SUB_EMPL)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void findSubEmplsBySDIdTest() throws Exception
    {        mockMvc.perform(MockMvcRequestBuilders
            .get(URL_FIND_SUB_EMPLS_BY_SD_ID, 1, 2)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andDo(print());
    }

    @Test
    public void findSubEmplByIdTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get(URL_FIND_SUB_EMPL_BY_ID, 1, 3, 3)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public  void createSubEmplTest() throws Exception{
        String createEmpl = "{"
                          + "\"Last Name\": \"Bercut\","
                          + "\"First Name\": \"Yaroslav\","
                          + "\"Middle Name\": \"Yurievich\","
                          + "\"Birth Date\": \"1975-12-12\","
                          + "\"Passport\": \"24 12 521214\","
                          + "\"Sub-Department\": \"Department_of_General_Education\""
                          + "}";

        mockMvc.perform(MockMvcRequestBuilders
                .post(URL_CREATE_SUB_EMPL)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(createEmpl))
                .andExpect(status().isCreated())
                .andDo(print());
    }

    @Test
    public  void updateSubEmplTest() throws Exception{
        String updateEmpl = "{"
                          + "\"Last Name\": \"Dadashin\","
                          + "\"First Name\": \"Victor\","
                          + "\"Middle Name\": \"Yurievich\","
                          + "\"Birth Date\": \"1981-07-15\","
                          + "\"Passport\": \"19 25  582134\","
                          + "\"Sub-Department\": \"Department_of_Preschool_Education\""
                          + "}";

        mockMvc.perform(MockMvcRequestBuilders
                .put(URL_UPDATE_SUB_EMPL, 1, 2, 2)
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
                .delete(URL_DELETE_SUB_EMPL, 1, 1, 1))
                .andExpect(status().isNoContent());

        mockMvc.perform(MockMvcRequestBuilders
                .get(URL_FIND_ALL_SUB_EMPL)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print());
    }
}
