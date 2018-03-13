package jp.co.valtech.items.rdb.repository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import jp.co.valtech.items.rdb.domain.CategoryStatusTbl;
import jp.co.valtech.items.rdb.domain.GoodsStatusTbl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * @author uratamanabu
 * @version 1.0
 * @since 1.0
 */
@Api(tags = "CategoryStatusTbl Entity")
@RepositoryRestResource(path = "category")
public interface CategoryStatusRepository
        extends JpaRepository<CategoryStatusTbl, Long> {

    /**
     * @param categoryId カテゴリーID
     * @return GoodsStatusTbl
     */
    @ApiOperation("find by categoryId")
    GoodsStatusTbl findByCategoryId(@Param("categoryId") @ApiParam(value = "categoryId") final Long categoryId);

}
