package jp.co.valtech.items.api.config;

import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
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

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlElement;


@Component
public class ApiModelPropertyPlugin implements ModelPropertyBuilderPlugin {

    @Override
    public boolean supports(DocumentationType delimiter) {
        return true;
    }

    @Override
    public void apply(ModelPropertyContext context) {

        ModelPropertyBuilder builder = context.getBuilder();

        // プロパティのgetterを取得する
        Optional<BeanPropertyDefinition> beanPropDef = context.getBeanPropertyDefinition();
        BeanPropertyDefinition beanDef = beanPropDef.get();
        AnnotatedMethod method = beanDef.getGetter();
        if (method == null) {
            return;
        }

        // jsonラベルを取得する
        XmlElement xmlElement = method.getAnnotation(XmlElement.class);
        if (xmlElement != null) {
            builder.name(xmlElement.name());
        }

        // 必須・非必須を取得する
        NotNull notNull = method.getAnnotation(NotNull.class);
        NotEmpty notEmpty = method.getAnnotation(NotEmpty.class);
        NotBlank notBlank = method.getAnnotation(NotBlank.class);
        if (notNull != null || notEmpty != null) {
            builder.required(true).allowEmptyValue(false);
        } else if (notBlank != null) {
            builder.required(true);
        }

        // 範囲制約を取得する
        Range range = method.getAnnotation(Range.class);
        if (range != null) {
            builder.allowableValues(new AllowableRangeValues(
                    Long.toString(range.min()), Long.toString(range.max())));
        }
        Size size = method.getAnnotation(Size.class);
        if (size != null) {
            builder.allowableValues(
                    new AllowableRangeValues(
                            Long.toString(size.min()),
                            Long.toString(size.max())));
        }
        Length length = method.getAnnotation(Length.class);
        if (length != null) {
            builder.allowableValues(
                    new AllowableRangeValues(
                            Long.toString(length.min()),
                            Long.toString(length.max())));
        }

    }
}
