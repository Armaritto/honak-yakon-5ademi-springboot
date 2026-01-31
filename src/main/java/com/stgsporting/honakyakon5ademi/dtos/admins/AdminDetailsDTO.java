package com.stgsporting.honakyakon5ademi.dtos.admins;

import com.stgsporting.honakyakon5ademi.entities.Admin;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AdminDetailsDTO {
    private Long id;
    private String username;


    public AdminDetailsDTO(Admin admin) {
        this.id = admin.getId();
        this.username = admin.getUsername();
    }
}
