package com.cybavo.sofa.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(Include.NON_DEFAULT)
public class BaseResponse {

    @JsonProperty("error_code")
    private int errorCode;

    @JsonProperty("error")
    private String errorMessage;

    @JsonProperty("message")
    private String errorSubMessage;

    public int getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public String getErrorSubMessage() {
        return errorSubMessage;
    }

    public BaseResponse() {
    }

    public BaseResponse(Exception e) {
        this.errorMessage = e.toString();
    }
}