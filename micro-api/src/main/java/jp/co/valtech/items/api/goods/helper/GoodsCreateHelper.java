package jp.co.valtech.items.api.goods.helper;

import jp.co.valtech.items.api.category.util.CategoryUtil;
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

/**
 * @author uratamanabu
 * @version 1.0
 * @since 1.0
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class GoodsCreateHelper {

    private final CategoryService cService;
    private final GoodsService gService;

    /**
     * 商品を1件登録します。
     *
     * @param request PostのRequestBody
     * @return ResponseEntity
     * @throws ConflictException 商品コード重複時
     * @throws NotFoundException カテゴリーコードが存在しない場合
     * @author uratamanabu
     * @since 1.0
     */
    public ResponseEntity<GoodsCreateResponse> execute(
            final GoodsCreateRequest request
    ) throws ConflictException, NotFoundException {

        GoodsReq goodsReq = request.getGoods();
        GoodsTbl entity = new GoodsTbl();
        GoodsUtil.requestToEntity(goodsReq, entity);
        String goodsCode = goodsReq.getGoodsCode();
        GoodsUtil.duplicationGoodsCodeCheck(gService, goodsCode);
        entity.setCode(goodsCode);
        CategoryTbl categoryTbl = CategoryUtil
                .findByCategoryCode(cService, goodsReq.getCategoryCode());
        entity.setCategoryId(categoryTbl.getId());
        create(entity);
        GoodsCreateResponse response = new GoodsCreateResponse();
        GoodsCreateResponse.Goods goods = response.new Goods();
        goods.setId(entity.getId());
        response.setGoods(goods);
        return new ResponseEntity<>(response, HttpStatus.CREATED);

    }

    private void create(final GoodsTbl entity) {
        gService.insert(entity);
    }
}