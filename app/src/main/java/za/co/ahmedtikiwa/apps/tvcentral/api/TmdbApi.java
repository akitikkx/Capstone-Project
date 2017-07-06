package za.co.ahmedtikiwa.apps.tvcentral.api;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import za.co.ahmedtikiwa.apps.tvcentral.models.ShowCreditsResponse;
import za.co.ahmedtikiwa.apps.tvcentral.models.ShowInfoResponse;
import za.co.ahmedtikiwa.apps.tvcentral.models.ShowsResponse;

public class TmdbApi {

    public static final String BASE_URL = "https://api.themoviedb.org/3/";
    private static TmdbApiInterface mTmdbApiService;

    public static TmdbApiInterface getTmdbApiClient() {
        if (mTmdbApiService == null) {

            // set the logging for the retrofit calls
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

            // add the logging interceptor to the call
            httpClient.addInterceptor(loggingInterceptor);

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(httpClient.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            mTmdbApiService = retrofit.create(TmdbApiInterface.class);
        }
        return mTmdbApiService;
    }

    public interface TmdbApiInterface {
        @GET("tv/popular/")
        Call<ShowsResponse> popularShows(@Query("api_key") String apiKey);

        @GET("tv/airing_today/")
        Call<ShowsResponse> airingToday(@Query("api_key") String apiKey);

        @GET("tv/on_the_air/")
        Call<ShowsResponse> upcomingWeek(@Query("api_key") String apiKey);

        @GET("tv/top_rated/")
        Call<ShowsResponse> topRated(@Query("api_key") String apiKey);

        @GET("tv/{tv_id}")
        Call<ShowInfoResponse> getDetails(@Path("tv_id") long tvId, @Query("api_key") String apiKey);

        @GET("tv/{tv_id}/credits")
        Call<ShowCreditsResponse> getCredits(@Path("tv_id") long tvId, @Query("api_key") String apiKey);

        @GET("tv/{tv_id}/similar")
        Call<ShowsResponse> getSimilar(@Path("tv_id") long tvId, @Query("api_key") String apiKey);

        @GET("tv/{tv_id}/videos")
        Call<ShowsResponse> getVideos(@Path("tv_id") long tvId, @Query("api_key") String apiKey);
    }

}
