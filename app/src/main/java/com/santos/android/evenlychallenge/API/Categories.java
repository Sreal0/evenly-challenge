package com.santos.android.evenlychallenge.API;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSetter;

/**
 * Created by Abel Cruz dos Santos on 17.08.2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Categories {
    private String mId;
    private String mName;
    private String mPluralName;
    private String mNameShort;

    public String getId() {
        return mId;
    }
    @JsonSetter("id")
    public void setId(String id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }
    @JsonSetter("name")
    public void setName(String name) {
        mName = name;
    }

    public String getPluralName() {
        return mPluralName;
    }
    @JsonSetter("pluralName")
    public void setPluralName(String pluralName) {
        mPluralName = pluralName;
    }

    public String getNameShort() {
        return mNameShort;
    }
    @JsonSetter("shortName")
    public void setNameShort(String nameShort) {
        mNameShort = nameShort;
    }
}
