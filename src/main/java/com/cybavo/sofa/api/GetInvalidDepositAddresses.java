package com.cybavo.sofa.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GetInvalidDepositAddresses {
    public static class Response extends BaseResponse {
        @JsonProperty("addresses")
        String[] addresses;
    }
}