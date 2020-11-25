package com.team1.welshrowing;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureWebMvc
@WebMvcTest
public class WebSecurityTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void homepageShouldNotRequireAuthentication() throws Exception {
        this.mockMvc
                .perform(get("/"))
                .andExpect(status().isOk());
    }

    @Test
    public void resourcesShouldLoadWithoutAuthentication() throws Exception {
        this.mockMvc
                .perform(get("/css/main.css"))
                .andExpect(status().isOk());
        this.mockMvc
                .perform(get("/assets/logo.png"))
                .andExpect(status().isOk());
    }

}
