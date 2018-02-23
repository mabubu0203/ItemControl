package jp.co.valtech.items.api.handler;

import jp.co.valtech.items.interfaces.definitions.responses.ErrorRes;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class BadRequestHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request
    ) {

        ErrorRes res = new ErrorRes();
        List<ErrorRes.Error_data> errorDataList = new ArrayList<>();
        BindingResult result = ex.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();

        for (FieldError fieldError : fieldErrors) {
            ErrorRes.Error_data error_data = res.new Error_data();
            error_data.setKeyName(fieldError.getField());
            error_data.setMessage(fieldError.getDefaultMessage());
            errorDataList.add(error_data);
        }
        res.setErrorDataList(errorDataList);

        return super.handleExceptionInternal(ex, res, null, HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({ConstraintViolationException.class})
    public final ResponseEntity<Object> handleException(
            ConstraintViolationException ex
    ) {

        ErrorRes res = new ErrorRes();
        List<ErrorRes.Error_data> errorDataList = new ArrayList<>();
        for (ConstraintViolation cv : ex.getConstraintViolations()) {
            ErrorRes.Error_data error_data = res.new Error_data();
            error_data.setKeyName(cv.getPropertyPath().toString());
            error_data.setMessage(cv.getMessage());
            errorDataList.add(error_data);
        }
        res.setErrorDataList(errorDataList);

        return new ResponseEntity<>(res, null, HttpStatus.BAD_REQUEST);
    }

}
