package jp.co.valtech.items.api.goods.helper;

import jp.co.valtech.items.api.goods.util.GoodsUtil;
import jp.co.valtech.items.interfaces.definitions.responses.GoodsRes;
import jp.co.valtech.items.interfaces.goods.requests.GoodsSearchRequest;
import jp.co.valtech.items.interfaces.goods.responses.GoodsSearchResponse;
import jp.co.valtech.items.rdb.domain.GoodsTbl;
import jp.co.valtech.items.rdb.service.GoodsService;
import jp.co.valtech.items.rdb.service.conditions.GoodsCondtionBean;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class GoodsSearchHelper {

    private final GoodsService service;
    private final ModelMapper modelMapper;

    public ResponseEntity<GoodsSearchResponse> execute(
            final GoodsSearchRequest request
    ) {

        GoodsSearchRequest.Goods condition = request.getCondition();
        GoodsCondtionBean conditionBean = modelMapper.map(condition, GoodsCondtionBean.class);
        conditionBean.setCode(condition.getGoodsCode());
        List<GoodsTbl> entities = service.search(conditionBean);
        List<GoodsRes> goodsList = new ArrayList<>();
        for (GoodsTbl entity : entities) {
            GoodsRes goodsRes = GoodsUtil.createResponse(modelMapper, entity);
            goodsList.add(goodsRes);
        }
        GoodsSearchResponse response = new GoodsSearchResponse();
        response.setGoodsList(goodsList);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }
}
