package jp.co.valtech.items.validator.constraints;

import org.hibernate.validator.constraints.Length;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author uratamanabu
 * @version 1.0
 * @since 1.0
 */
@Target({ElementType.PARAMETER, ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = {})
@ReportAsSingleViolation
@Length(min = 1, max = 25)
public @interface GoodsNameField {

    String message() default "GoodsName is invalid";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
