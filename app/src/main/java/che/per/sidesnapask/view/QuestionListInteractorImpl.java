package che.per.sidesnapask.view;

import java.util.HashMap;

import che.per.sidesnapask.constant.ApiKeys;
import che.per.sidesnapask.model.HttpResponse;
import che.per.sidesnapask.model.QuestionsWrapper;
import che.per.sidesnapask.network.ApiService;
import che.per.sidesnapask.util.ApiUtils;
import io.reactivex.Observable;

/**
 * Created by Jerry on 2018/1/7.
 */

public class QuestionListInteractorImpl implements QuestionListInteractor {

    private ApiService mApiService;

    public QuestionListInteractorImpl(ApiService apiService) {
        mApiService = apiService;
    }

    @Override
    public Observable<QuestionsWrapper> getQuestions(int userId, int pageSize, int pageNum, String[] status,
                                                     int[] subjectIds, String description) {
        HashMap<String, Object> args = ApiUtils.getBasicParams();
        args.put(ApiKeys.BUNDLE_QUESTION_PAGE, pageNum + "");
        args.put(ApiKeys.BUNDLE_QUESTION_PAGE_SIZE, pageSize + "");
        return mApiService.getQuestions(userId, status, subjectIds, args).map(HttpResponse::getData);
    }

}
