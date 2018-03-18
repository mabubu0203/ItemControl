package jp.co.valtech.items.api.goods.helper;

import jp.co.valtech.items.api.goods.util.GoodsUtil;
import jp.co.valtech.items.interfaces.definitions.responses.GoodsRes;
import jp.co.valtech.items.interfaces.goods.requests.GoodsSearchRequest;
import jp.co.valtech.items.interfaces.goods.responses.GoodsSearchResponse;
import jp.co.valtech.items.rdb.domain.GoodsTbl;
import jp.co.valtech.items.rdb.service.GoodsService;
import jp.co.valtech.items.rdb.service.conditions.GoodsConditionBean;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
public class GoodsSearchHelper {

    private final GoodsService gService;
    private final ModelMapper modelMapper;

    @PersistenceContext
    private final EntityManager entityManager;

    /**
     * @param request 　Request
     * @return ResponseEntity
     * @author uratamanabu
     * @since 1.0
     */
    public ResponseEntity<GoodsSearchResponse> execute(
            final GoodsSearchRequest request
    ) {

        GoodsSearchRequest.Goods condition = request.getCondition();
        GoodsConditionBean conditionBean = modelMapper.map(condition, GoodsConditionBean.class);
        conditionBean.setCode(condition.getGoodsCode());
        List<GoodsRes> goodsList = new ArrayList<>();
        try (Stream<GoodsTbl> stream = gService.search(conditionBean)) {
            stream.forEach(entity -> {
                GoodsRes goodsRes = new GoodsRes();
                GoodsUtil.entityToResponse(entity, goodsRes);
                entityManager.detach(entity);
                goodsList.add(goodsRes);
            });
        }
        GoodsSearchResponse response = new GoodsSearchResponse();
        response.setGoodsList(goodsList);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }
}