package jp.co.valtech.items.common.config.swagger;

import com.fasterxml.classmate.TypeResolver;
import com.google.common.base.Predicate;
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
        final Docket docket = createDocket();
        return docket;
    }

    private Docket createDocket() {
        Set<String> set = new HashSet<>();
        set.add(MediaType.APPLICATION_JSON_UTF8_VALUE);
        Docket docket = new Docket(DocumentationType.SWAGGER_2);
        docket.select()
                .apis(RequestHandlerSelectors.any())
                .paths(paths())
                .build();
        docket.apiInfo(apiInfo())
                .produces(set)
                .consumes(set)
                .alternateTypeRules(
                        newRule(typeResolver.resolve(DeferredResult.class,
                                typeResolver.resolve(ResponseEntity.class, WildcardType.class)),
                                typeResolver.resolve(WildcardType.class)))
                .genericModelSubstitutes(Optional.class, ResponseEntity.class)
                .useDefaultResponseMessages(false)
                .enable(true)
                .forCodeGeneration(true)
                .pathMapping("/")
                .enableUrlTemplating(false);
        return docket;
    }

    private Predicate<String> paths() {
        return or(PathSelectors.any());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Springfox")
                .description("ここにはDescriptionを記載します。")
                .license("Apache License Version 2.0")
                .licenseUrl("https://github.com/springfox/springfox/blob/master/LICENSE")
                .version("2.0")
                .build();
    }

    @Override
    public Class<?> getObjectType() {
        return Docket.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

}
