package jp.co.valtech.items.rdb.service;

import jp.co.valtech.items.common.exception.NotFoundException;
import jp.co.valtech.items.rdb.domain.GoodsTbl;
import jp.co.valtech.items.rdb.service.conditions.GoodsConditionBean;
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
public interface GoodsService {

    /**
     * 商品を１件削除します。
     *
     * @param masterEntity 商品
     * @throws NotFoundException
     * @author uratamanabu
     * @since 1.0
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    void delete(final GoodsTbl masterEntity) throws NotFoundException;

    /**
     * 商品コードを条件にEntityのOptionalを返却します。
     *
     * @param code 商品コード
     * @return Optional
     * @author uratamanabu
     * @since 1.0
     */
    Optional<GoodsTbl> findByCode(final String code);

    /**
     * 商品TblのPKを条件にEntityを1件返却します。
     * 商品が取得できない時NoResultExceptionを発生します。
     *
     * @param id 商品の識別key
     * @return GoodsTbl
     * @author uratamanabu
     * @since 1.0
     */
    GoodsTbl findById(final Long id) throws NoResultException;

    /**
     * 商品を全件抽出します。
     *
     * @return Stream
     * @author uratamanabu
     * @since 1.0
     */
    Stream<GoodsTbl> getAll();

    /**
     * 商品を１件挿入します。
     *
     * @param masterEntity 商品
     * @author uratamanabu
     * @since 1.0
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    void insert(final GoodsTbl masterEntity);

    /**
     * 商品を検索して抽出します。
     *
     * @param condition 検索条件
     * @return Stream
     * @author uratamanabu
     * @since 1.0
     */
    Stream<GoodsTbl> search(final GoodsConditionBean condition);

    /**
     * @param masterEntity 商品
     * @throws NotFoundException
     * @author uratamanabu
     * @since 1.0
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    void update(final GoodsTbl masterEntity) throws NotFoundException;

}