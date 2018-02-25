package jp.co.valtech.items.rdb.repository;

import jp.co.valtech.items.rdb.domain.GoodsStatusTbl;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author uratamanabu
 * @version 1.0
 * @since 1.0
 */
public interface GoodsStatusRepository
        extends JpaRepository<GoodsStatusTbl, Long> {

    /**
     * @param goodsId
     * @return
     */
    GoodsStatusTbl findByGoodsId(final Long goodsId);

}
