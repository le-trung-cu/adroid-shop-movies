package funix.prm.prm391x_shopmovies_cultfx02223funixeduvn.clients;


import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;

import funix.prm.prm391x_shopmovies_cultfx02223funixeduvn.models.Movie;

public class MovieClient extends AsyncTask<String, Void, ArrayList<Movie>> {
//"https://api.androidhive.info/json/movies_2017.json"
    private OnMovieLoad mListener;

    public void setOnLoadListener(OnMovieLoad listener){
        mListener = listener;
    }
    @Override
    protected ArrayList<Movie> doInBackground(String... strings) {
        ArrayList<Movie> movies = new ArrayList<>();

        try {
            URL movieUrl = new URL(strings[0]);
            InputStream in = movieUrl.openStream();
            JsonReader jsonReader = Json.createReader(in);
            JsonArray jsonValues = jsonReader.readArray();
            for(JsonObject result : jsonValues.getValuesAs(JsonObject.class)){
                Movie movie = Movie.from(result);
                movies.add(movie);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return movies;
    }

    @Override
    protected void onPostExecute(ArrayList<Movie> movies) {
        super.onPostExecute(movies);
        if(mListener != null){
            mListener.onLoaded(movies);
        }
    }

    public interface OnMovieLoad{
        void onLoaded(ArrayList<Movie> movies);
    }
}
