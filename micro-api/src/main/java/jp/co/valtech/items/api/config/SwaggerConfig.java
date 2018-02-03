package jp.co.valtech.items.api.config;

import com.google.common.base.Predicate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static com.google.common.base.Predicates.or;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket documentation() {
        Set<String> set = new HashSet<>();
        set.add(MediaType.APPLICATION_JSON_UTF8_VALUE);

        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(paths())
                .build()
                .useDefaultResponseMessages(false)
                .enableUrlTemplating(false)
                .genericModelSubstitutes(Optional.class)
                .pathMapping("/")
                .produces(set)
                .consumes(set)
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Springfox ItemsAPI")
                .description("ここにはDiscriptionを記載します。")
                .license("Apache License Version 2.0")
                .licenseUrl("https://github.com/springfox/springfox/blob/master/LICENSE")
                .version("2.0")
                .build();
    }

    private Predicate<String> paths() {
        return or(PathSelectors.any());
    }
}
