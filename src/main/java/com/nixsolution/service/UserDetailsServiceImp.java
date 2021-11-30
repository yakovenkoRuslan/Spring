package com.nixsolution.service;

import com.nixsolution.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserDetailsServiceImp implements UserDetailsService {

    private final UserService userService;

    public UserDetailsServiceImp(UserService userService) {
        this.userService = userService;
    }

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String login)
            throws UsernameNotFoundException {
        User user = userService.findByLogin(login);
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        if (user == null) {
            throw new UsernameNotFoundException(
                    String.format("User with %s not found", login));
        }
        grantedAuthorities.add(
                new SimpleGrantedAuthority(user.getRole().getName()));
        return new org.springframework.security.core.userdetails.User(
                user.getLogin(), user.getPassword(), grantedAuthorities);
    }
}
