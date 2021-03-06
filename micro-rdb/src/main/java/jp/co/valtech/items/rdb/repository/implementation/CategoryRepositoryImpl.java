package jp.co.valtech.items.rdb.repository.implementation;

import jp.co.valtech.items.rdb.domain.CategoryStatusTbl;
import jp.co.valtech.items.rdb.domain.CategoryTbl;
import jp.co.valtech.items.rdb.repository.custom.CategoryRepositoryCustom;
import jp.co.valtech.items.rdb.service.conditions.CategoryConditionBean;
import org.hibernate.query.Query;

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
public class CategoryRepositoryImpl implements CategoryRepositoryCustom {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public CategoryTbl findByIdJoinStatus(final Long id) throws NoResultException {

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

    @Override
    public Stream<CategoryTbl> getAllJoinStatus() {

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

    @Override
    public Stream<CategoryTbl> searchJoinStatus(final CategoryConditionBean condition) {

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
