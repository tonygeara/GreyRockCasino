package com.example.tony.greycasino;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Tony on 30/5/19.
 */
public class Events implements Parcelable {

    String title, thumbnail, details, detailsLink, eventDate, addressHtml, mapsLink, email,phoneNo;

    public Events(String title, String thumbnail, String details, String detailsLink, String eventDate, String addressHtml, String mapsLink, String email, String phoneNo) {
        this.title = title;
        this.thumbnail = thumbnail;
        this.details = details;
        this.detailsLink = detailsLink;
        this.eventDate = eventDate;
        this.addressHtml = addressHtml;
        this.mapsLink = mapsLink;
        this.email = email;
        this.phoneNo = phoneNo;
    }

    protected Events(Parcel in) {
        title = in.readString();
        thumbnail = in.readString();
        details = in.readString();
        detailsLink = in.readString();
        eventDate = in.readString();
        addressHtml = in.readString();
        mapsLink = in.readString();
        email = in.readString();
        phoneNo = in.readString();
    }

    public static final Creator<Events> CREATOR = new Creator<Events>() {
        @Override
        public Events createFromParcel(Parcel in) {
            return new Events(in);
        }

        @Override
        public Events[] newArray(int size) {
            return new Events[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(thumbnail);
        dest.writeString(details);
        dest.writeString(detailsLink);
        dest.writeString(eventDate);
        dest.writeString(addressHtml);
        dest.writeString(mapsLink);
        dest.writeString(email);
        dest.writeString(phoneNo);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getDetailsLink() {
        return detailsLink;
    }

    public void setDetailsLink(String detailsLink) {
        this.detailsLink = detailsLink;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public String getAddressHtml() {
        return addressHtml;
    }

    public void setAddressHtml(String addressHtml) {
        this.addressHtml = addressHtml;
    }

    public String getMapsLink() {
        return mapsLink;
    }

    public void setMapsLink(String mapsLink) {
        this.mapsLink = mapsLink;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }
}
