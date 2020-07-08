package com.ptit.ncovihdv.exception;

import lombok.Data;

@Data
public class ApplicationException extends Exception {

    private int code;
    private String message;
    private String language;

    public ApplicationException(int code, String message) {
        this.code = code;
        this.message = message;
    }


}
