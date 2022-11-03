package com.example.demo.Dto.payload;

import lombok.Data;

@Data
public class AuthenticationResponse {
    private String accessToken;
    private String tokenType = "Bearer";

}
