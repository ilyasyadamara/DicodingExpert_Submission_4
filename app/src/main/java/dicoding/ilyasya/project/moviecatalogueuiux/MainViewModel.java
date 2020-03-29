package dicoding.ilyasya.project.moviecatalogueuiux;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MainViewModel extends ViewModel {
    private static final String API_KEY = BuildConfig.TMDB_API_KEY;
    private static final String API_POSTER = BuildConfig.TMDB_API_POSTER;
    private MutableLiveData<ArrayList<Movie>> listMovie = new MutableLiveData<>();

    public MutableLiveData<ArrayList<Movie>> getListMovie() {
        return listMovie;
    }

    public void setListMovie() {
        AsyncHttpClient client = new AsyncHttpClient();
        final ArrayList<Movie> listItems = new ArrayList<>();
        String url = "https://api.themoviedb.org/3/discover/movie?api_key=dba7100ddaff47a7f37e8d8e6622d6a0&language=en-US" + API_KEY;
        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String result = new String(responseBody);
                    JSONObject responseObject = new JSONObject(result);
                    JSONArray list = responseObject.getJSONArray("results");
                    for (int i = 0; i < list.length(); i++) {
                        JSONObject results = list.getJSONObject(i);
                        Movie movieItems = new Movie();

                        movieItems.setId(results.getInt("id"));
                        movieItems.setJudul(results.getString("title"));
                        movieItems.setTanggal(results.getString("release_date"));
                        movieItems.setRating(results.getString("vote_average"));
                        movieItems.setDeskripsi(results.getString("overview"));
                        movieItems.setPoster(API_POSTER +(results.getString("poster_path")));
                        movieItems.setBg(API_POSTER +(results.getString("backdrop_path")));

                        listItems.add(movieItems);
                        // Log.d("data :", movieItems);
                    }
                    listMovie.postValue(listItems);
                } catch (Exception e) {
                    Log.d("Exception", e.getMessage());
                }
            }

            @Override
            public void onFailure(int statusCode, cz.msebera.android.httpclient.Header[] headers, byte[] responseBody, Throwable error) {
                Log.d("onFailure", error.getMessage());
            }
        });
    }

    //public LiveData<ArrayList<Movie>> getMovies() {
    //    return listMovie;
    //}
}
