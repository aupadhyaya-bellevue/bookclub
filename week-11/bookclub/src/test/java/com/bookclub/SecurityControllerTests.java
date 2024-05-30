package com.bookclub;

import com.bookclub.security.SecurityController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SecurityControllerTests {

    @LocalServerPort
    private int port;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private SecurityController securityController;

    @Test
    public void testSecurityControllerLoad() {
        assertThat(securityController).isNotNull();
    }

    @Test
    public void loginFormShouldOpenOnApplicationStart() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:" + port))
                .andExpect(status().is(302))
                .andExpect(redirectedUrl("http://localhost:" + port + "/login"));
    }

    @Test
    public void homeScreenShouldOpenForLoggedInUser() throws Exception {
        mockMvc.perform(formLogin()
                        .user("user").password("password"))
                .andExpect(redirectedUrl("/"));
    }
}
