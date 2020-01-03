package com.hussein.study.controller;

import com.hussein.study.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
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

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @GetMapping("/findUserById/{id}")
    public User findById(@PathVariable("id") long id) {
        return restTemplate.getForObject("http://simple-provider-user/findUserById/" + id, User.class);
    }

    @GetMapping("/chooseService")
    public ServiceInstance chooseService() {
        return loadBalancerClient.choose("simple-provider-user");
    }
}
