package jp.co.valtech.items.api.goods.controller;

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
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
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

    }

    @FlywayTest
    @Test
    public void deleteGoodsTest() throws Exception {

    }

    @FlywayTest
    @Test
    public void getGoodsTest() throws Exception {
        String url = "http://localhost:" + port + "/goods/";

        ResponseEntity<GoodsGetResponse> entity = testRestTemplate.getForEntity(url, GoodsGetResponse.class);
        assertEquals(entity.getStatusCode(), HttpStatus.OK);
        assertTrue(entity.getBody().getGoodsList().size() > 0);
    }

    @FlywayTest
    @Test
    public void findGoodsTest() throws Exception {
        String id = "3";
        String url = "http://localhost:" + port + "/goods/" + id;

        ResponseEntity<GoodsFindResponse> entity = testRestTemplate.getForEntity(url, GoodsFindResponse.class);
        assertEquals(entity.getStatusCode(), HttpStatus.OK);
        assertNotNull(entity.getBody().getGoods());
    }

    @FlywayTest
    @Test
    public void updateGoodsTest() throws Exception {

    }

}
