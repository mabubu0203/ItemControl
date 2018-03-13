package jp.co.valtech.items.rdb.service;

import jp.co.valtech.items.rdb.domain.CategoryStatusTbl;
import jp.co.valtech.items.rdb.domain.CategoryTbl;
import jp.co.valtech.items.rdb.repository.CategoryRepository;
import jp.co.valtech.items.rdb.repository.CategoryStatusRepository;
import jp.co.valtech.items.rdb.service.conditions.CategoryConditionBean;
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
public class CategoryService {

    private final CategoryRepository master;
    private final CategoryStatusRepository status;

    @PersistenceContext
    EntityManager entityManager;

    /**
     * カテゴリーコードを条件にEntityのOptionalを返却します。
     *
     * @param code カテゴリーコード
     * @return Optional
     * @author uratamanabu
     * @since 1.0
     */
    public Optional<CategoryTbl> findByCode(final String code) {
        return master.findByCode(code);
    }

    /**
     * カテゴリーTblのPKを条件にEntityを1件返却します。
     * カテゴリーが取得できない時NoResultExceptionを発生します。
     *
     * @param id カテゴリーの識別key
     * @return CategoryTbl
     * @author uratamanabu
     * @since 1.0
     */
    public CategoryTbl findById(final Long id) throws NoResultException {

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<CategoryTbl> query = builder.createQuery(CategoryTbl.class);
        Root<CategoryTbl> root = query.from(CategoryTbl.class);
        Join<CategoryTbl, CategoryStatusTbl> join1 = root.join("statusTbl", JoinType.INNER);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.equal(root.get("id"), id));
        predicates.add(builder.equal(join1.get("deleteFlag"), false));
        query.select(root).where(builder.and(predicates.toArray(new Predicate[]{})));
        return entityManager.createQuery(query).getSingleResult();

    }

    /**
     * カテゴリーを全件抽出します。
     *
     * @return Stream
     * @author uratamanabu
     * @since 1.0
     */
    public Stream<CategoryTbl> getAll() {

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<CategoryTbl> query = builder.createQuery(CategoryTbl.class);
        Root<CategoryTbl> root = query.from(CategoryTbl.class);
        Join<CategoryTbl, CategoryStatusTbl> join1 = root.join("statusTbl", JoinType.INNER);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.equal(join1.get("deleteFlag"), false));
        Order order = builder.asc(root.get("id"));
        query.select(root).where(builder.and(predicates.toArray(new Predicate[]{}))).orderBy(order);
        return entityManager.createQuery(query).unwrap(Query.class).stream();

    }

    /**
     * カテゴリーを１件挿入します。
     *
     * @param masterEntity カテゴリー
     * @author uratamanabu
     * @since 1.0
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void insert(final CategoryTbl masterEntity) {
        master.saveAndFlush(masterEntity);
        CategoryStatusTbl statusEntity = new CategoryStatusTbl();
        statusEntity.setCategoryId(masterEntity.getId());
        status.saveAndFlush(statusEntity);
    }

    /**
     * カテゴリーを検索して抽出します。
     *
     * @param condition 検索条件
     * @return Stream
     * @author uratamanabu
     * @since 1.0
     */
    public Stream<CategoryTbl> search(final CategoryConditionBean condition) {

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<CategoryTbl> query = builder.createQuery(CategoryTbl.class);
        Root<CategoryTbl> root = query.from(CategoryTbl.class);
        Join<CategoryTbl, CategoryStatusTbl> join1 = root.join("statusTbl", JoinType.INNER);
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

}