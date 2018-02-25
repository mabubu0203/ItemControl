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

    public static void duplicationGoodsCodeCheck(
            final GoodsService service,
            final String goodsCode
    ) throws ConflictException {

        Optional<GoodsTbl> optionalCode = service.findByCode(goodsCode);
        if (optionalCode.isPresent()) {// goodsCode(unique制約)の存在チェック
            throw new ConflictException("goodsCode", "CODEが重複しています。");
        }

    }

    public static void exclusionCheck(
            final GoodsStatusTbl entity,
            final Integer version
    ) throws ConflictException {

        if (entity.getVersion().compareTo(version) != 0) {// 楽観排他
            throw new ConflictException("id", "排他エラー");
        }

    }

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

    public static GoodsTbl findById(
            final GoodsService service,
            final Long id
    ) throws NotFoundException {

        Optional<GoodsTbl> optionalId = service.findById(id);
        return optionalId.orElseThrow(() -> new NotFoundException("id", "IDが存在しません。"));

    }

    public static void requestToEntity(
            final GoodsReq goodsReq,
            final GoodsTbl entity
    ) {

        entity.setName(goodsReq.getName());
        entity.setPrice(goodsReq.getPrice());
        entity.setNote(goodsReq.getNote());

    }

}
