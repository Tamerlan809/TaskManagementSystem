package com.example.taskmanagementsystem.service;

import com.example.taskmanagementsystem.entity.Role;
import com.example.taskmanagementsystem.entity.User;
import com.example.taskmanagementsystem.repo.RoleRepository;
import com.example.taskmanagementsystem.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    public User createUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Role employeeRole = roleRepository.findByName("EMPLOYEE");
        user.setRoles(Set.of(employeeRole));

        return userRepository.save(user);
    }

    public List<User> findAllUsers(){
        return userRepository.findAll();
    }

    public void deleteUserById(Long id){
        userRepository.deleteById(id);
    }

    public User findUserById(Long id){
        return userRepository.findById(id).orElse(null);
    }

    public List<Role> findAllRoles(){
        return roleRepository.findAll();
    }

    public Role findRoleById(Long id){
        return roleRepository.findById(id).orElse(null);
    }

    public void updateUserWithRole(User user, Long roleId){
        Role selectedRole = roleRepository.findById(roleId).orElse(null);
        if (selectedRole != null){
            user.setRoles(Set.of(selectedRole));
        }

        userRepository.save(user);
    }
}
