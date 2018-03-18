package jp.co.valtech.items.rdb.service;

import jp.co.valtech.items.rdb.domain.CategoryTbl;
import jp.co.valtech.items.rdb.service.conditions.CategoryConditionBean;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * @author uratamanabu
 * @version 1.0
 * @since 1.0
 */
public interface CategoryService {

    /**
     * カテゴリーコードを条件にEntityのOptionalを返却します。
     *
     * @param code カテゴリーコード
     * @return Optional
     * @author uratamanabu
     * @since 1.0
     */
    Optional<CategoryTbl> findByCode(final String code);

    /**
     * カテゴリーTblのPKを条件にEntityを1件返却します。
     * カテゴリーが取得できない時NoResultExceptionを発生します。
     *
     * @param id カテゴリーの識別key
     * @return CategoryTbl
     * @author uratamanabu
     * @since 1.0
     */
    CategoryTbl findById(final Long id) throws NoResultException;

    /**
     * カテゴリーを全件抽出します。
     *
     * @return Stream
     * @author uratamanabu
     * @since 1.0
     */
    Stream<CategoryTbl> getAll();

    /**
     * カテゴリーを１件挿入します。
     *
     * @param masterEntity カテゴリー
     * @author uratamanabu
     * @since 1.0
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    void insert(final CategoryTbl masterEntity);

    /**
     * カテゴリーを検索して抽出します。
     *
     * @param condition 検索条件
     * @return Stream
     * @author uratamanabu
     * @since 1.0
     */
    Stream<CategoryTbl> search(final CategoryConditionBean condition);

}