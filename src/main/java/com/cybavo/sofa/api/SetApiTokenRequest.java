package com.cybavo.sofa.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SetApiTokenRequest {

    @JsonProperty("api_code")
    private String apiCode;

    @JsonProperty("api_secret")
    private String apiSecret;

    @Override
    public String toString() {
        return String.format("ApiCode(%s, %s)", apiCode, apiSecret);
    }

    public String getApiCode() {
        return apiCode;
    }

    public String getApiSecret() {
        return apiSecret;
    }   
}