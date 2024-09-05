package com.example.taskmanagementsystem;

import com.example.taskmanagementsystem.entity.Role;
import com.example.taskmanagementsystem.entity.User;
import com.example.taskmanagementsystem.repo.RoleRepository;
import com.example.taskmanagementsystem.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class DataSeeder implements CommandLineRunner {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args){

        Role adminRole = roleRepository.findByName("ADMIN");
        if (adminRole == null){
            adminRole = new Role();
            adminRole.setName("ADMIN");
            roleRepository.save(adminRole);
        }

        Role employeeRole = roleRepository.findByName("EMPLOYEE");
        if (employeeRole == null){
            employeeRole = new Role();
            employeeRole.setName("EMPLOYEE");
            roleRepository.save(employeeRole);
        }

        if (userRepository.findByEmail("admin").isEmpty()){
            User adminUser = new User();
            adminUser.setUsername("Username Admin");
            adminUser.setEmail("admin");
            adminUser.setPassword(passwordEncoder.encode("admin"));

            Set<Role> roles = new HashSet<>();
            roles.add(adminRole);

            adminUser.setRoles(roles);

            userRepository.save(adminUser);
        }
    }
}
