package jp.co.valtech.items.rdb.service;

import jp.co.valtech.items.rdb.domain.GoodsStatusTbl;
import jp.co.valtech.items.rdb.domain.GoodsTbl;
import jp.co.valtech.items.rdb.repository.GoodsRepository;
import jp.co.valtech.items.rdb.repository.GoodsStatusRepository;
import jp.co.valtech.items.rdb.service.conditions.GoodsConditionBean;
import lombok.RequiredArgsConstructor;
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
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
     * @author uratamanabu
     * @since 1.0
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void delete(final GoodsTbl masterEntity) {

        GoodsStatusTbl statusEntity = status.findByGoodsId(masterEntity.getId());
        statusEntity.setDeleteFlag(true);
        statusEntity.addVersion();
        status.saveAndFlush(statusEntity);

    }

    /**
     * @author uratamanabu
     * @since 1.0
     */
    public Optional<GoodsTbl> findByCode(final String code) {
        return Optional.ofNullable(master.findByCode(code));
    }

    /**
     * @author uratamanabu
     * @since 1.0
     */
    public Optional<GoodsTbl> findById(final Long id) {

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<GoodsTbl> query = builder.createQuery(GoodsTbl.class);
        Root<GoodsTbl> root = query.from(GoodsTbl.class);
        Join<GoodsTbl, GoodsStatusTbl> join1 = root.join("statusTbl", JoinType.INNER);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.equal(join1.get("deleteFlag"), false));
        predicates.add(builder.equal(root.get("id"), id));
        query.select(root).where(builder.and(predicates.toArray(new Predicate[]{})));
        try {
            return Optional.of(entityManager.createQuery(query).getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }

    }

    /**
     * @author uratamanabu
     * @since 1.0
     */
    public List<GoodsTbl> getAll() {

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<GoodsTbl> query = builder.createQuery(GoodsTbl.class);
        Root<GoodsTbl> root = query.from(GoodsTbl.class);
        Join<GoodsTbl, GoodsStatusTbl> join1 = root.join("statusTbl", JoinType.INNER);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.equal(join1.get("deleteFlag"), false));
        query.select(root).where(builder.and(predicates.toArray(new Predicate[]{})));
        return entityManager.createQuery(query).getResultList();

    }

    /**
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
     * @author uratamanabu
     * @since 1.0
     */
    public List<GoodsTbl> search(final GoodsConditionBean condtion) {

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<GoodsTbl> query = builder.createQuery(GoodsTbl.class);
        Root<GoodsTbl> root = query.from(GoodsTbl.class);
        Join<GoodsTbl, GoodsStatusTbl> join1 = root.join("statusTbl", JoinType.INNER);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.equal(join1.get("deleteFlag"), false));
        // 検索条件設定
        Optional<String> code = Optional.ofNullable(condtion.getCode());
        code.ifPresent(str -> predicates.add(builder.like(root.get("code"), "%" + str + "%")));
        Optional<String> name = Optional.ofNullable(condtion.getName());
        name.ifPresent(str -> predicates.add(builder.like(root.get("name"), "%" + str + "%")));
        Optional<String> note = Optional.ofNullable(condtion.getNote());
        note.ifPresent(str -> predicates.add(builder.like(root.get("note"), "%" + str + "%")));
        query.select(root).where(builder.and(predicates.toArray(new Predicate[]{})));
        return entityManager.createQuery(query).getResultList();

    }

    /**
     * @author uratamanabu
     * @since 1.0
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void update(final GoodsTbl masterEntity) {

        master.saveAndFlush(masterEntity);
        GoodsStatusTbl statusEntity = status.findByGoodsId(masterEntity.getId());
        statusEntity.addVersion();
        status.saveAndFlush(statusEntity);

    }

}