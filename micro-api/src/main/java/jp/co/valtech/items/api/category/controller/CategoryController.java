package jp.co.valtech.items.api.category.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import jp.co.valtech.items.api.category.helper.CategoryCreateHelper;
import jp.co.valtech.items.api.category.helper.CategoryFindHelper;
import jp.co.valtech.items.api.category.helper.CategoryGetHelper;
import jp.co.valtech.items.common.exception.ConflictException;
import jp.co.valtech.items.common.exception.NotFoundException;
import jp.co.valtech.items.interfaces.category.requests.CategoryCreateRequest;
import jp.co.valtech.items.interfaces.category.requests.CategorySearchRequest;
import jp.co.valtech.items.interfaces.category.responses.CategoryCreateResponse;
import jp.co.valtech.items.interfaces.category.responses.CategoryFindResponse;
import jp.co.valtech.items.interfaces.category.responses.CategoryGetResponse;
import jp.co.valtech.items.interfaces.category.responses.CategorySearchResponse;
import jp.co.valtech.items.interfaces.definitions.responses.ErrorRes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Range;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


/**
 * カテゴリーのエンドポイントを制御します。
 *
 * @author uratamanabu
 * @version 1.0
 * @since 1.0
 */
@Slf4j
@RestController
@RequestMapping(
        value = {"/category/"},
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE
)
@RequiredArgsConstructor
@Api(tags = {"カテゴリーを扱います。"})
public class CategoryController {
    private final CategoryCreateHelper create;
    private final CategoryFindHelper find;
    private final CategoryGetHelper get;

    /**
     * カテゴリーを1件登録します。
     *
     * @param request PostのRequestBody
     * @author uratamanabu
     * @since 1.0
     */
    @PostMapping(value = {"/"})
    @ApiOperation(
            value = "${CategoryController.createCategory.value}",
            notes = "${CategoryController.createCategory.notes}"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(code = 201, message = "", response = CategoryCreateResponse.class),
                    @ApiResponse(code = 400, message = "", response = ErrorRes.class),
                    @ApiResponse(code = 409, message = "", response = ErrorRes.class)
            }
    )
    public ResponseEntity<CategoryCreateResponse> createCategory(
            @RequestBody @Valid final CategoryCreateRequest request
    ) throws ConflictException {

        log.info("create");
        return create.execute(request);

    }

    /**
     * カテゴリーを1件取得します。
     *
     * @param id カテゴリーの識別キー
     * @author uratamanabu
     * @since 1.0
     */
    @GetMapping(value = {"/{id}"})
    @ApiOperation(
            value = "${CategoryController.findCategory.value}",
            notes = "${CategoryController.findCategory.notes}"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "", response = CategoryFindResponse.class),
                    @ApiResponse(code = 400, message = "", response = ErrorRes.class),
                    @ApiResponse(code = 404, message = "", response = ErrorRes.class)
            }
    )
    public ResponseEntity<CategoryFindResponse> findGoods(
            @PathVariable(name = "id") @Range(min = 0, max = 999999999) @ApiParam(example = "1", value = "${CategoryController.findCategory.request.id.value}") final Long id
    ) throws NotFoundException {

        log.info("find");
        return find.execute(id);

    }

    /**
     * カテゴリーを全件取得します。
     *
     * @author uratamanabu
     * @since 1.0
     */
    @GetMapping(value = {"/all"})
    @ApiOperation(
            value = "${CategoryController.getCategory.value}",
            notes = "${CategoryController.getCategory.notes}"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "", response = CategoryGetResponse.class)
            }
    )
    public ResponseEntity<CategoryGetResponse> getCategory() {

        log.info("getAll");
        return get.execute();

    }

    /**
     * 商品を検索取得します。
     *
     * @param request PostのRequestBody
     * @author uratamanabu
     * @since 1.0
     */
    @PostMapping(value = {"/search"})
    @ApiOperation(
            value = "${CategoryController.searchCategory.value}",
            notes = "${CategoryController.searchCategory.notes}"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "", response = CategorySearchResponse.class),
                    @ApiResponse(code = 400, message = "", response = ErrorRes.class)
            }
    )
    public ResponseEntity<CategorySearchResponse> searchCategory(
            @RequestBody @Valid final CategorySearchRequest request
    ) {

        log.info("search");
        return null;

    }

}