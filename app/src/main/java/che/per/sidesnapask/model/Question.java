package che.per.sidesnapask.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Jerry on 2018/1/3.
 */

public class Question implements Parcelable {

    @SerializedName("aasm_state")
    private String mStateStatus;

    public static final String STATUS_OPEN = "open";
    public static final String STATUS_PICKED = "picked";
    public static final String STATUS_ONGOING = "on_going";
    public static final String STATUS_UNRATE = "unrate";
    public static final String STATUS_FINISHED = "finished";
    public static final String STATUS_CANCELLED = "cancelled";

    // This status is not recorded on server, and this is for showing rescue UI.
    // When an open question is picked up by a tutor, we send a api request to server and show rescue-is-sent UI right away.
    // So this state is an interim state between 'open' and 'pick-up request confirmation' from server.
    public static final String STATUS_PICK_UP_NOT_CONFIRMED = "STATUS_PICK_UP_NOT_CONFIRMED";

    private int id;
    private String description;
    private int user_id;
    private int answer_tutor_id;
    private String updated_at;
    private String created_at;
    private Curriculum curriculum;
    private Subject subject;
    private String audio_length;
    private String picture_thumb_url;
    private String picture_url;
    private String audio_record_url;
    private User answered_by;
    private User asked_by;
    private String pubnub_channel_name;
    private int newMessageCount;
    private Message latest_message;
    private boolean is_read;

    private String user_rating;
    private String user_rating_comment;
    private List user_rating_messages;
    private String quiz_id;
    private Integer quiz_answer;
    private String topic_name;
    private Integer topic_id;

    private boolean isQuestionTaken = false;
    private boolean isQuestionCanceled = false;

    @SerializedName("tutor_subject_statistic")
    private TutorProfileData mTutorInfoInSubject;

    @SerializedName("is_invalid")
    private boolean mIsInvalid;

    @SerializedName("tutor_define_topics")
    private String[] mTopicsSelectedByTutor;

    @SerializedName("ask_type")
    private String mAskType;

    public static final String ASK_TYPE_PRIVATE = "to_designate";
    public static final String ASK_TYPE_PUBLIC = "to_open";

    private String school;


    protected Question(Parcel in) {
        mStateStatus = in.readString();
        id = in.readInt();
        description = in.readString();
        user_id = in.readInt();
        answer_tutor_id = in.readInt();
        updated_at = in.readString();
        created_at = in.readString();
        curriculum = in.readParcelable(Curriculum.class.getClassLoader());
        subject = in.readParcelable(Subject.class.getClassLoader());
        audio_length = in.readString();
        picture_thumb_url = in.readString();
        picture_url = in.readString();
        audio_record_url = in.readString();
        answered_by = in.readParcelable(User.class.getClassLoader());
        asked_by = in.readParcelable(User.class.getClassLoader());
        pubnub_channel_name = in.readString();
        newMessageCount = in.readInt();
        latest_message = in.readParcelable(Message.class.getClassLoader());
        is_read = in.readByte() != 0;
        user_rating = in.readString();
        user_rating_comment = in.readString();
        quiz_id = in.readString();
        if (in.readByte() == 0) {
            quiz_answer = null;
        } else {
            quiz_answer = in.readInt();
        }
        topic_name = in.readString();
        if (in.readByte() == 0) {
            topic_id = null;
        } else {
            topic_id = in.readInt();
        }
        isQuestionTaken = in.readByte() != 0;
        isQuestionCanceled = in.readByte() != 0;
        mTutorInfoInSubject = in.readParcelable(TutorProfileData.class.getClassLoader());
        mIsInvalid = in.readByte() != 0;
        mTopicsSelectedByTutor = in.createStringArray();
        mAskType = in.readString();
        school = in.readString();
    }

    public static final Creator<Question> CREATOR = new Creator<Question>() {
        @Override
        public Question createFromParcel(Parcel in) {
            return new Question(in);
        }

        @Override
        public Question[] newArray(int size) {
            return new Question[size];
        }
    };

    public TutorProfileData getTutorInfoInSubject() {
        return mTutorInfoInSubject;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPictureThumbUrl() {
        return picture_thumb_url;
    }

    public String getPubnubChannelName() {
        return pubnub_channel_name;
    }

    public String getPictureUrl() {
        return picture_url;
    }

    public User getAskedBy() {
        return asked_by;
    }

    public boolean getIsInvalid() {
        return mIsInvalid;
    }

    public String getDescription() {
        return description;
    }

    public String getCreatedAt() {
        return created_at;
    }

    public String getUpdatedAt() {
        return updated_at;
    }

    public User getAnsweredBy() {
        return answered_by;
    }

    public void setAnsweredBy(User user) {
        this.answered_by = user;
    }

    public Subject getSubject() {
        return subject;
    }

    public String getStateStatus() {
        return mStateStatus;
    }

    public void setStateStatus(String stateStatus) {
        mStateStatus = stateStatus;
    }

    public String getRating() {
        return user_rating;
    }

    public List<String> getRatingMessage() {
        return user_rating_messages;
    }

    public String getRatingComment() {
        return user_rating_comment;
    }


    public void setRatingComment(String ratingComment) {
        this.user_rating_comment = ratingComment;
    }

    public void setRatingMessage(List msgs) {
        this.user_rating_messages = msgs;
    }

    public boolean isFinished() {
        return mStateStatus.equals(STATUS_FINISHED) || mStateStatus.equals(STATUS_UNRATE);
    }

    public void setUpdatedAt(String s) {
        updated_at = s;
    }

    public boolean isQuestionTaken() {
        return isQuestionTaken;
    }

    public void setQuestionTaken(boolean questionTaken) {
        isQuestionTaken = questionTaken;
    }

    public boolean isQuestionCanceled() {
        return isQuestionCanceled;
    }

    public void setQuestionCanceled(boolean questionCanceled) {
        isQuestionCanceled = questionCanceled;
    }

    public String getTopicName() {
        return topic_name;
    }

    public String getQuizId() {
        return quiz_id;
    }

    public int getQuizAnswer() {
        return quiz_answer != null ? quiz_answer : -1;
    }

    public Integer getTopicId() {
        return topic_id;
    }

    public Message getLatestMessage() {
        return latest_message;
    }

    public boolean getIsRead() {
        return is_read;
    }

    public void setIsRead(boolean isRead) {
        is_read = isRead;
    }

    public Curriculum getCurriculum() {
        return curriculum;
    }

    public String[] getTopicsSelectedByTutor() {
        return mTopicsSelectedByTutor;
    }

    public void setTopicsSelectedByTutor(String[] topics) {
        mTopicsSelectedByTutor = topics;
    }

    public String getAskType() {
        return mAskType;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mStateStatus);
        dest.writeInt(id);
        dest.writeString(description);
        dest.writeInt(user_id);
        dest.writeInt(answer_tutor_id);
        dest.writeString(updated_at);
        dest.writeString(created_at);
        dest.writeParcelable(curriculum, flags);
        dest.writeParcelable(subject, flags);
        dest.writeString(audio_length);
        dest.writeString(picture_thumb_url);
        dest.writeString(picture_url);
        dest.writeString(audio_record_url);
        dest.writeParcelable(answered_by, flags);
        dest.writeParcelable(asked_by, flags);
        dest.writeString(pubnub_channel_name);
        dest.writeInt(newMessageCount);
        dest.writeParcelable(latest_message, flags);
        dest.writeByte((byte) (is_read ? 1 : 0));
        dest.writeString(user_rating);
        dest.writeString(user_rating_comment);
        dest.writeString(quiz_id);
        if (quiz_answer == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(quiz_answer);
        }
        dest.writeString(topic_name);
        if (topic_id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(topic_id);
        }
        dest.writeByte((byte) (isQuestionTaken ? 1 : 0));
        dest.writeByte((byte) (isQuestionCanceled ? 1 : 0));
        dest.writeParcelable(mTutorInfoInSubject, flags);
        dest.writeByte((byte) (mIsInvalid ? 1 : 0));
        dest.writeStringArray(mTopicsSelectedByTutor);
        dest.writeString(mAskType);
        dest.writeString(school);
    }
}
