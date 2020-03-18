package com.cybavo.sofa.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

public class GetTransactionHistory {
    public static class Response extends BaseResponse {
        @JsonProperty("transaction_count")
        Integer transactionCount;

        @JsonProperty("transaction_item")
        TransactionItem[] transactionItem;

        public static class TransactionBatchStats {
            @JsonProperty("transaction_id")
            Long transactionId;

            @JsonProperty("total_amount")
            String totalAmount;

            @JsonProperty("transaction_count")
            Long transactionCount;

            @JsonProperty("outgoing_count")
            Long outgoingCount;

            @JsonProperty("success_count")
            Integer successCount;

            @JsonProperty("fail_count")
            Integer failCount;

            @JsonProperty("success_amount")
            String successAmount;

            @JsonProperty("create_time")
            String createTime;
        }

        public static class ApprovalItem {
            @JsonProperty("approval_id")
            Long approvalId;

            @JsonProperty("approval_user")
            String approvalUser;

            @JsonProperty("approval_time")
            Long approvalTime;

            @JsonProperty("user_message")
            String userMessage;

            @JsonProperty("level")
            Integer level;
            
            @JsonProperty("owner")
            Integer owner;

            @JsonProperty("confirm")
            Integer confirm;

            @JsonProperty("state")
            Integer state;

            @JsonProperty("error_code")
            Integer errorCode;
        }

        public static class TransactionItem {
            @JsonProperty("issue_user_id")
            Long issueUserId;

            @JsonProperty("issue_user_name")
            String issueUserName;

            @JsonProperty("description")
            String description;

            @JsonProperty("wallet_id")
            Long walletId;

            @JsonProperty("wallet_name")
            String walletName;

            @JsonProperty("wallet_address")
            String walletAddress;

            @JsonProperty("token_address")
            String tokenAddress;

            @JsonProperty("txid")
            String txId;

            @JsonProperty("currency")
            Long currency;

            @JsonProperty("currency_name")
            String currencyName;

            @JsonProperty("outgoing_address")
            String outgoingAddress;

            @JsonProperty("outgoing_address_name")
            String outgoingAddressName;

            @JsonProperty("amount")
            String amount;

            @JsonProperty("fee")
            String fee;

            @JsonProperty("txno")
            Long txNo;

            @JsonProperty("approval_item")
            ApprovalItem[] approvalItem;

            @JsonProperty("state")
            Integer state;

            @JsonProperty("create_time")
            Long createTime;

            @JsonProperty("transaction_time")
            Long transactionTime;

            @JsonProperty("scheduled_name")
            String scheduledName;

            @JsonProperty("transaction_type")
            Integer transactionType;

            @JsonProperty("batch")
            @JsonInclude(JsonInclude.Include.NON_NULL)
            TransactionBatchStats batch;

            @JsonProperty("eos_transaction_type")
            Integer eosTransactionType;

            @JsonProperty("real_amount")
            String realAmount;

            @JsonProperty("chain_fee")
            String chainFee;

            @JsonProperty("platform_fee")
            String platformFee;

            @JsonProperty("tx_category")
            String txCategory;
            
            @JsonProperty("memo")
            String memo;
        }
    }
}