package che.per.sidesnapask.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Jerry on 2018/1/3.
 */

public class QuestionsWrapper {

    @SerializedName("questions")
    private List<Question> mQuestions;

    @SerializedName("remain_questions_count")
    private int mRemainQuestionsCount;

    @SerializedName("current_page")
    private int mCurrentPage;

    @SerializedName("total_pages")
    private int mTotalPages;

    public List<Question> getQuestions() {
        return mQuestions;
    }

    public int getCurrentPage() {
        return mCurrentPage;
    }

    public int getTotalPages() {
        return mTotalPages;
    }

    public int getRemainQuestionCount() {return mRemainQuestionsCount;}

}
