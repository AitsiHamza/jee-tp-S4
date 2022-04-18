package com.example.students_mvc.sec.service;


import com.example.students_mvc.sec.entities.AppRole;
import com.example.students_mvc.sec.entities.AppUser;

public interface SecurityService {
    AppUser saveNewUser(String username, String password, String verifyPassword);
    AppRole saveNewRole(String roleName);
    void addRoleToUser(String username, String roleName);
    void removeRoleFromUser(String username, String roleName);
    AppUser loadUserByUsername(String username);
}
