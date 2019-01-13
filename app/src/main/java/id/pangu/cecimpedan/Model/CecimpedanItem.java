package id.pangu.cecimpedan.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class CecimpedanItem implements Parcelable {

    private int id;
    private String cecimpedan;
    private String arti;


    public CecimpedanItem(String cecimpedan, String arti) {
        this.cecimpedan = cecimpedan;
        this.arti = arti;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCecimpedan() {
        return cecimpedan;
    }

    public void setCecimpedan(String cecimpedan) {
        this.cecimpedan = cecimpedan;
    }

    public String getArti() {
        return arti;
    }

    public void setArti(String arti) {
        this.arti = arti;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.cecimpedan);
        dest.writeString(this.arti);
    }

    public CecimpedanItem() {
    }

    protected CecimpedanItem(Parcel in) {
        this.id = in.readInt();
        this.cecimpedan = in.readString();
        this.arti = in.readString();
    }

    public static final Parcelable.Creator<CecimpedanItem> CREATOR = new Parcelable.Creator<CecimpedanItem>() {
        @Override
        public CecimpedanItem createFromParcel(Parcel source) {
            return new CecimpedanItem(source);
        }

        @Override
        public CecimpedanItem[] newArray(int size) {
            return new CecimpedanItem[size];
        }
    };
}
