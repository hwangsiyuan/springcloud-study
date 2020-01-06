package com.hussein.config;

import feign.Contract;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>Title: FeignConfiguration</p>
 * <p>Description: </p>
 * <p>Company: www.hussein.com</p>
 *
 * @author hwangsy
 * @date 2020/1/6 10:30 AM
 */
@Configuration
public class FeignConfiguration {

    @Bean
    public Contract feignContract() {
        return new Contract.Default();
    }
}
