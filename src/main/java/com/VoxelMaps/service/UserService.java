package com.VoxelMaps.service;

import com.VoxelMaps.model.Role;
import com.VoxelMaps.model.User;
import com.VoxelMaps.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    private static final String NO_ENCODING_TOKEN = "{noop}";

    private final List<Role> roles = Arrays.asList(new Role(0L, "ROLE_ADMIN"),
            new Role(1L, "ROLE_USER"));

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null)
            throw new UsernameNotFoundException("User not found");
        return user;
    }

    public List<User> allUsers() {
        return new ArrayList<User>(userRepository.findAll());
    }

    public boolean saveUser(User user) {
        User userFromDB = userRepository.findByUsername(user.getUsername());
        if (userFromDB != null)
            return false;
        user.setRoles(Collections.singleton(roles.get(1)));
        user.setPassword(NO_ENCODING_TOKEN + user.getPassword());
        user.setPasswordConfirm(user.getPassword());
        userRepository.save(user);
        return true;
    }

    public boolean deleteUser(Long userId) {
        if (userRepository.findById(userId).isPresent()) {
            userRepository.deleteById(userId);
            return true;
        }
        return false;
    }
}