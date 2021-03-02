package com.cybavo.sofa.api;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WithdrawTransaction {
    public static class Request {
        @JsonProperty("requests")
        Transaction[] requests;

        @JsonProperty("ignore_black_list")
        @JsonInclude(Include.NON_NULL)
        Boolean IgnoreBlackList;

        public static class Transaction {
            @JsonProperty("order_id")
            String orderID;

            @JsonProperty("address")
            String address;

            @JsonProperty("amount")
            String amount;

            @JsonProperty("memo")
            String memo;

            @JsonProperty("user_id")
            String userID;

            @JsonProperty("message")
            String message;

            @JsonProperty("block_average_fee")
            @JsonInclude(Include.NON_NULL)
            Integer blockAverageFee;

            @JsonProperty("manual_fee")
            @JsonInclude(Include.NON_NULL)
            Integer manualFee;
        }
    }

    public static class Response extends BaseResponse {
        @JsonProperty("results")
        Map<String, Long> results;

        @JsonProperty("blacklist")
        @JsonInclude(Include.NON_NULL)
        Map<String, List<String>> blacklist;
    }
}