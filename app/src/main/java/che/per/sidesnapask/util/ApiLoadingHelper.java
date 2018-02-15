package che.per.sidesnapask.util;

/**
 * Created by Jerry on 2018/1/7.
 */

public abstract class ApiLoadingHelper {

    private int mCurrentPageNum = 0;
    private int mPageSize = 16;

    public void refresh() {
        mCurrentPageNum = 0;
        load(mCurrentPageNum, mPageSize);
    }

    public void loadNextPage() {
        load(++mCurrentPageNum, mPageSize);
    }

    protected abstract void load(int pageNum, int pageSize);

}
