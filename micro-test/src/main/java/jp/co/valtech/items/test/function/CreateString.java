package jp.co.valtech.items.test.function;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

/**
 * @author uratamanabu
 * @version 1.0
 * @since 1.0
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class CreateString {

    public static String loopStr(String str, int length) {
        String retStr = StringUtils.EMPTY;
        for (int i = 0; i <= length; i++) {
            retStr += str;
        }
        return retStr;
    }
}
