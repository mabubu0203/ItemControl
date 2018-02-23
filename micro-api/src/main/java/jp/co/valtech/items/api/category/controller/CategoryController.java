package jp.co.valtech.items.api.category.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import jp.co.valtech.items.api.category.helper.CategoryCreateHelper;
import jp.co.valtech.items.common.exception.ConflictException;
import jp.co.valtech.items.interfaces.category.requests.CategoryCreateRequest;
import jp.co.valtech.items.interfaces.category.responses.CategoryCreateResponse;
import jp.co.valtech.items.interfaces.definitions.responses.ErrorRes;
import jp.co.valtech.items.interfaces.goods.responses.GoodsGetResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping(
        value = {"/category/"},
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE
)
@RequiredArgsConstructor
@Api(description = "カテゴリーを扱います。")
public class CategoryController {
    private final CategoryCreateHelper create;

    /**
     * カテゴリーを1件登録します。
     *
     * @author uratamanabu
     * @version 1.0
     * @since 1.0
     */
    @PostMapping(value = {"/"})
    @ApiOperation(
            value = "${CategoryController.createCategory.value}",
            notes = "${CategoryController.createCategory.notes}"
    )
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "", response = CategoryCreateResponse.class),
            @ApiResponse(code = 400, message = "", response = ErrorRes.class),
            @ApiResponse(code = 409, message = "", response = ErrorRes.class)
    })
    public ResponseEntity<CategoryCreateResponse> createCategory(
            @RequestBody @Valid final CategoryCreateRequest request
    ) throws ConflictException {

        log.info("create");
        return create.execute(request);

    }

    /**
     * カテゴリーを全件取得します。
     *
     * @author uratamanabu
     * @version 1.0
     * @since 1.0
     */
    // TODO:未実装
    @GetMapping(value = {"/all"})
    @ApiOperation(
            value = "${CategoryController.getCategory.value}",
            notes = "${CategoryController.getCategory.notes}"
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "", response = GoodsGetResponse.class)
    })
    public ResponseEntity<GoodsGetResponse> getAllCategory() {

        log.info("getAll");
        return null;

    }

}
