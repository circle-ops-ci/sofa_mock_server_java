package com.cybavo.sofa.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CommonResponse {

    private final long result;

    @JsonCreator
    public CommonResponse(@JsonProperty("result") long result) {
        this.result = result;
    }

    public long getResult() {
        return result;
    }
}
