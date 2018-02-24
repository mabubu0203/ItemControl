package jp.co.valtech.items.api;

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jp.co.valtech.items.common.config.swagger.SwaggerCore;
import jp.co.valtech.items.rdb.RdbCore;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import springfox.documentation.spring.data.rest.configuration.SpringDataRestConfiguration;

/**
 * @author uratamanabu
 * @version 1.0
 * @since 1.0
 */
@SpringBootApplication
@Import({RdbCore.class, SwaggerCore.class, SpringDataRestConfiguration.class})
@EntityScan(basePackageClasses = {ApiApp.class, Jsr310JpaConverters.class})
public class ApiApp extends SpringBootServletInitializer {

    /**
     * @author uratamanabu
     * @version 1.0
     * @since 1.0
     */
    public static void main(final String[] args) {
        new SpringApplicationBuilder(ApiApp.class).run(args);
    }

    @Override
    protected SpringApplicationBuilder configure(final SpringApplicationBuilder builder) {
        return builder.sources(ApiApp.class);
    }

    @Configuration
    public class JacksonConfiguration {
        @Bean
        public JavaTimeModule javaTimeModule() {
            return new JavaTimeModule();
        }
    }
}
