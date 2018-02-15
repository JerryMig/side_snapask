package che.per.sidesnapask.presenter;

import che.per.sidesnapask.model.Question;
import che.per.sidesnapask.view.QuestionListView;

/**
 * Created by Jerry on 2018/1/7.
 */

public interface QuestionListPresenter {
    void ask();
    void showDetails(Question question);
    void cancelQuestion(Question question);
    void setView(QuestionListView view);
    void onRefresh();
    void displayQuestions(String[] status);
    void destroy();
}
