package com.apecoder.club.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Tony on 2018/1/12.
 */

public class UserInfoBean implements Parcelable {
    private Long id;
    private String date;
    private String updateDate;

    private Integer age;
    private Integer userLevel;//管理员是0；普通用户是1，后续会开通vip功能
    private Double money;
    private Float income;
    private String cupSize;
    private String des;

    private String name;
    private String nickName;
    private String hobby;


    private String password;
    private String phone;

    private String gender;
    private String avatar;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(Integer userLevel) {
        this.userLevel = userLevel;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public Float getIncome() {
        return income;
    }

    public void setIncome(Float income) {
        this.income = income;
    }

    public String getCupSize() {
        return cupSize;
    }

    public void setCupSize(String cupSize) {
        this.cupSize = cupSize;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public UserInfoBean() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeString(this.date);
        dest.writeString(this.updateDate);
        dest.writeValue(this.age);
        dest.writeValue(this.userLevel);
        dest.writeValue(this.money);
        dest.writeValue(this.income);
        dest.writeString(this.cupSize);
        dest.writeString(this.des);
        dest.writeString(this.name);
        dest.writeString(this.nickName);
        dest.writeString(this.hobby);
        dest.writeString(this.password);
        dest.writeString(this.phone);
        dest.writeString(this.gender);
        dest.writeString(this.avatar);
    }

    protected UserInfoBean(Parcel in) {
        this.id = (Long) in.readValue(Long.class.getClassLoader());
        this.date = in.readString();
        this.updateDate = in.readString();
        this.age = (Integer) in.readValue(Integer.class.getClassLoader());
        this.userLevel = (Integer) in.readValue(Integer.class.getClassLoader());
        this.money = (Double) in.readValue(Double.class.getClassLoader());
        this.income = (Float) in.readValue(Float.class.getClassLoader());
        this.cupSize = in.readString();
        this.des = in.readString();
        this.name = in.readString();
        this.nickName = in.readString();
        this.hobby = in.readString();
        this.password = in.readString();
        this.phone = in.readString();
        this.gender = in.readString();
        this.avatar = in.readString();
    }

    public static final Creator<UserInfoBean> CREATOR = new Creator<UserInfoBean>() {
        @Override
        public UserInfoBean createFromParcel(Parcel source) {
            return new UserInfoBean(source);
        }

        @Override
        public UserInfoBean[] newArray(int size) {
            return new UserInfoBean[size];
        }
    };
}
