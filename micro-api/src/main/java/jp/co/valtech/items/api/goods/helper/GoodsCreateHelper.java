package jp.co.valtech.items.api.goods.helper;

import jp.co.valtech.items.api.goods.util.GoodsUtil;
import jp.co.valtech.items.common.exception.ConflictException;
import jp.co.valtech.items.common.exception.NotFoundException;
import jp.co.valtech.items.interfaces.definitions.requests.GoodsReq;
import jp.co.valtech.items.interfaces.goods.requests.GoodsCreateRequest;
import jp.co.valtech.items.interfaces.goods.responses.GoodsCreateResponse;
import jp.co.valtech.items.rdb.domain.CategoryTbl;
import jp.co.valtech.items.rdb.domain.GoodsTbl;
import jp.co.valtech.items.rdb.service.CategoryService;
import jp.co.valtech.items.rdb.service.GoodsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    private final CategoryService cService;
    private final GoodsService gService;

    public ResponseEntity<GoodsCreateResponse> execute(
            final GoodsCreateRequest request
    ) throws ConflictException, NotFoundException {

        GoodsReq goodsReq = request.getGoods();
        String goodsCode = goodsReq.getGoodsCode();
        GoodsUtil.duplicationGoodsCodeCheck(gService, goodsCode);
        String categoryCode = goodsReq.getCategoryCode();
        Optional<CategoryTbl> optionalCode = cService.findByCode(categoryCode);
        CategoryTbl categoryTbl = optionalCode.orElseThrow(() -> new NotFoundException("code", "CODEが存在しません。"));
        GoodsTbl entity = new GoodsTbl();
        GoodsUtil.requestToEntity(goodsReq, entity);
        entity.setCategory_id(categoryTbl.getId());
        entity.setCode(goodsCode);
        create(entity);
        GoodsCreateResponse response = new GoodsCreateResponse();
        GoodsCreateResponse.Goods goods = response.new Goods();
        goods.setId(entity.getId());
        response.setGoods(goods);
        return new ResponseEntity<>(response, HttpStatus.CREATED);

    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    private void create(final GoodsTbl entity) {
        gService.insert(entity);
    }
}