package com.ptit.ncovihdv.dto.response;

import lombok.Data;

import java.io.Serializable;

@Data
public class BaseResponseData<T> implements Serializable {
    private T data;
    private int errorCode;
    private String message;
}
