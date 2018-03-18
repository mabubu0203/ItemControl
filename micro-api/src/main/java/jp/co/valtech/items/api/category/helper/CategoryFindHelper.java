package jp.co.valtech.items.api.category.helper;

import jp.co.valtech.items.api.category.util.CategoryUtil;
import jp.co.valtech.items.common.exception.NotFoundException;
import jp.co.valtech.items.interfaces.category.responses.CategoryFindResponse;
import jp.co.valtech.items.rdb.domain.CategoryStatusTbl;
import jp.co.valtech.items.rdb.domain.CategoryTbl;
import jp.co.valtech.items.rdb.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
public class CategoryFindHelper {

    private final CategoryService cService;

    /**
     * @param id カテゴリーの識別key
     * @return ResponseEntity
     * @throws NotFoundException 　カテゴリーが取得できない時
     * @author uratamanabu
     * @since 1.0
     */
    public ResponseEntity<CategoryFindResponse> execute(
            final Long id
    ) throws NotFoundException {

        CategoryTbl entity = CategoryUtil.findById(cService, id);
        CategoryFindResponse response = new CategoryFindResponse();
        entityToResponse(entity, response);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    private void entityToResponse(
            final CategoryTbl entity,
            final CategoryFindResponse response
    ) {

        CategoryFindResponse.CategoryDetail category = response.new CategoryDetail();
        CategoryUtil.entityToResponse(entity, category);
        category.setCategoryCode(entity.getCode());
        category.setNote(entity.getNote());
        CategoryStatusTbl statusTbl = entity.getStatusTbl();
        category.setCreateDatetime(statusTbl.getCreateDatetime());
        category.setUpdateDatetime(statusTbl.getUpdateDatetime());
        response.setCategory(category);

    }
}