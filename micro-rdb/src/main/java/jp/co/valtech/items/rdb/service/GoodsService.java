package jp.co.valtech.items.rdb.service;

import jp.co.valtech.items.rdb.domain.GoodsTbl;
import jp.co.valtech.items.rdb.repository.GoodsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GoodsService {

    private final GoodsRepository repository;

    public void deleteById(final long id) {
        repository.deleteById(id);
    }

    public List<GoodsTbl> findAll() {
        return repository.findAll();
    }

    public Optional<GoodsTbl> findByCode(final String code) {
        return Optional.ofNullable(repository.findByCode(code));
    }

    public Optional<GoodsTbl> findById(final long id) {
        return repository.findById(id);
    }

    public void insert(final GoodsTbl entity) {
        repository.saveAndFlush(entity);
    }

    public void update(final GoodsTbl entity) {
        repository.saveAndFlush(entity);
    }
}
