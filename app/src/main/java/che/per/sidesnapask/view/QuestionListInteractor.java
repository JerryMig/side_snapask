package che.per.sidesnapask.view;

import che.per.sidesnapask.model.QuestionsWrapper;
import io.reactivex.Observable;

/**
 * Created by Jerry on 2018/1/7.
 */

public interface QuestionListInteractor {
    Observable<QuestionsWrapper> getQuestions(int userId, int pageSize, int pageNum, String[] status,
                                              int[] subjectIds, String description);
}
