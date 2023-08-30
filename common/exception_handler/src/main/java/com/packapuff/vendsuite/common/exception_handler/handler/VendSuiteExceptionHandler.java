package com.packapuff.vendsuite.common.exception_handler.handler;

import com.packapuff.vendsuite.common.error_library.constants.VendSuiteErrorLibrary;
import com.packapuff.vendsuite.common.exception_handler.exception.VendSuiteException;
import com.packapuff.vendsuite.common.exception_handler.service.VendSuiteExceptionGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.List;

public class VendSuiteExceptionHandler {

    @Autowired
    VendSuiteExceptionGenerator vendSuiteExceptionGenerator;

    @ExceptionHandler(VendSuiteException.class)
    public ResponseEntity<VendSuiteException> handleVendSuiteException(
            VendSuiteException vendSuiteException
    ) {
        return new ResponseEntity<>(vendSuiteException, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<VendSuiteException> handleValidationErrors(
            MethodArgumentNotValidException ex
    ) {
        List<FieldError> fieldErrorList = ((MethodArgumentNotValidException) ex)
                .getBindingResult().getFieldErrors();

        String errorCode = VendSuiteErrorLibrary.VEND_SUITE_GENERIC_INVALID_INPUT.getError().getErrorCode();
        String errorMessage = VendSuiteErrorLibrary.VEND_SUITE_GENERIC_INVALID_INPUT.getError().getErrorMessage();
        if (null != fieldErrorList.get(0)) {
            String temp = fieldErrorList.get(0).getDefaultMessage();
            if (null != temp) {
                temp = temp.strip();
                temp = temp.replace(";", "");
                errorCode = temp.split(": ")[0];
                errorMessage = temp.split(": ")[1];
            }
        }

        VendSuiteException vendSuiteException =
                VendSuiteExceptionGenerator.createVendSuiteException(
                        errorCode,
                        errorMessage,
                        null,
                        HttpStatus.INTERNAL_SERVER_ERROR
            );

        return new ResponseEntity<>(vendSuiteException, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MissingRequestHeaderException.class)
    public ResponseEntity<VendSuiteException> handleHeaderMissingException(
            MissingRequestHeaderException ex
    ) {
        String errorCode = VendSuiteErrorLibrary.VEND_SUITE_GENERIC_MISSING_HEADER.getError().getErrorCode();
        String errorMessage = String.format(
                VendSuiteErrorLibrary.VEND_SUITE_GENERIC_MISSING_HEADER.getError().getErrorMessage(),
                ex.getHeaderName());

        VendSuiteException vendSuiteException =
                VendSuiteExceptionGenerator.createVendSuiteException(
                        errorCode,
                        errorMessage,
                        null,
                        HttpStatus.BAD_REQUEST
                );

        return new ResponseEntity<>(vendSuiteException, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<VendSuiteException> handleMethodArgumentTypeMismatchException(
            final MethodArgumentTypeMismatchException ex
    ) {
        final String errorMessageValue = ex.getMessage().split("; ")[1];
        String errorCode = VendSuiteErrorLibrary.VEND_SUITE_GENERIC_INVALID_INPUT.getError().getErrorCode();
        String errorMessage = VendSuiteErrorLibrary.VEND_SUITE_GENERIC_INVALID_INPUT.getError().getErrorMessage();

        VendSuiteException vendSuiteException =
                VendSuiteExceptionGenerator.createVendSuiteException(
                        errorCode,
                        String.format(errorMessage, errorMessageValue),
                        null,
                        HttpStatus.BAD_REQUEST
                );

        return new ResponseEntity<>(vendSuiteException, HttpStatus.BAD_REQUEST);
    }
}