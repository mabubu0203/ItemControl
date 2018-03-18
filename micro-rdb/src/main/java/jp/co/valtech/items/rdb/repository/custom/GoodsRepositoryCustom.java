package jp.co.valtech.items.rdb.repository.custom;

import jp.co.valtech.items.rdb.domain.GoodsTbl;
import jp.co.valtech.items.rdb.service.conditions.GoodsConditionBean;

import javax.persistence.NoResultException;
import java.util.stream.Stream;

public interface GoodsRepositoryCustom {

    GoodsTbl findByIdJoinStatus(final Long id) throws NoResultException;

    Stream<GoodsTbl> getAllJoinStatus();

    Stream<GoodsTbl> searchJoinStatus(final GoodsConditionBean condition);

}
