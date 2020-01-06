package com.hussein.controller;

import com.hussein.dto.User;
import com.hussein.feign.UserFeignClient;
import feign.Client;
import feign.Contract;
import feign.Feign;
import feign.auth.BasicAuthRequestInterceptor;
import feign.codec.Decoder;
import feign.codec.Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClientsConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>Title: UserController</p>
 * <p>Description: </p>
 * <p>Company: www.hussein.com</p>
 *
 * @author hwangsy
 * @date 2020/1/2 10:20 AM
 */
@Import(FeignClientsConfiguration.class)
@RestController
public class UserController {

    private UserFeignClient userFeignClient;

    private UserFeignClient adminFeignClient;

    @Autowired
    public UserController(Encoder encoder, Decoder decoder, Client client, Contract contract) {
        this.userFeignClient = Feign.builder().encoder(encoder).decoder(decoder).client(client).contract(contract)
                .requestInterceptor(new BasicAuthRequestInterceptor("user", "112233"))
                .target(UserFeignClient.class, "http://simple-provider-user/");
        this.adminFeignClient = Feign.builder().encoder(encoder).decoder(decoder).client(client).contract(contract)
                .requestInterceptor(new BasicAuthRequestInterceptor("admin", "112233"))
                .target(UserFeignClient.class, "http://simple-provider-user/");
    }

    @GetMapping("/findUserByIdAdmin/{id}")
    public User findByIdAdmin(@PathVariable("id") long id) {
        return adminFeignClient.findUserById(id);
    }

    @GetMapping("/findUserByIdUser/{id}")
    public User findByIdUser(@PathVariable("id") long id) {
        return userFeignClient.findUserById(id);
    }
}
