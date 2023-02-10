package com.example.services;

import com.example.Repository.UserRepository;
import com.example.entity.User;
import com.example.entity.roles.ERole;
import com.example.exceptions.UserException;
import com.example.payload.request.SignupRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    public static final Logger LOG = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = bCryptPasswordEncoder;
    }
//    тут сам метод создания пользователя и выдача ему роли
    public User createUser(SignupRequest userIn){
        User user =  new User();
        user.setEmail(userIn.getEmail());
        user.setF_name(userIn.getF_name());
        user.setL_name(userIn.getL_name());
        user.setUsernames(userIn.getUsernames());
        user.setPassword(passwordEncoder.encode(userIn.getPassword()));
        user.getRole().add(ERole.ROLE_USER);

        try {
            LOG.info("saving user {}", user.getEmail());
            return userRepository.save(user);
        }catch (Exception e){
            System.out.println("error during registration");
            throw new UserException("user" + user.getUsername() + "already exists");
        }
    }
}
