package com.gutiere.activityservice.data;

import com.google.common.collect.ImmutableMap;

import java.util.Map;

public class Response {

    public boolean isBase64Encoded;
    public int statusCode;
    public Map<String, String> headers;
    public String body;

    public Response( int statusCode, String body ) {
        this.isBase64Encoded = false;
        this.statusCode = statusCode;
        this.headers = ImmutableMap.of();
        this.body = body;
    }

}
