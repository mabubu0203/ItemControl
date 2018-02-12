package jp.co.valtech.items.api.goods.helper;

import jp.co.valtech.items.common.exception.NotFoundException;
import jp.co.valtech.items.interfaces.definitions.responses.GoodsRes;
import jp.co.valtech.items.interfaces.goods.responses.GoodsFindResponse;
import jp.co.valtech.items.rdb.domain.GoodsTbl;
import jp.co.valtech.items.rdb.service.GoodsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class GoodsFindHelper {

    private final GoodsService service;
    private final ModelMapper modelMapper;

    public ResponseEntity<GoodsFindResponse> execute(
            final String id
    ) throws NotFoundException {

        Optional<GoodsTbl> optionalId = service.findById(Long.valueOf(id));
        if (!optionalId.isPresent()) {
            throw new NotFoundException("id", "IDが存在しません。");
        }

        GoodsTbl entity = optionalId.get();
        GoodsRes goodsRes = modelMapper.map(entity, GoodsRes.class);
        GoodsFindResponse response = new GoodsFindResponse();
        response.setGoods(goodsRes);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }
}
