package movies.udacity.com.popularmovies.network;

import android.util.Log;


import java.io.IOException;

import movies.udacity.com.popularmovies.Constants;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class BaseClient {


    protected Retrofit builder = null;

    private final String BASE_URL = "http://Api.themoviedb.org/";

    public BaseClient() {

    }

    private MovieAPI movieApi;


    public synchronized void init() {


        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build();

        builder = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        movieApi = builder.create(MovieAPI.class);
    }


    public void getMoviesList(final String order, final APICallBack<MovieList> apiCallBack) {

        Call<MovieList> callBack = movieApi.getMoviesList(order, Constants.API_KEY);
        callBack.enqueue(new Callback<MovieList>() {
            @Override
            public void onResponse(Call<MovieList> call, Response<MovieList> response) {
                if (response.isSuccess()) {
                    Log.d("LIST IS  **", response.body().toString());
                    apiCallBack.success(response.body());
                } else {
                    sendError(apiCallBack, response);
                }
            }

            @Override
            public void onFailure(Call<MovieList> call, Throwable t) {
                sendError(apiCallBack, t);
            }
        });
    }


    protected void sendError(APICallBack callback, Throwable t) {
        if (callback != null) {
            callback.error(new APIError(t.getMessage()));
        }
    }

    protected <T> void sendError(APICallBack callback, Response<T> response) {
        if (callback != null) {

            if (response != null && response.errorBody() != null) {
                try {
                    String message = new String(response.errorBody().bytes());
                    callback.error(new APIError(message));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}

