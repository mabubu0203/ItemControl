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
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryCreateHelper {

    private final CategoryService service;
    private final ModelMapper modelMapper;

    public ResponseEntity<CategoryCreateResponse> execute(
            final CategoryCreateRequest request
    ) throws ConflictException {

        CategoryReq categoryReq = request.getCategory();
        Optional<CategoryTbl> optionalCode = service.findByCode(categoryReq.getCode());
        if (optionalCode.isPresent()) {// code(unique制約)の存在チェック
            throw new ConflictException("code", "CODEが重複しています。");
        }
        CategoryTbl entity = modelMapper.map(categoryReq, CategoryTbl.class);
        create(entity);
        CategoryCreateResponse response = new CategoryCreateResponse();
        CategoryCreateResponse.CategoryRes categoryRes = response.new CategoryRes();
        categoryRes.setId(entity.getId());
        response.setCategoryRes(categoryRes);
        return new ResponseEntity<>(response, HttpStatus.CREATED);

    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    private void create(final CategoryTbl entity) {
        service.insert(entity);
    }
}
