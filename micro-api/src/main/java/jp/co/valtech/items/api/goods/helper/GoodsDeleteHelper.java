package jp.co.valtech.items.api.goods.helper;

import jp.co.valtech.items.api.goods.util.GoodsUtil;
import jp.co.valtech.items.common.exception.ConflictException;
import jp.co.valtech.items.common.exception.NotFoundException;
import jp.co.valtech.items.rdb.domain.GoodsTbl;
import jp.co.valtech.items.rdb.service.GoodsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author uratamanabu
 * @version 1.0
 * @since 1.0
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class GoodsDeleteHelper {

    private final GoodsService gService;

    public ResponseEntity execute(
            final Long id,
            final Integer version
    ) throws NotFoundException, ConflictException {

        GoodsTbl entity = GoodsUtil.findById(gService, id);
        GoodsUtil.exclusionCheck(entity.getStatusTbl(), version);
        delete(entity);
        return new ResponseEntity(HttpStatus.NO_CONTENT);

    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    private void delete(GoodsTbl entity) {
        gService.delete(entity);
    }
}
