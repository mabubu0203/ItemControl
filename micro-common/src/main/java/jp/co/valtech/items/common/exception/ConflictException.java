package jp.co.valtech.items.common.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author uratamanabu
 * @version 1.0
 * @since 1.0
 */
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class ConflictException
        extends Exception {
    private final String keyName;
    private final String message;
}
