package jp.co.valtech.items.api.goods.controller;

import jp.co.valtech.items.interfaces.definitions.requests.GoodsReq;
import jp.co.valtech.items.interfaces.definitions.responses.GoodsRes;
import jp.co.valtech.items.interfaces.goods.requests.GoodsCreateRequest;
import jp.co.valtech.items.interfaces.goods.requests.GoodsUpdateRequest;
import jp.co.valtech.items.interfaces.goods.responses.GoodsCreateResponse;
import jp.co.valtech.items.interfaces.goods.responses.GoodsFindResponse;
import jp.co.valtech.items.interfaces.goods.responses.GoodsGetResponse;
import jp.co.valtech.items.interfaces.goods.responses.GoodsUpdateResponse;
import org.flywaydb.test.FlywayTestExecutionListener;
import org.flywaydb.test.annotation.FlywayTest;
import org.junit.jupiter.api.Assertions;
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

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Nested
    @DisplayName("登録")
    class createGoodsTest {
        @FlywayTest
        @Test
        void success() throws Exception {
            String url = "http://localhost:" + port + "/goods/";
            GoodsCreateRequest request = new GoodsCreateRequest();
            GoodsReq goodsReq = new GoodsReq();
            goodsReq.setGoodsCode("AAAAA");
            goodsReq.setName("aaa");
            goodsReq.setPrice(1);
            goodsReq.setNote("aaa");
            request.setGoods(goodsReq);
            ResponseEntity<GoodsCreateResponse> entity = testRestTemplate.postForEntity(url, request, GoodsCreateResponse.class);
            Assertions.assertEquals(entity.getStatusCode(), HttpStatus.CREATED);
            Assertions.assertNotNull(entity.getBody().getGoods().getId());
        }

    }

    @Nested
    @DisplayName("削除")
    class deleteGoodsTest {
        @FlywayTest
        @Test
        void success() throws Exception {
            String id = "4";
            String url = "http://localhost:" + port + "/goods/{id}";

            Map<String, String> uriParams = new HashMap<String, String>();
            uriParams.put("id", id);

            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
                    .queryParam("version", 0);

            ResponseEntity<Void> entity = testRestTemplate
                    .exchange(
                            builder.buildAndExpand(uriParams).toUri(),
                            HttpMethod.DELETE,
                            HttpEntity.EMPTY,
                            Void.class);
            Assertions.assertEquals(entity.getStatusCode(), HttpStatus.NO_CONTENT);

        }
    }

    @Nested
    @DisplayName("全件取得")
    class getGoodsTest {
        @FlywayTest
        @Test
        void success() throws Exception {
            String url = "http://localhost:" + port + "/goods/all";

            ResponseEntity<GoodsGetResponse> entity = testRestTemplate.getForEntity(url, GoodsGetResponse.class);
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
        void findGoodsTest() throws Exception {
            String id = "1";
            String url = "http://localhost:" + port + "/goods/{id}";

            ResponseEntity<GoodsFindResponse> entity = testRestTemplate.getForEntity(url, GoodsFindResponse.class, id);
            Assertions.assertEquals(entity.getStatusCode(), HttpStatus.OK);
            GoodsFindResponse response = entity.getBody();
            Assertions.assertNotNull(response);
            GoodsRes goods = response.getGoods();
            Assertions.assertEquals(goods.getGoodsCode(), "CODE1");
        }
    }

    @Nested
    @DisplayName("更新")
    class updateGoodsTest {
        @FlywayTest
        @Test
        void updateGoodsTest() throws Exception {
            String id = "3";
            String url = "http://localhost:" + port + "/goods/{id}";

            Map<String, String> uriParams = new HashMap<>();
            uriParams.put("id", id);

            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);

            GoodsUpdateRequest request = new GoodsUpdateRequest();
            GoodsReq goodsReq = new GoodsReq();
            goodsReq.setGoodsCode("ACCCA");
            goodsReq.setName("aaa");
            goodsReq.setPrice(1);
            goodsReq.setNote("aaa");
            request.setGoods(goodsReq);
            request.setVersion(0);

            URI uri = builder.buildAndExpand(uriParams).toUri();
            RequestEntity<GoodsUpdateRequest> requestEntity = RequestEntity.put(uri).body(request);

            ResponseEntity<GoodsUpdateResponse> entity = testRestTemplate
                    .exchange(
                            uri,
                            HttpMethod.PUT,
                            requestEntity,
                            GoodsUpdateResponse.class);

            Assertions.assertEquals(entity.getStatusCode(), HttpStatus.OK);
            Assertions.assertNotNull(entity.getBody().getGoods().getId());
        }
    }

}
