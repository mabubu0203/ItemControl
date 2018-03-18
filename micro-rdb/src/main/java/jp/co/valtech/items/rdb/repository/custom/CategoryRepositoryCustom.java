package jp.co.valtech.items.rdb.repository.custom;

import jp.co.valtech.items.rdb.domain.CategoryTbl;
import jp.co.valtech.items.rdb.service.conditions.CategoryConditionBean;

import javax.persistence.NoResultException;
import java.util.stream.Stream;

public interface CategoryRepositoryCustom {

    CategoryTbl findByIdJoinStatus(final Long id) throws NoResultException;

    Stream<CategoryTbl> getAllJoinStatusAll();

    Stream<CategoryTbl> searchJoinStatus(final CategoryConditionBean condition);

}
