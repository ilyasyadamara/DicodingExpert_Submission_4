package dicoding.ilyasya.project.moviecatalogueuiux;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class TvShowFragment extends Fragment {
    private RecyclerView rvMovies;
    private ListTvAdapter adapter;
    private ProgressBar progressBar;
    private MainViewModelTv mainViewModelTv;

    public TvShowFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tv_show, container, false);

        progressBar = view.findViewById(R.id.progressBar);
        rvMovies = view.findViewById(R.id.rv_movies);
        rvMovies.setHasFixedSize(true);

        mainViewModelTv = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(MainViewModelTv.class);
        rvMovies.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new ListTvAdapter();
        adapter.notifyDataSetChanged();
        rvMovies.setAdapter(adapter);
        mainViewModelTv.setListMovieShow();
        showLoading(true);
        //list.addAll(getListMovies());
        //showRecyclerList();

        adapter.setOnItemClickCallback(new ListTvAdapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(TvShow data) {
                showSelectedMovie(data);
            }
        });

        mainViewModelTv.getListMovieShow().observe(this, new Observer<ArrayList<TvShow>>() {
            @Override
            public void onChanged(ArrayList<TvShow> movieItemsTv) {
                if (movieItemsTv != null) {
                    adapter.setData(movieItemsTv);
                    showLoading(false);
                }
            }
        });
        return view;
    }

    private void showLoading(Boolean state) {
        if (state) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }

    //private ArrayList<Movie> getListMovies() {
    //    String[] dataJudul = getResources().getStringArray(R.array.data_judul2);
    //    String[] dataRilis = getResources().getStringArray(R.array.data_rilis2);
    //    String[] dataRating = getResources().getStringArray(R.array.data_rating2);
    //    String[] dataStatus = getResources().getStringArray(R.array.data_status2);
    //    String[] dataBahasa = getResources().getStringArray(R.array.data_bahasa2);
    //    String[] dataDurasi = getResources().getStringArray(R.array.data_durasi2);
    //    String[] dataDeskripsi = getResources().getStringArray(R.array.data_deskripsi2);
    //    @SuppressLint("Recycle") TypedArray dataPhoto = getResources().obtainTypedArray(R.array.data_poster2);
    //    ArrayList<Movie> listMovie = new ArrayList<>();
    //    for (int i = 0; i < dataJudul.length; i++) {
    //        Movie movie = new Movie();
    //        movie.setJudul(dataJudul[i]);
    //        movie.setTanggal(dataRilis[i]);
    //        movie.setRating(dataRating[i]);
    //        movie.setDeskripsi(dataDeskripsi[i]);
    //        movie.setStatus(dataStatus[i]);
    //        movie.setBahasa(dataBahasa[i]);
    //        movie.setDurasi(dataDurasi[i]);
    //        movie.setPoster(dataPhoto.getResourceId(i, -1));
    //        listMovie.add(movie);
    //    }
    //    return listMovie;
    //}

    //private void showRecyclerList(){
     //   rvMovies.setLayoutManager(new LinearLayoutManager(getContext()));
     //   ListMovieAdapter listMovieAdapter = new ListMovieAdapter(list);
     //   rvMovies.setAdapter(listMovieAdapter);

     //   listMovieAdapter.setOnItemClickCallback(new ListMovieAdapter.OnItemClickCallback() {
     //       @Override
     //       public void onItemClicked(Movie data) {
     //           showSelectedHero(data);
     //       }
     //   });
    // }

    private void showSelectedMovie(TvShow tvshow) {
        Intent detailMovie = new Intent(getContext(), DetailTvActivity.class);
        detailMovie.putExtra(DetailTvActivity.EXTRA_MOVIE, tvshow);
        startActivity(detailMovie);
    }

}
