package movies.udacity.com.popularmovies.network;



public interface APICallBack<T> {

    void success(T t);

    void error(APIError error);
}