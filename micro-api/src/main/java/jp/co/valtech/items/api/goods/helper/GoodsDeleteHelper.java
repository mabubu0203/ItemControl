package jp.co.valtech.items.api.goods.helper;

import jp.co.valtech.items.api.goods.util.GoodsUtil;
import jp.co.valtech.items.common.exception.ConflictException;
import jp.co.valtech.items.common.exception.NotFoundException;
import jp.co.valtech.items.rdb.domain.GoodsTbl;
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
public class GoodsDeleteHelper {

    private final GoodsService gService;

    /**
     * 商品を1件削除します。
     *
     * @param id      商品の識別key
     * @param version 排他用
     * @return ResponseEntity
     * @throws ConflictException 楽観排他エラー
     * @throws NotFoundException 商品が取得できない場合
     * @author uratamanabu
     * @since 1.0
     */
    public ResponseEntity execute(
            final Long id,
            final Integer version
    ) throws ConflictException, NotFoundException {

        GoodsTbl entity = GoodsUtil.findById(gService, id);
        GoodsUtil.exclusionCheck(entity.getStatusTbl(), version);
        delete(entity);
        return new ResponseEntity(HttpStatus.NO_CONTENT);

    }

    private void delete(GoodsTbl entity) throws NotFoundException {
        gService.delete(entity);
    }
}