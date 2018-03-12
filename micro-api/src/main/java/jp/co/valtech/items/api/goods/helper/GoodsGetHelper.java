package jp.co.valtech.items.api.goods.helper;

import jp.co.valtech.items.api.goods.util.GoodsUtil;
import jp.co.valtech.items.interfaces.definitions.responses.GoodsRes;
import jp.co.valtech.items.interfaces.goods.responses.GoodsGetResponse;
import jp.co.valtech.items.rdb.domain.GoodsTbl;
import jp.co.valtech.items.rdb.service.GoodsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author uratamanabu
 * @version 1.0
 * @since 1.0
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class GoodsGetHelper {

    private final GoodsService gService;

    /**
     * @return ResponseEntity
     * @author uratamanabu
     * @since 1.0
     */
    public ResponseEntity<GoodsGetResponse> execute() {

        List<GoodsRes> goodsList = new ArrayList<>();
        try (Stream<GoodsTbl> stream = gService.getAll()) {
            stream.forEach(entity -> {
                GoodsRes goodsRes = new GoodsRes();
                GoodsUtil.entityToResponse(entity, goodsRes);
                goodsList.add(goodsRes);
            });
        }
        GoodsGetResponse response = new GoodsGetResponse();
        response.setGoodsList(goodsList);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }
}