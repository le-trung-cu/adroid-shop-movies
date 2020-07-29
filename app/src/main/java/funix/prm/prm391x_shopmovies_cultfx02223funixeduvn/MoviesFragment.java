package funix.prm.prm391x_shopmovies_cultfx02223funixeduvn;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;

import funix.prm.prm391x_shopmovies_cultfx02223funixeduvn.adapters.MovieAdapter;
import funix.prm.prm391x_shopmovies_cultfx02223funixeduvn.clients.MovieClient;
import funix.prm.prm391x_shopmovies_cultfx02223funixeduvn.models.Movie;

public class MoviesFragment extends Fragment {

    private MovieAdapter mMovieAdapter;
    private final String MOVIE_DETAIL_CODE = "movie_detail";

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
        GridView gridViewMovies = view.findViewById(R.id.gv_movies);
        gridViewMovies.setAdapter(mMovieAdapter);
        gridViewMovies.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Movie movie = mMovieAdapter.getItem(position);
                Intent intent = new Intent(getActivity(), MovieDetailActivity.class);
                intent.putExtra(MOVIE_DETAIL_CODE, movie);
                startActivity(intent);
            }
        });
    }
}