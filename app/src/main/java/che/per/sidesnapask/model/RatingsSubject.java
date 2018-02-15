package che.per.sidesnapask.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Jerry on 2018/1/3.
 */

public class RatingsSubject extends Subject implements Parcelable {

    @SerializedName("name")
    private String mName;

    @SerializedName("answer_count")
    private int mAnswerCount;

    @SerializedName("average_rate")
    private float mAverageRate;

    private RatingsSubject(Parcel in) {
        super(in);
        mName = in.readString();
        mAnswerCount = in.readInt();
        mAverageRate = in.readFloat();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(mName);
        dest.writeInt(mAnswerCount);
        dest.writeFloat(mAverageRate);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<RatingsSubject> CREATOR = new Creator<RatingsSubject>() {
        @Override
        public RatingsSubject createFromParcel(Parcel in) {
            return new RatingsSubject(in);
        }

        @Override
        public RatingsSubject[] newArray(int size) {
            return new RatingsSubject[size];
        }
    };

    public String getName() {
        return mName;
    }

    public int getAnswerCount() {
        return mAnswerCount;
    }

    public float getAverageRate() {
        return mAverageRate;
    }

    public void setName(String name) {
        mName = name;
    }

    public void setAnswerCount(int answerCount) {
        mAnswerCount = answerCount;
    }

    public void setAverageRate(float rate) {
        mAverageRate = rate;
    }

}

