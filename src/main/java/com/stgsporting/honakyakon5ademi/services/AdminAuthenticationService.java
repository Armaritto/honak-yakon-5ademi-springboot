package com.stgsporting.honakyakon5ademi.services;

import com.stgsporting.honakyakon5ademi.authentication.CheckIfUserExistsHandler;
import com.stgsporting.honakyakon5ademi.authentication.LoginHandler;
import com.stgsporting.honakyakon5ademi.dtos.AuthInfo;
import com.stgsporting.honakyakon5ademi.dtos.LoginDTO;
import org.springframework.stereotype.Component;

@Component
public class AdminAuthenticationService {
    private final AdminService adminService;
    private final JWTService jwtService;

    AdminAuthenticationService(AdminService adminService, JWTService jwtService){
        this.adminService = adminService;
        this.jwtService = jwtService;
    }

    public AuthInfo login(LoginDTO adminLoginDTO) {
        LoginHandler loginHandler = new CheckIfUserExistsHandler(adminService);
        AuthInfo authInfo = loginHandler.handle(adminLoginDTO);
        authInfo.setJWTToken(jwtService.generateAdminToken(authInfo));
        return authInfo;
    }
}
