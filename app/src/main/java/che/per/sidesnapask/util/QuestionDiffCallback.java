package che.per.sidesnapask.util;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;

import java.util.List;

import che.per.sidesnapask.model.Question;
import che.per.sidesnapask.view.adapter.QuestionListAdapter;

/**
 * Created by Jerry on 2018/1/8.
 */

public class QuestionDiffCallback extends DiffUtil.Callback {

    private List<Question> mOldData;
    private List<Question> mNewData;

    public QuestionDiffCallback(List<Question> oldData, List<Question> newData) {
        mOldData = oldData;
        mNewData = newData;
    }

    @Override
    public int getOldListSize() {
        return mOldData != null ? mOldData.size() : 0;
    }

    @Override
    public int getNewListSize() {
        return mNewData != null ? mNewData.size() : 0;
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        if (mOldData != null && mNewData != null) {
            Question oldQ = mOldData.get(oldItemPosition);
            Question newQ = mNewData.get(newItemPosition);
            return oldQ.getId() == newQ.getId();
        }
        return false;
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        if (mOldData != null && mNewData != null) {
            Question oldQ = mOldData.get(oldItemPosition);
            Question newQ = mNewData.get(newItemPosition);
            return oldQ.getLatestMessage().getId() == newQ.getLatestMessage().getId();
        }
        return false;
    }

    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        Bundle bundle = new Bundle();
        bundle.putInt(QuestionListAdapter.ACTION_NEW_MESSAGE, oldItemPosition);
        return bundle;
    }

}
