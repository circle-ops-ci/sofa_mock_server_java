package com.cybavo.sofa.api;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

public class WithdrawTransaction {
    public static class Request {
        @JsonProperty("requests")
        Transaction[] requests;

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
    }
}