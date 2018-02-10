package jp.co.valtech.items.common.config.swagger;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@ComponentScan
@EntityScan("jp.co.valtech.items.common.config.swagger.plugin")
@EnableSwagger2
public class SwaggerCore {
}
