package jp.co.valtech.items.rdb.service;

import jp.co.valtech.items.rdb.domain.GoodsStatusTbl;
import jp.co.valtech.items.rdb.domain.GoodsTbl;
import jp.co.valtech.items.rdb.repository.GoodsRepository;
import jp.co.valtech.items.rdb.repository.GoodsStatusRepository;
import jp.co.valtech.items.rdb.service.conditions.GoodsConditionBean;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
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

@Service
@RequiredArgsConstructor
public class GoodsService {

    private final GoodsRepository master;
    private final GoodsStatusRepository status;

    @PersistenceContext
    EntityManager entityManager;

    public void delete(final GoodsTbl masterEntity) {

        GoodsStatusTbl statusEntity = status.findByGoodsId(masterEntity.getId());
        statusEntity.setDeleteFlag(true);
        statusEntity.addVersion();
        status.saveAndFlush(statusEntity);

    }

    public Optional<GoodsTbl> findByCode(final String code) {
        return Optional.ofNullable(master.findByCode(code));
    }

    public Optional<GoodsTbl> findById(final long id) {

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<GoodsTbl> query = builder.createQuery(GoodsTbl.class);
        Root<GoodsTbl> root = query.from(GoodsTbl.class);
        Join<GoodsTbl, GoodsStatusTbl> join1 = root.join("statusTbl", JoinType.INNER);
        List<Predicate> preds = new ArrayList<>();
        preds.add(builder.equal(join1.get("deleteFlag"), false));
        preds.add(builder.equal(root.get("id"), id));
        query.select(root).where(builder.and(preds.toArray(new Predicate[]{})));
        return Optional.ofNullable(entityManager.createQuery(query).getSingleResult());

    }

    public List<GoodsTbl> getAll() {

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<GoodsTbl> query = builder.createQuery(GoodsTbl.class);
        Root<GoodsTbl> root = query.from(GoodsTbl.class);
        Join<GoodsTbl, GoodsStatusTbl> join1 = root.join("statusTbl", JoinType.INNER);
        List<Predicate> preds = new ArrayList<>();
        preds.add(builder.equal(join1.get("deleteFlag"), false));
        query.select(root).where(builder.and(preds.toArray(new Predicate[]{})));
        return entityManager.createQuery(query).getResultList();

    }

    public void insert(final GoodsTbl masterEntity) {

        master.saveAndFlush(masterEntity);
        GoodsStatusTbl statusEntity = new GoodsStatusTbl();
        statusEntity.setGoodsId(masterEntity.getId());
        status.saveAndFlush(statusEntity);

    }

    public List<GoodsTbl> search(final GoodsConditionBean condtion) {

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<GoodsTbl> query = builder.createQuery(GoodsTbl.class);
        Root<GoodsTbl> root = query.from(GoodsTbl.class);
        Join<GoodsTbl, GoodsStatusTbl> join1 = root.join("statusTbl", JoinType.INNER);
        List<Predicate> preds = new ArrayList<>();
        preds.add(builder.equal(join1.get("deleteFlag"), false));
        // 検索条件設定
        Optional<String> code = Optional.ofNullable(condtion.getCode());
        code.ifPresent(str -> preds.add(builder.like(root.get("code"), "%" + str + "%")));
        Optional<String> name = Optional.ofNullable(condtion.getName());
        name.ifPresent(str -> preds.add(builder.like(root.get("name"), "%" + str + "%")));
        Optional<String> note = Optional.ofNullable(condtion.getNote());
        note.ifPresent(str -> preds.add(builder.like(root.get("note"), "%" + str + "%")));

        query.select(root).where(builder.and(preds.toArray(new Predicate[]{})));
        return entityManager.createQuery(query).getResultList();

    }

    public void update(final GoodsTbl masterEntity) {

        master.saveAndFlush(masterEntity);
        GoodsStatusTbl statusEntity = status.findByGoodsId(masterEntity.getId());
        statusEntity.addVersion();
        status.saveAndFlush(statusEntity);

    }

}
