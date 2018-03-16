package jp.co.valtech.items.interfaces.goods.requests;

import jp.co.valtech.items.interfaces.definitions.requests.GoodsReq;
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

@DisplayName("商品更新")
public class GoodsUpdateRequestTest
        extends AbstractRequestTest {

    private GoodsUpdateRequest request;

    private Set<ConstraintViolation<GoodsUpdateRequest>> violationSet;

    @BeforeEach
    @Override
    public void before() {

        super.before();

        request = new GoodsUpdateRequest();
        GoodsReq goodsReq = new GoodsReq();
        goodsReq.setGoodsCode("ACCCA");
        goodsReq.setCategoryCode("CODEC1");
        goodsReq.setName("aaa");
        goodsReq.setPrice(1);
        goodsReq.setNote("aaa");
        request.setGoods(goodsReq);
        request.setVersion(0);

    }

    @AfterEach
    @Override
    public void after() {
        violationSet = new HashSet<>();
    }

    @Test
    void goodsCodeValidate() throws Exception {

        request.getGoods().setGoodsCode("");
        violationSet = validator.validate(request);
        Assertions.assertTrue(violationSet.size() > 0);

        request.getGoods().setGoodsCode(CreateString.loopStr("あ", 11));
        violationSet = validator.validate(request);
        Assertions.assertTrue(violationSet.size() > 0);

    }

    @Test
    void NameValidate() throws Exception {

        request.getGoods().setName("");
        violationSet = validator.validate(request);
        Assertions.assertTrue(violationSet.size() > 0);

        request.getGoods().setName(CreateString.loopStr("あ", 26));
        violationSet = validator.validate(request);
        Assertions.assertTrue(violationSet.size() > 0);

    }

    @Test
    void PriceValidate() throws Exception {

        request.getGoods().setPrice(null);
        violationSet = validator.validate(request);
        Assertions.assertTrue(violationSet.size() > 0);

        request.getGoods().setPrice(99999);
        violationSet = validator.validate(request);
        Assertions.assertTrue(violationSet.size() > 0);

    }

}
