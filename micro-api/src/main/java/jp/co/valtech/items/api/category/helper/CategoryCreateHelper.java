package jp.co.valtech.items.api.category.helper;

import jp.co.valtech.items.api.category.util.CategoryUtil;
import jp.co.valtech.items.common.exception.ConflictException;
import jp.co.valtech.items.interfaces.category.requests.CategoryCreateRequest;
import jp.co.valtech.items.interfaces.category.responses.CategoryCreateResponse;
import jp.co.valtech.items.interfaces.definitions.requests.CategoryReq;
import jp.co.valtech.items.rdb.domain.CategoryTbl;
import jp.co.valtech.items.rdb.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * @author uratamanabu
 * @version 1.0
 * @since 1.0
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryCreateHelper {

    private final CategoryService cService;
    private final ModelMapper modelMapper;

    /**
     * @param request Request
     * @return ResponseEntity
     * @throws ConflictException カテゴリーコード重複時
     * @author uratamanabu
     * @since 1.0
     */
    public ResponseEntity<CategoryCreateResponse> execute(
            final CategoryCreateRequest request
    ) throws ConflictException {

        CategoryReq categoryReq = request.getCategory();
        CategoryTbl entity = modelMapper.map(categoryReq, CategoryTbl.class);
        String categoryCode = categoryReq.getCategoryCode();
        CategoryUtil.duplicationCategoryCodeCheck(cService, categoryCode);
        entity.setCode(categoryCode);
        create(entity);
        CategoryCreateResponse response = new CategoryCreateResponse();
        CategoryCreateResponse.CategoryRes categoryRes = response.new CategoryRes();
        categoryRes.setId(entity.getId());
        response.setCategory(categoryRes);
        return new ResponseEntity<>(response, HttpStatus.CREATED);

    }

    private void create(final CategoryTbl entity) {
        cService.insert(entity);
    }
}