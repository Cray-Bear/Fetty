package com.fty1.fetty.core.config.datasource;

import com.fty1.fetty.utils.PropertyUtil;
import com.fty1.fetty.utils.expr.InlineExpressionParser;
import com.google.common.base.Preconditions;
import com.mysql.cj.jdbc.MysqlDataSource;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.apache.shardingsphere.infra.datasource.pool.creator.DataSourcePoolCreator;
import org.apache.shardingsphere.infra.datasource.props.DataSourceProperties;
import org.apache.shardingsphere.infra.util.expr.InlineExpressionParser;
import org.apache.shardingsphere.spring.boot.util.PropertyUtil;
import org.springframework.core.env.Environment;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.util.Assert;

import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.DriverManager;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Data source map setter.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DataSourceMapSetter {

    private static final String PREFIX = "spring.fetty.datasource.";

    private static final String DATA_SOURCE_NAME = "name";

    private static final String DATA_SOURCE_NAMES = "names";

    private static final String DATA_SOURCE_TYPE = "type";

    private static final String JNDI_NAME = "jndi-name";

    /**
     * Get data source map.
     *
     * @param environment spring boot environment
     * @return data source map
     */
    public static Map<String, DataSource> getDataSourceMap(final Environment environment) {
        Map<String, DataSource> result = new LinkedHashMap<>();
        for (String each : getDataSourceNames(environment)) {
            try {
                result.put(each, getDataSource(environment, each));
            } catch (final NamingException ex) {
                throw new RuntimeException(ex);
            }
        }
        return result;
    }

    private static List<String> getDataSourceNames(final Environment environment) {
        StandardEnvironment standardEnv = (StandardEnvironment) environment;
        standardEnv.setIgnoreUnresolvableNestedPlaceholders(true);
        String dataSourceNames = standardEnv.getProperty(PREFIX + DATA_SOURCE_NAME);
        if (StringUtils.isBlank(dataSourceNames)) {
            dataSourceNames = standardEnv.getProperty(PREFIX + DATA_SOURCE_NAMES);
        }
        return Arrays.stream(Objects.requireNonNull(StringUtils.split(dataSourceNames, ","))).distinct().collect(Collectors.toList());
    }

    @SuppressWarnings("unchecked")
    private static DataSource getDataSource(final Environment environment, final String dataSourceName) throws NamingException {
        Map<String, Object> dataSourceProps = PropertyUtil.handle(environment, String.join("", PREFIX, dataSourceName), Map.class);
        Assert.isTrue(!dataSourceProps.isEmpty(), String.format("Wrong datasource [%s] properties.", dataSourceName));

        DataSourceProperty property;




        return new MysqlDataSource();
    }
}
