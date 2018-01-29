package jp.co.valtech.items.rdb.service;

import jp.co.valtech.items.rdb.domain.GoodsTbl;
import jp.co.valtech.items.rdb.repository.GoodsRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class GoodsService {

    private GoodsRepository repository;

    public Optional<GoodsTbl> selectId(final GoodsTbl entity) {
        return repository.findById(entity.getId());
    }

    public Optional<GoodsTbl> selectCode(final GoodsTbl entity) {
        return Optional.ofNullable(repository.findByCode(entity.getCode()));
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public GoodsTbl insert(final GoodsTbl entity) {
        return repository.saveAndFlush(entity);
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public GoodsTbl update(final GoodsTbl entity) {
        return repository.saveAndFlush(entity);
    }
}
