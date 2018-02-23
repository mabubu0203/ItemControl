package jp.co.valtech.items.common.config.swagger;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
@ConditionalOnClass(Docket.class)
public class SwaggerAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean(DocketFactoryBean.class)
    public DocketFactoryBean docketFactoryBean() {
        return new DocketFactoryBean();
    }

}
