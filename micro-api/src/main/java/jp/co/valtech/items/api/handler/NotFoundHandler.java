package jp.co.valtech.items.api.handler;

import jp.co.valtech.items.common.exception.NotFoundException;
import jp.co.valtech.items.interfaces.definitions.responses.ErrorRes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

/**
 * @author uratamanabu
 * @version 1.0
 * @since 1.0
 */
@RestControllerAdvice
public class NotFoundHandler {

    /**
     * 404エラーを返却します。
     *
     * @param ex
     * @return ResponseEntity
     * @author uratamanabu
     * @since 1.0
     */
    @ExceptionHandler({NotFoundException.class})
    public final ResponseEntity<Object> handleException(
            final NotFoundException ex
    ) {

        ErrorRes res = new ErrorRes();
        List<ErrorRes.ErrorData> errorDataList = new ArrayList<>();
        ErrorRes.ErrorData errorData = res.new ErrorData();
        errorData.setKeyName(ex.getKeyName());
        errorData.setMessage(ex.getMessage());
        errorDataList.add(errorData);
        res.setErrorDataList(errorDataList);
        return new ResponseEntity<>(res, null, HttpStatus.NOT_FOUND);

    }
}
