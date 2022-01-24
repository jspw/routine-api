package com.funstuff.routine.service.impl;

import com.funstuff.routine.entity.Role;
import com.funstuff.routine.entity.User;
import com.funstuff.routine.repository.RoleRepository;
import com.funstuff.routine.repository.UserRepository;
import com.funstuff.routine.request.SignupForm;
import com.funstuff.routine.request.UserUpdateForm;
import com.funstuff.routine.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;


@Service
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User createUser(SignupForm userForm) throws NoSuchAlgorithmException, InvalidKeySpecException {
        User user = new User();
        user.setDisplayName(userForm.getDisplayName());
        user.setUsername(userForm.getUsername());
        user.setEmail(userForm.getEmail());
        user.setPassword(passwordEncoder.encode(userForm.getPassword()));
        user.setGithub(userForm.getGithub());
        user.setCompany(userForm.getCompany());
        user.setAddress(userForm.getAddress());
        user.setCreatedAt(new Date());
        User newUser  =  userRepository.save(user);
        newUser.setPassword(null);
        return newUser;
    }

    @Override
    public User updateUser(long id, UserUpdateForm userUpdateForm) {
        User user = userRepository.getById(id);
        user.setAddress(userUpdateForm.getAddress());
        user.setCompany(userUpdateForm.getCompany());
        user.setDisplayName(userUpdateForm.getDisplayName());
        user.setGithub(userUpdateForm.getGithub());
        user.setUpdatedAt(new Date());
        User  updatedUser = userRepository.save(user);
        updatedUser.setPassword(null);
        return updatedUser;
    }

    @Override
    public void deleteUser(long id) {
        User user = userRepository.findUserById(id);
        userRepository.delete(user);
    }

    @Override
    public User getUser(long id) {
        User user = userRepository.findUserById (id);
        if(user!=null)
        user.setPassword(null);
        return user;
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public Role addRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public void addUserRole(long id, String roleName) {
    User user = userRepository.findUserById(id);
    Role role = roleRepository.findByName(roleName);
    user.getRoles().add(role);
    userRepository.save(user);
    }


}
