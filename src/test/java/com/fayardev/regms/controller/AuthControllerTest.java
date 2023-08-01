package com.fayardev.regms.controller;

import com.fayardev.regms.controllers.AuthController;
import com.fayardev.regms.services.UserService;
import com.fayardev.regms.util.JwtTestUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(AuthController.class)
public class AuthControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    public void testSignUp() throws Exception {

    }

    @Test
    public void testSecureEndpointWithValidToken() throws Exception {
        String username = "fayar";
        String role = "ROLE_USER";
        String secretKey = "token_1";

        String jwtToken = JwtTestUtil.createToken(username, role, secretKey);

        MvcResult result = mockMvc.perform(get("/api/secure")
                        .header("Authorization", "Bearer " + jwtToken))
                .andExpect(status().isOk())
                .andReturn();

        // Assert
        String responseContent = result.getResponse().getContentAsString();
        assertEquals("Hello, secured endpoint!", responseContent);
    }

    @Test
    public void testSecureEndpointWithInvalidToken() throws Exception {
        String invalidToken = "invalid-token";

        MvcResult result = mockMvc.perform(get("/api/secure")
                        .header("Authorization", "Bearer " + invalidToken))
                .andExpect(status().isUnauthorized()) // 401 Unauthorized
                .andReturn();
    }
}
