package com.stgsporting.honakyakon5ademi.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.stgsporting.honakyakon5ademi.authentication.Authenticatable;
import com.stgsporting.honakyakon5ademi.config.DatabaseEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = DatabaseEnum.usersTable)
public class User extends BaseEntity implements Authenticatable {

    @Column(name = DatabaseEnum.username, nullable = false, unique = true)
    private String username;

    @Column(name = DatabaseEnum.password, nullable = false)
    private String password;

    @ManyToOne
    @JoinColumn(name = DatabaseEnum.khedmaId, nullable = false)
    private Khedma khedma;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Response> responses;

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
        this.password = encoder.encode(password);
    }

}
