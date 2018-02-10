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
import jp.co.valtech.items.api.goods.helper.GoodsUpdateHelper;
import jp.co.valtech.items.interfaces.goods.requests.GoodsCreateRequest;
import jp.co.valtech.items.interfaces.goods.requests.GoodsDeleteRequest;
import jp.co.valtech.items.interfaces.goods.requests.GoodsUpdateRequest;
import jp.co.valtech.items.interfaces.goods.responses.GoodsCreateResponse;
import jp.co.valtech.items.interfaces.goods.responses.GoodsFindResponse;
import jp.co.valtech.items.interfaces.goods.responses.GoodsGetResponse;
import jp.co.valtech.items.interfaces.goods.responses.GoodsUpdateResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@Slf4j
@RestController
@RequestMapping(
        value = {"/goods/"},
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE
)
@AllArgsConstructor
@Api(description = "商品を扱います。")
public class GoodsController {
    private final GoodsCreateHelper create;
    private final GoodsDeleteHelper delete;
    private final GoodsFindHelper find;
    private final GoodsGetHelper get;
    private final GoodsUpdateHelper update;


    /**
     * 商品を1件登録します。
     *
     * @author uratamanabu
     * @version 1.0
     * @since 1.0
     */
    @PostMapping(value = {"/"})
    @ApiOperation(value = "${GoodsController.createGoods.value}", notes = "${GoodsController.createGoods.notes}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = GoodsCreateResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    public ResponseEntity<GoodsCreateResponse> createGoods(
            @RequestBody @Valid final GoodsCreateRequest request,
            final BindingResult result
    ) {

        if (result.hasErrors()) {
            for (FieldError err : result.getFieldErrors()) {
                log.debug("error code = [" + err.getCode() + "]");
            }
        }

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
    @ApiOperation(value = "${GoodsController.deleteGoods.value}", notes = "${GoodsController.deleteGoods.notes}")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "No Content"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    public ResponseEntity deleteGoods(
            @PathVariable(name = "id") @ApiParam(example = "1", value = "${GoodsController.deleteGoods.request.id.value}") final String id,
            @RequestBody @Valid final GoodsDeleteRequest request,
            final BindingResult result
    ) {
        if (result.hasErrors()) {
            for (FieldError err : result.getFieldErrors()) {
                log.debug("error code = [" + err.getCode() + "]");
            }
        }

        log.info("delete");
        return delete.execute(id, request);
    }

    /**
     * 商品を全件取得します。
     *
     * @author uratamanabu
     * @version 1.0
     * @since 1.0
     */
    @GetMapping(value = {"/"})
    @ApiOperation(value = "${GoodsController.getGoods.value}", notes = "${GoodsController.getGoods.notes}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = GoodsGetResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
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
    @ApiOperation(value = "${GoodsController.findGoods.value}", notes = "${GoodsController.findGoods.notes}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = GoodsFindResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    public ResponseEntity<GoodsFindResponse> findGoods(
            @PathVariable(name = "id") @ApiParam(example = "1", value = "${GoodsController.findGoods.request.id.value}") final String id
    ) {
        log.info("find");
        return find.execute(id);
    }

    /**
     * 商品を1件更新します。
     *
     * @author uratamanabu
     * @version 1.0
     * @since 1.0
     */
    @PutMapping(value = {"/{id}"})
    @ApiOperation(value = "${GoodsController.updateGoods.value}", notes = "${GoodsController.updateGoods.notes}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = GoodsUpdateResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    public ResponseEntity<GoodsUpdateResponse> updateGoods(
            @PathVariable(name = "id") @ApiParam(example = "1", value = "${GoodsController.updateGoods.request.id.value}") final String id,
            @RequestBody @Valid final GoodsUpdateRequest request,
            final BindingResult result
    ) {

        if (result.hasErrors()) {
            for (FieldError err : result.getFieldErrors()) {
                log.debug("error code = [" + err.getCode() + "]");
            }
        }

        log.info("update");
        return update.execute(id, request);

    }


}
