package jp.co.valtech.items.test.base;

import javax.validation.Validation;
import javax.validation.Validator;

public class AbstractRequestTest {

    protected Validator validator;

    public void before() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    public void after() {
    }

}
