package com.example.demo.controles;

import com.example.demo.service.AuthService;
import com.example.demo.service.RefreshTokenService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class AuthContTest {

    private MockMvc mockMvc;

    @Mock
    private AuthService authService;

    @Mock
    private RefreshTokenService refreshTokenService;

    @InjectMocks
    private AuthCont AuthCont;

    @BeforeEach
    void setUp() {
        this.mockMvc = MockMvcBuilders
                .standaloneSetup(AuthCont)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .build();
    }

    @Test
    void authUser() {
    }

    @Test
    void signup() throws Exception {


    }

    @Test
    void refreshToken() {
    }

    @Test
    void logout() {
    }


}