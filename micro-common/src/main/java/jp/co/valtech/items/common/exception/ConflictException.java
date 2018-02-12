package jp.co.valtech.items.common.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ConflictException extends Exception {
    private String KeyName;
    private String Message;
}
