package che.per.sidesnapask.view;

/**
 * Created by Jerry on 2018/1/7.
 */

public interface InternetView {
    void noInternetConnected();
    void onRequestError(Throwable t);
    void showLoading(boolean show);
}
