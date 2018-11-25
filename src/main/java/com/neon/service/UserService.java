package com.neon.service;

import com.neon.model.User;
import com.neon.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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

    @Transactional(readOnly = false)
    public User save(User entity){
        return userRepository.save(entity);
    }

    public void delete(User entity){
        userRepository.delete(entity);
    }
}
