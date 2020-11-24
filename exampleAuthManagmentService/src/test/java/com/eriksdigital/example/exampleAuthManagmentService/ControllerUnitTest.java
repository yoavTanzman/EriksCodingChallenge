package com.eriksdigital.example.exampleAuthManagmentService;

import com.eriksdigital.example.exampleAuthManagmentService.controller.LogInController;
import com.eriksdigital.example.exampleAuthManagmentService.db.dto.UserCredentialsDTO;
import com.eriksdigital.example.exampleAuthManagmentService.db.entity.UserCredentials;
import com.eriksdigital.example.exampleAuthManagmentService.db.repository.UserCredentialsRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

//@ExtendWith(SpringExtension.class)
//@WebMvcTest(LogInController.class)
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ControllerUnitTest {

    @Autowired
    private MockMvc mvc;

    @InjectMocks
    LogInController logInController;

    @MockBean
    UserCredentialsRepository userCredentialsRepository;

    @Autowired
    ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        mvc = MockMvcBuilders.standaloneSetup(logInController).build();
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void shouldreturn200() throws Exception {
        String actualResult = mvc
                .perform(MockMvcRequestBuilders.get("/authentication/hello"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString();

    }

    @Test
    public void shouldAddUser() throws Exception {
        UserCredentialsDTO userCredentialsDTO = new UserCredentialsDTO("test@Eriks.com","12!@Qwaszx",
                "test","test");
        String userCredentialsDTOJson = objectMapper.writeValueAsString(userCredentialsDTO);
        System.out.println(userCredentialsDTOJson);
        String actualResult =
                mvc.perform(MockMvcRequestBuilders.post("/authentication/signup")
                        .contentType("application/json")
                        .content(userCredentialsDTOJson)).andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString();
        boolean isOk = actualResult.toLowerCase().contains("user added");
        Assertions.assertTrue(isOk);

    }

    @Test
    public void shouldLogin() throws Exception {
        UserCredentialsDTO userCredentialsDTO= new UserCredentialsDTO("test@eriks.com","123!qwaszx",
                "test","test");
        String userCredentialsDTOJson = objectMapper.writeValueAsString(userCredentialsDTO);
        String actualResult =
                mvc.perform(MockMvcRequestBuilders.get("/authentication/initDb"))
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andReturn().getResponse().getContentAsString();

                actualResult =
                        mvc.perform(MockMvcRequestBuilders.post("/authentication/login")
                                .contentType("application/json")
                                .content(userCredentialsDTOJson))
                                .andReturn().getResponse().getContentAsString();

                boolean isFalse = actualResult.toLowerCase().contains("user not found");
                Assertions.assertTrue(isFalse);


    }


}
