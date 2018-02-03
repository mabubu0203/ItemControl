package jp.co.valtech.items.api.goods.helper;

import jp.co.valtech.items.interfaces.definitions.responses.GoodsRes;
import jp.co.valtech.items.interfaces.goods.responses.GoodsFindResponse;
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
public class GoodsFindHelper {

    private final GoodsService service;

    public ResponseEntity<GoodsFindResponse> findGoods(final String id) {

        Optional<GoodsTbl> optionalId = service.findById(Long.valueOf(id));

        if (optionalId.isPresent()) {
            GoodsTbl entity = optionalId.get();
            GoodsRes goodsRes = new GoodsRes();
            goodsRes.setId(entity.getId());
            goodsRes.setCode(entity.getCode());
            goodsRes.setName(entity.getName());
            goodsRes.setPrice(entity.getPrice());
            goodsRes.setNote(entity.getNote());
            GoodsFindResponse response = new GoodsFindResponse();
            response.setGoods(goodsRes);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(
                    new GoodsFindResponse(), HttpStatus.NOT_FOUND);
        }
    }
}