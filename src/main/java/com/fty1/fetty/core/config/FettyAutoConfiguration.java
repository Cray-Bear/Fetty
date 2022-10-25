package com.fty1.fetty.core.config;

import com.fty1.fetty.core.config.rule.FettyDataSourceRuleSpringBootConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;
import java.util.LinkedHashMap;
import java.util.Map;

@EnableConfigurationProperties(FettyDataSourceRuleSpringBootConfiguration.class)
public class FettyAutoConfiguration implements EnvironmentAware {


    @Autowired
    private FettyDataSourceRuleSpringBootConfiguration config;
    private final Map<String, DataSource> dataSourceMap = new LinkedHashMap<>();


    @Override
    public void setEnvironment(Environment environment) {
        dataSourceMap.putAll(DataSourceMapSetter.getDataSourceMap(environment));}
}
