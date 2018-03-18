package jp.co.valtech.items.rdb.repository.custom;

import jp.co.valtech.items.rdb.domain.GoodsTbl;
import jp.co.valtech.items.rdb.service.conditions.GoodsConditionBean;

import javax.persistence.NoResultException;
import java.util.stream.Stream;

/**
 * @author uratamanabu
 * @version 1.0
 * @since 1.0
 */
public interface GoodsRepositoryCustom {

    /**
     * @param id
     * @return GoodsTbl
     * @throws NoResultException
     */
    GoodsTbl findByIdJoinStatus(final Long id) throws NoResultException;

    /**
     * @return Stream
     */
    Stream<GoodsTbl> getAllJoinStatus();

    /**
     * @return Stream
     */
    Stream<GoodsTbl> searchJoinStatus(final GoodsConditionBean condition);

}
