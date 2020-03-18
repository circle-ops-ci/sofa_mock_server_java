package com.cybavo.sofa.api;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CallbackStruct {
    @JsonProperty("type")
    Integer type;

    @JsonProperty("serial")
    Long serial;

    @JsonProperty("order_id")
    String orderID;

    @JsonProperty("currency")
    String currency;

    @JsonProperty("txid")
    String txId;

    @JsonProperty("block_height")
    Long blockHeight;

    @JsonProperty("tindex")
    Integer tIndex;

    @JsonProperty("vout_index")
    Integer vOutIndex;

    @JsonProperty("amount")
    String amount;

    @JsonProperty("fees")
    String fees;

    @JsonProperty("memo")
    String memo;

    @JsonProperty("broadcast_at")
    Long broadcastAt;

    @JsonProperty("chain_at")
    Long chainAt;

    @JsonProperty("from_address")
    String fromAddress;

    @JsonProperty("to_address")
    String toAddress;

    @JsonProperty("wallet_id")
    Long walletId;

    @JsonProperty("state")
    Long state;

    @JsonProperty("confirm_blocks")
    Long confirmBlocks;

    @JsonProperty("processing_state")
    Short ProcessingState;

    @JsonProperty("addon")
    Map<String, Object> addon;

    public Long getWalletId() {
        return walletId;
    }
}