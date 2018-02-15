package che.per.sidesnapask.view.viewHolder;

import android.content.Context;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.lang.ref.WeakReference;
import java.util.List;
import java.util.WeakHashMap;

import che.per.sidesnapask.R;
import che.per.sidesnapask.app.BaseApplication;
import che.per.sidesnapask.model.Curriculum;
import che.per.sidesnapask.model.Message;
import che.per.sidesnapask.model.Question;
import che.per.sidesnapask.model.Subject;
import che.per.sidesnapask.model.User;
import che.per.sidesnapask.util.CircleTransform;
import che.per.sidesnapask.util.PrefManager;
import che.per.sidesnapask.util.RoundedCornersTransformation;
import che.per.sidesnapask.util.UiUtils;
import che.per.sidesnapask.view.QuestionListView;

/**
 * Created by Jerry on 2018/1/8.
 */

public class QuestionViewHolder extends BaseViewHolder<Question> {

    private ImageView mQuestionPhoto;
    private ImageView mTutorPic;
    private TextView mQuestionDescription;
    private View mRedDot;
    private TextView mQuestionTopic;
    private TextView mQuestionInteraction;
    private TextView mQuestionTime;
    private TextView mRatingComment;

    private View.OnClickListener mViewListener;

    // Rating view
    private LinearLayout mStarPanel;
    private ImageView mScoreFive;
    private ImageView mScoreFour;
    private ImageView mScoreThree;
    private ImageView mScoreTwo;
    private ImageView mScoreOne;

    public QuestionViewHolder(View itemView) {
        super(itemView);
        initView(itemView);
    }

    private void initView(View v) {
        RelativeLayout container = (RelativeLayout) v.findViewById(R.id.q_container);
        container.setOnClickListener(mViewListener);
        mQuestionPhoto = (ImageView) v.findViewById(R.id.q_photo);
        mTutorPic = (ImageView) v.findViewById(R.id.q_tutor_pic);
        mQuestionTopic = (TextView) v.findViewById(R.id.q_topic);
        mQuestionDescription = (TextView) v.findViewById(R.id.q_question);
        mQuestionTime = (TextView) v.findViewById(R.id.q_time);
        mRedDot = v.findViewById(R.id.red_dot);
        mRatingComment = (TextView) v.findViewById(R.id.rating_comment);

        mQuestionInteraction = (TextView) v.findViewById(R.id.q_interaction);
        mStarPanel = (LinearLayout) v.findViewById(R.id.stars);

        mScoreFive = (ImageView) v.findViewById(R.id.love);
        mScoreFour = (ImageView) v.findViewById(R.id.like);
        mScoreThree = (ImageView) v.findViewById(R.id.meh);
        mScoreTwo = (ImageView) v.findViewById(R.id.sad);
        mScoreOne = (ImageView) v.findViewById(R.id.angry);
    }

    @Override
    public void setDate(Question data) {
        if (data != null) {

            // Question pics
            determinePictures(data);

            // Question content, e.g. Why is answer C correct? , this text view can be empty.
            setDescription(data);

            // Question topic, e.g. Math
            setQuestionTopic(data);

            // Question creation time
            setTime(data);

            // Question message count, shown only when there's new messages.
            boolean isRead = data.getIsRead();
            mRedDot.setVisibility(isRead ? View.GONE : View.VISIBLE);

            // Show rating stars or interaction based on type
            setLatestMessageOrStars(data);

            // Show rating comment, e.g. Friendly
            showOrHideRatingComment(data);

            boolean isInteractionShown = mQuestionInteraction.getVisibility() == View.VISIBLE;
            boolean isStarsShown = mStarPanel.getVisibility() == View.VISIBLE;

            // Questions can be posted without a description
            View viewToAdjust = isInteractionShown ? mQuestionInteraction : mStarPanel;
            if (mQuestionDescription.getVisibility() == View.VISIBLE) {
                ((RelativeLayout.LayoutParams) viewToAdjust.getLayoutParams()).topMargin = (int) UiUtils.convertDpToPx(8);
            } else if (mQuestionDescription.getVisibility() == View.GONE) {
                ((RelativeLayout.LayoutParams) viewToAdjust.getLayoutParams()).topMargin = (int) UiUtils.convertDpToPx(19);
            }

            if (isInteractionShown) {
                ((RelativeLayout.LayoutParams) mQuestionTopic.getLayoutParams()).addRule(RelativeLayout.BELOW, R.id.q_interaction);
            } else if (isStarsShown) {
                ((RelativeLayout.LayoutParams) mQuestionTopic.getLayoutParams()).addRule(RelativeLayout.BELOW, R.id.stars);
            }
        }
    }

    /************************************************************
     *  Rating Comment Methods - Tutor's completed list specific
     * **********************************************************/

    private void showOrHideRatingComment(Question question) {
        if (!PrefManager.isStudent()) {
            List<String> comments = question.getRatingMessage();
            if (question.getStateStatus().equals(Question.STATUS_FINISHED) && comments != null && !comments.isEmpty()) {
                mRatingComment.setVisibility(View.VISIBLE);
                mRatingComment.setText(comments.get(0));
                ((RelativeLayout.LayoutParams) mQuestionTopic.getLayoutParams()).bottomMargin = (int) UiUtils.convertDpToPx(8);
            } else {
                mRatingComment.setVisibility(View.GONE);
                ((RelativeLayout.LayoutParams) mQuestionTopic.getLayoutParams()).bottomMargin = (int) UiUtils.convertDpToPx(16);
            }
        } else {
            mRatingComment.setVisibility(View.GONE);
        }
    }

    /******************************************************
     *                    Topic Methods
     * ****************************************************/

    private void setQuestionTopic(Question data) {
        if (mQuestionTopic != null) {
            Curriculum curriculum = data.getCurriculum();
            Subject subject = data.getSubject();
            String topic = "";
            if (curriculum != null && subject != null) {
                topic = curriculum.getName() + " " + subject.getDescription();
            } else if (subject != null) {
                topic = subject.getDescription();
            }
            mQuestionTopic.setText(topic);
        }
    }

    /******************************************************
     *                Description Methods
     * ****************************************************/

    private void setDescription(Question data) {
        String description = data.getDescription();
        if (TextUtils.isEmpty(description)) {
            mQuestionDescription.setVisibility(View.GONE);
        } else {
            mQuestionDescription.setVisibility(View.VISIBLE);
            mQuestionDescription.setText(data.getDescription());
        }
    }

    /******************************************************
     *               Latest Message Methods
     * ****************************************************/

    private void setLatestMessageOrStars(Question data) {
        if (data != null) {
            if (data.getIsInvalid()) {
                Context appCxt = BaseApplication.getContext();
                mQuestionInteraction.setTextColor(appCxt.getResources().getColor(R.color.blush));
                mQuestionInteraction.setText(appCxt.getString(R.string.action_settings));
                mQuestionInteraction.setVisibility(View.VISIBLE);
                mStarPanel.setVisibility(View.GONE);
            } else {
                String state = data.getStateStatus();
                switch (state) {
                    case Question.STATUS_ONGOING:
                    case Question.STATUS_PICKED:
                        showOngoingText(data);
                        mQuestionInteraction.setVisibility(View.VISIBLE);
                        mStarPanel.setVisibility(View.GONE);
                        break;
                    case Question.STATUS_UNRATE:
                        Context appCxt = BaseApplication.getContext();
                        mQuestionInteraction.setTextColor(appCxt.getResources().getColor(R.color.grey));
                        mQuestionInteraction.setText(PrefManager.isStudent() ?
                                appCxt.getString(R.string.session_unrate_remark_stu) : appCxt.getString(R.string.session_unrate_remark_tut));
                        mQuestionInteraction.setVisibility(View.VISIBLE);
                        mStarPanel.setVisibility(View.GONE);
                        break;
                    case Question.STATUS_FINISHED:
                        brightenStar(data);
                        mQuestionInteraction.setVisibility(View.GONE);
                        mStarPanel.setVisibility(View.VISIBLE);
                        break;
                }
            }
        }
    }

    private void showOngoingText(Question data) {
        Message msg = data.getLatestMessage();
        String content = "";
        if (msg != null) {
            Context context = BaseApplication.getContext();
            if (msg.getMessageAction() != null
                    && msg.getMessageAction().equals("pick")
                    && PrefManager.isStudent()) {
                // if message_action is Pickup, we show a different text message.
                User tutor = data.getAnsweredBy();
                if (tutor != null) {
                    content = context.getResources().getString(R.string.qa_msg_tutor_pickup, tutor.getUsername());
                } else {
                    content = msg.getDescription();
                }
            } else {
                String type = msg.getType();
                boolean isSelf = isMessageFromSelf(msg);
                switch (type) {
                    case Message.TYPE_TEXT:
                        content = msg.getDescription();
                        break;
                    case Message.TYPE_AUDIO:
                        content = isSelf ? context.getResources().getString(R.string.session_preview_send_audio)
                                : context.getResources().getString(R.string.session_preview_receive_audio, getName(data));
                        break;
                    case Message.TYPE_IMAGE:
                        content = isSelf ? context.getResources().getString(R.string.session_preview_send_photo)
                                : context.getResources().getString(R.string.session_preview_receive_photo, getName(data));
                        break;
                }
            }
            setTextColour(msg);
            setTextBoldness(data);
            mQuestionInteraction.setText(content);
            mQuestionInteraction.setVisibility(View.VISIBLE);
        } else {
            mQuestionInteraction.setVisibility(View.GONE);
        }
    }

    private String getName(Question data) {
        User user;
        if (PrefManager.isStudent()) {
            user = data.getAnsweredBy();
        } else {
            user = data.getAskedBy();
        }

        if (user != null) {
            return user.getUsername();
        }
        return "";
    }

    private void setTextColour(Message message) {
        if (message != null) {
            if (message.getMessageAction() != null
                    && message.getMessageAction().equals("pick")) {
                mQuestionInteraction.setTextColor(BaseApplication.getContext().getResources().getColor(R.color.hint_green));
            } else {
                mQuestionInteraction.setTextColor(BaseApplication.getContext().getResources().getColor(R.color.purple_brown));
            }
        }
    }

    private boolean isMessageFromSelf(Message message) {
        int senderId = message.getSnapaskUid();
        return senderId == PrefManager.getUserId();
    }

    private void setTextBoldness(Question question) {
        if (mQuestionInteraction != null) {
            boolean isRead = question.getIsRead();
            if (isRead) {
                mQuestionInteraction.setTypeface(null, Typeface.NORMAL);
            } else {
                mQuestionInteraction.setTypeface(null, Typeface.BOLD);
            }
        }
    }

    /******************************************************
     *                  Picture Methods
     * ****************************************************/

    private void determinePictures(Question data) {
        Context appCxt = BaseApplication.getContext();
        String url = data.getPictureUrl();
        User tutor = data.getAnsweredBy();
        Transformation roundedCorners = new RoundedCornersTransformation((int) appCxt.getResources().getDimension(R.dimen.question_photo_radius), 0);

        boolean isCirclePicNeeded;
        if (!TextUtils.isEmpty(url)) {
            // if question was originally posted with a pic
            String fullImageUrl = UiUtils.getFullAssetUrl(url);
            Picasso.with(appCxt).load(fullImageUrl).placeholder(R.drawable.ic_tutor_placeholder_radius)
                    .fit().centerCrop().transform(roundedCorners).into(mQuestionPhoto);
            // Small tutor pic is needed
            isCirclePicNeeded = true;
        } else {
            // if question was originally NOT posted with a pic, we load tutor's pic into mQuestionPhoto instead
            if (tutor != null) {
                Picasso.with(appCxt).load(tutor.getProfilePicUrl()).placeholder(R.drawable.ic_tutor_placeholder_radius)
                        .fit().centerCrop().transform(roundedCorners).into(mQuestionPhoto);
            } else {
                Picasso.with(appCxt).load(R.drawable.ic_tutor_placeholder_radius).fit().centerInside().into(mQuestionPhoto);
            }

            // Small tutor pic is NOT needed
            isCirclePicNeeded = false;
        }

        if (isCirclePicNeeded) {
            Transformation circle = new CircleTransform();
            if (tutor != null) {
                Picasso.with(appCxt).load(tutor.getProfilePicUrl()).fit().centerInside().transform(circle).into(mTutorPic);
            } else {
                Picasso.with(appCxt).load(R.drawable.ic_tutor_placeholder_radius).fit().centerInside().transform(circle).into(mTutorPic);
            }
            mTutorPic.setVisibility(View.VISIBLE);
        } else {
            mTutorPic.setVisibility(View.GONE);
        }
    }

    /******************************************************
     *                  Rating Star Methods
     * ****************************************************/

    private void brightenStar(Question data) {
        String rating = data.getRating();
        if (!TextUtils.isEmpty(rating)) {
            int score = Integer.valueOf(rating);
            for (int i = 1; i < 6; i++) {
                ImageView image = getView(i);
                if (image != null) {
                    if (i <= score) {
                        image.setImageResource(R.drawable.ic_rating_active);
                    } else {
                        image.setImageResource(R.drawable.ic_rating_normal);
                    }
                }
            }
        }
    }

    private ImageView getView(int index) {
        switch (index) {
            case 1:
                return mScoreOne;
            case 2:
                return mScoreTwo;
            case 3:
                return mScoreThree;
            case 4:
                return mScoreFour;
            case 5:
                return mScoreFive;
        }
        return null;
    }

    /******************************************************
     *                  Time Methods
     * ****************************************************/

    private void setTime(Question data) {
        Message message = data.getLatestMessage();
        String time;
        if (message != null) {
            time = message.getUpdatedAt();
        } else {
            time = data.getUpdatedAt();
        }
        mQuestionTime.setText(UiUtils.getTimeDifferenceFromNow(time));
    }

}
