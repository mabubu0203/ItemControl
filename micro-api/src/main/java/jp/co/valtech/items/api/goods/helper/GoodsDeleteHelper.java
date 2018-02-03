package jp.co.valtech.items.api.goods.helper;

import jp.co.valtech.items.interfaces.goods.requests.GoodsDeleteRequest;
import jp.co.valtech.items.rdb.domain.GoodsTbl;
import jp.co.valtech.items.rdb.service.GoodsService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class GoodsDeleteHelper {

    private final GoodsService service;

    public ResponseEntity deleteGoods(
            final String id,
            final GoodsDeleteRequest request) {

        Optional<GoodsTbl> optionalId = service.findById(Long.valueOf(id));
        if (optionalId.isPresent()) {
            GoodsTbl entity = optionalId.get();
            if (entity.getVersion() == request.getVersion()) {
                service.deleteById(Long.valueOf(id));
                return new ResponseEntity(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity(HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
}
