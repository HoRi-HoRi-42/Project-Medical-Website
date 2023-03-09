package com.example.project.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.project.model.Role;
import com.example.project.model.User;
import com.example.project.repository.UserRepository;
import com.example.project.dto.UserRegistrationDto;

@Service
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;
    private ArrayList<User> activeUsers;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.activeUsers = new ArrayList<User>();
    }

    @Override
    public User save(UserRegistrationDto registrationDto) {
        User user = new User(registrationDto.getFirstName(),
                registrationDto.getLastName(), registrationDto.getEmail(),
                passwordEncoder.encode(registrationDto.getPassword()), registrationDto.getRole());

        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        if(user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        } else {
            activeUsers.add(user);
        }
        Collection<Role> roles = new ArrayList<Role>();
        roles.add(new Role (user.getRole()));
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), mapRolesToAuthorities(roles));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

    public ArrayList<User> getActiveUsers() {
        return activeUsers;
    }

    public void setActiveUsers(ArrayList<User> activeUsers) {
        this.activeUsers = activeUsers;
    }
}

