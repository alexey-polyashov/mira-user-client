package mira.users.ms;

import com.fasterxml.jackson.databind.ObjectMapper;
import mira.users.ms.dto.JwtRequestDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("dev")
public class SecurityTest {

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper;
    private JwtRequestDTO jwtRequestDTO;

    @BeforeEach
    public void init(){
        this.jwtRequestDTO = new JwtRequestDTO();
        this.jwtRequestDTO.setPassword("foo");
        this.jwtRequestDTO.setUsername("foo");

        this.objectMapper = new ObjectMapper();
    }

    @Test
    public void securityAccessAllowedTest() throws Exception {

        Assertions.assertEquals(mockMvc.perform(post("/api/v1/auth")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(jwtRequestDTO)))
                .andDo(print())
                .andReturn()
                .getResponse()
                .getStatus(), 403);

    }

    @Test
    public void securityAccessDeniedTest() throws Exception {
        mockMvc.perform(get("/api/v1/users"))
                .andDo(print())
                .andExpect(status().isUnauthorized());
   }

    @Test
    @WithMockUser(username="user", authorities = "USER")
    public void checkUserAccess() throws Exception {
        Assertions.assertNotEquals(200, mockMvc.perform(get("/api/v1/users"))
                .andDo(print())
                .andReturn()
                .getResponse()
                .getStatus());
    }

}
