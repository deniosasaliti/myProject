package com.example.demo.service;

import com.example.demo.Dto.payload.AuthResponseDto;
import com.example.demo.Dto.payload.LoginRequestDto;
import com.example.demo.Dto.payload.RefreshTokenRequest;
import com.example.demo.Dto.payload.SignUpRequestDto;
import com.example.demo.security.PrincipalDetails;
import org.springframework.http.ResponseEntity;

public interface AuthService {



    AuthResponseDto login(LoginRequestDto loginRequestDto);


    void signup(SignUpRequestDto signUpRequestDto) throws Exception;

    AuthResponseDto refreshToken(RefreshTokenRequest refreshTokenRequest);
}
