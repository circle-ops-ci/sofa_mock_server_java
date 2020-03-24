package com.cybavo.sofa.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CreateReceiveWalletAddresses {
    public static class Request {
        @JsonProperty("count")
        int count;
        @JsonProperty("memos")
        String[] memos;
    }

    public static class Response extends BaseResponse {
        @JsonProperty("addresses")
        String[] addresses;
    }
}