/**
 *
 */
package com.apecoder.club.base;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * description: 实体类基类
 * author: liujie
 * date: 2017/12/27 16:40
 */
public class BaseBean<T> implements Parcelable {

    public int code;
    public String msg;
    public long now_time;
    public String attachment_path;
    public T data;
    public static int SUCCESS_CODE = 1;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public long getNow_time() {
        return now_time;
    }

    public void setNow_time(long now_time) {
        this.now_time = now_time;
    }

    public String getAttachment_path() {
        return attachment_path;
    }

    public void setAttachment_path(String attachment_path) {
        this.attachment_path = attachment_path;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.code);
        dest.writeString(this.msg);
        dest.writeLong(this.now_time);
        dest.writeString(this.attachment_path);
        dest.writeParcelable((Parcelable) this.data, flags);
    }

    public BaseBean() {
    }

    protected BaseBean(Parcel in) {
        this.code = in.readInt();
        this.msg = in.readString();
        this.now_time = in.readLong();
        this.attachment_path = in.readString();
        this.data = in.readParcelable(((Class) data).getClassLoader());
    }

    public static final Creator<BaseBean> CREATOR = new Creator<BaseBean>() {
        @Override
        public BaseBean createFromParcel(Parcel source) {
            return new BaseBean(source);
        }

        @Override
        public BaseBean[] newArray(int size) {
            return new BaseBean[size];
        }
    };
}
