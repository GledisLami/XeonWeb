package com.Xeon.XeonWeb.services;

import com.Xeon.XeonWeb.entities.User;
import com.Xeon.XeonWeb.repositories.RoleRepository;
import com.Xeon.XeonWeb.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public Optional<User> findById(Integer id){
        return userRepository.findById(id);
    }

    public void saveUser(User user){
        userRepository.save(user);
    }

    public List<User> getAllEngineerUsers() {
        //assuming roli i ing eshte 1 bejme return all engineers
        //admin can assign userId of a project to an engineer from the list
        return userRepository.findAll().stream()
                .filter(user -> user.getRoleId() == 1)
                .collect(Collectors.toList());
    }

    @Transactional
    public void updateUser(Integer id,String username, String password, String role) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            if (username != null && username.length() > 0) {
                user.get().setUsername(username);
            }
            if (password != null && password.length() > 0) {
                user.get().setPassword(password);
            }
            if (role != null && role.length() > 0 && roleRepository.findByRole(role).isPresent()) {
                user.get().setRoleId(roleRepository.findByRole(role).get().getId());
            }
        }
    }

    @Transactional
    public String deleteUser(Integer id){
        if (!userRepository.existsById(id)) {
            return "User me id: " + id + " nuk ekziston";
        }
        userRepository.deleteById(id);
        return "User me id: " + id + " u fshi";
    }
}
