package jp.co.valtech.items.api.goods.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import jp.co.valtech.items.api.goods.helper.GoodsCreateHelper;
import jp.co.valtech.items.api.goods.helper.GoodsDeleteHelper;
import jp.co.valtech.items.api.goods.helper.GoodsFindHelper;
import jp.co.valtech.items.api.goods.helper.GoodsGetHelper;
import jp.co.valtech.items.api.goods.helper.GoodsSearchHelper;
import jp.co.valtech.items.api.goods.helper.GoodsUpdateHelper;
import jp.co.valtech.items.common.exception.ConflictException;
import jp.co.valtech.items.common.exception.NotFoundException;
import jp.co.valtech.items.interfaces.definitions.responses.ErrorRes;
import jp.co.valtech.items.interfaces.goods.requests.GoodsCreateRequest;
import jp.co.valtech.items.interfaces.goods.requests.GoodsUpdateRequest;
import jp.co.valtech.items.interfaces.goods.responses.GoodsCreateResponse;
import jp.co.valtech.items.interfaces.goods.responses.GoodsFindResponse;
import jp.co.valtech.items.interfaces.goods.responses.GoodsGetResponse;
import jp.co.valtech.items.interfaces.goods.responses.GoodsSearchResponse;
import jp.co.valtech.items.interfaces.goods.responses.GoodsUpdateResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Range;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping(
        value = {"/goods/"},
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE
)
@RequiredArgsConstructor
@Api(description = "商品を扱います。")
public class GoodsController {
    private final GoodsCreateHelper create;
    private final GoodsDeleteHelper delete;
    private final GoodsFindHelper find;
    private final GoodsGetHelper get;
    private final GoodsSearchHelper search;
    private final GoodsUpdateHelper update;

    /**
     * 商品を1件登録します。
     *
     * @author uratamanabu
     * @version 1.0
     * @since 1.0
     */
    @PostMapping(value = {"/"})
    @ApiOperation(
            value = "${GoodsController.createGoods.value}",
            notes = "${GoodsController.createGoods.notes}"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(code = 201, message = "", response = GoodsCreateResponse.class),
                    @ApiResponse(code = 400, message = "", response = ErrorRes.class),
                    @ApiResponse(code = 409, message = "", response = ErrorRes.class)
            }
    )
    public ResponseEntity<GoodsCreateResponse> createGoods(
            @RequestBody @Valid final GoodsCreateRequest request
    ) throws ConflictException {

        log.info("create");
        return create.execute(request);

    }

    /**
     * 商品を1件削除します。
     *
     * @author uratamanabu
     * @version 1.0
     * @since 1.0
     */
    @DeleteMapping(value = {"/{id}"})
    @ApiOperation(
            value = "${GoodsController.deleteGoods.value}",
            notes = "${GoodsController.deleteGoods.notes}"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(code = 204, message = ""),
                    @ApiResponse(code = 400, message = "", response = ErrorRes.class),
                    @ApiResponse(code = 404, message = "", response = ErrorRes.class),
                    @ApiResponse(code = 409, message = "", response = ErrorRes.class)
            }
    )
    public ResponseEntity deleteGoods(
            @PathVariable(name = "id") @Range(min = 0, max = 999999999) @ApiParam(example = "1", value = "${GoodsController.deleteGoods.request.id.value}") final long id,
            @RequestParam(name = "version") @ApiParam(example = "1", value = "${GoodsController.deleteGoods.request.version.value}") final int version
    ) throws NotFoundException, ConflictException {

        log.info("delete");
        return delete.execute(id, version);

    }

    /**
     * 商品を全件取得します。
     *
     * @author uratamanabu
     * @version 1.0
     * @since 1.0
     */
    @GetMapping(value = {"/all"})
    @ApiOperation(
            value = "${GoodsController.getGoods.value}",
            notes = "${GoodsController.getGoods.notes}"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "", response = GoodsGetResponse.class)
            }
    )
    public ResponseEntity<GoodsGetResponse> getGoods() {

        log.info("get");
        return get.execute();

    }

    /**
     * 商品を1件取得します。
     *
     * @author uratamanabu
     * @version 1.0
     * @since 1.0
     */
    @GetMapping(value = {"/{id}"})
    @ApiOperation(
            value = "${GoodsController.findGoods.value}",
            notes = "${GoodsController.findGoods.notes}"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "", response = GoodsFindResponse.class),
                    @ApiResponse(code = 400, message = "", response = ErrorRes.class),
                    @ApiResponse(code = 404, message = "", response = ErrorRes.class)
            }
    )
    public ResponseEntity<GoodsFindResponse> findGoods(
            @PathVariable(name = "id") @Range(min = 0, max = 999999999) @ApiParam(example = "1", value = "${GoodsController.findGoods.request.id.value}") final long id
    ) throws NotFoundException {

        log.info("find");
        return find.execute(id);

    }

    /**
     * 商品を検索取得します。
     *
     * @author uratamanabu
     * @version 1.0
     * @since 1.0
     */
    @GetMapping(value = {"/search"})
    @ApiOperation(
            value = "${GoodsController.searchGoods.value}",
            notes = "${GoodsController.searchGoods.notes}"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "", response = GoodsGetResponse.class),
                    @ApiResponse(code = 400, message = "", response = ErrorRes.class)
            }
    )
    public ResponseEntity<GoodsSearchResponse> searchGoods(
            @RequestBody @Valid final GoodsSearchHelper request
    ) {

        log.info("search");
        return search.execute(request);

    }

    /**
     * 商品を1件更新します。
     *
     * @author uratamanabu
     * @version 1.0
     * @since 1.0
     */
    @PutMapping(value = {"/{id}"})
    @ApiOperation(
            value = "${GoodsController.updateGoods.value}",
            notes = "${GoodsController.updateGoods.notes}"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "", response = GoodsUpdateResponse.class),
                    @ApiResponse(code = 400, message = "", response = ErrorRes.class),
                    @ApiResponse(code = 404, message = "", response = ErrorRes.class),
                    @ApiResponse(code = 409, message = "", response = ErrorRes.class)
            }
    )
    public ResponseEntity<GoodsUpdateResponse> updateGoods(
            @PathVariable(name = "id") @Range(min = 0, max = 999999999) @ApiParam(example = "1", value = "${GoodsController.updateGoods.request.id.value}") final long id,
            @RequestBody @Valid final GoodsUpdateRequest request
    ) throws ConflictException, NotFoundException {

        log.info("update");
        return update.execute(id, request);

    }

}
