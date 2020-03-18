package com.cybavo.sofa.api;

import java.util.Map;

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
        }
    }

    public static class Response extends BaseResponse {
        @JsonProperty("results")
        Map<String, Long> results;
    }
}