package com.hussein.feign;

import com.hussein.dto.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.math.BigDecimal;

/**
 * <p>Title: UserFeignClient</p>
 * <p>Description: </p>
 * <p>Company: www.hussein.com</p>
 *
 * @author hwangsy
 * @date 2020/1/4 11:25 AM
 */
@FeignClient(name = "simple-provider-user", fallback = UserFeignClient.FallbackUserFeignClient.class)
public interface UserFeignClient {

    @RequestMapping(value = "/findUserById/{id}", method = RequestMethod.GET)
    User findUserById(@PathVariable("id") Long id);

    @Component
    class FallbackUserFeignClient implements UserFeignClient {

        @Override
        public User findUserById(Long id) {
            User user = new User();
            user.setId(id);
            user.setUserName("");
            user.setName("");
            user.setAge(0);
            user.setBalance(new BigDecimal("0"));
            return user;
        }
    }
}
