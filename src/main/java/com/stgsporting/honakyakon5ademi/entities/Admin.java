package com.stgsporting.honakyakon5ademi.entities;


import com.stgsporting.honakyakon5ademi.authentication.Authenticatable;
import com.stgsporting.honakyakon5ademi.config.DatabaseEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Setter
@Getter
@Entity(name = DatabaseEnum.adminsTable)
public class Admin extends BaseEntity implements Authenticatable {

    @Column(name = DatabaseEnum.username, nullable = false, unique = true)
    private String username;

    @Column(name = DatabaseEnum.password, nullable = false)
    private String password;

    public void setPassword(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

        this.password = encoder.encode(password);
    }
}