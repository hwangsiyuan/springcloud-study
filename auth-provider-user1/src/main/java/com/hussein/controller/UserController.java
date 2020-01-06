package com.hussein.controller;

import com.hussein.dao.UserRepository;
import com.hussein.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

/**
 * <p>Title: UserController</p>
 * <p>Description: </p>
 * <p>Company: www.hussein.com</p>
 *
 * @author hwangsy
 * @date 2019/12/31 8:19 PM
 */
@Slf4j
@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/findUserById/{id}")
    public User findById(@PathVariable("id") long id) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) principal;
            Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
            authorities.forEach(a -> log.info("当前用户:{},权限:{}", userDetails.getUsername(), a.getAuthority()));
        }
        return userRepository.findById(id).orElseGet(null);
    }
}
