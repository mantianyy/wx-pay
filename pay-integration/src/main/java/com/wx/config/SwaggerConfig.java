package com.wx.config;

import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.ApiSelectorBuilder;
import springfox.documentation.spring.web.plugins.Docket;


@Configuration
@EnableOpenApi
public class SwaggerConfig {

    @Bean
    public Docket createRestApi() {
        Docket docket = new Docket(DocumentationType.SWAGGER_2);

        // ApiInfoBuilder 用于在Swagger界面上添加各种信息
        ApiInfoBuilder builder = new ApiInfoBuilder();
        builder.title("微信支付接口");
        ApiInfo apiInfo = builder.build();
        docket.apiInfo(apiInfo);

        // ApiSelectorBuilder 用来设置哪些类中的方法会生成到REST API中
        ApiSelectorBuilder selectorBuilder = docket.select();
        //所有包下的类
        selectorBuilder.paths(PathSelectors.any());
        //使用@ApiOperation的方法会被提取到REST API中
        selectorBuilder.apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class));
        docket = selectorBuilder.build();

        return docket;
    }

}
