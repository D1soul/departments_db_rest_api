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
public class SubDepartmentTests {

    @Autowired
    private MockMvc mockMvc;

    private static final String URL_FIND_ALL_SUB_DEP = "http://localhost:8080/departments/main_departments/sub_departments";
    private static final String URL_FIND_SUB_DEP_BY_ID = "http://localhost:8080/departments/main_departments/{mainDepId}/sub_departments/{id}";
    private static final String URL_FIND_SUB_DEPS_BY_MD_ID = "http://localhost:8080/departments/main_departments/{mainDepId}/sub_departments";
    private static final String URL_FIND_SUB_DEP_BY_NAME = "http://localhost:8080/departments/main_departments/{mainDepId}/sub_departments/name/{name}";
    private static final String URL_UPDATE_SUB_DEP = "http://localhost:8080/departments/main_departments/{mainDepId}/sub_departments/{id}";
    private static final String URL_CREATE_SUB_DEP = "http://localhost:8080/departments/main_departments/sub_departments";
    private static final String URL_DELETE_SUB_DEP = "http://localhost:8080/departments/main_departments/{mainDepId}/sub_departments/{id}";


    @Test
    public void findAllSubDepTest() throws Exception
    {        mockMvc.perform(MockMvcRequestBuilders
                .get(URL_FIND_ALL_SUB_DEP)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void findSubDepsByMDIdTest() throws Exception
    {        mockMvc.perform(MockMvcRequestBuilders
            .get(URL_FIND_SUB_DEPS_BY_MD_ID, 1)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andDo(print());
    }

    @Test
    public void findSubDepByIdTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get(URL_FIND_SUB_DEP_BY_ID, 1, 2)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void findSubDepByName() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders
                .get(URL_FIND_SUB_DEP_BY_NAME, 1, "Department of Preschool Education")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public  void createSubDepTest() throws Exception{
        String createSubDep = "{"
                            + "\"Name\": \"Department of architectural appearance and urban environment\","
                            + "\"Main Department\": \"Department of Architecture\""
                            + "}";

        mockMvc.perform(MockMvcRequestBuilders
                .post(URL_CREATE_SUB_DEP)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(createSubDep))
                .andExpect(status().isCreated())
                .andDo(print());
    }

    @Test
    public  void updateSubDepTest() throws Exception{
        String updateSubDep = "{"
                            + "\"Name\": \"Department of General Education and Training\","
                            + "\"Main Department\": \"Department of Education and Science\""
                            + "}";

        mockMvc.perform(MockMvcRequestBuilders
                .put(URL_UPDATE_SUB_DEP, 1, 2)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(updateSubDep))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void deleteMainEmplTest() throws Exception
    {
        mockMvc.perform( MockMvcRequestBuilders
                .delete(URL_DELETE_SUB_DEP, 1, 2))
                .andExpect(status().isNoContent());

        mockMvc.perform(MockMvcRequestBuilders
                .get(URL_FIND_ALL_SUB_DEP)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print());
    }
}
