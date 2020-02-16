package br.com.concrete.teste.controllers;

import br.com.concrete.teste.controller.RegisterController;
import br.com.concrete.teste.model.Phone;
import br.com.concrete.teste.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.Matchers.*;

@WebMvcTest(RegisterController.class)
public class RegisterControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private RegisterController registerController;

    @Test
    public void addUserTest() throws Exception {
        User user = new User();
        user.setName("Teste");
        user.setEmail("email@gmail.com");
        user.setPassword("123");
        List<Phone> phoneList = new ArrayList<>();
        Phone phone = new Phone();
        phone.setDdd("82");
        phone.setNumber("988888888");
        phoneList.add(phone);
        user.setPhones(phoneList);

        ResultActions result = mvc.perform(post("/register/user").content(asJsonString(user)).characterEncoding("UTF-8").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
