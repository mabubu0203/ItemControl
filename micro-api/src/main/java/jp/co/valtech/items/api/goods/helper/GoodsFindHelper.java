package jp.co.valtech.items.api.goods.helper;

import jp.co.valtech.items.api.goods.util.GoodsUtil;
import jp.co.valtech.items.common.exception.NotFoundException;
import jp.co.valtech.items.interfaces.definitions.responses.GoodsRes;
import jp.co.valtech.items.interfaces.goods.responses.GoodsFindResponse;
import jp.co.valtech.items.rdb.domain.GoodsTbl;
import jp.co.valtech.items.rdb.service.GoodsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class GoodsFindHelper {

    private final GoodsService gService;
    private final ModelMapper modelMapper;

    public ResponseEntity<GoodsFindResponse> execute(
            final long id
    ) throws NotFoundException {

        GoodsTbl entity = GoodsUtil.findById(gService, id);
        GoodsRes goodsRes = new GoodsRes();
        GoodsUtil.entityToResponse(modelMapper, entity, goodsRes);
        GoodsFindResponse response = new GoodsFindResponse();
        response.setGoods(goodsRes);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }
}
