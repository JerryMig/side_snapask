package che.per.sidesnapask.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import che.per.sidesnapask.R;
import che.per.sidesnapask.app.AppComponent;
import che.per.sidesnapask.app.BaseApplication;
import che.per.sidesnapask.model.Question;
import che.per.sidesnapask.presenter.QuestionListPresenter;
import che.per.sidesnapask.view.QuestionListModule;
import che.per.sidesnapask.view.QuestionListView;
import che.per.sidesnapask.view.adapter.QuestionListAdapter;

/**
 * Created by Jerry on 2018/1/7.
 */

public class QuestionListFragment extends BaseFragment implements QuestionListView {

    @Inject LocalBroadcastManager mLocalBroadcastManager;
    @Inject QuestionListPresenter mQuestionListPresenter;
    @BindView(R.id.recycler_view) RecyclerView mRecyclerView;
    @BindView(R.id.swipe_refresh_view) SwipeRefreshLayout mSwipeRefreshView;
    @BindView(R.id.loading_icon) ProgressBar mProgressBar;

    private Unbinder mUnbinder;
    private QuestionListAdapter mAdapter;

    public static QuestionListFragment newInstance() {
        return new QuestionListFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        injectModule();
    }

    private void injectModule() {
        AppComponent component = ((BaseApplication) getActivity().getApplication()).getAppComponent();
        component.plus(new QuestionListModule()).inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_question_list, container, false);
        mUnbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        mQuestionListPresenter.setView(this);
        initRecyclerView();
        initSwipeView();
        mQuestionListPresenter.displayQuestions(new String[]{});
    }

    @Override
    protected void registerResumeBroadcast() {
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }

    private void initRecyclerView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new QuestionListAdapter(this);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        mRecyclerView.setAdapter(mAdapter);
    }

    private void initSwipeView() {
        mSwipeRefreshView.setColorSchemeResources(R.color.primaryColorDark,
                R.color.light_teal, R.color.light_transcent_primaryColor);
        mSwipeRefreshView.setOnRefreshListener(() -> {
            mQuestionListPresenter.onRefresh();
        });
    }

    /*****************************************************
     *
     * Below are methods overridden from QuestionListView
     *
     * ***************************************************/

    @Override
    public void noInternetConnected() {
        Toast.makeText(getContext(), "There's no connection", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onRequestError(Throwable t) {
        Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void ask() {
        // go to question asking activity
    }

    @Override
    public void cancel(Question question) {
    }

    @Override
    public void onQuestionsLoaded(List<Question> questions) {
        mAdapter.setData(questions);
    }

    @Override
    public void showLoading(boolean show) {
        mSwipeRefreshView.setRefreshing(false);
        mProgressBar.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    @Override
    public void showDetails(Question question) {

    }

}
