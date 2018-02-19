package jp.co.valtech.items.rdb.service;

import jp.co.valtech.items.rdb.domain.GoodsStatusTbl;
import jp.co.valtech.items.rdb.domain.GoodsTbl;
import jp.co.valtech.items.rdb.repository.GoodsRepository;
import jp.co.valtech.items.rdb.repository.GoodsStatusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;
import java.util.StringJoiner;

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

    public List<GoodsTbl> findAll() {
        StringJoiner sj = new StringJoiner(" ");
        sj.add("SELECT");
        sj.add("goodsTbl");
        sj.add("FROM GoodsTbl goodsTbl");
        sj.add("INNER JOIN goodsTbl.statusTbl goodsStatusTbl");
        sj.add("WHERE");
        sj.add("goodsStatusTbl.deleteFlag = false");

        TypedQuery<GoodsTbl> q = entityManager.createQuery(sj.toString(), GoodsTbl.class);
        return q.getResultList();
    }

    public Optional<GoodsTbl> findByCode(final String code) {
        return Optional.ofNullable(master.findByCode(code));
    }

    public Optional<GoodsTbl> findById(final long id) {
        StringJoiner sj = new StringJoiner(" ");
        sj.add("SELECT");
        sj.add("goodsTbl");
        sj.add("FROM GoodsTbl goodsTbl");
        sj.add("INNER JOIN goodsTbl.statusTbl goodsStatusTbl");
        sj.add("WHERE");
        sj.add("goodsStatusTbl.deleteFlag = false");
        sj.add("AND goodsTbl.id = :id");

        TypedQuery<GoodsTbl> q = entityManager.createQuery(sj.toString(), GoodsTbl.class);
        q.setParameter("id", id);
        return Optional.ofNullable(q.getSingleResult());
    }

    public void insert(final GoodsTbl masterEntity) {
        master.saveAndFlush(masterEntity);
        GoodsStatusTbl statusEntity = new GoodsStatusTbl();
        statusEntity.setGoodsId(masterEntity.getId());
        status.saveAndFlush(statusEntity);
    }

    public void update(final GoodsTbl masterEntity) {
        master.saveAndFlush(masterEntity);
        GoodsStatusTbl statusEntity = status.findByGoodsId(masterEntity.getId());
        statusEntity.addVersion();
        status.saveAndFlush(statusEntity);
    }
}
