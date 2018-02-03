package jp.co.valtech.items.api.config.swagger;

import com.fasterxml.classmate.TypeResolver;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import springfox.documentation.builders.ModelBuilder;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.schema.ModelBuilderPlugin;
import springfox.documentation.spi.schema.contexts.ModelContext;
import springfox.documentation.swagger.common.SwaggerPluginSupport;

import javax.xml.bind.annotation.XmlRootElement;

@Component
public class ApiModelPlugin implements ModelBuilderPlugin {

    private final TypeResolver typeResolver;

    public ApiModelPlugin(TypeResolver typeResolver) {
        this.typeResolver = typeResolver;
    }

    @Override
    public boolean supports(DocumentationType delimiter) {
        return SwaggerPluginSupport.pluginDoesApply(delimiter);
    }

    @Override
    public void apply(ModelContext context) {
        XmlRootElement xmlRootElement = AnnotationUtils.findAnnotation(forClass(context), XmlRootElement.class);
        if (xmlRootElement != null) {
            ModelBuilder builder = context.getBuilder();
            builder.name(xmlRootElement.name());
        }
    }

    private Class<?> forClass(ModelContext context) {
        return typeResolver.resolve(context.getType()).getErasedType();
    }

}
