package che.per.sidesnapask.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Jerry on 2018/1/3.
 */

public class RatingsCurriculum extends Curriculum implements Parcelable {

    @SerializedName("answer_count")
    private int mAnswerCount;

    @SerializedName("average_rate")
    private float mAverageRate;

    @SerializedName("subjects")
    private RatingsSubject[] mSubjects;

    public RatingsCurriculum(int id, String name) {
        super(id, name);
    }

    protected RatingsCurriculum(Parcel in) {
        super(in);
        mAnswerCount = in.readInt();
        mAverageRate = in.readFloat();
        mSubjects = in.createTypedArray(RatingsSubject.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeInt(mAnswerCount);
        dest.writeFloat(mAverageRate);
        dest.writeTypedArray(mSubjects, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<RatingsCurriculum> CREATOR = new Creator<RatingsCurriculum>() {
        @Override
        public RatingsCurriculum createFromParcel(Parcel in) {
            return new RatingsCurriculum(in);
        }

        @Override
        public RatingsCurriculum[] newArray(int size) {
            return new RatingsCurriculum[size];
        }
    };

    public int getAnswerCount() {
        return mAnswerCount;
    }

    public float getAverageRate() {
        return mAverageRate;
    }

    public RatingsSubject[] getSubjects() {
        return mSubjects;
    }

    public void setSubjects(RatingsSubject[] subjects) {
        mSubjects = subjects;
    }

    public void setAnswerCount(int answerCount) {
        mAnswerCount = answerCount;
    }

}
