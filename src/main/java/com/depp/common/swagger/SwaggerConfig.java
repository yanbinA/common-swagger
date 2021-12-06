package com.depp.common.swagger;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.CollectionUtils;
import springfox.documentation.builders.*;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.RequestParameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Depp
 */
@Configuration
@ConditionalOnClass({Docket.class, ApiInfoBuilder.class})
@ConditionalOnProperty(prefix = "swagger", value = "enable", matchIfMissing = true)
@EnableConfigurationProperties(SwaggerProperties.class)
@EnableOpenApi
public class SwaggerConfig {

    @Bean
    @ConditionalOnMissingBean
    public SwaggerProperties swaggerProperties() {
        return new SwaggerProperties();
    }

    @Bean
    public Docket customDocket() {
        SwaggerProperties swaggerProperties = swaggerProperties();
        List<RequestParameter> pars = new ArrayList<>();
        if (!CollectionUtils.isEmpty(swaggerProperties.getParameters())) {
            swaggerProperties.getParameters().values().forEach(parameterProperties -> {
                RequestParameterBuilder builder = new RequestParameterBuilder();
                builder.name(parameterProperties.getName())
                        .description(parameterProperties.getDescription())
                        .in(parameterProperties.getParameterType())
                        .required(parameterProperties.isRequired())
                        .build();
                pars.add(builder.build());
            });
        }
        return new Docket(DocumentationType.OAS_30)
                .host(swaggerProperties.getHost())
                .apiInfo(apiInfo(swaggerProperties))
                .select()
                .apis(RequestHandlerSelectors.basePackage(swaggerProperties.getBasePackage()))
                .paths(PathSelectors.any())
                .build()
                //设置参数选项
                .globalRequestParameters(pars);
    }

    /**
     * 配置API文档基本信息
     */
    private ApiInfo apiInfo(SwaggerProperties swaggerProperties) {
        return new ApiInfoBuilder()
                .title(swaggerProperties.getTitle())
                .description(swaggerProperties.getDescription())
                .version(swaggerProperties.getVersion())
                .contact(new Contact(swaggerProperties.getAuthor(), swaggerProperties.getUrl(), swaggerProperties.getEmail()))
                .license(swaggerProperties.getLicense())
                .licenseUrl(swaggerProperties.getLicenseUrl())
                .build();
    }
}
