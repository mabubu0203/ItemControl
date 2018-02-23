package jp.co.valtech.items.rdb.service;

import jp.co.valtech.items.rdb.domain.CategoryStatusTbl;
import jp.co.valtech.items.rdb.domain.CategoryTbl;
import jp.co.valtech.items.rdb.repository.CategoryRepository;
import jp.co.valtech.items.rdb.repository.CategoryStatusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository master;
    private final CategoryStatusRepository status;

    public Optional<CategoryTbl> findByCode(final String code) {
        return Optional.ofNullable(master.findByCode(code));
    }

    public void insert(final CategoryTbl masterEntity) {
        master.saveAndFlush(masterEntity);
        CategoryStatusTbl statusEntity = new CategoryStatusTbl();
        statusEntity.setCategoryId(masterEntity.getId());
        status.saveAndFlush(statusEntity);
    }
}
