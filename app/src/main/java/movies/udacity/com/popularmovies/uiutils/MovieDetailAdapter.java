package movies.udacity.com.popularmovies.uiutils;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import movies.udacity.com.popularmovies.R;
import movies.udacity.com.popularmovies.network.MovieList;


public class MovieDetailAdapter extends RecyclerView.Adapter<MovieDetailAdapter.MovieView> {

    private Context context;

    public void setMovieList(MovieList movieList) {
        this.movieList = movieList;
    }

    private MovieList movieList;



    public MovieDetailAdapter(Context context, MovieList movieList) {
        this.context = context;
        this.movieList = movieList;
    }

    @Override
    public MovieView onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_item, parent, false);
        MovieView masonryView = new MovieView(layoutView);
        return masonryView;
    }

    @Override
    public void onBindViewHolder(MovieView holder, int position) {
        //   holder.imageView.setImageResource(imgList[position]);
        Glide.with(context).load(getCompleteImageURL(movieList.getResults().get(position).getPosterPath())).into(holder.imageView);
        holder.textView.setText(movieList.getResults().get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return movieList.getResults().size();
    }

    class MovieView extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;

        public MovieView(View itemView) {
            super(itemView);

            imageView = (ImageView) itemView.findViewById(R.id.img);
            textView = (TextView) itemView.findViewById(R.id.movie_name);

        }
    }

    public String getCompleteImageURL(String url) {
        String baseURL = "http://image.tmdb.org/t/p/w185";
        String completeURL = baseURL + url;
        Log.d("URL IS *****", completeURL);
        return completeURL;

    }


}
