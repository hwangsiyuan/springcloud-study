package com.hussein.feign;

import com.hussein.dto.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * <p>Title: UserFeignClient</p>
 * <p>Description: </p>
 * <p>Company: www.hussein.com</p>
 *
 * @author hwangsy
 * @date 2020/1/4 11:25 AM
 */
public interface UserFeignClient {

    @RequestMapping(value = "/findUserById/{id}", method = RequestMethod.GET)
    User findUserById(@PathVariable("id") Long id);
}
