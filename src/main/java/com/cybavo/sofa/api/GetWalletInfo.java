package com.cybavo.sofa.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

public class GetWalletInfo {
    public static class Response extends BaseResponse {
        @JsonProperty("currency")
        Long currency;

        @JsonProperty("currency_name")
        String currencyName;

        @JsonProperty("address")
        String address;

        @JsonProperty("token_name")
        @JsonInclude(Include.NON_NULL)
        String tokenName;

        @JsonProperty("token_symbol")
        @JsonInclude(Include.NON_NULL)
        String tokenSymbol;

        @JsonProperty("token_contract_address")
        @JsonInclude(Include.NON_NULL)
        String tokenContractAddress;

        @JsonProperty("token_decimals")
        @JsonInclude(Include.NON_NULL)
        String tokenDecimals;
    }
}