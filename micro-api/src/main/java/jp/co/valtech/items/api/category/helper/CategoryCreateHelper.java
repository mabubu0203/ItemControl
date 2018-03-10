package jp.co.valtech.items.api.category.helper;

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

import java.util.Optional;

/**
 * @author uratamanabu
 * @version 1.0
 * @since 1.0
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryCreateHelper {

    private final CategoryService service;
    private final ModelMapper modelMapper;

    /**
     * @param request
     * @return
     * @throws ConflictException
     * @author uratamanabu
     * @since 1.0
     */
    public ResponseEntity<CategoryCreateResponse> execute(
            final CategoryCreateRequest request
    ) throws ConflictException {

        CategoryReq categoryReq = request.getCategory();
        String categoryCode = categoryReq.getCategoryCode();
        Optional<CategoryTbl> optionalCode = service.findByCode(categoryCode);
        if (optionalCode.isPresent()) {// categoryCode(unique制約)の存在チェック
            throw new ConflictException("categoryCode", "CODEが重複しています。");
        }
        CategoryTbl entity = modelMapper.map(categoryReq, CategoryTbl.class);
        entity.setCode(categoryCode);
        create(entity);
        CategoryCreateResponse response = new CategoryCreateResponse();
        CategoryCreateResponse.CategoryRes categoryRes = response.new CategoryRes();
        categoryRes.setId(entity.getId());
        response.setCategory(categoryRes);
        return new ResponseEntity<>(response, HttpStatus.CREATED);

    }

    private void create(final CategoryTbl entity) {
        service.insert(entity);
    }
}