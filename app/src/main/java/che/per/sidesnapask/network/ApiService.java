package che.per.sidesnapask.network;

import java.util.Map;

import che.per.sidesnapask.constant.ApiKeys;
import che.per.sidesnapask.model.HttpResponse;
import che.per.sidesnapask.model.QuestionsWrapper;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by Jerry on 2018/1/3.
 */

public interface ApiService {

//    @GET("tutors/{tutor_id}/questions_history")
//    Observable<HttpResponse<QuestionsWrapper>> getQuestions(@Path("tutor_id") int tutor_id,
//                                                            @Query(ApiKeys.BUNDLE_QUESTION_STATUSES) String[] value,
//                                                            @Query(ApiKeys.BUNDLE_SUBJECT_IDS_ARRAY) int[] search_ids,
//                                                            @QueryMap Map<String, Object> map);

    @GET("students/{student_id}/questions_history")
    Observable<HttpResponse<QuestionsWrapper>> getQuestions(@Path("student_id") int student_id,
                                                                @Query(ApiKeys.BUNDLE_QUESTION_STATUSES) String[] statuses,
                                                                @Query(ApiKeys.BUNDLE_SUBJECT_IDS_ARRAY) int[] search_ids,
                                                                @QueryMap Map<String, Object> args);
}
