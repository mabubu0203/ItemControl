package jp.co.valtech.items.rdb.repository;

import jp.co.valtech.items.rdb.domain.CategoryTbl;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author uratamanabu
 * @version 1.0
 * @since 1.0
 */
public interface CategoryRepository
        extends JpaRepository<CategoryTbl, Long> {

    /**
     * @param code
     * @return
     */
    CategoryTbl findByCode(final String code);
}
