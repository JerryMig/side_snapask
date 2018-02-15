package che.per.sidesnapask.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Jerry on 2018/1/3.
 */

public class User implements Parcelable {

    @SerializedName("id")
    private int mId;

    @SerializedName("email")
    private String mEmail;

    @SerializedName("username")
    private String mUsername;

    @SerializedName("confirmed_at")
    private String mConfirmedAt;

    @SerializedName("unconfirmed_email")
    private String mUnconfirmedEmail;

    @SerializedName("country_code")
    private String mCountryCode;

    @SerializedName("phone")
    private String mPhone;

    @SerializedName("phone_confirmed_at")
    private String mPhoneConfirmedAt;

    @SerializedName("unconfirmed_phone")
    private String mUnconfirmedPhone;

    @SerializedName("phone_confirmed_sent_at")
    private String mPhoneConfirmedSentAt;

    @SerializedName("authentication_token")
    private String mAuthenticationToken;

    @SerializedName("profile_pic_url")
    private String mProfilePicUrl;

    @SerializedName("gender")
    private String mGender;

    @SerializedName("role")
    private Role mRole;

    @SerializedName("first_name")
    private String mFirstName;

    @SerializedName("last_name")
    private String mLastName;

    @SerializedName("region")
    private Region mRegion;

    @SerializedName("is_demo")
    private boolean mIsDemo;

    @SerializedName("is_fb_login")
    private boolean mIsFbLogin;

    @SerializedName("curriculum")
    private String mCurriculum;

    @SerializedName("subjects")
    private Subject[] mSubjects;

    @SerializedName("rating")
    private float mRating;

    @SerializedName("user_from_third_party")
    private int mUserFromThirdParty;

    @SerializedName("birthday")
    private String mBirthday;

    @SerializedName("school_name")
    private String mSchool;

    @SerializedName("answer_count")
    private int mCountOfAnsweringQuestions;

    @SerializedName("average_rate")
    private float mRatingAsTutor;

    protected User(Parcel in) {
        mId = in.readInt();
        mEmail = in.readString();
        mUsername = in.readString();
        mConfirmedAt = in.readString();
        mUnconfirmedEmail = in.readString();
        mCountryCode = in.readString();
        mPhone = in.readString();
        mPhoneConfirmedAt = in.readString();
        mUnconfirmedPhone = in.readString();
        mPhoneConfirmedSentAt = in.readString();
        mAuthenticationToken = in.readString();
        mProfilePicUrl = in.readString();
        mGender = in.readString();
        mRole = in.readParcelable(Role.class.getClassLoader());
        mFirstName = in.readString();
        mLastName = in.readString();
        mRegion = in.readParcelable(Region.class.getClassLoader());
        mIsDemo = in.readByte() != 0;
        mIsFbLogin = in.readByte() != 0;
        mCurriculum = in.readString();
        mSubjects = in.createTypedArray(Subject.CREATOR);
        mRating = in.readFloat();
        mUserFromThirdParty = in.readInt();
        mBirthday = in.readString();
        mSchool = in.readString();
        mCountOfAnsweringQuestions = in.readInt();
        mRatingAsTutor = in.readFloat();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mId);
        dest.writeString(mEmail);
        dest.writeString(mUsername);
        dest.writeString(mConfirmedAt);
        dest.writeString(mUnconfirmedEmail);
        dest.writeString(mCountryCode);
        dest.writeString(mPhone);
        dest.writeString(mPhoneConfirmedAt);
        dest.writeString(mUnconfirmedPhone);
        dest.writeString(mPhoneConfirmedSentAt);
        dest.writeString(mAuthenticationToken);
        dest.writeString(mProfilePicUrl);
        dest.writeString(mGender);
        dest.writeParcelable(mRole, flags);
        dest.writeString(mFirstName);
        dest.writeString(mLastName);
        dest.writeParcelable(mRegion, flags);
        dest.writeByte((byte) (mIsDemo ? 1 : 0));
        dest.writeByte((byte) (mIsFbLogin ? 1 : 0));
        dest.writeString(mCurriculum);
        dest.writeTypedArray(mSubjects, flags);
        dest.writeFloat(mRating);
        dest.writeInt(mUserFromThirdParty);
        dest.writeString(mBirthday);
        dest.writeString(mSchool);
        dest.writeInt(mCountOfAnsweringQuestions);
        dest.writeFloat(mRatingAsTutor);
    }

    public String getUsername() {
        return mUsername;
    }

    public String getProfilePicUrl() {
        return mProfilePicUrl;
    }
}
