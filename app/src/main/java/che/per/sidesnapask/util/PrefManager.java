package che.per.sidesnapask.util;

import android.content.Context;
import android.content.SharedPreferences;

import che.per.sidesnapask.app.BaseApplication;

/**
 * Created by Jerry on 2018/1/7.
 */

public class PrefManager {

    public static class AppPref {

        private static String PREF_NAME = "pref";

        private static final String USER_ID = "USER_ID";
        private static final String ROLE = "ROLE";

        public static SharedPreferences getPreference() {
            return  BaseApplication.getContext().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        }
    }

    public static int getUserId() {
        return AppPref.getPreference().getInt(AppPref.USER_ID, 120724);
    }

    public static void setUserId(int userId) {
        AppPref.getPreference().edit().putInt(AppPref.USER_ID, userId).apply();
    }

    public static boolean isStudent() {
        return AppPref.getPreference().getBoolean(AppPref.ROLE, true);
    }

}
