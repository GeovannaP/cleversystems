package com.example.aplicativo.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.aplicativo.Model.Hours;
import com.example.aplicativo.Model.Menu;

import java.util.List;

public class CleverModel implements Parcelable {

    private String name;
    private String address;
    private String image;
    private float clever_charge;
    private Hours hours;
    private List<Menu> menus;

    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public float getClever_charge() {
        return clever_charge;
    }

    public void setClever_charge(float clever_charge) {
        this.clever_charge = clever_charge;
    }

    public Hours getHours() {
        return hours;
    }

    public void setHours(Hours hours) {
        this.hours = hours;
    }

    public static List<Menu> getMenus() {
        return getMenus();
    }

    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }

    public CleverModel(Parcel in) {
        name = in.readString();
        address = in.readString();
        image = in.readString();
        clever_charge = in.readFloat();
        menus = in.createTypedArrayList(Menu.CREATOR);
    }

    public static final Creator<CleverModel> CREATOR = new Creator<CleverModel>() {
        @Override
        public CleverModel createFromParcel(Parcel in) {
            return new CleverModel(in);
        }

        @Override
        public CleverModel[] newArray(int size) {
            return new CleverModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(address);
        dest.writeString(image);
        dest.writeFloat(clever_charge);
        dest.writeTypedList(menus);
    }
}
