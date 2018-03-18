package jp.co.valtech.items.api.goods.helper;

import jp.co.valtech.items.api.category.util.CategoryUtil;
import jp.co.valtech.items.api.goods.util.GoodsUtil;
import jp.co.valtech.items.common.exception.ConflictException;
import jp.co.valtech.items.common.exception.NotFoundException;
import jp.co.valtech.items.interfaces.definitions.requests.GoodsReq;
import jp.co.valtech.items.interfaces.goods.requests.GoodsUpdateRequest;
import jp.co.valtech.items.interfaces.goods.responses.GoodsUpdateResponse;
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
public class GoodsUpdateHelper {

    private final CategoryService cService;
    private final GoodsService gService;

    /**
     * 商品を1件更新します。
     *
     * @param id      商品の識別key
     * @param request PutのRequestBody
     * @return ResponseEntity
     * @throws ConflictException 更新先商品コードにおいて、重複エラー発生時
     * @throws NotFoundException カテゴリーコードが存在しない場合
     * @author uratamanabu
     * @since 1.0
     */
    public ResponseEntity<GoodsUpdateResponse> execute(
            final Long id,
            final GoodsUpdateRequest request
    ) throws ConflictException, NotFoundException {

        GoodsReq goodsReq = request.getGoods();
        GoodsTbl entity = GoodsUtil.findById(gService, id);
        Integer version = request.getVersion();
        GoodsUtil.exclusionCheck(entity.getStatusTbl(), version);
        GoodsUtil.requestToEntity(goodsReq, entity);
        String goodsCode = goodsReq.getGoodsCode();
        if (!entity.getCode().equals(goodsCode)) {// Codeの更新あり
            GoodsUtil.duplicationGoodsCodeCheck(gService, goodsCode);
        }
        entity.setCode(goodsCode);
        CategoryTbl categoryTbl = CategoryUtil
                .findByCategoryCode(cService, goodsReq.getCategoryCode());
        entity.setCategoryId(categoryTbl.getId());
        update(entity);
        GoodsUpdateResponse response = new GoodsUpdateResponse();
        GoodsUpdateResponse.Goods goods = response.new Goods();
        goods.setId(entity.getId());
        response.setGoods(goods);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    private void update(final GoodsTbl entity) throws NotFoundException {
        gService.update(entity);
    }

}