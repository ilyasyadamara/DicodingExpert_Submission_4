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

public class MainViewModelTv extends ViewModel {
    private static final String API_KEY = BuildConfig.TMDB_API_KEY;
    private static final String API_POSTER = BuildConfig.TMDB_API_POSTER;
    private MutableLiveData<ArrayList<TvShow>> listTvShow = new MutableLiveData<>();

    public MutableLiveData<ArrayList<TvShow>> getListMovieShow() {
        return listTvShow;
    }

    public void setListMovieShow() {
        AsyncHttpClient client = new AsyncHttpClient();
        final ArrayList<TvShow> listItemsTv = new ArrayList<>();
        String url = "https://api.themoviedb.org/3/discover/tv?api_key=dba7100ddaff47a7f37e8d8e6622d6a0&language=en-US" + API_KEY;
        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String result = new String(responseBody);
                    JSONObject responseObject = new JSONObject(result);
                    JSONArray list = responseObject.getJSONArray("results");
                    for (int i = 0; i < list.length(); i++) {
                        JSONObject resultstv = list.getJSONObject(i);
                        TvShow movieItemsTv = new TvShow();

                        movieItemsTv.setId(resultstv.getInt("id"));
                        movieItemsTv.setJudul(resultstv.getString("name"));
                        movieItemsTv.setTanggal(resultstv.getString("first_air_date"));
                        movieItemsTv.setRating(resultstv.getString("vote_average"));
                        movieItemsTv.setDeskripsi(resultstv.getString("overview"));
                        movieItemsTv.setPoster(API_POSTER +(resultstv.getString("poster_path")));
                        movieItemsTv.setBg(API_POSTER +(resultstv.getString("backdrop_path")));

                        listItemsTv.add(movieItemsTv);
                        // Log.d("data :", movieItems);
                    }
                    listTvShow.postValue(listItemsTv);
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




}
