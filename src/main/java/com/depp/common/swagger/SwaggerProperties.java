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


    private Map<String, ParameterProperties> parameters;
}
