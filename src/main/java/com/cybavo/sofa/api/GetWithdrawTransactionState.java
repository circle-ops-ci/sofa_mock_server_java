package com.cybavo.sofa.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GetWithdrawTransactionState {
    public static class Response extends BaseResponse {
        @JsonProperty("order_id")
        String orderID;

        @JsonProperty("address")
        String address;

        @JsonProperty("amount")
        String amount;

        @JsonProperty("memo")
        String memo;

        @JsonProperty("in_chain_block")
        Long inChainBlock;

        @JsonProperty("txid")
        String txId;

        @JsonProperty("create_time")
        String createTime;
    }
}