package che.per.sidesnapask.util;

import java.util.HashMap;

/**
 * Created by Jerry on 2018/1/7.
 */

public class ApiUtils {

    public static HashMap<String, Object> getBasicParams() {
        HashMap<String, Object> params = new HashMap<>();
        params.put("device_type", "android");
        params.put("lang", "en");
        return params;
    }

}
