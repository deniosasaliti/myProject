package com.example.demo.controles;


import com.example.demo.Dto.payload.AuthResponseDto;
import com.example.demo.Dto.payload.LoginRequestDto;
import com.example.demo.Dto.payload.RefreshTokenRequest;
import com.example.demo.Dto.payload.SignUpRequestDto;
import com.example.demo.Entity.User;
import com.example.demo.security.PrincipalDetails;
import com.example.demo.security.oauth2.OAuth2AuthenticationSuccessHandler;
import com.example.demo.service.AuthService;
import com.example.demo.service.RefreshTokenService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthCont {

    private final AuthService authService;

    private final RefreshTokenService refreshTokenService;
    private final OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler;
    private final ObjectMapper mapper;



    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> authUser(@RequestBody LoginRequestDto loginRequestDto) {
            return new ResponseEntity<>(authService.login(loginRequestDto),HttpStatus.OK);
    }

//    @GetMapping("/oauth2login")
//    public ResponseEntity<AuthResponseDto> auth2User(@RequestParam String url) {
//        return new ResponseEntity<>(authService.oauth2login(),HttpStatus.OK);
//    }


    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody SignUpRequestDto signUpRequestDto) throws Exception {
        authService.signup(signUpRequestDto);
        return new ResponseEntity<>("User Registration Successful", HttpStatus.OK);
    }

    @PostMapping("refreshToken")
    public ResponseEntity<AuthResponseDto> refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest){
       return new ResponseEntity<>(authService.refreshToken(refreshTokenRequest),HttpStatus.OK);
    }

    @PostMapping("logout")
    public ResponseEntity<String> logout(@RequestBody RefreshTokenRequest refreshTokenRequest){
        refreshTokenService.deleteToken(refreshTokenRequest.getRefreshToken());
        return ResponseEntity.status(HttpStatus.OK).body("refresh token deleted ");
    }









}
