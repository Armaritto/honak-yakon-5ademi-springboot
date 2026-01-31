package com.stgsporting.honakyakon5ademi.services;

import com.stgsporting.honakyakon5ademi.dtos.AuthInfo;
import com.stgsporting.honakyakon5ademi.dtos.UserLoginDTO;
import com.stgsporting.honakyakon5ademi.authentication.LoginHandler;
import com.stgsporting.honakyakon5ademi.authentication.CheckIfUserExistsHandler;
import org.springframework.stereotype.Component;

@Component
public class UserAuthenticationService {
    private final UserService userService;
    private final JWTService jwtService;

    UserAuthenticationService(UserService userService, JWTService jwtService){
        this.userService = userService;
        this.jwtService = jwtService;
    }


    public AuthInfo login(UserLoginDTO userLoginDTO) {
        LoginHandler loginHandler = new CheckIfUserExistsHandler(userService);
        AuthInfo authInfo = loginHandler.handle(userLoginDTO);
        authInfo.setJWTToken(jwtService.generateUserToken(authInfo));
        return authInfo;
    }
}
