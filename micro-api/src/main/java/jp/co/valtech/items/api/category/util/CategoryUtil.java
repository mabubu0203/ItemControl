package jp.co.valtech.items.api.category.util;

import jp.co.valtech.items.common.exception.ConflictException;
import jp.co.valtech.items.rdb.domain.CategoryTbl;
import jp.co.valtech.items.rdb.service.CategoryService;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CategoryUtil {

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
