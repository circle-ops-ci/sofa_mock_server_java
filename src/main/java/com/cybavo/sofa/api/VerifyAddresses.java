package com.cybavo.sofa.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class VerifyAddresses {
    
    public static class Request {
        @JsonProperty("addresses")
        String[] addresses;
    }

    public static class Response extends BaseResponse {
        @JsonProperty("result")
        AddressStatus[] result;

        public static class AddressStatus {
            @JsonProperty("address")
            String address;

            @JsonProperty("valid")
            Boolean valid;
        }
    }
}