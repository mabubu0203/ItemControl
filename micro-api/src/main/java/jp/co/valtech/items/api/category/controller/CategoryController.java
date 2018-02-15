package jp.co.valtech.items.api.category.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import jp.co.valtech.items.interfaces.goods.responses.GoodsGetResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(
        value = {"/category/"},
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE
)
@RequiredArgsConstructor
@Api(description = "カテゴリーを扱います。")
public class CategoryController {

    /**
     * カテゴリーを全件取得します。
     *
     * @author uratamanabu
     * @version 1.0
     * @since 1.0
     */
    // TODO:未実装
    @GetMapping(value = {"/all"})
    @ApiOperation(value = "${GoodsController.getGoods.value}", notes = "${GoodsController.getGoods.notes}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "", response = GoodsGetResponse.class)
    })
    public ResponseEntity<GoodsGetResponse> getAllGoods() {

        log.info("getAll");
        return null;

    }

}
