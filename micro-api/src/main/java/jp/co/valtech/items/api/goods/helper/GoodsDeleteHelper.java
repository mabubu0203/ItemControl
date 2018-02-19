package jp.co.valtech.items.api.goods.helper;

import jp.co.valtech.items.common.exception.ConflictException;
import jp.co.valtech.items.common.exception.NotFoundException;
import jp.co.valtech.items.interfaces.goods.requests.GoodsDeleteRequest;
import jp.co.valtech.items.rdb.domain.GoodsTbl;
import jp.co.valtech.items.rdb.service.GoodsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class GoodsDeleteHelper {

    private final GoodsService service;

    public ResponseEntity execute(
            final String id,
            final GoodsDeleteRequest request
    ) throws NotFoundException, ConflictException {

        Optional<GoodsTbl> optionalId = service.findById(Long.valueOf(id));
        if (!optionalId.isPresent()) {
            throw new NotFoundException("id", "IDが存在しません。");
        }
        GoodsTbl entity = optionalId.get();
        if (entity.getStatusTbl().getVersion() != request.getVersion()) {// 楽観排他
            throw new ConflictException("id", "排他エラー");
        }
        delete(entity);
        return new ResponseEntity(HttpStatus.NO_CONTENT);

    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    private void delete(GoodsTbl entity) {
        service.delete(entity);
    }
}
