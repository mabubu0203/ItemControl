package jp.co.valtech.items.common.config.swagger.plugin;

import com.google.common.base.Optional;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.service.ResolvedMethodParameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.ParameterBuilderPlugin;
import springfox.documentation.spi.service.contexts.ParameterContext;
import springfox.documentation.swagger.common.SwaggerPluginSupport;

@Component
public class ApiParameterPlugin implements ParameterBuilderPlugin {

    @Override
    public boolean supports(DocumentationType delimiter) {
        return SwaggerPluginSupport.pluginDoesApply(delimiter);
    }

    @Override
    public void apply(ParameterContext context) {

        ResolvedMethodParameter parameter = context.resolvedMethodParameter();
        Optional<PathVariable> pathVariable = parameter.findAnnotation(PathVariable.class);
        if (pathVariable.isPresent()) {
            PathVariable path = pathVariable.get();
            ParameterBuilder builder = context.parameterBuilder();
            builder.name(path.name());
            builder.required(path.required());
        } else {
            // nothing
        }
    }

}
