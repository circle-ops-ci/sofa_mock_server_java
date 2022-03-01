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

public class SetApiTokenRequest {

    @JsonProperty("api_code")
    private String apiCode;

    @JsonProperty("api_secret")
    private String apiSecret;

    @Override
    public String toString() {
        return String.format("ApiCode(%s, %s)", apiCode, apiSecret);
    }

    public String getApiCode() {
        return apiCode;
    }

    public String getApiSecret() {
        return apiSecret;
    }   
}