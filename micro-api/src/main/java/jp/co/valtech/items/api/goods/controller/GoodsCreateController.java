package jp.co.valtech.items.api.goods.controller;

import jp.co.valtech.items.api.goods.helper.GoodsCreateHelper;
import jp.co.valtech.items.interfaces.goods.requests.GoodsCreateRequest;
import jp.co.valtech.items.interfaces.goods.responses.GoodsCreateResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequestMapping(
        value = {"/goods/"},
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE
)
@AllArgsConstructor
public class GoodsCreateController {

    private final GoodsCreateHelper helper;

    /**
     * 商品をRDBに保存します。
     *
     * @author uratamanabu
     * @version 1.0
     * @since 1.0
     */
    @PostMapping(value = {"/"})
    public ResponseEntity<GoodsCreateResponse> createGoods(
            @RequestBody @Validated final GoodsCreateRequest request) {
        log.info("create");
        return helper.createGoods(request);
    }
}
