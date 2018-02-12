package jp.co.valtech.items.api.handler;

import jp.co.valtech.items.common.exception.ConflictException;
import jp.co.valtech.items.common.exception.NotFoundException;
import jp.co.valtech.items.interfaces.definitions.responses.ErrorRes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class NotFoundHandler {

    @ExceptionHandler({NotFoundException.class})
    public final ResponseEntity<Object> handleException(
            NotFoundException ex,
            WebRequest request
    ) {
        ErrorRes res = new ErrorRes();
        List<ErrorRes.Error_data> errorDataList = new ArrayList<>();
        ErrorRes.Error_data error_data = res.new Error_data();
        error_data.setKeyName(ex.getKeyName());
        error_data.setMessage(ex.getMessage());
        errorDataList.add(error_data);
        res.setErrorDataList(errorDataList);
        return new ResponseEntity<>(res, null, HttpStatus.NOT_FOUND);
    }
}
