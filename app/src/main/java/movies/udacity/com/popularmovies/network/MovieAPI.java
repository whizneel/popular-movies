package movies.udacity.com.popularmovies.network;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;



public interface MovieAPI {

    @GET("3/discover/movie?")
    Call<MovieList> getMoviesList(@Query("sort_by") String sort, @Query("api_key") String apiKey);

}



