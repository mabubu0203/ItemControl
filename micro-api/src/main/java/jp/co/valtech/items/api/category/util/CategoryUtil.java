package jp.co.valtech.items.api.category.util;

import jp.co.valtech.items.common.exception.ConflictException;
import jp.co.valtech.items.rdb.domain.CategoryTbl;
import jp.co.valtech.items.rdb.service.CategoryService;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Optional;

/**
 * @author uratamanabu
 * @version 1.0
 * @since 1.0
 */
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

}