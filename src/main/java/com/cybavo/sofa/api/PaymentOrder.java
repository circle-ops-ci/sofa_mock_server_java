// Copyright (c) 2018-2022 The CYBAVO developers
// All Rights Reserved.
// NOTICE: All information contained herein is, and remains
// the property of CYBAVO and its suppliers,
// if any. The intellectual and technical concepts contained
// herein are proprietary to CYBAVO
// Dissemination of this information or reproduction of this materia
// is strictly forbidden unless prior written permission is obtained
// from CYBAVO.

package com.cybavo.sofa.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class PaymentOrder {

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class RequestPaymentOrderRequest {
        @JsonProperty("order_id")
        public String orderId;
    
        @JsonProperty("currency")
        public Long currency;
    
        @JsonProperty("token_address")
        public String tokenAddress;
    
        @JsonProperty("amount")
        public String amount;
    
        @JsonProperty("duration")
        public Long duration;
    
        @JsonProperty("redirect_url")
        public String redirectUrl;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class QueryPaymentOrderResponse extends BaseResponse {
        @JsonProperty("address")
        public String address;

        @JsonProperty("state")
        public Long state;

        @JsonProperty("tx_id")
        public String txId;

        @JsonProperty("expired_time")
        public Long expiredTime;
    
        @JsonProperty("redirect_url")
        public String redirectUrl;
    }
}
