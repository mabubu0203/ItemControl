package jp.co.valtech.items.interfaces.validator.constraint;

import org.hibernate.validator.constraints.Length;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;
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
@Length(min = 1, max = 64)
public @interface CategoryNoteField {

    String message() default "CategoryNote is invalid";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}