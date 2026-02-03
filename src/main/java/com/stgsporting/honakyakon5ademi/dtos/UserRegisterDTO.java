package com.stgsporting.honakyakon5ademi.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegisterDTO extends RegisterDTO {
    private Long khedmaId;

    @Override
    public String toString() {
        return "UserRegisterDTO{" +
                "username='" + getUsername() + '\'' +
                ", password='" + getPassword()  + '\'' +
                ", khedma='" + khedmaId + '\'' +
                '}';
    }
}
