package jp.co.valtech.items.api.goods.helper;

import jp.co.valtech.items.common.exception.ConflictException;
import jp.co.valtech.items.interfaces.definitions.requests.GoodsReq;
import jp.co.valtech.items.interfaces.goods.requests.GoodsCreateRequest;
import jp.co.valtech.items.interfaces.goods.responses.GoodsCreateResponse;
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
public class GoodsCreateHelper {

    private final GoodsService service;
    private final ModelMapper modelMapper;

    public ResponseEntity<GoodsCreateResponse> execute(
            final GoodsCreateRequest request
    ) throws ConflictException {

        GoodsReq goodsReq = request.getGoods();
        Optional<GoodsTbl> optionalCode = service.findByCode(goodsReq.getCode());
        if (optionalCode.isPresent()) {
            throw new ConflictException("code", "CODEが重複しています。");
        }

        GoodsTbl entity = modelMapper.map(goodsReq, GoodsTbl.class);
        create(entity);
        GoodsCreateResponse response = new GoodsCreateResponse();
        GoodsCreateResponse.Goods goods = response.new Goods();
        goods.setId(entity.getId());
        response.setGoods(goods);
        return new ResponseEntity<>(response, HttpStatus.CREATED);

    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    private void create(final GoodsTbl entity) {
        service.insert(entity);
    }
}