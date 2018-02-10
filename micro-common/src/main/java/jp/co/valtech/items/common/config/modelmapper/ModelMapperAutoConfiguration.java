package jp.co.valtech.items.common.config.modelmapper;

import org.modelmapper.ModelMapper;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass(ModelMapper.class)
public class ModelMapperAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean(ModelMapperFactoryBean.class)
    public ModelMapperFactoryBean modelMapperFactoryBean() {

        return new ModelMapperFactoryBean();
    }

}
