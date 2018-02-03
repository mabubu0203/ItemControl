package jp.co.valtech.items.rdb.repository;

import jp.co.valtech.items.rdb.domain.GoodsTbl;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 *
 * @author uratamanabu
 * @version 1.0
 * @since 1.0
 */
public interface GoodsRepository
        extends JpaRepository<GoodsTbl, Long> {

    /**
     * @param code
     * @return
     */
    GoodsTbl findByCode(final String code);

}
