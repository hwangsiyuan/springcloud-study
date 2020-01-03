package com.hussein.controller;

import com.hussein.dao.UserRepository;
import com.hussein.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>Title: UserController</p>
 * <p>Description: </p>
 * <p>Company: www.hussein.com</p>
 *
 * @author hwangsy
 * @date 2019/12/31 8:19 PM
 */
@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/findUserById/{id}")
    public User findById(@PathVariable("id") long id){
        return userRepository.findById(id).orElseGet(null);
    }
}
