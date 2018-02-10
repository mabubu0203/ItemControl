package jp.co.valtech.items.common.config.swagger;

import com.fasterxml.classmate.TypeResolver;
import com.google.common.base.Predicate;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.async.DeferredResult;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.WildcardType;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static com.google.common.base.Predicates.or;
import static springfox.documentation.schema.AlternateTypeRules.newRule;

public class DocketFactoryBean implements FactoryBean<Docket> {
    @Autowired
    private TypeResolver typeResolver;

    @Override
    public Docket getObject() throws Exception {
        Set<String> set = new HashSet<>();
        set.add(MediaType.APPLICATION_JSON_UTF8_VALUE);

        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(paths())
                .build()
                .useDefaultResponseMessages(false)
                .enableUrlTemplating(false)
                .genericModelSubstitutes(Optional.class, ResponseEntity.class)
                .alternateTypeRules(
                        newRule(typeResolver.resolve(DeferredResult.class,
                                typeResolver.resolve(ResponseEntity.class, WildcardType.class)),
                                typeResolver.resolve(WildcardType.class)))
                .pathMapping("/")
                .produces(set)
                .consumes(set)
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Springfox")
                .description("ここにはDiscriptionを記載します。")
                .license("Apache License Version 2.0")
                .licenseUrl("https://github.com/springfox/springfox/blob/master/LICENSE")
                .version("2.0")
                .build();
    }

    private Predicate<String> paths() {
        return or(PathSelectors.any());
    }

    @Override
    public Class<?> getObjectType() {
        return ModelMapper.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

}
