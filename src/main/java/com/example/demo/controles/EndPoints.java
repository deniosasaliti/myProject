package com.example.demo.controles;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class EndPoints {

    public static final String AUTH = "/auth";
    public static final String AUTH_LOGIN = "/login";
    public static final String AUTH_SIGNUP = "/signup";
    public static final String SERIAL = "/serial";
    public static final String POST = "/post";
}
