package jp.co.valtech.items.api.goods.helper;

import jp.co.valtech.items.interfaces.definitions.responses.GoodsRes;
import jp.co.valtech.items.interfaces.goods.responses.GoodsGetResponse;
import jp.co.valtech.items.rdb.domain.GoodsTbl;
import jp.co.valtech.items.rdb.service.GoodsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class GoodsGetHelper {

    private final GoodsService service;

    public ResponseEntity<GoodsGetResponse> execute() {

        List<GoodsTbl> entities = service.findAll();
        List<GoodsRes> goodsList = new ArrayList<>();
        for (GoodsTbl entity : entities) {
            GoodsRes goodsRes = new GoodsRes();
            BeanUtils.copyProperties(goodsRes,entity);
            goodsList.add(goodsRes);
        }
        GoodsGetResponse response = new GoodsGetResponse();
        response.setGoodsList(goodsList);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
