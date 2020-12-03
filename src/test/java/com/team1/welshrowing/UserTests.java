package com.team1.welshrowing;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.team1.welshrowing.domain.User;
import com.team1.welshrowing.service.UserCreateService;
import com.team1.welshrowing.service.UserReadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserTests {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private UserCreateService userCreateService;

    @Autowired
    private UserReadService userReadService;

    @Test
    public void create_and_find_a_user_by_username() {
        User newUser = new User();
        newUser.setUserName("Ryan");
        newUser.setRoles("ATHLETE");
        newUser.setEmail("ryan@ryan.com");
        newUser.setPassword("pass");
        userCreateService.addUser(newUser);
        Assertions.assertEquals(userReadService.findByUserName("Ryan").get(), newUser);
    }

    @Test
    @WithUserDetails("coach")
    public void create_a_coach_and_check_that_exists() throws Exception {
        mockMvc
            .perform(post("/coach/add-coach")
                    .with(csrf())
                    .param("userName", "coach2")
                    .param("email", "coach2@example.com"))
            .andExpect(
                    status().isOk());

        Assertions.assertEquals("COACH", userReadService.findByUserName("coach2").get().getRoles());
    }

}
