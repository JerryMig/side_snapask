package che.per.sidesnapask.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Jerry on 2018/1/3.
 */

public class Curriculum implements Parcelable {

    @SerializedName("id")
    private int mId;

    @SerializedName("name")
    private String mName;

    @SerializedName("region")
    private Region mRegion;

    @SerializedName("icon_pic_url")
    private String mPicUrl;

    public Curriculum(int id, String name) {
        mId = id;
        mName = name;
    }

    protected Curriculum(Parcel in) {
        mId = in.readInt();
        mName = in.readString();
        mRegion = in.readParcelable(Region.class.getClassLoader());
        mPicUrl = in.readString();
    }

    public static final Creator<Curriculum> CREATOR = new Creator<Curriculum>() {
        @Override
        public Curriculum createFromParcel(Parcel in) {
            return new Curriculum(in);
        }

        @Override
        public Curriculum[] newArray(int size) {
            return new Curriculum[size];
        }
    };

    public int getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public Region getRegion() {
        return mRegion;
    }

    public String getPicUrl() {
        return mPicUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mId);
        dest.writeString(mName);
        dest.writeParcelable(mRegion, flags);
        dest.writeString(mPicUrl);
    }

}
