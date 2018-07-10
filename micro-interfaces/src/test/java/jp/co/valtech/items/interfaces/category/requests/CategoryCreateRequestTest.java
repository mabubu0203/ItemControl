package jp.co.valtech.items.interfaces.category.requests;

import jp.co.valtech.items.interfaces.definitions.requests.CategoryReq;
import jp.co.valtech.items.test.base.AbstractRequestTest;
import jp.co.valtech.items.test.function.CreateString;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import java.util.HashSet;
import java.util.Set;

@DisplayName("カテゴリー登録")
public class CategoryCreateRequestTest extends AbstractRequestTest {

    private CategoryCreateRequest request;

    private Set<ConstraintViolation<CategoryCreateRequest>> violationSet;

    @BeforeEach
    @Override
    public void before() {

        super.before();

        request = new CategoryCreateRequest();
        CategoryReq categoryReq = new CategoryReq();
        categoryReq.setCategoryCode("CODEC1");
        categoryReq.setName("aaa");
        categoryReq.setNote("aaa");
        request.setCategory(categoryReq);

    }

    @AfterEach
    @Override
    public void after() {
        violationSet = new HashSet<>();
    }

    @Test
    void categoryCodeValidate() throws Exception {

        request.getCategory().setCategoryCode("");
        violationSet = validator.validate(request);
        Assertions.assertTrue(violationSet.size() > 0);

        request.getCategory().setCategoryCode(CreateString.loopStr("あ", 11));
        violationSet = validator.validate(request);
        Assertions.assertTrue(violationSet.size() > 0);

    }

    @Test
    void nameValidate() throws Exception {

        request.getCategory().setName("");
        violationSet = validator.validate(request);
        Assertions.assertTrue(violationSet.size() > 0);

        request.getCategory().setName(CreateString.loopStr("あ", 26));
        violationSet = validator.validate(request);
        Assertions.assertTrue(violationSet.size() > 0);

    }

    @Test
    void noteValidate() throws Exception {

        request.getCategory().setNote(CreateString.loopStr("あ", 65));
        violationSet = validator.validate(request);
        Assertions.assertTrue(violationSet.size() > 0);

    }

}
