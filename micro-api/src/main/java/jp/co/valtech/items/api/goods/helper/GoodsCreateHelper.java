package jp.co.valtech.items.api.goods.helper;

import jp.co.valtech.items.interfaces.definitions.requests.GoodsReq;
import jp.co.valtech.items.interfaces.definitions.responses.GoodsRes;
import jp.co.valtech.items.interfaces.goods.requests.GoodsCreateRequest;
import jp.co.valtech.items.interfaces.goods.responses.GoodsCreateResponse;
import jp.co.valtech.items.rdb.domain.GoodsTbl;
import jp.co.valtech.items.rdb.service.GoodsService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class GoodsCreateHelper {

    private final GoodsService service;

    public ResponseEntity<GoodsCreateResponse> createGoods(final GoodsCreateRequest request) {
        GoodsReq goodsReq = request.getGoods();
        Optional<GoodsTbl> optionalCode = service.findByCode(goodsReq.getCode());
        if (optionalCode.isPresent()) {
            return new ResponseEntity<>(new GoodsCreateResponse(), HttpStatus.CONFLICT);
        } else {
            GoodsTbl entity = new GoodsTbl();
            entity.setCode(goodsReq.getCode());
            entity.setName(goodsReq.getName());
            entity.setPrice(goodsReq.getPrice());
            entity.setNote(goodsReq.getNote());

            service.insert(entity);

            GoodsRes goodsRes = new GoodsRes();
            goodsRes.setId(entity.getId());
            goodsRes.setCode(entity.getCode());
            goodsRes.setName(entity.getName());
            goodsRes.setPrice(entity.getPrice());
            goodsRes.setNote(entity.getNote());
            GoodsCreateResponse response = new GoodsCreateResponse();
            response.setGoods(goodsRes);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }
    }
}