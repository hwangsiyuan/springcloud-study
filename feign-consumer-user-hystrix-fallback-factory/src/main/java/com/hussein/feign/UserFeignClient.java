package com.hussein.feign;

import com.hussein.dto.User;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
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
@FeignClient(name = "simple-provider-user", fallbackFactory = UserFeignClient.UserFeignClientFeignFallbackFactory.class)
public interface UserFeignClient {

    @RequestMapping(value = "/findUserById/{id}", method = RequestMethod.GET)
    User findUserById(@PathVariable("id") Long id);

    @Component
    class UserFeignClientFeignFallbackFactory implements FallbackFactory<UserFeignClient> {

        @Override
        public UserFeignClient create(Throwable throwable) {
            return new UserFeignClientFallback(throwable);
        }

    }

    @Slf4j
    class UserFeignClientFallback implements UserFeignClient {

        private Throwable throwable;

        public UserFeignClientFallback(Throwable throwable) {
            this.throwable = throwable;
        }

        @Override
        public User findUserById(Long id) {
            log.info("fallback reason was:", throwable);
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
