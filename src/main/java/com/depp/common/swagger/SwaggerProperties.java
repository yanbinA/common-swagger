package com.depp.common.swagger;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

/**
 * @author Depp
 */
@ConfigurationProperties("swagger")
@Data
public class SwaggerProperties {
    /**
     * server name
     */
    private String title;

    /**
     * description of services
     */
    private String description;

    /**
     * version number
     */
    private String version;

    /**
     * basePackage
     */
    private String basePackage;
    /**
     * 请求host
     * 指定请求的host为ip或域名,可解决swagger页面请求跨域的问题
     */
    private String host;

    private String author;
    private String url;
    private String email;
    private String license;
    private String licenseUrl;


    private Map<String, ParameterProperties> parameters;
}
