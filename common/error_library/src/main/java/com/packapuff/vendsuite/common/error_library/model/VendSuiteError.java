package com.packapuff.vendsuite.common.error_library.model;

import com.packapuff.vendsuite.common.error_library.constants.IVendSuiteErrorProvider;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class VendSuiteError {

    private HttpStatus httpStatus;

    private String errorCode;

    private String errorMessage;

    public VendSuiteError(final IVendSuiteErrorProvider error) {
        this.httpStatus = error.getError().getHttpStatus();
        this.errorCode = error.getError().getErrorCode();
        this.errorMessage = error.getError().getErrorMessage();
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
