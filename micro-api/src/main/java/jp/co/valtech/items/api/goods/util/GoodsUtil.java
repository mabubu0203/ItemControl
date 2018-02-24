package jp.co.valtech.items.api.goods.util;

import jp.co.valtech.items.common.exception.ConflictException;
import jp.co.valtech.items.interfaces.definitions.responses.GoodsRes;
import jp.co.valtech.items.rdb.domain.GoodsTbl;
import jp.co.valtech.items.rdb.service.GoodsService;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GoodsUtil {

    public static void codeCheck(
            final GoodsService service,
            final String goodsCode
    ) throws ConflictException {

        Optional<GoodsTbl> optionalCode = service.findByCode(goodsCode);
        if (optionalCode.isPresent()) {// categoryCode(unique制約)の存在チェック
            throw new ConflictException("categoryCode", "CODEが重複しています。");
        }

    }

    public static GoodsRes createResponse(
            final ModelMapper modelMapper,
            final GoodsTbl entity
    ) {

        GoodsRes goodsRes = modelMapper.map(entity, GoodsRes.class);
        goodsRes.setGoodsCode(entity.getCode());
        modelMapper.map(entity.getStatusTbl(), goodsRes);
        return goodsRes;

    }
}
