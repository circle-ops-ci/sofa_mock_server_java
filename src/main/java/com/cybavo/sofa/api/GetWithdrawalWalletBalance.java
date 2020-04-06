package com.cybavo.sofa.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

public class GetWithdrawalWalletBalance {
    public static class Response extends BaseResponse {
        @JsonProperty("currency")
        Long currency;

        @JsonProperty("wallet_address")
        String walletAddress;

        @JsonProperty("token_address")
        String tokenAddress;

        @JsonProperty("balance")
        String balance;

        @JsonProperty("token_balance")
        String tokenBalance;

        @JsonProperty("unconfirm_balance")
        String unconfirmBalance;

        @JsonProperty("unconfirm_token_balance")
        String unconfirmTokenBalance;

        @JsonProperty("err_reason")
        @JsonInclude(Include.NON_NULL)
        String errReason;
    }
}