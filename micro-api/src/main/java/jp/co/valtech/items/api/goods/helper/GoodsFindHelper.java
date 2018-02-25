package jp.co.valtech.items.api.goods.helper;

import jp.co.valtech.items.api.goods.util.GoodsUtil;
import jp.co.valtech.items.common.exception.NotFoundException;
import jp.co.valtech.items.interfaces.goods.responses.GoodsFindResponse;
import jp.co.valtech.items.rdb.domain.GoodsStatusTbl;
import jp.co.valtech.items.rdb.domain.GoodsTbl;
import jp.co.valtech.items.rdb.service.GoodsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class GoodsFindHelper {

    private final GoodsService gService;

    public ResponseEntity<GoodsFindResponse> execute(
            final Long id
    ) throws NotFoundException {

        GoodsTbl entity = GoodsUtil.findById(gService, id);
        GoodsFindResponse response = new GoodsFindResponse();
        GoodsFindResponse.GoodsDetail goods = response.new GoodsDetail();
        entityToResponse(entity, goods);
        response.setGoods(goods);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    private void entityToResponse(
            final GoodsTbl entity,
            final GoodsFindResponse.GoodsDetail goods
    ) {

        GoodsUtil.entityToResponse(entity, goods);
        goods.setGoodsCode(entity.getCode());
        goods.setCategoryCode("");
        goods.setNote(entity.getNote());
        GoodsStatusTbl statusTbl = entity.getStatusTbl();
        goods.setCreateDatetime(statusTbl.getCreateDatetime());
        goods.setUpdateDatetime(statusTbl.getUpdateDatetime());

    }
}
