package br.com.concrete.teste.controllers;

import br.com.concrete.teste.controller.ProfileController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProfileController.class)
public class ProfileControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private ProfileController profileController;

    @Test
    public void getErrorPermission() throws Exception {
        mvc.perform(get("/profile")).andExpect(status().is4xxClientError());
    }
}
