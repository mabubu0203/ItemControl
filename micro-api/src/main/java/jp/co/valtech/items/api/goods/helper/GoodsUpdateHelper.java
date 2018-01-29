package jp.co.valtech.items.api.goods.helper;

import jp.co.valtech.items.interfaces.definitions.requests.GoodsReq;
import jp.co.valtech.items.interfaces.definitions.responses.GoodsRes;
import jp.co.valtech.items.interfaces.goods.requests.GoodsUpdateRequest;
import jp.co.valtech.items.interfaces.goods.responses.GoodsCreateResponse;
import jp.co.valtech.items.interfaces.goods.responses.GoodsUpdateResponse;
import jp.co.valtech.items.rdb.domain.GoodsTbl;
import jp.co.valtech.items.rdb.service.GoodsService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class GoodsUpdateHelper {

    private final GoodsService service;

    public ResponseEntity<GoodsUpdateResponse> updateGoods(
            final String id,
            final GoodsUpdateRequest request) {

        GoodsTbl entity = new GoodsTbl();
        entity.setId(Long.valueOf(id));
        Optional<GoodsTbl> optionalId = service.selectId(entity);
        GoodsReq goodsReq = request.getGoods();
        entity.setCode(goodsReq.getCode());
        Optional<GoodsTbl> optionalCode = service.selectCode(entity);

        if (optionalId.isPresent() && !optionalCode.isPresent()) {
            entity = optionalId.get();
            entity.setName(goodsReq.getName());
            entity.setPrice(goodsReq.getPrice());
            entity.setNote(goodsReq.getNote());
            service.update(entity);
            GoodsRes goodsRes = new GoodsRes();
            goodsRes.setId(entity.getId());
            goodsRes.setCode(entity.getCode());
            goodsRes.setName(entity.getName());
            goodsRes.setPrice(entity.getPrice());
            goodsRes.setNote(entity.getNote());
            GoodsUpdateResponse response = new GoodsUpdateResponse();
            response.setGoods(goodsRes);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(
                    new GoodsUpdateResponse(), HttpStatus.NOT_FOUND);
        }
    }
}
