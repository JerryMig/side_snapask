package che.per.sidesnapask.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Jerry on 2018/1/3.
 */

public class Subject implements Parcelable {

    @SerializedName("id")
    private int mId;

    @SerializedName("abbr")
    private String mAbbr;

    @SerializedName("description")
    private String mDescription;

    @SerializedName("minimum_credits_required")
    private int mMinimumCreditsRequired;

    @SerializedName("bg_color")
    private String mBgColor;

    @SerializedName("icon_color")
    private String mIconColor;

    @SerializedName("icon_pic_url")
    private String mIconPicUrl;

    @SerializedName("text_color")
    private String mTextColor;

    @SerializedName("topic_count")
    private int mTopicCount;

    @SerializedName("is_default")
    private boolean mIsDefault;

    @SerializedName("is_show_quizzes")
    private boolean mIsShowQuizzes;


    protected Subject(Parcel in) {
        mId = in.readInt();
        mAbbr = in.readString();
        mDescription = in.readString();
        mMinimumCreditsRequired = in.readInt();
        mBgColor = in.readString();
        mIconColor = in.readString();
        mIconPicUrl = in.readString();
        mTextColor = in.readString();
        mTopicCount = in.readInt();
        mIsDefault = in.readByte() != 0;
        mIsShowQuizzes = in.readByte() != 0;
    }

    public static final Creator<Subject> CREATOR = new Creator<Subject>() {
        @Override
        public Subject createFromParcel(Parcel in) {
            return new Subject(in);
        }

        @Override
        public Subject[] newArray(int size) {
            return new Subject[size];
        }
    };

    public int getId() {
        return mId;
    }

    public String getAbbr() {
        return mAbbr;
    }

    public String getDescription() {
        return mDescription;
    }

    public int getMinimumCreditsRequired() {
        return mMinimumCreditsRequired;
    }

    public String getBgColor() {
        return mBgColor;
    }

    public String getIconColor() {
        return mIconColor;
    }

    public String getIconPicUrl() {
        return mIconPicUrl;
    }

    public String getTextColor() {
        return mTextColor;
    }

    public int getTopicCount() {
        return mTopicCount;
    }

    public boolean isDefault() {
        return mIsDefault;
    }

    public boolean isShowQuizzes() {
        return mIsShowQuizzes;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mId);
        dest.writeString(mAbbr);
        dest.writeString(mDescription);
        dest.writeInt(mMinimumCreditsRequired);
        dest.writeString(mBgColor);
        dest.writeString(mIconColor);
        dest.writeString(mIconPicUrl);
        dest.writeString(mTextColor);
        dest.writeInt(mTopicCount);
        dest.writeByte((byte) (mIsDefault ? 1 : 0));
        dest.writeByte((byte) (mIsShowQuizzes ? 1 : 0));
    }
}
