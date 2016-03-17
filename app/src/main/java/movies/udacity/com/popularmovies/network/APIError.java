package movies.udacity.com.popularmovies.network;




public class APIError {
    private String errorMessage = null;

    public APIError(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}