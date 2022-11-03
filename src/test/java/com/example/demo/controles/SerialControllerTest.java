package com.example.demo.controles;

import com.example.demo.Dto.payload.RefreshTokenRequest;
import com.example.demo.service.AuthService;
import com.example.demo.service.SerialService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static com.example.demo.controles.EndPoints.POST;
import static com.example.demo.controles.EndPoints.SERIAL;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class SerialControllerTest {

    private MockMvc mockMvc;

    @Mock
    private SerialService serialService;

    @InjectMocks
    private SerialController serialController;

    @BeforeEach
    void setUp() {
        this.mockMvc = MockMvcBuilders
                .standaloneSetup(serialController)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .build();
    }

    @Test
    void getAllSerialByPage() throws Exception {

        String uri = "/getFrontPageInfo";

        mockMvc.perform(get(SERIAL + uri)).andExpect(status().isOk());

        verify(serialService,times(1)).findAllBy(any());


    }

    @Test
    void getSerialByUserId() {
    }

    @Test
    void getSerialsById() {
    }
}