package com.example.students_mvc.sec.service;

import com.example.students_mvc.sec.entities.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private SecurityService securityService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser= securityService.loadUserByUsername(username);
        /**version1*/
        /*Collection<GrantedAuthority> authorityCollection=new ArrayList<>();
        appUser.getAppRoles().forEach(role->{
            SimpleGrantedAuthority authority=new SimpleGrantedAuthority(role.getRoleName());
            authorityCollection.add(authority);
        });
        User user=new User(username,appUser.getPassword(),authorityCollection);
        */
        /**version2*/
        Collection<GrantedAuthority> grantedAuthorities=
                appUser.getAppRoles().stream().map(role->new SimpleGrantedAuthority(role.getRoleName()))
                        .collect(Collectors.toList());

        User user=new User(username,appUser.getPassword(),grantedAuthorities);

        return user;
    }
}
