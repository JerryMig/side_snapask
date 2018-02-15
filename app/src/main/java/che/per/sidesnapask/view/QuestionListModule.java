package che.per.sidesnapask.view;

import che.per.sidesnapask.BroadcastModule;
import che.per.sidesnapask.network.ApiService;
import che.per.sidesnapask.presenter.QuestionListPresenter;
import che.per.sidesnapask.presenter.QuestionListPresenterImpl;
import dagger.Module;
import dagger.Provides;

/**
 * Created by Jerry on 2018/1/7.
 */

@Module(includes = BroadcastModule.class)
@QuestionListScope
public class QuestionListModule {

    @Provides
    public QuestionListInteractor provideQuestionListInteractor(ApiService apiService) {
        return new QuestionListInteractorImpl(apiService);
    }

    @Provides
    public QuestionListPresenter provideQuestionListPresenter(QuestionListInteractor interactor) {
        return new QuestionListPresenterImpl(interactor);
    }

}
