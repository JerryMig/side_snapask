package che.per.sidesnapask.view.adapter;

import android.os.Bundle;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import che.per.sidesnapask.R;
import che.per.sidesnapask.model.Question;
import che.per.sidesnapask.util.QuestionDiffCallback;
import che.per.sidesnapask.view.QuestionListView;
import che.per.sidesnapask.view.viewHolder.QuestionViewHolder;

/**
 * Created by Jerry on 2018/1/8.
 */

public class QuestionListAdapter extends BaseListAdapter<QuestionViewHolder, List<Question>> {

    public static final String ACTION_NEW_MESSAGE = "ACTION_NEW_MESSAGE";

    private List<Question> mQuestions = new ArrayList<>();
    private QuestionListView mQuestionListView;
    private View.OnClickListener mOnClickListener;

    public QuestionListAdapter(QuestionListView view) {
        mQuestionListView = view;
    }

    @Override
    public void setData(List<Question> data) {
        mQuestions.clear();
        mQuestions.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public void setDataWithUtil(List<Question> data) {
        DiffUtil.DiffResult result =
                DiffUtil.calculateDiff(new QuestionDiffCallback(mQuestions, data), true);
        result.dispatchUpdatesTo(this);
        mQuestions.clear();
        mQuestions.addAll(data);
    }

    @Override
    public QuestionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        QuestionViewHolder holder =
                new QuestionViewHolder(inflater.inflate(R.layout.holder_question, parent, false));
        setListener(holder);
        return holder;
    }

    private void setListener(QuestionViewHolder holder) {
        if (mQuestionListView != null) {
            if (mOnClickListener == null) {
                mOnClickListener = (View v) -> {
                    int position = holder.getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        mQuestionListView.showDetails(mQuestions.get(position));
                    }
                };
            }
            holder.itemView.setOnClickListener(mOnClickListener);
        }
    }

    @Override
    public void onBindViewHolder(QuestionViewHolder holder, int position) {
        Question question = mQuestions.get(position);
        if (question != null) holder.setDate(question);
    }

    @Override
    public void onBindViewHolder(QuestionViewHolder holder, int position, List<Object> payloads) {
        if (!payloads.isEmpty()) {
            handlePartialBinding((Bundle) payloads.get(0));
        } else {
            onBindViewHolder(holder, position);
        }
    }

    private void handlePartialBinding(Bundle bundle) {
        if (bundle != null) {
            int moveFrom = bundle.getInt(ACTION_NEW_MESSAGE, -1);
            if (moveFrom != -1) {
                notifyItemMoved(moveFrom, 0);
            }
        }
    }

    @Override
    public int getItemCount() {
        return mQuestions.size();
    }

}
