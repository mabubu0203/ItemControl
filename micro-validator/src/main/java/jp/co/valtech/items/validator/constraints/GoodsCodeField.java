package jp.co.valtech.items.validator.constraints;

import org.hibernate.validator.constraints.Length;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;
import javax.validation.constraints.Pattern;
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
@Length(min = 1, max = 10)
@Pattern(regexp = "[a-zA-Z0-9]+")
public @interface GoodsCodeField {

    String message() default "GoodsCode is invalid";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
