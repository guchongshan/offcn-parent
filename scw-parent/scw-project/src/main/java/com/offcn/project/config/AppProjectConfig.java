package com.offcn.project.config;

import com.offcn.util.OssTemplate;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 胡长生
 * @create 2020-10-24 17:01
 */
@Configuration
public class AppProjectConfig {
    @ConfigurationProperties(prefix="oss") //加载配置文件中的oss属性
    @Bean
    public OssTemplate ossTemplate(){
        return new OssTemplate();
    }
}

