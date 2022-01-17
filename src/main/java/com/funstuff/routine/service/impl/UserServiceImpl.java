package com.funstuff.routine.service.impl;

import com.funstuff.routine.entity.Role;
import com.funstuff.routine.entity.User;
import com.funstuff.routine.repository.RoleRepository;
import com.funstuff.routine.repository.UserRepository;
import com.funstuff.routine.request.SignupForm;
import com.funstuff.routine.request.UserUpdateForm;
import com.funstuff.routine.service.UserService;
import com.funstuff.routine.utility.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;


@Service
public class UserServiceImpl implements UserService, UserDetailsService {


    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if(user == null){
            throw  new UsernameNotFoundException("Username not found");
        }

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> {authorities.add(new SimpleGrantedAuthority(role.getName()));} );

        return new  org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),
                authorities);
    }

    @Override
    public User createUser(SignupForm userForm) throws NoSuchAlgorithmException, InvalidKeySpecException {
        User user = new User();
        user.setDisplayName(userForm.getDisplayName());
        user.setUsername(userForm.getUsername());
        user.setEmail(userForm.getEmail());
        user.setPassword(Utils.generateHashedPassword(userForm.getPassword()));
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
