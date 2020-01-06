package com.hussein.study.feign;

import com.hussein.config.FeignConfiguration;
import com.hussein.study.dto.User;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * <p>Title: UserFeignClient</p>
 * <p>Description: </p>
 * <p>Company: www.hussein.com</p>
 *
 * @author hwangsy
 * @date 2020/1/4 11:25 AM
 */
@FeignClient(name = "simple-provider-user", configuration = FeignConfiguration.class)
public interface UserFeignClient {

    @RequestLine(value = "GET /findUserById/{id}")
    User findUserById(@Param("id") Long id);
}
