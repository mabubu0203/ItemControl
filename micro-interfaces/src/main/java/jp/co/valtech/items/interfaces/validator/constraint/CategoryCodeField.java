package jp.co.valtech.items.interfaces.validator.constraint;

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

@Target({ElementType.PARAMETER, ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = {})
@ReportAsSingleViolation
@Length(min = 1, max = 10)
@Pattern(regexp = "[a-zA-Z0-9]+")
public @interface CategoryCodeField {

    String message() default "CategoryCode is invalid";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}