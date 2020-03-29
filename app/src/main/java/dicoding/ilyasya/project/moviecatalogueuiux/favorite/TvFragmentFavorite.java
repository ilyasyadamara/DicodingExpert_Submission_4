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

import dicoding.ilyasya.project.moviecatalogueuiux.DetailTvActivity;
import dicoding.ilyasya.project.moviecatalogueuiux.R;
import dicoding.ilyasya.project.moviecatalogueuiux.TvShow;
import dicoding.ilyasya.project.moviecatalogueuiux.db.TvShowHelper;


/**
 * A simple {@link Fragment} subclass.
 */
public class TvFragmentFavorite extends Fragment {

    private ArrayList<TvShow> tvShows = new ArrayList<>();
    private ProgressBar progressBar;
    private RecyclerView rvMovies;
    private ListTvAdapterFav adapter;
    //private RecyclerView.LayoutManager layoutManager;
    private TvShowHelper tvHelper;


    public TvFragmentFavorite() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tv_fragment_favorite, container, false);

        progressBar = view.findViewById(R.id.progressBar);
        rvMovies = view.findViewById(R.id.rv_movies);
        rvMovies.setLayoutManager(new LinearLayoutManager(getContext()));
        rvMovies.setHasFixedSize(true);
        adapter = new ListTvAdapterFav(view.getContext());
        adapter.setmData(tvShows);
        rvMovies.setAdapter(adapter);

        tvHelper = TvShowHelper.getInstance(getContext());
        tvHelper.open();

        adapter.setOnItemClickCallback(new ListTvAdapterFav.OnItemClickCallback() {
            @Override
            public void onItemClicked(TvShow data) {
                showSelectedMovie(data);
            }
        });


        return view;
    }

    private void showSelectedMovie(TvShow tvshow) {
        Intent detailMovie = new Intent(getContext(), DetailTvActivity.class);
        detailMovie.putExtra(DetailTvActivity.EXTRA_MOVIE, tvshow);
        startActivity(detailMovie);
    }

    @Override
    public void onResume() {
        super.onResume();
        tvShows = tvHelper.getAllTvShows();
        adapter.setData(tvShows);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        tvHelper.close();
    }

}
