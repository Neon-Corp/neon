package com.neon.service;

import com.neon.model.User;
import com.neon.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> findAll(){
        return (List<User>) userRepository.findAll();
    }

    public Optional<User> findOne(Integer id){
        return userRepository.findById(id);
    }

    public User findOneByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User save(User entity){
        String rawPassword = entity.getPassword();
        String encodedPassword = BCrypt.hashpw(rawPassword, BCrypt.gensalt(4));
        entity.setPassword(encodedPassword);
        return userRepository.save(entity);
    }

    public void delete(User entity){
        userRepository.delete(entity);
    }
}
