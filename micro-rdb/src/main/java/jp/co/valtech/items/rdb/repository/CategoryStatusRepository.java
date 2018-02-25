package jp.co.valtech.items.rdb.repository;

import jp.co.valtech.items.rdb.domain.CategoryStatusTbl;
import jp.co.valtech.items.rdb.domain.GoodsStatusTbl;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author uratamanabu
 * @version 1.0
 * @since 1.0
 */
public interface CategoryStatusRepository
        extends JpaRepository<CategoryStatusTbl, Long> {

    /**
     * @param categoryId
     * @return
     */
    GoodsStatusTbl findByCategoryId(final Long categoryId);

}
