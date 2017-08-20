package com.santos.android.evenlychallenge.API;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSetter;

/**
 * Created by Abel Cruz dos Santos on 18.08.2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Contact {
    private String mPhone;
    private String mFormattedPhone;
    private String mTwitter;

    public String getPhone() {
        return mPhone;
    }
    @JsonSetter("phone")
    public void setPhone(String phone) {
        mPhone = phone;
    }

    public String getFormattedPhone() {
        return mFormattedPhone;
    }
    @JsonSetter("formattedPhone")
    public void setFormattedPhone(String formattedPhone) {
        mFormattedPhone = formattedPhone;
    }

    public String getTwitter() {
        return mTwitter;
    }
    @JsonSetter("twitter")
    public void setTwitter(String twitter) {
        mTwitter = twitter;
    }
}
