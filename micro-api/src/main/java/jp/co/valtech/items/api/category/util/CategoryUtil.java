package jp.co.valtech.items.api.category.util;

import jp.co.valtech.items.common.exception.ConflictException;
import jp.co.valtech.items.common.exception.NotFoundException;
import jp.co.valtech.items.interfaces.definitions.responses.CategoryRes;
import jp.co.valtech.items.rdb.domain.CategoryStatusTbl;
import jp.co.valtech.items.rdb.domain.CategoryTbl;
import jp.co.valtech.items.rdb.service.CategoryService;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.NoResultException;
import java.util.Optional;

/**
 * @author uratamanabu
 * @version 1.0
 * @since 1.0
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CategoryUtil {

    /**
     * カテゴリーコードの重複チェックを実施します。
     * カテゴリーコードが存在する時、ConflictExceptionを発行します。
     *
     * @param service      サービス
     * @param categoryCode カテゴリーコード
     * @throws ConflictException 重複時
     * @author uratamanabu
     * @since 1.0
     */
    public static void duplicationCategoryCodeCheck(
            final CategoryService service,
            final String categoryCode
    ) throws ConflictException {

        Optional<CategoryTbl> optionalCode = service.findByCode(categoryCode);
        if (optionalCode.isPresent()) {// categoryCode(unique制約)の存在チェック
            throw new ConflictException("categoryCode", "CODEが重複しています。");
        }

    }

    /**
     * Entity → Responseに変換します。
     *
     * @param entity      Entity
     * @param categoryRes Response
     * @author uratamanabu
     * @since 1.0
     */
    public static void entityToResponse(
            final CategoryTbl entity,
            final CategoryRes categoryRes
    ) {

        categoryRes.setId(entity.getId());
        categoryRes.setName(entity.getName());
        CategoryStatusTbl statusTbl = entity.getStatusTbl();
        categoryRes.setVersion(statusTbl.getVersion());

    }

    /**
     * カテゴリーIDからカテゴリーを１件取得します。
     * 取得できない時、NotFoundExceptionを発行します。
     *
     * @param service サービス
     * @param id      カテゴリーの識別key
     * @return CategoryTbl
     * @throws NotFoundException 存在しない時
     * @author uratamanabu
     * @since 1.0
     */
    public static CategoryTbl findById(
            final CategoryService service,
            final Long id
    ) throws NotFoundException {

        try {
            return service.findById(id);
        } catch (NoResultException e) {
            log.info(e.toString());
            throw new NotFoundException("id", "IDが存在しません。");
        }
    }

    /**
     * @param service      サービス
     * @param categoryCode カテゴリーコード
     * @throws NotFoundException
     * @author uratamanabu
     * @since 1.0
     */
    public static CategoryTbl findByCategoryCode(
            final CategoryService service,
            final String categoryCode
    ) throws NotFoundException {

        return service
                .findByCode(categoryCode)
                .orElseThrow(() -> new NotFoundException("code", "CODEが存在しません。"));

    }


}