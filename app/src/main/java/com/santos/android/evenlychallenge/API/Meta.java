package com.santos.android.evenlychallenge.API;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;

/**
 * Created by Abel Cruz dos Santos on 18.08.2017.
 */

public class Meta {

    private int mCode;
    private String mRequestId;

    public int getCode() {
        return mCode;
    }
    @JsonProperty("code")
    public void setCode(int code) {
        mCode = code;
    }

    public String getRequestId() {
        return mRequestId;
    }
    @JsonProperty("requestId")
    public void setRequestId(String requestId) {
        mRequestId = requestId;
    }
}
