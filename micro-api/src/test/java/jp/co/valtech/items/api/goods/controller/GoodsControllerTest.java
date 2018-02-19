package jp.co.valtech.items.api.goods.controller;

import jp.co.valtech.items.interfaces.definitions.requests.GoodsReq;
import jp.co.valtech.items.interfaces.definitions.responses.GoodsRes;
import jp.co.valtech.items.interfaces.goods.requests.GoodsCreateRequest;
import jp.co.valtech.items.interfaces.goods.requests.GoodsUpdateRequest;
import jp.co.valtech.items.interfaces.goods.responses.GoodsCreateResponse;
import jp.co.valtech.items.interfaces.goods.responses.GoodsFindResponse;
import jp.co.valtech.items.interfaces.goods.responses.GoodsGetResponse;
import org.flywaydb.test.FlywayTestExecutionListener;
import org.flywaydb.test.annotation.FlywayTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles(profiles = {"test"})
@TestExecutionListeners({
        DependencyInjectionTestExecutionListener.class,
        FlywayTestExecutionListener.class})
@FlywayTest(locationsForMigrate = {"/db/migration/h2"})
public class GoodsControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @LocalServerPort
    private int port;

    @FlywayTest
    @Test
    public void createGoodsTest() throws Exception {
        String url = "http://localhost:" + port + "/goods/";
        GoodsCreateRequest request = new GoodsCreateRequest();
        GoodsReq goodsReq = new GoodsReq();
        goodsReq.setCode("AAAAA");
        goodsReq.setName("aaa");
        goodsReq.setPrice(1);
        goodsReq.setNote("aaa");
        request.setGoods(goodsReq);
        ResponseEntity<GoodsCreateResponse> entity = testRestTemplate.postForEntity(url, request, GoodsCreateResponse.class);
        assertEquals(entity.getStatusCode(), HttpStatus.CREATED);
        assertNotNull(entity.getBody().getGoods().getId());
    }

    @FlywayTest
    @Test
    public void deleteGoodsTest() throws Exception {
        String id = "4";
        String url = "http://localhost:" + port + "/goods/" + id;
    }

    @FlywayTest
    @Test
    public void getGoodsTest() throws Exception {
        String url = "http://localhost:" + port + "/goods/all";

        ResponseEntity<GoodsGetResponse> entity = testRestTemplate.getForEntity(url, GoodsGetResponse.class);
        assertEquals(entity.getStatusCode(), HttpStatus.OK);
        GoodsGetResponse response = entity.getBody();
        assertNotNull(response);
        List<GoodsRes> goodsList = response.getGoodsList();
        assertTrue(goodsList.size() > 0);
    }

    @FlywayTest
    @Test
    public void findGoodsTest() throws Exception {
        String id = "1";
        String url = "http://localhost:" + port + "/goods/" + id;

        ResponseEntity<GoodsFindResponse> entity = testRestTemplate.getForEntity(url, GoodsFindResponse.class);
        assertEquals(entity.getStatusCode(), HttpStatus.OK);
        GoodsFindResponse response = entity.getBody();
        assertNotNull(response);
        GoodsRes goods = response.getGoods();
        assertEquals(goods.getCode(), "CODE1");
    }

    @FlywayTest
    @Test
    public void updateGoodsTest() throws Exception {
        String url = "http://localhost:" + port + "/goods/" + 3;
        GoodsUpdateRequest request = new GoodsUpdateRequest();
        GoodsReq goodsReq = new GoodsReq();
        goodsReq.setCode("CODE3");
        goodsReq.setName("");
        goodsReq.setPrice(1);
        goodsReq.setNote("");
        request.setGoods(goodsReq);
        request.setVersion(1);
        testRestTemplate.put(url, request, GoodsCreateResponse.class);
    }

}
