package che.per.sidesnapask.view;

import che.per.sidesnapask.view.fragment.QuestionListFragment;
import dagger.Subcomponent;

/**
 * Created by Jerry on 2018/1/7.
 */
@QuestionListScope
@Subcomponent(modules = QuestionListModule.class)
public interface QuestionListComponent {
    void inject(QuestionListFragment fragment);
}
