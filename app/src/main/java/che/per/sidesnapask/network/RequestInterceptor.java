package che.per.sidesnapask.network;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import javax.inject.Inject;
import javax.inject.Singleton;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Jerry on 2018/1/3.
 */

@Singleton
public class RequestInterceptor implements Interceptor {

    @Inject
    public RequestInterceptor() {
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();
//        String jwt = PrefManager.getJWT(mContext);
        Request request = original.newBuilder()
                .header("Snapask-User-Agent", getAgentString())
                .header("Snapask-Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VyX3Nlc3Npb25faWQiOjc2OTg3LCJ1c2VyX2FnZW50Ijp7ImRldmljZV9pZCI6Ijg5Y2UxYmFlMzkxMmQ1ZmIiLCJvcyI6ImFuZHJvaWQiLCJwbGF0Zm9ybSI6ImFwcCIsInZlcnNpb24iOjUxMTAwfSwiaXNzIjoiU25hcGFzayBDby4sIEx0ZC4gfCBVQVQifQ.9x_mbWjfElh3j8QdPKMEhnX5W59UDq7WJSnL_Du022g")
                .method(original.method(), original.body())
                .build();
        Log.d("OKHttp", request.headers().toString());
        return chain.proceed(request);
    }

    private String getAgentString() {
        JSONObject agent = new JSONObject();
        try {
            agent.put("device_id", "89ce1bae3912d5fb");
            agent.put("os", "android");
            agent.put("platform", "app");
            agent.put("version", "51000");
            return agent.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "";
    }

}
