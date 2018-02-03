package jp.co.valtech.items.api.goods.helper;

import jp.co.valtech.items.interfaces.definitions.requests.GoodsReq;
import jp.co.valtech.items.interfaces.goods.requests.GoodsUpdateRequest;
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

        Optional<GoodsTbl> optionalId = service.findById(Long.valueOf(id));
        GoodsReq goodsReq = request.getGoods();

        Optional<GoodsTbl> optionalCode = service.findByCode(goodsReq.getCode());

        if (optionalId.isPresent() && !optionalCode.isPresent()) {
            GoodsTbl entity = optionalId.get();
            if (entity.getVersion() == request.getVersion()) {
                entity.setName(goodsReq.getName());
                entity.setPrice(goodsReq.getPrice());
                entity.setNote(goodsReq.getNote());
                service.update();
                GoodsUpdateResponse response = new GoodsUpdateResponse();
                GoodsUpdateResponse.Goods goods = response.new Goods();
                goods.setId(entity.getId());
                response.setGoods(goods);
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(
                        new GoodsUpdateResponse(), HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>(
                    new GoodsUpdateResponse(), HttpStatus.NOT_FOUND);
        }
    }
}
