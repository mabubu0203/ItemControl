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
import org.modelmapper.ModelMapper;

import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GoodsUtil {

    public static void duplicationGoodsCodeCheck(
            final GoodsService service,
            final String goodsCode
    ) throws ConflictException {

        Optional<GoodsTbl> optionalCode = service.findByCode(goodsCode);
        if (optionalCode.isPresent()) {// categoryCode(unique制約)の存在チェック
            throw new ConflictException("categoryCode", "CODEが重複しています。");
        }

    }

    public static void exclusionCheck(
            final GoodsStatusTbl entity,
            final int version
    ) throws ConflictException {

        if (entity.getVersion() != version) {// 楽観排他
            throw new ConflictException("id", "排他エラー");
        }

    }

    public static GoodsRes entityToResponse(
            final ModelMapper modelMapper,
            final GoodsTbl entity,
            final GoodsRes goodsRes
    ) {

        goodsRes.setGoodsCode(entity.getCode());
        goodsRes.setName(entity.getName());
        goodsRes.setPrice(entity.getPrice());
        goodsRes.setNote(entity.getNote());

        GoodsStatusTbl statusTbl = entity.getStatusTbl();
        modelMapper.map(statusTbl, goodsRes);
        return goodsRes;

    }

    public static GoodsTbl findById(
            final GoodsService service,
            final long id
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
