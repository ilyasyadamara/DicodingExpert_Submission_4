package dicoding.ilyasya.project.moviecatalogueuiux;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
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
public class MoviesFragment extends Fragment {
    private RecyclerView rvMovies;
    private ListMovieAdapter adapter;
    // private ArrayList<Movie> list = new ArrayList<>();
    private MainViewModel mainViewModel;
    private ProgressBar progressBar;

    public MoviesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_movies, container, false);

        progressBar = view.findViewById(R.id.progressBar);
        rvMovies = view.findViewById(R.id.rv_movies);
        rvMovies.setHasFixedSize(true);

        mainViewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(MainViewModel.class);
        rvMovies.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new ListMovieAdapter();
        adapter.notifyDataSetChanged();
        rvMovies.setAdapter(adapter);
        mainViewModel.setListMovie();
        showLoading(true);
        //list.addAll(getListMovies());
        //showRecyclerList();


        adapter.setOnItemClickCallback(new ListMovieAdapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(Movie data) {
                showSelectedMovie(data);
            }
        });

        mainViewModel.getListMovie().observe(this, new Observer<ArrayList<Movie>>() {
            @Override
            public void onChanged(ArrayList<Movie> movieItems) {
                if (movieItems != null) {
                    adapter.setData(movieItems);
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
    //    String[] dataJudul = getResources().getStringArray(R.array.data_judul);
    //    String[] dataRilis = getResources().getStringArray(R.array.data_rilis);
    //    String[] dataRating = getResources().getStringArray(R.array.data_rating);
    //    String[] dataStatus = getResources().getStringArray(R.array.data_status);
    //    String[] dataBahasa = getResources().getStringArray(R.array.data_bahasa);
    //    String[] dataDurasi = getResources().getStringArray(R.array.data_durasi);
    //    String[] dataDeskripsi = getResources().getStringArray(R.array.data_deskripsi);
    //    @SuppressLint("Recycle") TypedArray dataPhoto = getResources().obtainTypedArray(R.array.data_poster);
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
      //  rvMovies.setAdapter(listMovieAdapter);

      //  listMovieAdapter.setOnItemClickCallback(new ListMovieAdapter.OnItemClickCallback() {
      //      @Override
       //     public void onItemClicked(Movie data) {
       //             showSelectedHero(data);
       //     }
      //  });
   // }

    private void showSelectedMovie(Movie movie) {
        Intent detailMovie = new Intent(getContext(), DetailActivity.class);
        detailMovie.putExtra(DetailActivity.EXTRA_MOVIE, movie);
        startActivity(detailMovie);
    }

}
