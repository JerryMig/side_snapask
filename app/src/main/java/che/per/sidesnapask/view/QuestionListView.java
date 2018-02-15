package che.per.sidesnapask.view;

import java.util.List;

import che.per.sidesnapask.model.Question;

/**
 * Created by Jerry on 2018/1/7.
 */

public interface QuestionListView extends InternetView {
    void ask();
    void cancel(Question question);
    void onQuestionsLoaded(List<Question> questions);
    void showDetails(Question question);
}
