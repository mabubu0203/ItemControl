package jp.co.valtech.items.api.config;

import com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition;
import com.google.common.base.Optional;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import org.springframework.stereotype.Component;
import springfox.documentation.builders.ModelPropertyBuilder;
import springfox.documentation.service.AllowableRangeValues;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.schema.ModelPropertyBuilderPlugin;
import springfox.documentation.spi.schema.contexts.ModelPropertyContext;
import springfox.documentation.swagger.common.SwaggerPluginSupport;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlElement;

import static springfox.documentation.schema.Annotations.findPropertyAnnotation;


@Component
public class ApiModelPropertyPlugin implements ModelPropertyBuilderPlugin {

    @Override
    public boolean supports(DocumentationType delimiter) {
        return SwaggerPluginSupport.pluginDoesApply(delimiter);
    }

    @Override
    public void apply(ModelPropertyContext context) {

        Optional<BeanPropertyDefinition> beanPropDef = context.getBeanPropertyDefinition();

        if (beanPropDef.isPresent()) {
            BeanPropertyDefinition beanDef = beanPropDef.get();
            ModelPropertyBuilder builder = context.getBuilder();

            // jsonラベルを取得する
            Optional<XmlElement> xmlElement = findPropertyAnnotation(beanDef, XmlElement.class);
            if (xmlElement.isPresent()) {
                builder.name(xmlElement.get().name());
            }

            // 必須・非必須を取得する
            Optional<NotNull> notNull = findPropertyAnnotation(beanDef, NotNull.class);
            Optional<NotEmpty> notEmpty = findPropertyAnnotation(beanDef, NotEmpty.class);
            Optional<NotBlank> notBlank = findPropertyAnnotation(beanDef, NotBlank.class);
            if (notNull.isPresent() || notEmpty.isPresent()) {
                builder.required(true).allowEmptyValue(false);
            } else if (notBlank.isPresent()) {
                builder.required(true);
            }

            // 範囲制約を取得する
            Optional<Range> range = findPropertyAnnotation(beanDef, Range.class);
            if (range.isPresent()) {
                builder.allowableValues(
                        new AllowableRangeValues(
                                Long.toString(range.get().min()),
                                Long.toString(range.get().max())));
            }

            Optional<Size> size = findPropertyAnnotation(beanDef, Size.class);
            if (size.isPresent()) {
                builder.allowableValues(
                        new AllowableRangeValues(
                                Long.toString(size.get().min()),
                                Long.toString(size.get().max())));
            }

            Optional<Length> length = findPropertyAnnotation(beanDef, Length.class);
            if (length.isPresent()) {
                builder.allowableValues(
                        new AllowableRangeValues(
                                Long.toString(length.get().min()),
                                Long.toString(length.get().max())));
            }

        } else {
            // nothing
        }
    }
}
