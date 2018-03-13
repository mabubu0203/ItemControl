package jp.co.valtech.items.rdb.service;

import jp.co.valtech.items.common.exception.NotFoundException;
import jp.co.valtech.items.rdb.domain.CategoryStatusTbl;
import jp.co.valtech.items.rdb.domain.CategoryTbl;
import jp.co.valtech.items.rdb.domain.GoodsStatusTbl;
import jp.co.valtech.items.rdb.domain.GoodsTbl;
import jp.co.valtech.items.rdb.repository.GoodsRepository;
import jp.co.valtech.items.rdb.repository.GoodsStatusRepository;
import jp.co.valtech.items.rdb.service.conditions.GoodsConditionBean;
import lombok.RequiredArgsConstructor;
import org.hibernate.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * @author uratamanabu
 * @version 1.0
 * @since 1.0
 */
@Service
@RequiredArgsConstructor
public class GoodsService {

    private final GoodsRepository master;
    private final GoodsStatusRepository status;

    @PersistenceContext
    EntityManager entityManager;

    /**
     * 商品を１件削除します。
     *
     * @param masterEntity 商品
     * @throws NotFoundException
     * @author uratamanabu
     * @since 1.0
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void delete(final GoodsTbl masterEntity) throws NotFoundException {

        GoodsStatusTbl statusEntity = status
                .findByGoodsId(masterEntity.getId())
                .orElseThrow(() -> new NotFoundException("id", "IDが存在しません。"));
        statusEntity.setDeleteFlag(true);
        statusEntity.addVersion();
        status.saveAndFlush(statusEntity);

    }

    /**
     * 商品コードを条件にEntityのOptionalを返却します。
     *
     * @param code 商品コード
     * @return Optional
     * @author uratamanabu
     * @since 1.0
     */
    public Optional<GoodsTbl> findByCode(final String code) {
        return master.findByCode(code);
    }

    /**
     * 商品TblのPKを条件にEntityを1件返却します。
     * 商品が取得できない時NoResultExceptionを発生します。
     *
     * @param id 商品の識別key
     * @return GoodsTbl
     * @author uratamanabu
     * @since 1.0
     */
    public GoodsTbl findById(final Long id) throws NoResultException {

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<GoodsTbl> query = builder.createQuery(GoodsTbl.class);
        Root<GoodsTbl> root = query.from(GoodsTbl.class);
        Join<GoodsTbl, GoodsStatusTbl> join1 = root.join("statusTbl", JoinType.INNER);
        Join<GoodsTbl, CategoryTbl> join2 = root.join("categoryTbl", JoinType.INNER);
        Join<CategoryTbl, CategoryStatusTbl> join3 = join2.join("statusTbl", JoinType.INNER);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.equal(root.get("id"), id));
        predicates.add(builder.equal(join1.get("deleteFlag"), false));
        predicates.add(builder.equal(join3.get("deleteFlag"), false));
        query.select(root).where(builder.and(predicates.toArray(new Predicate[]{})));
        return entityManager.createQuery(query).getSingleResult();

    }

    /**
     * 商品を全件抽出します。
     *
     * @return Stream
     * @author uratamanabu
     * @since 1.0
     */
    public Stream<GoodsTbl> getAll() {

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<GoodsTbl> query = builder.createQuery(GoodsTbl.class);
        Root<GoodsTbl> root = query.from(GoodsTbl.class);
        Join<GoodsTbl, GoodsStatusTbl> join1 = root.join("statusTbl", JoinType.INNER);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.equal(join1.get("deleteFlag"), false));
        Order order = builder.asc(root.get("id"));
        query.select(root).where(builder.and(predicates.toArray(new Predicate[]{}))).orderBy(order);
        return entityManager.createQuery(query).unwrap(Query.class).stream();

    }

    /**
     * 商品を１件挿入します。
     *
     * @param masterEntity 商品
     * @author uratamanabu
     * @since 1.0
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void insert(final GoodsTbl masterEntity) {

        master.saveAndFlush(masterEntity);
        GoodsStatusTbl statusEntity = new GoodsStatusTbl();
        statusEntity.setGoodsId(masterEntity.getId());
        status.saveAndFlush(statusEntity);

    }

    /**
     * 商品を検索して抽出します。
     *
     * @param condition 検索条件
     * @return Stream
     * @author uratamanabu
     * @since 1.0
     */
    public Stream<GoodsTbl> search(final GoodsConditionBean condition) {

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<GoodsTbl> query = builder.createQuery(GoodsTbl.class);
        Root<GoodsTbl> root = query.from(GoodsTbl.class);
        Join<GoodsTbl, GoodsStatusTbl> join1 = root.join("statusTbl", JoinType.INNER);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.equal(join1.get("deleteFlag"), false));
        // 検索条件設定
        Optional
                .ofNullable(condition.getCode())
                .ifPresent(str -> predicates.add(builder.like(root.get("code"), "%" + str + "%")));
        Optional
                .ofNullable(condition.getName())
                .ifPresent(str -> predicates.add(builder.like(root.get("name"), "%" + str + "%")));
        Optional
                .ofNullable(condition.getNote())
                .ifPresent(str -> predicates.add(builder.like(root.get("note"), "%" + str + "%")));
        Order order = builder.asc(root.get("id"));
        query.select(root).where(builder.and(predicates.toArray(new Predicate[]{}))).orderBy(order);
        return entityManager.createQuery(query).unwrap(Query.class).stream();

    }

    /**
     * @param masterEntity 商品
     * @throws NotFoundException
     * @author uratamanabu
     * @since 1.0
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void update(final GoodsTbl masterEntity) throws NotFoundException {

        master.saveAndFlush(masterEntity);
        GoodsStatusTbl statusEntity = status
                .findByGoodsId(masterEntity.getId())
                .orElseThrow(() -> new NotFoundException("id", "IDが存在しません。"));
        statusEntity.addVersion();
        status.saveAndFlush(statusEntity);

    }

}