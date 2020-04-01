package com.cybavo.sofa.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

public class GetDepositWalletAddresses {
    public static class Response extends BaseResponse {
        @JsonProperty("wallet_id")
        Long walletId;

        @JsonProperty("wallet_address")
        WalletAddress[] walletAddress;

        @JsonProperty("wallet_count")
        @JsonInclude(JsonInclude.Include.NON_EMPTY)
        Long walletCount;

        public static class WalletAddress {
            @JsonProperty("currency")
            Long currency;

            @JsonProperty("token_address")
            @JsonInclude(JsonInclude.Include.NON_EMPTY)
            String tokenAddress;

            @JsonProperty("address")
            String address;

            @JsonProperty("memo")
            @JsonInclude(JsonInclude.Include.NON_EMPTY)
            String memo;
        }
    }
}