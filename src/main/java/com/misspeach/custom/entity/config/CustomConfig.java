package com.misspeach.custom.entity.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Created by shizhan on 16/7/22.
 */
@Configuration
@ConfigurationProperties(prefix = "hello")
public class CustomConfig {

    Long hello;
}
