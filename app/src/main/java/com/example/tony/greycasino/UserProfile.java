package com.example.tony.greycasino;

import android.support.annotation.NonNull;

import java.io.Serializable;

public class UserProfile implements Serializable {
    int PlayerID;
    int PointBalance;
    private int CompBalance;
    String FirstName;

    @NonNull
    @Override
    public String toString() {
        return "UserProfile{" +
                "PlayerID=" + PlayerID +
                ", PointBalance=" + PointBalance +
                ", CompBalance=" + CompBalance +
                ", FirstName='" + FirstName + '\'' +
                '}';
    }
}
