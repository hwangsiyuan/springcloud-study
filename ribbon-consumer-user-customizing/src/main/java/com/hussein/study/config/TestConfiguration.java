package com.hussein.study.config;

import com.hussein.config.RibbonConfiguration;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Configuration;

/**
 * <p>Title: TestConfiguration</p>
 * <p>Description: </p>
 * <p>Company: www.hussein.com</p>
 *
 * @author hwangsy
 * @date 2020/1/3 6:14 PM
 */
@Configuration
@RibbonClient(name = "simple-provider-user", configuration = RibbonConfiguration.class)
public class TestConfiguration {
}
