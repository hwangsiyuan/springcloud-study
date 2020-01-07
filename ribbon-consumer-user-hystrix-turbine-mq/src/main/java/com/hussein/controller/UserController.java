package com.hussein.controller;

import com.hussein.dto.User;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.contrib.javanica.conf.HystrixPropertiesManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

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

    @HystrixCommand(fallbackMethod = "findByIdFallBack", commandProperties = {
            @HystrixProperty(name = HystrixPropertiesManager.EXECUTION_ISOLATION_THREAD_TIMEOUT_IN_MILLISECONDS, value = "5000"),
            @HystrixProperty(name = HystrixPropertiesManager.METRICS_ROLLING_STATS_TIME_IN_MILLISECONDS, value = "10000")
    }, threadPoolProperties = {
            @HystrixProperty(name = HystrixPropertiesManager.CORE_SIZE, value = "1"),
            @HystrixProperty(name = HystrixPropertiesManager.MAX_QUEUE_SIZE, value = "10")
    })
    @GetMapping("/findUserById/{id}")
    public User findById(@PathVariable("id") Long id) {
        return restTemplate.getForObject("http://simple-provider-user/findUserById/" + id, User.class);
    }

    public User findByIdFallBack(Long id) {
        User user = new User();
        user.setUserName("默认用户");
        user.setId(id);
        user.setName("");
        user.setAge(0);
        user.setBalance(new BigDecimal("0"));
        return user;
    }

    @GetMapping("/chooseService")
    public ServiceInstance chooseService() {
        return loadBalancerClient.choose("simple-provider-user");
    }
}
