package com.neon.service;

import com.neon.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class UserService {
    private static int idCount = 0;

    private static List<User> userList = new ArrayList<>();

    public User save(User entity){
        entity.setId(idCount);
        idCount++;
        userList.add(entity);
        return entity;
    }

    public User findOne(Integer id){
        for (User u : userList){
            if (u.getId() == id){
                return u;
            }
        }
        return null;
    }



//    public List<User> findAll() {
//        return userRepository.findAll();
//    }
//
//    public Optional<User> findOne(Integer id) {
//        return userRepository.findById(id);
//    }
//
//    @Transactional(readOnly = false)
//    public User save(User entity) {
//        return userRepository.save(entity);
//    }
//
//    @Transactional(readOnly = false)
//    public void delete(User entity) {
//        userRepository.delete(entity);
//    }

}
