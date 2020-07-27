package ru.leodev.examples.springboot.springbootwebspringsecurity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.leodev.examples.springboot.springbootwebspringsecurity.repository.UserRepository;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository dao;

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        return dao.findUserByLogin(login);
    }
}
