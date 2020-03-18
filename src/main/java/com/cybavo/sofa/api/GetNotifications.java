package com.cybavo.sofa.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GetNotifications {
    public static class Response extends BaseResponse {
        @JsonProperty("notifications")
        CallbackStruct[] Notifications;
    }
}