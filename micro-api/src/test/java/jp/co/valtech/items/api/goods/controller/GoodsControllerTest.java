package jp.co.valtech.items.api.goods.controller;

import jp.co.valtech.items.interfaces.definitions.requests.GoodsReq;
import jp.co.valtech.items.interfaces.definitions.responses.GoodsRes;
import jp.co.valtech.items.interfaces.goods.requests.GoodsCreateRequest;
import jp.co.valtech.items.interfaces.goods.requests.GoodsUpdateRequest;
import jp.co.valtech.items.interfaces.goods.responses.GoodsCreateResponse;
import jp.co.valtech.items.interfaces.goods.responses.GoodsFindResponse;
import jp.co.valtech.items.interfaces.goods.responses.GoodsGetResponse;
import jp.co.valtech.items.interfaces.goods.responses.GoodsUpdateResponse;
import jp.co.valtech.items.test.function.CreateString;
import org.flywaydb.test.FlywayTestExecutionListener;
import org.flywaydb.test.annotation.FlywayTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.net.URI;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles(profiles = {"test"})
@TestExecutionListeners({
        DependencyInjectionTestExecutionListener.class,
        FlywayTestExecutionListener.class})
@FlywayTest(locationsForMigrate = {"/db/migration/h2"})
class GoodsControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @LocalServerPort
    private int port;

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

        @FlywayTest
        @Test
        void goodsCodeValidate() throws Exception {

            request.getGoods().setGoodsCode("");
            violationSet = validator.validate(request);
            Assertions.assertTrue(violationSet.size() > 0);

            request.getGoods().setGoodsCode(CreateString.loopStr("あ", 11));
            violationSet = validator.validate(request);
            Assertions.assertTrue(violationSet.size() > 0);

        }

        @FlywayTest
        @Test
        void nameValidate() throws Exception {

            request.getGoods().setName("");
            violationSet = validator.validate(request);
            Assertions.assertTrue(violationSet.size() > 0);

            request.getGoods().setName(CreateString.loopStr("あ", 26));
            violationSet = validator.validate(request);
            Assertions.assertTrue(violationSet.size() > 0);

        }

        @FlywayTest
        @Test
        void priceValidate() throws Exception {

            request.getGoods().setPrice(null);
            violationSet = validator.validate(request);
            Assertions.assertTrue(violationSet.size() > 0);

            request.getGoods().setPrice(99999);
            violationSet = validator.validate(request);
            Assertions.assertTrue(violationSet.size() > 0);

        }

        @FlywayTest
        @Test
        void CreateSuccess() throws Exception {

            violationSet = validator.validate(request);
            Assertions.assertTrue(violationSet.size() == 0);
            ResponseEntity<GoodsCreateResponse> entity = post(request);
            Assertions.assertEquals(entity.getStatusCode(), HttpStatus.CREATED);
            Assertions.assertNotNull(entity.getBody().getGoods().getId());

        }

        private ResponseEntity<GoodsCreateResponse> post(GoodsCreateRequest request) {

            String url = "http://localhost:" + port + "/goods/";
            return testRestTemplate.postForEntity(url, request, GoodsCreateResponse.class);

        }

    }

    @Nested
    @DisplayName("削除")
    class deleteGoodsTest {

        @FlywayTest
        @Test
        void versionValidate() throws Exception {
            String id = "4";
            Integer version = null;
            ResponseEntity<Void> entity = delete(id, version);
            Assertions.assertEquals(entity.getStatusCode(), HttpStatus.CONFLICT);
        }

        @FlywayTest
        @Test
        void DeleteNotFound() throws Exception {
            String id = "90";
            Integer version = 0;
            ResponseEntity<Void> entity = delete(id, version);
            Assertions.assertEquals(entity.getStatusCode(), HttpStatus.NOT_FOUND);
        }

        @FlywayTest
        @Test
        void DeleteConflict() throws Exception {
            String id = "4";
            Integer version = 23;
            ResponseEntity<Void> entity = delete(id, version);
            Assertions.assertEquals(entity.getStatusCode(), HttpStatus.CONFLICT);
        }

        @FlywayTest
        @Test
        void DeleteSuccess() throws Exception {
            String id = "5";
            Integer version = 0;
            ResponseEntity<Void> entity = delete(id, version);
            Assertions.assertEquals(entity.getStatusCode(), HttpStatus.NO_CONTENT);
        }

        private ResponseEntity<Void> delete(String id, Integer version) {

            String url = "http://localhost:" + port + "/goods/{id}";
            UriComponentsBuilder builder = UriComponentsBuilder
                    .fromHttpUrl(url)
                    .queryParam("version", version);
            Map<String, String> uriParams = new HashMap<>();
            uriParams.put("id", id);
            return testRestTemplate
                    .exchange(
                            builder.buildAndExpand(uriParams).toUri(),
                            HttpMethod.DELETE,
                            HttpEntity.EMPTY,
                            Void.class);

        }

    }

    @Nested
    @DisplayName("全件取得")
    class getGoodsTest {
        @FlywayTest
        @Test
        void GetSuccess() throws Exception {
            String url = "http://localhost:" + port + "/goods/all";
            ResponseEntity<GoodsGetResponse> entity = testRestTemplate
                    .getForEntity(url, GoodsGetResponse.class);
            Assertions.assertEquals(entity.getStatusCode(), HttpStatus.OK);
            GoodsGetResponse response = entity.getBody();
            Assertions.assertNotNull(response);
            List<GoodsRes> goodsList = response.getGoodsList();
            Assertions.assertTrue(goodsList.size() > 0);
        }

    }

    @Nested
    @DisplayName("1件取得")
    class findGoodsTest {

        @FlywayTest
        @Test
        void FindFail() throws Exception {
            String id = "111111";
            ResponseEntity<GoodsFindResponse> entity = get(id);
            Assertions.assertEquals(entity.getStatusCode(), HttpStatus.NOT_FOUND);
        }

        @FlywayTest
        @Test
        void FindSuccess() throws Exception {
            String id = "1";
            ResponseEntity<GoodsFindResponse> entity = get(id);
            Assertions.assertEquals(entity.getStatusCode(), HttpStatus.OK);
            GoodsFindResponse response = entity.getBody();
            Assertions.assertNotNull(response);
        }

        private ResponseEntity<GoodsFindResponse> get(String id) {

            String url = "http://localhost:" + port + "/goods/{id}";
            return testRestTemplate.getForEntity(url, GoodsFindResponse.class, id);

        }

    }

    @Nested
    @DisplayName("更新")
    class updateGoodsTest {

        private GoodsUpdateRequest request;

        @BeforeEach
        void before() {

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

        @FlywayTest
        @Test
        void UpdateSuccess() throws Exception {
            String id = "3";
            ResponseEntity<GoodsUpdateResponse> entity = update(id, request);
            Assertions.assertEquals(entity.getStatusCode(), HttpStatus.OK);
            Assertions.assertNotNull(entity.getBody().getGoods().getId());
        }

        private ResponseEntity<GoodsUpdateResponse> update(String id, GoodsUpdateRequest request) {

            String url = "http://localhost:" + port + "/goods/{id}";
            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
            Map<String, String> uriParams = new HashMap<>();
            uriParams.put("id", id);
            URI uri = builder.buildAndExpand(uriParams).toUri();
            RequestEntity<GoodsUpdateRequest> requestEntity = RequestEntity.put(uri).body(request);
            return testRestTemplate
                    .exchange(
                            uri,
                            HttpMethod.PUT,
                            requestEntity,
                            GoodsUpdateResponse.class);

        }

    }

}
