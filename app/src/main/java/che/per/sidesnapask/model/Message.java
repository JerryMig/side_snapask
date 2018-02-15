package che.per.sidesnapask.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Jerry on 2018/1/3.
 */

public class Message implements Parcelable {

    public static final String TYPE_TEXT = "text";
    public static final String TYPE_IMAGE = "image";
    public static final String TYPE_AUDIO = "audio";

    @SerializedName("id")
    private int mId;

    @SerializedName("mtype")
    private String mtype;

    @SerializedName("description")
    private String mDescription;

    @SerializedName("question_id")
    private int mQuestionId;

    @SerializedName("snapask_uid")
    private int mSnapaskUid;

    @SerializedName("updated_at")
    private String mUpdatedAt;

    @SerializedName("created_at")
    private String mCreatedAt;

    @SerializedName("picture_thumb_url")
    private String mPictureThumbUrl;

    @SerializedName("picture_url")
    private String mPictureUrl;

    @SerializedName("audio_record_url")
    private String mAudioRecordUrl;

    @SerializedName("user")
    private User mUser;

    @SerializedName("topic_name")
    private String mTopicName;

    @SerializedName("quiz_id")
    private String mQuizId;

    @SerializedName("quiz_answer")
    private int mQuizAnswer;

    @SerializedName("message_action")
    private String mMessageAction;

    protected Message(Parcel in) {
        mId = in.readInt();
        mtype = in.readString();
        mDescription = in.readString();
        mQuestionId = in.readInt();
        mSnapaskUid = in.readInt();
        mUpdatedAt = in.readString();
        mCreatedAt = in.readString();
        mPictureThumbUrl = in.readString();
        mPictureUrl = in.readString();
        mAudioRecordUrl = in.readString();
        mUser = in.readParcelable(User.class.getClassLoader());
        mTopicName = in.readString();
        mQuizId = in.readString();
        mQuizAnswer = in.readInt();
        mMessageAction = in.readString();
    }

    public static final Creator<Message> CREATOR = new Creator<Message>() {
        @Override
        public Message createFromParcel(Parcel in) {
            return new Message(in);
        }

        @Override
        public Message[] newArray(int size) {
            return new Message[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mId);
        dest.writeString(mtype);
        dest.writeString(mDescription);
        dest.writeInt(mQuestionId);
        dest.writeInt(mSnapaskUid);
        dest.writeString(mUpdatedAt);
        dest.writeString(mCreatedAt);
        dest.writeString(mPictureThumbUrl);
        dest.writeString(mPictureUrl);
        dest.writeString(mAudioRecordUrl);
        dest.writeParcelable(mUser, flags);
        dest.writeString(mTopicName);
        dest.writeString(mQuizId);
        dest.writeInt(mQuizAnswer);
        dest.writeString(mMessageAction);
    }

    public int getId() {
        return mId;
    }

    public String getDescription() {
        return mDescription;
    }

    public String getUpdatedAt() {
        return mUpdatedAt;
    }

    public String getMessageAction() {
        return mMessageAction;
    }

    public String getType() {
        return mtype;
    }

    public int getSnapaskUid() {
        return mSnapaskUid;
    }
}
