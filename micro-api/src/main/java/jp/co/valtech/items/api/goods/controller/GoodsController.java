package jp.co.valtech.items.api.goods.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jp.co.valtech.items.api.goods.helper.GoodsCreateHelper;
import jp.co.valtech.items.api.goods.helper.GoodsFindHelper;
import jp.co.valtech.items.api.goods.helper.GoodsUpdateHelper;
import jp.co.valtech.items.interfaces.goods.requests.GoodsCreateRequest;
import jp.co.valtech.items.interfaces.goods.requests.GoodsUpdateRequest;
import jp.co.valtech.items.interfaces.goods.responses.GoodsCreateResponse;
import jp.co.valtech.items.interfaces.goods.responses.GoodsFindResponse;
import jp.co.valtech.items.interfaces.goods.responses.GoodsUpdateResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;


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
    private final GoodsFindHelper find;
    private final GoodsUpdateHelper update;


    /**
     * 商品を登録します。
     *
     * @author uratamanabu
     * @version 1.0
     * @since 1.0
     */
    @PostMapping(value = {"/"})
    @ApiOperation(value = "商品を登録します。")
    public ResponseEntity<GoodsCreateResponse> createGoods(
            @RequestBody @Valid @NotNull final GoodsCreateRequest request,
            BindingResult result
    ) {

        if (result.hasErrors()) {
            for (FieldError err : result.getFieldErrors()) {
                log.debug("error code = [" + err.getCode() + "]");
            }
        }

        log.info("create");
        return create.createGoods(request);

    }

    /**
     * 商品を取得します。
     *
     * @author uratamanabu
     * @version 1.0
     * @since 1.0
     */
    @GetMapping(value = {"/{id}"})
    @ApiOperation(value = "商品を取得します。")
    public ResponseEntity<GoodsFindResponse> findGoods(
            @PathVariable("id") @NotNull final String id
    ) {
        log.info("find");
        return find.findGoods(id);
    }

    /**
     * 商品を更新します。
     *
     * @author uratamanabu
     * @version 1.0
     * @since 1.0
     */
    @PutMapping(value = {"/{id}"})
    @ApiOperation(value = "商品を更新します。")
    public ResponseEntity<GoodsUpdateResponse> updateGoods(
            @PathVariable("id") @NotNull final String id,
            @RequestBody @Valid @NotNull final GoodsUpdateRequest request,
            BindingResult result
    ) {

        if (result.hasErrors()) {
            for (FieldError err : result.getFieldErrors()) {
                log.debug("error code = [" + err.getCode() + "]");
            }
        }

        log.info("update");
        return update.updateGoods(id, request);

    }


}
