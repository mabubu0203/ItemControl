package jp.co.valtech.items.interfaces.category.requests;

import jp.co.valtech.items.test.base.AbstractRequestTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import javax.validation.ConstraintViolation;
import java.util.HashSet;
import java.util.Set;

@DisplayName("カテゴリー検索")
public class CategorySearchRequestTest extends AbstractRequestTest {

    private CategorySearchRequest request;

    private Set<ConstraintViolation<CategorySearchRequest>> violationSet;

    @BeforeEach
    @Override
    public void before() {

        super.before();

        request = new CategorySearchRequest();

    }

    @AfterEach
    @Override
    public void after() {
        violationSet = new HashSet<>();
    }

}
