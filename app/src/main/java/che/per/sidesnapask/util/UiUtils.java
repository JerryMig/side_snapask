package che.per.sidesnapask.util;

import android.content.res.Resources;
import android.text.TextUtils;
import android.text.format.DateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import che.per.sidesnapask.app.BaseApplication;

/**
 * Created by Jerry on 2018/1/8.
 */

public class UiUtils {

    private static final SimpleDateFormat RAW_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss Z");

    public static float convertDpToPx(int dp) {
        return dp * Resources.getSystem().getDisplayMetrics().density;
    }

    private static Date parseToDate(String thenTime) {
        Date parsedDate;
        if (TextUtils.isEmpty(thenTime)) {
            thenTime = "9999-12-31 12:00:00 +0800";
        }
        try {
            parsedDate = RAW_FORMAT.parse(thenTime);
            return parsedDate;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getTimeDifferenceFromNow(String thenTime) {
        Date thenDate = parseToDate(thenTime);

        if (thenDate != null) {
            // Get a calendar with current time.
            Calendar nowCalendar = Calendar.getInstance();

            // Get a calendar with then time
            Calendar thenCalendar = Calendar.getInstance();
            thenCalendar.setTime(thenDate);

            //Compute difference days with current time;
            long days = (nowCalendar.getTime().getTime() - thenDate.getTime())/(24*60*60*1000);

            if (DateUtils.isToday(thenDate.getTime())) {
                // It's the same day
                return DateUtils.formatDateTime(BaseApplication.getContext(), thenDate.getTime(), DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_12HOUR);
            } else if (days < 2) {
                // It's been a day
                return "yesterday";
            } else if (days <= 7 && nowCalendar.get(Calendar.DAY_OF_WEEK) != thenCalendar.get(Calendar.DAY_OF_WEEK)) {
                // It's been two days
                return DateUtils.formatDateTime(BaseApplication.getContext(), thenDate.getTime(), DateUtils.FORMAT_SHOW_WEEKDAY);
            } else if (nowCalendar.get(Calendar.YEAR) == thenCalendar.get(Calendar.YEAR)) {
                // It's been a week
                return DateUtils.formatDateTime(BaseApplication.getContext(), thenDate.getTime(), DateUtils.FORMAT_NO_YEAR | DateUtils.FORMAT_ABBREV_MONTH);
            } else if (nowCalendar.get(Calendar.YEAR) > thenCalendar.get(Calendar.YEAR)) {
                // It's been a year
                return DateUtils.formatDateTime(BaseApplication.getContext(), thenDate.getTime(), DateUtils.FORMAT_ABBREV_MONTH);
            }
        }
        return "";
    }

    public static String getFullAssetUrl(String path){
        if (!TextUtils.isEmpty(path)) {
            if (path.contains("http://") || path.contains("https://")) {
                return path;
            } else {
                return "https://" + "alb-uat.snapask.co" + path;
            }
        }
        return "";
    }

}
