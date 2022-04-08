package ma.enset.patients_mvc.sec.repository;

import ma.enset.patients_mvc.sec.entities.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppRoleRepository  extends JpaRepository<AppRole,String> {
    AppRole findByRoleName(String roleName);
}
