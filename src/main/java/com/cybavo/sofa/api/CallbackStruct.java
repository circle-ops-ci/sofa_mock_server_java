package com.cybavo.sofa.api;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CallbackStruct {
    @JsonProperty("type")
    public Integer type;

    @JsonProperty("serial")
    public Long serial;

    @JsonProperty("order_id")
    public String orderID;

    @JsonProperty("currency")
    public String currency;

    @JsonProperty("txid")
    public String txId;

    @JsonProperty("block_height")
    public Long blockHeight;

    @JsonProperty("tindex")
    public Integer tIndex;

    @JsonProperty("vout_index")
    public Integer vOutIndex;

    @JsonProperty("amount")
    public String amount;

    @JsonProperty("fees")
    public String fees;

    @JsonProperty("memo")
    public String memo;

    @JsonProperty("broadcast_at")
    public Long broadcastAt;

    @JsonProperty("chain_at")
    public Long chainAt;

    @JsonProperty("from_address")
    public String fromAddress;

    @JsonProperty("to_address")
    public String toAddress;

    @JsonProperty("wallet_id")
    public Long walletId;

    @JsonProperty("state")
    public Long state;

    @JsonProperty("confirm_blocks")
    public Long confirmBlocks;

    @JsonProperty("processing_state")
    public Short processingState;

    @JsonProperty("decimal")
    public Long decimal;

    @JsonProperty("currency_bip44")
    public Integer currencyBip44;

    @JsonProperty("token_address")
    public String tokenAddress;

    @JsonProperty("addon")
    public Map<String, Object> addon;
}