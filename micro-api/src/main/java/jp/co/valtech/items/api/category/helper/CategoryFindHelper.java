package jp.co.valtech.items.api.category.helper;

import jp.co.valtech.items.common.exception.NotFoundException;
import jp.co.valtech.items.interfaces.category.responses.CategoryFindResponse;
import jp.co.valtech.items.interfaces.definitions.responses.CategoryRes;
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
public class CategoryFindHelper {

    private final CategoryService service;
    private final ModelMapper modelMapper;

    public ResponseEntity<CategoryFindResponse> execute(
            final Long id
    ) throws NotFoundException {

        Optional<CategoryTbl> optionalId = service.findById(id);
        CategoryTbl entity = optionalId
                .orElseThrow(() -> new NotFoundException("id", "IDが存在しません。"));
        CategoryRes categoryRes = modelMapper.map(entity, CategoryRes.class);
        categoryRes.setCategoryCode(entity.getCode());
        modelMapper.map(entity.getStatusTbl(), categoryRes);
        CategoryFindResponse response = new CategoryFindResponse();
        response.setCategory(categoryRes);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }
}
