package jp.co.valtech.items.rdb.service.implementation;

import jp.co.valtech.items.rdb.domain.CategoryStatusTbl;
import jp.co.valtech.items.rdb.domain.CategoryTbl;
import jp.co.valtech.items.rdb.repository.CategoryRepository;
import jp.co.valtech.items.rdb.repository.CategoryStatusRepository;
import jp.co.valtech.items.rdb.service.CategoryService;
import jp.co.valtech.items.rdb.service.conditions.CategoryConditionBean;
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
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository master;
    private final CategoryStatusRepository status;

    @Override
    public Optional<CategoryTbl> findByCode(final String code) {
        return master.findByCode(code);
    }

    @Override
    public CategoryTbl findById(final Long id) throws NoResultException {
        return master.findByIdJoinStatus(id);
    }

    @Override
    public Stream<CategoryTbl> getAll() {
        return master.getAllJoinStatus();
    }

    @Override
    public void insert(final CategoryTbl masterEntity) {

        master.saveAndFlush(masterEntity);
        CategoryStatusTbl statusEntity = new CategoryStatusTbl();
        statusEntity.setCategoryId(masterEntity.getId());
        status.saveAndFlush(statusEntity);

    }

    @Override
    public Stream<CategoryTbl> search(final CategoryConditionBean condition) {
        return master.searchJoinStatus(condition);
    }
}
