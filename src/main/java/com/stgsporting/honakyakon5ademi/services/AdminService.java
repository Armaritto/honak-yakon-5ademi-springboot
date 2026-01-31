package com.stgsporting.honakyakon5ademi.services;

import com.stgsporting.honakyakon5ademi.authentication.Authenticatable;
import com.stgsporting.honakyakon5ademi.dtos.admins.AdminFormDTO;
import com.stgsporting.honakyakon5ademi.entities.Admin;
import com.stgsporting.honakyakon5ademi.entities.AdminDetail;
import com.stgsporting.honakyakon5ademi.entities.Details;
import com.stgsporting.honakyakon5ademi.exceptions.*;
import com.stgsporting.honakyakon5ademi.repositories.AdminRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class AdminService implements AuthenticatableService {

    private final AdminRepository adminRepository;

    AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public Optional<Admin> getAdminById(long id) {
        return adminRepository.findById(id);
    }

    public Optional<Admin> getAdminByIdOrUsername(String idOrUsername) {
        if (idOrUsername.matches("\\d+")) {
            Optional<Admin> admin = getAdminById(Long.parseLong(idOrUsername));

            if (admin.isPresent())
                return admin;
        }

        return getAdminByUsername(idOrUsername);
    }

    public Page<Admin> getAllAdmins(Pageable pageable) {
        return adminRepository.findAll(pageable);
    }

    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }

    @Override
    public Authenticatable getAuthenticatableById(long id) {
        return getAdminById(id).orElseThrow(InvalidCredentialsException::new);
    }

    @Override
    public long getAuthenticatableId() {
        return getAuthenticatable().getId();
    }

    @Override
    public Authenticatable getAuthenticatable() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !(authentication.getPrincipal() instanceof AdminDetail))
            throw new UnauthorizedAccessException("Admin is not authenticated");

        return ((Details) authentication.getPrincipal()).getAuthenticatable();
    }

    public Optional<Admin> getAdminByUsername(String username) {
        return adminRepository.findByUsername(username);
    }

    @Override
    public Authenticatable getAuthenticatableByUsername(String username) {
        return getAdminByUsername(username)
                .orElseThrow(InvalidCredentialsException::new);
    }

    @Override
    public void save(Authenticatable authenticatable) {
        adminRepository.save((Admin) authenticatable);
    }

    public Admin createAdmin(AdminFormDTO adminDTO) {
        Admin admin = new Admin();
        this.getAdminByUsername(adminDTO.getUsername())
                .ifPresent(a -> {
                    throw new UsernameTakenException("Username already exists");
                });

        admin.setUsername(adminDTO.getUsername());

        validatePassword(adminDTO.getPassword());
        admin.setPassword(adminDTO.getPassword());

        return adminRepository.save(admin);
    }

    public void updateAdmin(Admin admin, AdminFormDTO adminDTO) {
        if (adminDTO.getUsername() != null && !adminDTO.getUsername().isEmpty() && !adminDTO.getUsername().isBlank()) {
            Optional<Admin> oldAdmin = this.getAdminByUsername(adminDTO.getUsername());
            if (oldAdmin.isPresent() && !Objects.equals(oldAdmin.get().getId(), admin.getId())) {
                throw new UsernameTakenException("Username already exists");
            }

            admin.setUsername(adminDTO.getUsername());
        }
        if (adminDTO.getPassword() != null && !adminDTO.getPassword().isEmpty() && !adminDTO.getPassword().isBlank()) {
            validatePassword(adminDTO.getPassword());
            admin.setPassword(adminDTO.getPassword());
        }

        adminRepository.save(admin);
    }

    private void validatePassword(String password) {
        if (password == null || password.isEmpty())
            throw new ChangePasswordException("Password cannot be empty");

        if (password.length() < 4 || password.length() > 64)
            throw new ChangePasswordException("Password must be between 6 and 64 characters");
    }
}
