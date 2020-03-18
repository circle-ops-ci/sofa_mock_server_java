package com.cybavo.sofa.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GetDepositWalletPoolAddress {
    public static class Response extends BaseResponse {
        @JsonProperty("address")
        String address;
    }
}