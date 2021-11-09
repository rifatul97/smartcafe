package com.project.smartcafe.service;

import com.project.smartcafe.domain.Role;
import com.project.smartcafe.domain.User;
import com.project.smartcafe.exception.AlreadyExistException;
import com.project.smartcafe.exception.NotFoundException;
import com.project.smartcafe.repository.RoleRepository;
import com.project.smartcafe.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service @RequiredArgsConstructor @Transactional @Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepository userRepo;
    private final RoleRepository roleRepo;

    @Autowired private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public User save(User newUser) {
        Optional<User> saveUser = Optional.empty();
        if (userRepo.findByUsername(newUser.getUsername()).isPresent()) {
            throw new AlreadyExistException("A user with the given username already exists.");
        }
        Optional<Role> role = roleRepo.findByRoleName("ROLE_CUSTOMER");
        saveUser = Optional.of(userRepo.save(new User(newUser.getFirstName(),
                newUser.getLastName(), newUser.getUsername(), newUser.getEmail(),
                passwordEncoder.encode(newUser.getPassword()),
                role.get())));

        return saveUser.get();
    }

    @Override
    public Optional<User> find(long id) {
        return userRepo.findById(id);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepo.findByUsername(username);
    }

    @Override
    public Iterable<User> findAll() {
        return userRepo.findAll();
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("the username {} is loading user by username:", username);
        User user = userRepo.findByUsername(username)
                .orElseThrow(()-> new UsernameNotFoundException("it is not found."));
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(), passwordEncoder.encode(user.getPassword()), getUserAuthority(user.getRoles()));
    }

    private List<GrantedAuthority> getUserAuthority(List<Role> userRoles) {
        Set<GrantedAuthority> roles = new HashSet<>();
        userRoles.forEach((role) -> {
            roles.add(new SimpleGrantedAuthority(role.getRoleName()));
        });
        return new ArrayList<GrantedAuthority>(roles);
    }
}
