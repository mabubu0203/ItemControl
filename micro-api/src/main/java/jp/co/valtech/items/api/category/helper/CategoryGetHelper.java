package jp.co.valtech.items.api.category.helper;

import jp.co.valtech.items.interfaces.category.responses.CategoryGetResponse;
import jp.co.valtech.items.interfaces.definitions.responses.CategoryRes;
import jp.co.valtech.items.rdb.domain.CategoryTbl;
import jp.co.valtech.items.rdb.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author uratamanabu
 * @version 1.0
 * @since 1.0
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryGetHelper {

    private final CategoryService cService;
    private final ModelMapper modelMapper;

    @PersistenceContext
    private final EntityManager entityManager;

    /**
     * @return ResponseEntity
     * @author uratamanabu
     * @since 1.0
     */
    public ResponseEntity<CategoryGetResponse> execute() {

        List<CategoryRes> categoryList = new ArrayList<>();
        try (Stream<CategoryTbl> stream = cService.getAll()) {
            stream.forEach(entity -> {
                CategoryRes categoryRes = modelMapper.map(entity, CategoryRes.class);
                modelMapper.map(entity.getStatusTbl(), categoryRes);
                entityManager.detach(entity);
                categoryList.add(categoryRes);
            });
        }
        CategoryGetResponse response = new CategoryGetResponse();
        response.setCategoryList(categoryList);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

}