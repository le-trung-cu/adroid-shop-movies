package funix.prm.prm391x_shopmovies_cultfx02223funixeduvn;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.GridView;

import java.util.ArrayList;

import funix.prm.prm391x_shopmovies_cultfx02223funixeduvn.adapters.MovieAdapter;
import funix.prm.prm391x_shopmovies_cultfx02223funixeduvn.clients.MovieClient;
import funix.prm.prm391x_shopmovies_cultfx02223funixeduvn.models.Movie;

public class MoviesFragment extends Fragment {

    private MovieAdapter mMovieAdapter;

    public MoviesFragment() {
    }

    public static MoviesFragment newInstance() {
        MoviesFragment fragment = new MoviesFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mMovieAdapter = new MovieAdapter(getContext(), new ArrayList<Movie>());
        MovieClient movieClient = new MovieClient();
        movieClient.setOnLoadListener(new MovieClient.OnMovieLoad() {
            @Override
            public void onLoaded(ArrayList<Movie> movies) {
                mMovieAdapter.addAll(movies);
            }
        });
        movieClient.execute("https://api.androidhive.info/json/movies_2017.json");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_movies, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        ArrayList<Movie> movies = new ArrayList<>();
//        movies.add(new Movie("htt", "title 1", "$4"));
//        movies.add(new Movie("htt", "title 2", "$4"));
//        movies.add(new Movie("htt", "title 3", "$4"));
//        movies.add(new Movie("htt", "title 4", "$4"));
//        movies.add(new Movie("htt", "title 5", "$4"));
//        movies.add(new Movie("htt", "title 6", "$4"));
//        movies.add(new Movie("htt", "title 7", "$4"));

        GridView gridViewMovies = view.findViewById(R.id.gv_movies);
        gridViewMovies.setAdapter(mMovieAdapter);
    }
}