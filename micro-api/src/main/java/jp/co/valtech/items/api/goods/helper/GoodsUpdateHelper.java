package jp.co.valtech.items.api.goods.helper;

import jp.co.valtech.items.common.exception.ConflictException;
import jp.co.valtech.items.common.exception.NotFoundException;
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
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class GoodsUpdateHelper {

    private final GoodsService service;

    public ResponseEntity<GoodsUpdateResponse> execute(
            final String id,
            final GoodsUpdateRequest request
    ) throws ConflictException, NotFoundException {

        Optional<GoodsTbl> optionalId = service.findById(Long.valueOf(id));
        if (optionalId.isPresent()) {// idの存在チェック
            GoodsTbl entity = optionalId.get();
            GoodsReq goodsReq = request.getGoods();
            if (entity.getVersion() == request.getVersion()) {// 楽観排他

                if (goodsReq.getCode().equals(entity.getCode())) {// Codeの更新なし
                    update(entity, goodsReq);
                    GoodsUpdateResponse response = new GoodsUpdateResponse();
                    GoodsUpdateResponse.Goods goods = response.new Goods();
                    goods.setId(entity.getId());
                    response.setGoods(goods);
                    return new ResponseEntity<>(response, HttpStatus.OK);
                }
                Optional<GoodsTbl> optionalCode = service.findByCode(goodsReq.getCode());
                if (optionalCode.isPresent()) {// code(unique制約)の存在チェック
                    throw new ConflictException("code", "CODEが重複しています。");
                } else {
                    update(entity, goodsReq);
                    GoodsUpdateResponse response = new GoodsUpdateResponse();
                    GoodsUpdateResponse.Goods goods = response.new Goods();
                    goods.setId(entity.getId());
                    response.setGoods(goods);
                    return new ResponseEntity<>(response, HttpStatus.OK);
                }
            } else {
                throw new ConflictException("id", "排他エラー");
            }
        } else {
            throw new NotFoundException("id", "IDが存在しません。");
        }
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    private void update(
            final GoodsTbl entity,
            final GoodsReq goodsReq
    ) {
        entity.setCode(goodsReq.getCode());
        entity.setName(goodsReq.getName());
        entity.setPrice(goodsReq.getPrice());
        entity.setNote(goodsReq.getNote());
        service.update(entity);
    }
}
