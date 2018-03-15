package jp.co.valtech.items.interfaces.goods.requests;

import jp.co.valtech.items.interfaces.definitions.requests.GoodsReq;
import jp.co.valtech.items.test.function.CreateString;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.HashSet;
import java.util.Set;

class GoodsCreateRequestTest {

    private Validator validator;

    @BeforeEach
    public void setUp() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Nested
    @DisplayName("登録")
    class createGoodsTest {

        private GoodsCreateRequest request;

        private Set<ConstraintViolation<GoodsCreateRequest>> violationSet;

        @BeforeEach
        void before() {

            request = new GoodsCreateRequest();
            GoodsReq goodsReq = new GoodsReq();
            goodsReq.setGoodsCode("AAAAA");
            goodsReq.setCategoryCode("CODEC1");
            goodsReq.setName("aaa");
            goodsReq.setPrice(1);
            goodsReq.setNote("aaa");
            request.setGoods(goodsReq);

        }

        @AfterEach
        void after() {
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
}
