package com.fty1.fetty.core.config.rule;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Properties;

@ConfigurationProperties(prefix = "spring.fetty")
@Getter
@Setter
public final class FettyDataSourceRuleSpringBootConfiguration {

    Properties rules = new Properties();

}
