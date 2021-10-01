package com.example.tacos;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringRunner.class)
@WebMvcTest
/*
@WebMvcTest is a special test annotation provided by Spring Boot
that arranges for the test to run in the context of a Spring MVC application.
It arranges for HomeController to be registered in Spring MVC
so that you can throw requests against it.
@WebMvcTest also sets up Spring support for testing Spring MVC. Although it
could be made to start a server, mocking the mechanics of Spring MVC is sufficient for
your purposes. The test class is injected with a MockMvc object for the test to drive the
mockup.
*/
public class HomeControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testHomePage() throws Exception {
        // performs GET /
        // expects HTTP 200
        // expects home view
        // expects "Welcome to..."
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("home"))
                .andExpect(content().string(
                        containsString("Welcome to...")));
    }

}
