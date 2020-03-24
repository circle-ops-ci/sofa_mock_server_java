package com.cybavo.sofa.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GetNotificationsById {
    public static class Request {
        @JsonProperty("ids")
        Long[] ids;
    }

    public static class Response extends BaseResponse {
        @JsonProperty("notifications")
        CallbackStruct[] Notifications;
    }
}