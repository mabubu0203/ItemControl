package jp.co.valtech.items.rdb.service.implementation;

import jp.co.valtech.items.common.exception.NotFoundException;
import jp.co.valtech.items.rdb.domain.GoodsStatusTbl;
import jp.co.valtech.items.rdb.domain.GoodsTbl;
import jp.co.valtech.items.rdb.repository.GoodsRepository;
import jp.co.valtech.items.rdb.repository.GoodsStatusRepository;
import jp.co.valtech.items.rdb.service.GoodsService;
import jp.co.valtech.items.rdb.service.conditions.GoodsConditionBean;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * @author uratamanabu
 * @version 1.0
 * @since 1.0
 */
@Service
@RequiredArgsConstructor
public class GoodsServiceImpl implements GoodsService {

    private final GoodsRepository master;
    private final GoodsStatusRepository status;

    @Override

    public void delete(final GoodsTbl masterEntity) throws NotFoundException {

        GoodsStatusTbl statusEntity = status
                .findByGoodsId(masterEntity.getId())
                .orElseThrow(() -> new NotFoundException("id", "IDが存在しません。"));
        statusEntity.setDeleteFlag(true);
        statusEntity.addVersion();
        status.saveAndFlush(statusEntity);

    }

    @Override
    public Optional<GoodsTbl> findByCode(final String code) {
        return master.findByCode(code);
    }

    @Override
    public GoodsTbl findById(final Long id) throws NoResultException {
        return master.findByIdJoinStatus(id);
    }

    @Override
    public Stream<GoodsTbl> getAll() {
        return master.getAllJoinStatus();
    }

    @Override
    public void insert(final GoodsTbl masterEntity) {

        master.saveAndFlush(masterEntity);
        GoodsStatusTbl statusEntity = new GoodsStatusTbl();
        statusEntity.setGoodsId(masterEntity.getId());
        status.saveAndFlush(statusEntity);

    }

    @Override
    public Stream<GoodsTbl> search(final GoodsConditionBean condition) {
        return master.searchJoinStatus(condition);
    }

    @Override
    public void update(final GoodsTbl masterEntity) throws NotFoundException {

        master.saveAndFlush(masterEntity);
        GoodsStatusTbl statusEntity = status
                .findByGoodsId(masterEntity.getId())
                .orElseThrow(() -> new NotFoundException("id", "IDが存在しません。"));
        statusEntity.addVersion();
        status.saveAndFlush(statusEntity);

    }

}
