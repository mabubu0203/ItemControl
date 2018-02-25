package jp.co.valtech.items.rdb.service;

import jp.co.valtech.items.rdb.domain.CategoryStatusTbl;
import jp.co.valtech.items.rdb.domain.CategoryTbl;
import jp.co.valtech.items.rdb.repository.CategoryRepository;
import jp.co.valtech.items.rdb.repository.CategoryStatusRepository;
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
public class CategoryService {

    private final CategoryRepository master;
    private final CategoryStatusRepository status;

    @PersistenceContext
    EntityManager entityManager;

    public Optional<CategoryTbl> findByCode(final String code) {
        return Optional.ofNullable(master.findByCode(code));
    }

    public Optional<CategoryTbl> findById(final long id) {

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<CategoryTbl> query = builder.createQuery(CategoryTbl.class);
        Root<CategoryTbl> root = query.from(CategoryTbl.class);
        Join<CategoryTbl, CategoryStatusTbl> join1 = root.join("statusTbl", JoinType.INNER);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.equal(join1.get("deleteFlag"), false));
        predicates.add(builder.equal(root.get("id"), id));
        query.select(root).where(builder.and(predicates.toArray(new Predicate[]{})));
        return Optional.ofNullable(entityManager.createQuery(query).getSingleResult());

    }

    public List<CategoryTbl> getAll() {

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<CategoryTbl> query = builder.createQuery(CategoryTbl.class);
        Root<CategoryTbl> root = query.from(CategoryTbl.class);
        Join<CategoryTbl, CategoryStatusTbl> join1 = root.join("statusTbl", JoinType.INNER);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.equal(join1.get("deleteFlag"), false));
        query.select(root).where(builder.and(predicates.toArray(new Predicate[]{})));
        return entityManager.createQuery(query).getResultList();

    }

    public void insert(final CategoryTbl masterEntity) {
        master.saveAndFlush(masterEntity);
        CategoryStatusTbl statusEntity = new CategoryStatusTbl();
        statusEntity.setCategoryId(masterEntity.getId());
        status.saveAndFlush(statusEntity);
    }
}
