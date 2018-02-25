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

import java.util.ArrayList;
import java.util.List;

/**
 * @author uratamanabu
 * @version 1.0
 * @since 1.0
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryGetHelper {

    private final CategoryService service;
    private final ModelMapper modelMapper;

    public ResponseEntity<CategoryGetResponse> execute() {

        List<CategoryTbl> entities = service.getAll();
        List<CategoryRes> categoryList = new ArrayList<>();
        for (CategoryTbl entity : entities) {
            CategoryRes categoryRes = modelMapper.map(entity, CategoryRes.class);
            categoryRes.setCategoryCode(entity.getCode());
            modelMapper.map(entity.getStatusTbl(), categoryRes);
            categoryList.add(categoryRes);
        }
        CategoryGetResponse response = new CategoryGetResponse();
        response.setCategoryList(categoryList);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

}
