package com.laotou.consumerUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 生产者properties工具类
 */
public class PropertiesUtils {
    public  static Properties getProperties(String name) throws IOException {
        InputStream resourceAsStream = PropertiesUtils.class.getClassLoader().getResourceAsStream(name + ".properties");
        Properties properties = new Properties();
        properties.load(resourceAsStream);
        resourceAsStream.close();
        return  properties;
    }
}
