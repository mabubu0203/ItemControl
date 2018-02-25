package jp.co.valtech.items.common.config.swagger;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@ComponentScan
@Import({BeanValidatorPluginsConfiguration.class})
@EntityScan("jp.co.valtech.items.common.config.swagger.plugin")
@EnableSwagger2
public class SwaggerCore {
}
