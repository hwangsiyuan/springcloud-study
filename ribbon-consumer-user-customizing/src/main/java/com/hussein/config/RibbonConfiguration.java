package com.hussein.config;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>Title: RibbonConfiguration</p>
 * <p>Description: </p>
 * <p>Company: www.hussein.com</p>
 *
 * @author hwangsy
 * @date 2020/1/3 6:11 PM
 */
@Configuration
public class RibbonConfiguration {

    @Bean
    public IRule rule() {
        return new RandomRule();
    }
}
