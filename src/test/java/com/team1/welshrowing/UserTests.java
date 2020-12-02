package com.team1.welshrowing;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.team1.welshrowing.domain.User;
import com.team1.welshrowing.service.UserCreateService;
import com.team1.welshrowing.service.UserReadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@AutoConfigureMockMvc
public class UserTests {

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

}
