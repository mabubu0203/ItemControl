package jp.co.valtech.items.common.config.swagger;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@ConditionalOnClass(ApiInfo.class)
public class SwaggerAutoConfiguration {


    @Bean
    @ConditionalOnMissingBean(DocketFactoryBean.class)
    public DocketFactoryBean docketFactoryBean() {
        return new DocketFactoryBean();
    }


}
