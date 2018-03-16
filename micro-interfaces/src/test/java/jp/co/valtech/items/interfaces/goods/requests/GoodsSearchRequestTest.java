package jp.co.valtech.items.interfaces.goods.requests;

import jp.co.valtech.items.test.base.AbstractRequestTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import javax.validation.ConstraintViolation;
import java.util.HashSet;
import java.util.Set;

@DisplayName("商品検索")
public class GoodsSearchRequestTest
        extends AbstractRequestTest {

    private GoodsSearchRequest request;

    private Set<ConstraintViolation<GoodsSearchRequest>> violationSet;

    @BeforeEach
    @Override
    public void before() {

        super.before();

        request = new GoodsSearchRequest();

    }

    @AfterEach
    @Override
    public void after() {
        violationSet = new HashSet<>();
    }
}
