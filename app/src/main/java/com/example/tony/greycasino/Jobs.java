package com.example.tony.greycasino;


import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Tony on 30/5/19.
 */
public class Jobs implements Parcelable {


    String title, apply, detailsLink, details, fileLink, emailAddress,mapsLink, phoneNo,addressHtml;

    protected Jobs(Parcel in) {
        title = in.readString();
        apply = in.readString();
        detailsLink = in.readString();
        details = in.readString();
        fileLink = in.readString();
        emailAddress = in.readString();
        mapsLink = in.readString();
        phoneNo = in.readString();
        addressHtml = in.readString();
    }

    public static final Creator<Jobs> CREATOR = new Creator<Jobs>() {
        @Override
        public Jobs createFromParcel(Parcel in) {
            return new Jobs(in);
        }

        @Override
        public Jobs[] newArray(int size) {
            return new Jobs[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(apply);
        dest.writeString(detailsLink);
        dest.writeString(details);
        dest.writeString(fileLink);
        dest.writeString(emailAddress);
        dest.writeString(mapsLink);
        dest.writeString(phoneNo);
        dest.writeString(addressHtml);
    }

    public Jobs(String title, String apply, String detailsLink, String details, String fileLink, String emailAddress, String mapsLink, String phoneNo, String addressHtml) {
        this.title = title;
        this.apply = apply;
        this.detailsLink = detailsLink;
        this.details = details;
        this.fileLink = fileLink;
        this.emailAddress = emailAddress;
        this.mapsLink = mapsLink;
        this.phoneNo = phoneNo;
        this.addressHtml = addressHtml;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getApply() {
        return apply;
    }

    public void setApply(String apply) {
        this.apply = apply;
    }

    public String getDetailsLink() {
        return detailsLink;
    }

    public void setDetailsLink(String detailsLink) {
        this.detailsLink = detailsLink;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getFileLink() {
        return fileLink;
    }

    public void setFileLink(String fileLink) {
        this.fileLink = fileLink;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getMapsLink() {
        return mapsLink;
    }

    public void setMapsLink(String mapsLink) {
        this.mapsLink = mapsLink;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getAddressHtml() {
        return addressHtml;
    }

    public void setAddressHtml(String addressHtml) {
        this.addressHtml = addressHtml;
    }
}
