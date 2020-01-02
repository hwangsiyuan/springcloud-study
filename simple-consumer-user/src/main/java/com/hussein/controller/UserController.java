package com.hussein.controller;

import com.hussein.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * <p>Title: UserController</p>
 * <p>Description: </p>
 * <p>Company: www.hussein.com</p>
 *
 * @author hwangsy
 * @date 2020/1/2 10:20 AM
 */
@RestController
public class UserController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("findUserById/{id}")
    public User findById(@PathVariable("id") long id) {
        return restTemplate.getForObject("http://127.0.0.1:8000/findUserById/" + id, User.class);
    }
}
