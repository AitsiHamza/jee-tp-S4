package ma.enset.patients_mvc.sec.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.enset.patients_mvc.sec.entities.AppRole;
import ma.enset.patients_mvc.sec.entities.AppUser;
import ma.enset.patients_mvc.sec.repository.AppRoleRepository;
import ma.enset.patients_mvc.sec.repository.AppUserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.management.relation.Role;
import java.util.UUID;

@Service
@Slf4j
@AllArgsConstructor
@Transactional
public class SecurityServiceImpl implements SecurityService {
    private AppUserRepository appUserRepository;
    private AppRoleRepository appRoleRepository;
    private PasswordEncoder passwordEncoder;

    @Override
    public AppUser saveNewUser(String username, String password, String verifyPassword) {
        if(!password.equals(verifyPassword))throw new RuntimeException("Passwords don't match!");
        AppUser appUser=appUserRepository.findByUsername(username);
        if(appUser!=null)throw new RuntimeException("User "+username+" already exist!");
        String hashedPassword=passwordEncoder.encode(password);
        appUser.setUserId(UUID.randomUUID().toString());
        appUser.setUsername(username);
        appUser.setPassword(hashedPassword);
        appUser.setActive(true);
        AppUser savedAppUser=appUserRepository.save(appUser);
        return savedAppUser;
    }

    @Override
    public AppRole saveNewRole(String roleName, String description) {
        AppRole appRole=appRoleRepository.findByRoleName(roleName);
        if(appRole!=null)throw new RuntimeException("Role "+roleName+" already exist!");
        appRole=new AppRole();
        appRole.setRoleId(UUID.randomUUID().toString());
        appRole.setRoleName(roleName);
        appRole.setDescription(description);
        AppRole savedAppRole= appRoleRepository.save(appRole);
        return savedAppRole;
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        AppUser appUser=appUserRepository.findByUsername(username);
        if(appUser==null)throw new RuntimeException("User "+username+" already exist!");
        AppRole appRole=appRoleRepository.findByRoleName(roleName);
        if(appRole==null)throw new RuntimeException("Role "+roleName+" already exist!");
        appUser.getAppRoles().add(appRole);
    }

    @Override
    public void removeRoleFromUser(String username, String roleName) {
        AppUser appUser=appUserRepository.findByUsername(username);
        if(appUser==null)throw new RuntimeException("User "+username+" already exist!");
        AppRole appRole=appRoleRepository.findByRoleName(roleName);
        if(appRole==null)throw new RuntimeException("Role "+roleName+" already exist!");
        appUser.getAppRoles().remove(appRole);

    }

    @Override
    public AppUser loadUserByUsername(String username) {
        return appUserRepository.findByUsername(username);
    }
}
