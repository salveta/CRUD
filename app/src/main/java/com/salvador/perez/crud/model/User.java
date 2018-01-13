package com.salvador.perez.crud.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Salva on 09/01/2018.
 */

public class User implements Parcelable {

    int id;
    String name;
    String birthdate;

    public User(String name, String birthday){
        this.name = name;
        this.birthdate = birthday;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getBirthdate() {
        return birthdate;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.name);
        dest.writeString(this.birthdate);
    }

    protected User(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.birthdate = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}
