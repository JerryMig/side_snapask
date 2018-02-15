package che.per.sidesnapask.view;

/**
 * Created by Jerry on 2018/1/22.
 */

public interface LoginView extends InternetView {
    void onLogin(int role);
    void onLoginError();
    void onLoginSuccessful();
}
