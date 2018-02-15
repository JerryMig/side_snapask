package che.per.sidesnapask.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Jerry on 2018/1/3.
 */
public class TutorProfileData implements Parcelable {

    @SerializedName("tutor_name")
    private String mName;

    @SerializedName("school_name")
    private String mSchool;

    @SerializedName("profile_pic_url")
    private String mPicUrl;

    @SerializedName("average_rate")
    private float mAverageRate;

    @SerializedName("answer_count")
    private int mAnswerCount;

    @SerializedName("since_date")
    private String mStartDate;

    @SerializedName("curriculums")
    private RatingsCurriculum[] mCurriculums;

    @SerializedName("followed_students_count")
    private int mFavoritedCount;    // this field comes with API when your logged in with a tutor account

    @SerializedName("is_followed")
    private boolean mIsFollowed;    // this field comes with API when your logged in with a student account

    public String getName() {
        return mName;
    }

    public String getSchool() {
        return mSchool;
    }

    public String getPicUrl() {
        return mPicUrl;
    }

    public float getAverageRate() {
        return mAverageRate;
    }

    public int getAnswerCount() {
        return mAnswerCount;
    }

    public String getStartDate() {
        return mStartDate;
    }

    public RatingsCurriculum[] getCurriculums() {
        return mCurriculums;
    }

    public int getFavoritedCount() {
        return mFavoritedCount;
    }

    public boolean getIsFollowed() {
        return mIsFollowed;
    }

    protected TutorProfileData(Parcel in) {
        mName = in.readString();
        mSchool = in.readString();
        mPicUrl = in.readString();
        mAverageRate = in.readFloat();
        mAnswerCount = in.readInt();
        mStartDate = in.readString();
        mCurriculums = in.createTypedArray(RatingsCurriculum.CREATOR);
        mFavoritedCount = in.readInt();
        mIsFollowed = in.readByte() != 0;
    }

    public static final Creator<TutorProfileData> CREATOR = new Creator<TutorProfileData>() {
        @Override
        public TutorProfileData createFromParcel(Parcel in) {
            return new TutorProfileData(in);
        }

        @Override
        public TutorProfileData[] newArray(int size) {
            return new TutorProfileData[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mName);
        dest.writeString(mSchool);
        dest.writeString(mPicUrl);
        dest.writeFloat(mAverageRate);
        dest.writeInt(mAnswerCount);
        dest.writeString(mStartDate);
        dest.writeTypedArray(mCurriculums, flags);
        dest.writeInt(mFavoritedCount);
        dest.writeByte((byte) (mIsFollowed ? 1 : 0));
    }
}
