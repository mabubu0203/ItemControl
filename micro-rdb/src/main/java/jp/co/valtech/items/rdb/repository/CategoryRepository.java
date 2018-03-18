package jp.co.valtech.items.rdb.repository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import jp.co.valtech.items.rdb.domain.CategoryTbl;
import jp.co.valtech.items.rdb.repository.custom.CategoryRepositoryCustom;
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
@Api(tags = "CategoryTbl Entity")
@Repository
@RepositoryRestResource(path = "category")
public interface CategoryRepository
        extends JpaRepository<CategoryTbl, Long>, CategoryRepositoryCustom {

    /**
     * カテゴリーコードからカテゴリーを１件取得します。
     *
     * @param code カテゴリーコード
     * @return Optional
     */
    @ApiOperation("find by categoryCode")
    Optional<CategoryTbl> findByCode(@Param("code") @ApiParam(value = "code") final String code);

}
