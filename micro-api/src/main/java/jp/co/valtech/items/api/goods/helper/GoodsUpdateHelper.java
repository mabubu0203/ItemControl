package jp.co.valtech.items.api.goods.helper;

import jp.co.valtech.items.api.goods.util.GoodsUtil;
import jp.co.valtech.items.common.exception.ConflictException;
import jp.co.valtech.items.common.exception.NotFoundException;
import jp.co.valtech.items.interfaces.definitions.requests.GoodsReq;
import jp.co.valtech.items.interfaces.goods.requests.GoodsUpdateRequest;
import jp.co.valtech.items.interfaces.goods.responses.GoodsUpdateResponse;
import jp.co.valtech.items.rdb.domain.GoodsTbl;
import jp.co.valtech.items.rdb.service.GoodsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class GoodsUpdateHelper {

    private final GoodsService service;
    private final ModelMapper modelMapper;

    public ResponseEntity<GoodsUpdateResponse> execute(
            final long id,
            final GoodsUpdateRequest request
    ) throws ConflictException, NotFoundException {

        Optional<GoodsTbl> optionalId = service.findById(id);
        GoodsTbl entity = optionalId
                .orElseThrow(() -> new NotFoundException("id", "IDが存在しません。"));
        GoodsReq goodsReq = request.getGoods();
        if (entity.getStatusTbl().getVersion() != request.getVersion()) {// 楽観排他
            throw new ConflictException("id", "排他エラー");
        }
        String goodsCode = goodsReq.getGoodsCode();
        if (!entity.getCode().equals(goodsCode)) {// Codeの更新あり
            GoodsUtil.codeCheck(service, goodsCode);
        }
        modelMapper.map(goodsReq, entity);
        entity.setCode(goodsCode);
        update(entity);
        GoodsUpdateResponse response = new GoodsUpdateResponse();
        GoodsUpdateResponse.Goods goods = response.new Goods();
        goods.setId(entity.getId());
        response.setGoods(goods);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    private void update(final GoodsTbl entity) {
        service.update(entity);
    }

}
