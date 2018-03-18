package jp.co.valtech.items.rdb.repository.custom;

import jp.co.valtech.items.rdb.domain.CategoryTbl;
import jp.co.valtech.items.rdb.service.conditions.CategoryConditionBean;

import javax.persistence.NoResultException;
import java.util.stream.Stream;

/**
 * @author uratamanabu
 * @version 1.0
 * @since 1.0
 */
public interface CategoryRepositoryCustom {

    /**
     * @param id
     * @return CategoryTbl
     * @throws NoResultException
     */
    CategoryTbl findByIdJoinStatus(final Long id) throws NoResultException;

    /**
     * @return Stream
     */
    Stream<CategoryTbl> getAllJoinStatusAll();

    /**
     * @return Stream
     */
    Stream<CategoryTbl> searchJoinStatus(final CategoryConditionBean condition);

}
