package com.example.students_mvc.sec.repository;


import com.example.students_mvc.sec.entities.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppRoleRepository  extends JpaRepository<AppRole,Long> {
    AppRole findByRoleName(String roleName);
}
