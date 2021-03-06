package jp.co.valtech.items.rdb.repository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import jp.co.valtech.items.rdb.domain.GoodsStatusTbl;
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
public interface GoodsStatusRepository
        extends JpaRepository<GoodsStatusTbl, Long> {

    /**
     * 商品IDから商品を１件取得します。
     *
     * @param goodsId 商品ID
     * @return Optional
     */
    @ApiOperation("find by goodsId")
    Optional<GoodsStatusTbl> findByGoodsId(@Param("goodsId") @ApiParam(value = "goodsId") final Long goodsId);

}
