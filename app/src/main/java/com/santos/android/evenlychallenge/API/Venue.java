package com.santos.android.evenlychallenge.API;

/**
 * Created by Abel Cruz dos Santos on 16.08.2017.
 */
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSetter;
@JsonIgnoreProperties(ignoreUnknown=true)
public class Venue {

    private String mId;
    private String mName;
    private Location mLocation;
    private boolean mLike;
    private boolean mDislike;
    private Categories[] mCategories;
    private Contact mContact;

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

    public Location getLocation() {
        return mLocation;
    }
    public void setLocation(Location location) {
        mLocation = location;
    }

    public boolean isLike() {
        return mLike;
    }
    @JsonSetter("like")
    public void setLike(boolean like) {
        mLike = like;
    }

    public boolean isDislike() {
        return mDislike;
    }
    @JsonSetter("dislike")
    public void setDislike(boolean dislike) {
        mDislike = dislike;
    }

    public Categories[] getCategories() {
        return mCategories;
    }
    @JsonSetter("categories")
    public void setCategories(Categories[] categories) {
        mCategories = categories;
    }

    public Contact getContact() {
        return mContact;
    }
    @JsonSetter("contact")
    public void setContact(Contact contact) {
        mContact = contact;
    }

}
