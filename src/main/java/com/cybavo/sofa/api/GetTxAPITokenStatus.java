package com.cybavo.sofa.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

public class GetTxAPITokenStatus {
    public static class Response extends BaseResponse {
        @JsonInclude(Include.NON_NULL)
        @JsonProperty("valid")
        private WalletApiCode valid;

        @JsonInclude(Include.NON_NULL)
        @JsonProperty("inactivated")
        private WalletApiCode inactivated;

        public static class WalletApiCode {
            @JsonProperty("api_code")
            private String apiCode;

            @JsonInclude(Include.NON_NULL)
            @JsonProperty("api_secret")
            private String apiSecret;

            @JsonProperty("exp")
            private Long expiresAt;

            @JsonInclude(Include.NON_NULL)
            @JsonProperty("status")
            private Integer status;
        }
    }
}