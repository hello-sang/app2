package com.example.m;

import android.os.Parcel;
import android.os.Parcelable;

public class Baothuc  implements Parcelable {
    private int id;
    private  long time;
    private String thoigianconlai,laplai;

    public Baothuc() {

    }

    public Baothuc(int id, long time, String thoigianconlai, String laplai) {
        this.id = id;
        this.time = time;
        this.thoigianconlai = thoigianconlai;
        this.laplai = laplai;
    }

    protected Baothuc(Parcel in) {
        id = in.readInt();
        time = in.readLong();
        thoigianconlai = in.readString();
        laplai = in.readString();
    }

    public static final Creator<Baothuc> CREATOR = new Creator<Baothuc>() {
        @Override
        public Baothuc createFromParcel(Parcel in) {
            return new Baothuc(in);
        }

        @Override
        public Baothuc[] newArray(int size) {
            return new Baothuc[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getThoigianconlai() {
        return thoigianconlai;
    }

    public void setThoigianconlai(String thoigianconlai) {
        this.thoigianconlai = thoigianconlai;
    }

    public String getLaplai() {
        return laplai;
    }

    public void setLaplai(String laplai) {
        this.laplai = laplai;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeLong(time);
        dest.writeString(thoigianconlai);
        dest.writeString(laplai);
    }


}
