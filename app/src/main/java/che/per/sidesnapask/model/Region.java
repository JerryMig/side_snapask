package che.per.sidesnapask.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Jerry on 2018/1/3.
 */

public class Region implements Parcelable {

    @SerializedName("id")
    private int mId;

    @SerializedName("name")
    private String mName;

    @SerializedName("full_name")
    private String mFullName;

    @SerializedName("is_have_quiz")
    private Boolean mHasQuiz;

    @SerializedName("search_districts_enabled")
    private Boolean mSearchDistrictsEnabled;

    @SerializedName("search_schools_enabled")
    private Boolean mSearchSchoolsEnabled;

    protected Region(Parcel in) {
        mId = in.readInt();
        mName = in.readString();
        mFullName = in.readString();
        byte tmpMHasQuiz = in.readByte();
        mHasQuiz = tmpMHasQuiz == 0 ? null : tmpMHasQuiz == 1;
        byte tmpMSearchDistrictsEnabled = in.readByte();
        mSearchDistrictsEnabled = tmpMSearchDistrictsEnabled == 0 ? null : tmpMSearchDistrictsEnabled == 1;
        byte tmpMSearchSchoolsEnabled = in.readByte();
        mSearchSchoolsEnabled = tmpMSearchSchoolsEnabled == 0 ? null : tmpMSearchSchoolsEnabled == 1;
    }

    public static final Creator<Region> CREATOR = new Creator<Region>() {
        @Override
        public Region createFromParcel(Parcel in) {
            return new Region(in);
        }

        @Override
        public Region[] newArray(int size) {
            return new Region[size];
        }
    };

    public int getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public String getFullName() {
        return mFullName;
    }

    public Boolean getHasQuiz() {
        return mHasQuiz;
    }

    public Boolean getSearchDistrictsEnabled() {
        return mSearchDistrictsEnabled;
    }

    public Boolean getSearchSchoolsEnabled() {
        return mSearchSchoolsEnabled;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mId);
        dest.writeString(mName);
        dest.writeString(mFullName);
        dest.writeByte((byte) (mHasQuiz == null ? 0 : mHasQuiz ? 1 : 2));
        dest.writeByte((byte) (mSearchDistrictsEnabled == null ? 0 : mSearchDistrictsEnabled ? 1 : 2));
        dest.writeByte((byte) (mSearchSchoolsEnabled == null ? 0 : mSearchSchoolsEnabled ? 1 : 2));
    }

}
