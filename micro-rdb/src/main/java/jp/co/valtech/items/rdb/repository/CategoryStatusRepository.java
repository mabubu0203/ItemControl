package jp.co.valtech.items.rdb.repository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import jp.co.valtech.items.rdb.domain.CategoryStatusTbl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

/**
 * @author uratamanabu
 * @version 1.0
 * @since 1.0
 */
@Api(tags = "CategoryStatusTbl Entity")
@Repository
@RepositoryRestResource(path = "category")
public interface CategoryStatusRepository
        extends JpaRepository<CategoryStatusTbl, Long> {

    /**
     * カテゴリーIDからカテゴリーを１件取得します。
     *
     * @param categoryId カテゴリーID
     * @return CategoryStatusTbl
     */
    @ApiOperation("find by categoryId")
    CategoryStatusTbl findByCategoryId(@Param("categoryId") @ApiParam(value = "categoryId") final Long categoryId);

}
