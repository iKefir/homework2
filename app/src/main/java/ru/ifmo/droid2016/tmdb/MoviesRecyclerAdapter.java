package ru.ifmo.droid2016.tmdb;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.Collections;
import java.util.List;
import java.util.Locale;

import ru.ifmo.droid2016.tmdb.model.Movie;

/**
 * Created by danilskarupin on 22.11.16.
 */

public class MoviesRecyclerAdapter
        extends RecyclerView.Adapter<MoviesRecyclerAdapter.MovieViewHolder> {

    private final Context context;
    private final LayoutInflater layoutInflater;

    @NonNull
    private List<Movie> movies = Collections.emptyList();

    public MoviesRecyclerAdapter(Context context) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
    }

    public void setMovies(@NonNull List<Movie> movies) {
        this.movies = movies;
        notifyDataSetChanged();
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return MovieViewHolder.newInstance(layoutInflater, parent);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        final Movie movie = movies.get(position);
        String additionalInfo = (movie.originalTitle.equals(movie.localizedTitle) ? "" : movie.originalTitle + " ") + "(" +
                movie.releaseDate.substring(0, 4) + ")";
        holder.titleView.setText(movie.localizedTitle);
        holder.imageView.setImageURI(movie.posterPath);
        holder.additionalInfoView.setText(additionalInfo);
        holder.descriptionView.setText(movie.overviewText);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    static class MovieViewHolder extends RecyclerView.ViewHolder {

        final SimpleDraweeView imageView;
        final TextView titleView;
        final TextView additionalInfoView;
        final TextView descriptionView;

        private MovieViewHolder(View itemView) {
            super(itemView);
            imageView = (SimpleDraweeView) itemView.findViewById(R.id.movie_image);
            titleView = (TextView) itemView.findViewById(R.id.movie_title);
            additionalInfoView = (TextView) itemView.findViewById(R.id.movie_additional_info);
            descriptionView = (TextView) itemView.findViewById(R.id.movie_description);
        }

        static MovieViewHolder newInstance(LayoutInflater layoutInflater, ViewGroup parent) {
            final View view = layoutInflater.inflate(R.layout.item_movie, parent, false);
            return new MovieViewHolder(view);
        }
    }
}
