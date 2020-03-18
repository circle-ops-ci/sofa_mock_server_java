package com.cybavo.sofa.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CallbackResend {

    public static class Request {
        @JsonProperty("notification_id")
        Long notificationId;
    }

    public static class Response extends BaseResponse {
        @JsonProperty("count")
        Integer count;
    }
}