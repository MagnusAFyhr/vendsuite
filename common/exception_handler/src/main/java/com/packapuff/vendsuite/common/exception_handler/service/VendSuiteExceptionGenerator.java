package com.packapuff.vendsuite.common.exception_handler.service;

import com.packapuff.vendsuite.common.error_library.model.VendSuiteError;
import com.packapuff.vendsuite.common.exception_handler.exception.VendSuiteException;
import org.springframework.http.HttpStatus;

public class VendSuiteExceptionGenerator {

    public static VendSuiteException createVendSuiteException(VendSuiteError vendSuiteError) {
        return createVendSuiteExceptionWithErrorCodeAndErrorMessage(
                vendSuiteError.getErrorCode(),
                vendSuiteError.getErrorMessage(),
                vendSuiteError.getHttpStatus()
        );
    }
    public static VendSuiteException createVendSuiteException(
            String errorCode,
            String errorMessage,
            Throwable cause,
            HttpStatus httpStatus
    ) {
        if (errorCode.isBlank() && errorMessage.isBlank() && cause != null) {
            return createVendSuiteExceptionWithErrorCodeAndErrorMessageAndCause(
                    errorCode, errorMessage, cause, httpStatus
            );
        }
        if (errorCode.isBlank() && errorMessage.isBlank() && cause == null) {
            return createVendSuiteExceptionWithErrorCodeAndErrorMessage(
                    errorCode, errorMessage, httpStatus
            );
        }
        if (cause != null) {
            return createVendSuiteExceptionWithCause(cause, httpStatus);
        }

        return createVendSuiteExceptionWithDefaultConstructor();
    }

    private static VendSuiteException createVendSuiteExceptionWithCause(
            Throwable cause,
            HttpStatus httpStatus
    ) {
        return new VendSuiteException(cause, httpStatus);
    }

    private static VendSuiteException createVendSuiteExceptionWithDefaultConstructor() {
        return new VendSuiteException();
    }

    private static VendSuiteException createVendSuiteExceptionWithErrorCodeAndErrorMessage(
            String errorCode, String errorMessage,
             HttpStatus httpStatus
    ) {
        return new VendSuiteException(errorCode, errorMessage, httpStatus);
    }

    private static VendSuiteException createVendSuiteExceptionWithErrorCodeAndErrorMessageAndCause(
            String errorCode, String errorMessage,
            Throwable cause, HttpStatus httpStatus
    ) {
        return new VendSuiteException(errorCode, errorMessage, cause, httpStatus);
    }
}