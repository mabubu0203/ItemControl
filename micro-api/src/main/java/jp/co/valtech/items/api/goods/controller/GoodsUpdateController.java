package jp.co.valtech.items.api.goods.controller;

import io.swagger.annotations.Api;
import jp.co.valtech.items.api.goods.helper.GoodsUpdateHelper;
import jp.co.valtech.items.interfaces.goods.requests.GoodsUpdateRequest;
import jp.co.valtech.items.interfaces.goods.responses.GoodsUpdateResponse;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@Api(description = "商品を更新します。")
@Slf4j
@RestController
@RequestMapping(
        value = {"/goods/"},
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE
)
@AllArgsConstructor
public class GoodsUpdateController {

    private final GoodsUpdateHelper helper;

    /**
     * 商品を更新します。
     *
     * @author uratamanabu
     * @version 1.0
     * @since 1.0
     */
    @PutMapping(value = {"/{id}"})
    public ResponseEntity<GoodsUpdateResponse> updateGoods(
            @PathVariable("id") @NonNull final Optional<String> id,
            @RequestBody @Validated final GoodsUpdateRequest request
    ) {

        log.info("update");
        return helper.updateGoods(id.get(), request);

    }
}