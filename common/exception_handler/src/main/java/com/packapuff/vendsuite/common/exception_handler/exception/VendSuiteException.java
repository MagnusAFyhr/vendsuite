package com.packapuff.vendsuite.common.exception_handler.exception;

import com.packapuff.vendsuite.common.error_library.model.VendSuiteError;
import org.springframework.http.HttpStatus;

public class VendSuiteException extends RuntimeException {

    private final String errorCode;
    private final String errorMessage;
    private final Throwable cause;
    private final HttpStatus httpStatus;

    public VendSuiteException() {
        super();
        this.errorCode = null;
        this.errorMessage = null;
        this.cause = null;
        this.httpStatus = null;
    }

    public VendSuiteException(
            String errorCode,
            String errorMessage,
            HttpStatus httpStatus
    ) {
        super();
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.cause = null;
        this.httpStatus = httpStatus;
    }

    public VendSuiteException(
            String errorCode,
            String errorMessage,
            Throwable cause,
            HttpStatus httpStatus
    ) {
        super();
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.cause = cause;
        this.httpStatus = httpStatus;
    }

    public VendSuiteException(
            Throwable cause,
            HttpStatus httpStatus
    ) {
        super();
        this.errorCode = "errorCode";
        this.errorMessage = "errorMessage";
        this.cause = cause;
        this.httpStatus = httpStatus;
    }

    public VendSuiteException(VendSuiteError vendSuiteError) {
        super();
        this.errorCode = vendSuiteError.getErrorCode();
        this.errorMessage = vendSuiteError.getErrorMessage();
        this.cause = null;
        this.httpStatus = vendSuiteError.getHttpStatus();
    }
}