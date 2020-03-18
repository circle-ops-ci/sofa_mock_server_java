package com.cybavo.sofa.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GetWalletBlockInfo {
    public static class Response extends BaseResponse {
        @JsonProperty("latest_block_height")
        Long latestBlockHeight;
        @JsonProperty("synced_block_height")
        Long syncedBlockHeight;
    }
}