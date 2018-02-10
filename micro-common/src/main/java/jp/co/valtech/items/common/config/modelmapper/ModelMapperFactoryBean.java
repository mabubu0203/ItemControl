package jp.co.valtech.items.common.config.modelmapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.FactoryBean;

public class ModelMapperFactoryBean implements FactoryBean<ModelMapper> {

    @Override
    public ModelMapper getObject() throws Exception {
        final ModelMapper modelMapper = new ModelMapper();
        return modelMapper;
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
