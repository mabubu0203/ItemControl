package jp.co.valtech.items.api.category.helper;

import jp.co.valtech.items.interfaces.category.requests.CategorySearchRequest;
import jp.co.valtech.items.interfaces.category.responses.CategorySearchResponse;
import jp.co.valtech.items.interfaces.definitions.responses.CategoryRes;
import jp.co.valtech.items.rdb.domain.CategoryTbl;
import jp.co.valtech.items.rdb.service.CategoryService;
import jp.co.valtech.items.rdb.service.conditions.CategoryConditionBean;
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
public class CategorySearchHelper {

    private final CategoryService cService;
    private final ModelMapper modelMapper;

    @PersistenceContext
    private final EntityManager entityManager;

    /**
     * @param request 　Request
     * @return ResponseEntity
     * @author uratamanabu
     * @since 1.0
     */
    public ResponseEntity<CategorySearchResponse> execute(
            final CategorySearchRequest request
    ) {

        CategorySearchRequest.Category condition = request.getCondition();
        CategoryConditionBean conditionBean = modelMapper.map(condition, CategoryConditionBean.class);
        conditionBean.setCode(condition.getCategoryCode());
        List<CategoryRes> categoryList = new ArrayList<>();
        try (Stream<CategoryTbl> stream = cService.search(conditionBean)) {
            stream.forEach(entity -> {
                CategoryRes categoryRes = modelMapper.map(entity, CategoryRes.class);
                modelMapper.map(entity.getStatusTbl(), categoryRes);
                entityManager.detach(entity);
                categoryList.add(categoryRes);
            });
        }
        CategorySearchResponse response = new CategorySearchResponse();
        response.setCategoryList(categoryList);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

}
