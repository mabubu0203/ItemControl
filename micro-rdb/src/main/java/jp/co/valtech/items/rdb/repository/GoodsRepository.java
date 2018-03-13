package jp.co.valtech.items.rdb.repository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import jp.co.valtech.items.rdb.domain.GoodsTbl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

/**
 * @author uratamanabu
 * @version 1.0
 * @since 1.0
 */
@Api(tags = "GoodsTbl Entity")
@RepositoryRestResource(path = "goods")
public interface GoodsRepository
        extends JpaRepository<GoodsTbl, Long> {

    /**
     * @param code 商品コード
     * @return GoodsTbl
     */
    @ApiOperation("find by goodsCode")
    Optional<GoodsTbl> findByCode(@Param("code") @ApiParam(value = "code") final String code);

}
