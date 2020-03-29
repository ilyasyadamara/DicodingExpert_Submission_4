package dicoding.ilyasya.project.moviecatalogueuiux.favorite;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import java.util.ArrayList;

import dicoding.ilyasya.project.moviecatalogueuiux.DetailActivity;
import dicoding.ilyasya.project.moviecatalogueuiux.Movie;
import dicoding.ilyasya.project.moviecatalogueuiux.R;
import dicoding.ilyasya.project.moviecatalogueuiux.db.MovieHelper;

/**
 * A simple {@link Fragment} subclass.
 */
public class MoviesFragmentFavorite extends Fragment {

    private ArrayList<Movie> movies = new ArrayList<>();
    private ProgressBar progressBar;
    private RecyclerView rvMovies;
    private ListMovieAdapterFav adapter;
    private MovieHelper movieHelper;


    public MoviesFragmentFavorite() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_movies_fragment_favorite, container, false);

        progressBar = view.findViewById(R.id.progressBar);
        rvMovies = view.findViewById(R.id.rv_movies);
        rvMovies.setLayoutManager(new LinearLayoutManager(getContext()));
        rvMovies.setHasFixedSize(true);
        adapter = new ListMovieAdapterFav(view.getContext());
        adapter.setmData(movies);
        rvMovies.setAdapter(adapter);
        //showLoading(true);

        movieHelper = MovieHelper.getInstance(getContext());
        movieHelper.open();

        adapter.setOnItemClickCallback(new ListMovieAdapterFav.OnItemClickCallback() {
            @Override
            public void onItemClicked(Movie data) {
                showSelectedMovie(data);
            }
        });

        // proses ambil data
        //new LoadMoviesAsync(movieHelper, this).execute();

        //if (savedInstanceState == null) {
            // proses ambil data
         //   new LoadMoviesAsync(movieHelper, this).execute();
        //} else {
         //   ArrayList<Movie> list = savedInstanceState.getParcelableArrayList(EXTRA_STATE);
         //   if (list != null) {
        //        adapter.setListMovie(list);
        //    }
        //}

        //daftarMovie = new ArrayList<>();

        //db = Room.databaseBuilder(getContext(),
         //       AppDatabase.class, "moviedb").allowMainThreadQueries().build();

        //daftarMovie.addAll(Arrays.asList(db.movieDAO().selectAllMovies()));

        //adapter.setOnItemClickCallback(new ListMovieAdapter.OnItemClickCallback() {
         //   @Override
         //   public void onItemClicked(Movie data) {
         //       showSelectedMovie(data);
          //  }
        //});


        return view;
    }

    private void showLoading(Boolean state) {
        if (state) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }

    //public static Intent getActIntent(Activity activity) {
    //    // kode untuk pengambilan Intent
    //    return new Intent(activity, MoviesFragmentFavorite.class);
    //}

    private void showSelectedMovie(Movie movie) {
        Intent detailMovie = new Intent(getContext(), DetailActivity.class);
        detailMovie.putExtra(DetailActivity.EXTRA_MOVIE, movie);
        startActivity(detailMovie);
    }

    @Override
    public void onResume() {
        super.onResume();
        movies = movieHelper.getAllMovies();
        adapter.setData(movies);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        movieHelper.close();
    }
}
