package jp.co.valtech.items.rdb.repository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import jp.co.valtech.items.rdb.domain.GoodsTbl;
import jp.co.valtech.items.rdb.repository.custom.GoodsRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author uratamanabu
 * @version 1.0
 * @since 1.0
 */
@Api(tags = "GoodsTbl Entity")
@Repository
@RepositoryRestResource(path = "goods")
public interface GoodsRepository
        extends JpaRepository<GoodsTbl, Long>, GoodsRepositoryCustom {

    /**
     * 商品コードから商品を１件取得します。
     *
     * @param code 商品コード
     * @return GoodsTbl
     */
    @ApiOperation("find by goodsCode")
    Optional<GoodsTbl> findByCode(@Param("code") @ApiParam(value = "code") final String code);

}
