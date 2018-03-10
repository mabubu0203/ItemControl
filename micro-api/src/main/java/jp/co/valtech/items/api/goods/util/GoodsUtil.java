package jp.co.valtech.items.api.goods.util;

import jp.co.valtech.items.common.exception.ConflictException;
import jp.co.valtech.items.common.exception.NotFoundException;
import jp.co.valtech.items.interfaces.definitions.requests.GoodsReq;
import jp.co.valtech.items.interfaces.definitions.responses.GoodsRes;
import jp.co.valtech.items.rdb.domain.GoodsStatusTbl;
import jp.co.valtech.items.rdb.domain.GoodsTbl;
import jp.co.valtech.items.rdb.service.GoodsService;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Optional;

/**
 * @author uratamanabu
 * @version 1.0
 * @since 1.0
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GoodsUtil {

    /**
     * 商品コードの重複チェックを実施します。
     * 商品コードが存在する時、ConflictExceptionを発行します。
     *
     * @param service   サービス
     * @param goodsCode 商品コード
     * @throws ConflictException 重複時
     * @author uratamanabu
     * @since 1.0
     */
    public static void duplicationGoodsCodeCheck(
            final GoodsService service,
            final String goodsCode
    ) throws ConflictException {

        Optional<GoodsTbl> optionalCode = service.findByCode(goodsCode);
        if (optionalCode.isPresent()) {// goodsCode(unique制約)の存在チェック
            throw new ConflictException("goodsCode", "CODEが重複しています。");
        }

    }

    /**
     * 楽観排他を実施します。
     * 排他が取れない時、ConflictExceptionを発行します。
     *
     * @param entity  Entity
     * @param version 排他用
     * @throws ConflictException 排他エラー時
     * @author uratamanabu
     * @since 1.0
     */
    public static void exclusionCheck(
            final GoodsStatusTbl entity,
            final Integer version
    ) throws ConflictException {

        if (entity.getVersion().compareTo(version) != 0) {// 楽観排他
            throw new ConflictException("id", "排他エラー");
        }

    }

    /**
     * Entity → Responseに変換します。
     *
     * @param entity   Entity
     * @param goodsRes Response
     * @author uratamanabu
     * @since 1.0
     */
    public static void entityToResponse(
            final GoodsTbl entity,
            final GoodsRes goodsRes
    ) {

        goodsRes.setId(entity.getId());
        goodsRes.setName(entity.getName());
        goodsRes.setPrice(entity.getPrice());
        GoodsStatusTbl statusTbl = entity.getStatusTbl();
        goodsRes.setVersion(statusTbl.getVersion());

    }

    /**
     * 商品IDから商品を１件取得します。
     * 取得できない時、NotFoundExceptionを発行します。
     *
     * @param service サービス
     * @param id      商品識別key
     * @return
     * @throws NotFoundException 存在しない時
     * @author uratamanabu
     * @since 1.0
     */
    public static GoodsTbl findById(
            final GoodsService service,
            final Long id
    ) throws NotFoundException {

        Optional<GoodsTbl> optionalId = service.findById(id);
        return optionalId.orElseThrow(() -> new NotFoundException("id", "IDが存在しません。"));

    }

    /**
     * Request → Entityに変換します。
     *
     * @param entity   Entity
     * @param goodsReq Request
     * @author uratamanabu
     * @since 1.0
     */
    public static void requestToEntity(
            final GoodsReq goodsReq,
            final GoodsTbl entity
    ) {

        entity.setName(goodsReq.getName());
        entity.setPrice(goodsReq.getPrice());
        entity.setNote(goodsReq.getNote());

    }

}