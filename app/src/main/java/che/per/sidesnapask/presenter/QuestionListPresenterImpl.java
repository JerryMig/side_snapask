package che.per.sidesnapask.presenter;

import che.per.sidesnapask.model.Question;
import che.per.sidesnapask.model.QuestionsWrapper;
import che.per.sidesnapask.util.ApiLoadingHelper;
import che.per.sidesnapask.util.PrefManager;
import che.per.sidesnapask.util.RxUtils;
import che.per.sidesnapask.view.QuestionListInteractor;
import che.per.sidesnapask.view.QuestionListView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Jerry on 2018/1/7.
 */

public class QuestionListPresenterImpl implements QuestionListPresenter {

    private QuestionListView mView;
    private QuestionListInteractor mQuestionListInteractor;
    private Disposable mQuestionWrapperSubscriber;

    private String[] mQuestionStatus;

    private ApiLoadingHelper mApiLoadingHelper = new ApiLoadingHelper() {
        @Override
        protected void load(int pageNum, int pageSize) {
            mQuestionWrapperSubscriber = mQuestionListInteractor
                    .getQuestions(PrefManager.getUserId(), pageSize, pageNum, new String[]{"on_going"}, null, null)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(QuestionListPresenterImpl.this::onNext, QuestionListPresenterImpl.this::onError);
        }
    };

    private void onNext(QuestionsWrapper questionsWrapper) {
        if (ensureView()) {
            mView.onQuestionsLoaded(questionsWrapper.getQuestions());
            mView.showLoading(false);
        }
    }

    private void onError(Throwable t) {
        if (ensureView()) {
            mView.onRequestError(t);
            mView.showLoading(false);
        }
    }

    public QuestionListPresenterImpl(QuestionListInteractor questionListInteractor) {
        mQuestionListInteractor = questionListInteractor;
    }

    @Override
    public void ask() {
        if (ensureView()) mView.ask();
    }

    @Override
    public void showDetails(Question question) {
        if (ensureView()) mView.showDetails(question);
    }

    @Override
    public void cancelQuestion(Question question) {
        if (ensureView()) mView.cancel(question);
    }

    private boolean ensureView() {
        return mView != null;
    }

    @Override
    public void setView(QuestionListView view) {
        mView = view;
    }

    @Override
    public void onRefresh() {
        mApiLoadingHelper.refresh();
    }

    @Override
    public void displayQuestions(String[] status) {
        mQuestionStatus = status;
        mApiLoadingHelper.loadNextPage();
    }

    @Override
    public void destroy() {
        mView = null;
        RxUtils.unsubscribe(mQuestionWrapperSubscriber);
    }

}
