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
                .get(URL_FIND_MAIN_DEP_BY_NAME, "Department of Architecture")
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
                             +  "\"Name\": \"Department of Education\""
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
                             + "\"Name\": \"Department of Agriculture\""
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
