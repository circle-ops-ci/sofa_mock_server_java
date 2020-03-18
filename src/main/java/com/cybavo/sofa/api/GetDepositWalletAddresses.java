package com.cybavo.sofa.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GetDepositWalletAddresses {
    public static class Response extends BaseResponse {
        @JsonProperty("wallet_id")
        Long walletId;
        @JsonProperty("wallet_address")
        WalletAddress[] walletAddress;

        public static class WalletAddress {
            @JsonProperty("currency")
            Long currency;
            @JsonProperty("token_address")
            String tokenAddress;
            @JsonProperty("address")
            String address;
            @JsonProperty("memo")
            String memo;
        }
    }
}