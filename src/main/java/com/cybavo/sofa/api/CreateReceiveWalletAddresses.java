package com.cybavo.sofa.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CreateReceiveWalletAddresses {
    public static class Request {
        @JsonProperty("count")
        private int count;
    }

    public static class Response extends BaseResponse {
        @JsonProperty("addresses")
        String[] addresses;
    }
}