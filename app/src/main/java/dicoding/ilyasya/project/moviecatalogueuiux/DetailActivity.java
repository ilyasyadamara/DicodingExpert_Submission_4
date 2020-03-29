package dicoding.ilyasya.project.moviecatalogueuiux;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import dicoding.ilyasya.project.moviecatalogueuiux.db.MovieHelper;

import static dicoding.ilyasya.project.moviecatalogueuiux.R.id.favorite;

public class DetailActivity extends AppCompatActivity {

    Integer id;
    String judul, rating, rilis, deskripsi, poster, bg;
    TextView tvJudul, tvRilis, tvRating, tvDeskripsi, tvid;
    ImageView imgPoster;
    ImageView imgBg;
    private Movie movie;

    private Menu menu;
    private boolean isFavorite;

    private MovieHelper movieHelper;

    public static final String EXTRA_MOVIE = "extra_movie";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

// set Textview
        tvJudul = findViewById(R.id.txt_judul);
        tvRilis = findViewById(R.id.txt_rilis);
        tvRating = findViewById(R.id.txt_rating);
        tvDeskripsi = findViewById(R.id.txt_deskripsi);
        imgPoster = findViewById(R.id.img_photo);
        imgBg = findViewById(R.id.img_bg);

        //get data Intent dari Activity
        Intent intent = getIntent();
        movie = intent.getParcelableExtra(EXTRA_MOVIE);
        if (movie != null) {
        judul = movie.getJudul();
        rilis = movie.getTanggal();
        rating = movie.getRating();
        deskripsi = movie.getDeskripsi();
        poster = movie.getPoster();
        bg = movie.getBg();
        id = movie.getId();

        }
        movieHelper = MovieHelper.getInstance(getApplicationContext());
        movieHelper.open();

        setDetailMovieView();

        isFavorite = false;
        checkFavorite();


    }

    private void checkFavorite() {
        ArrayList<Movie> moviesInDatabase = movieHelper.getAllMovies();

        for (Movie movie: moviesInDatabase){

            if (this.movie.getId() == movie.getId()){
                isFavorite = true;
            }

            if (isFavorite == true) {
                break;
            }
        }
    }

    private void setDetailMovieView() {
        if (movie != null){
            // set Data
            tvJudul.setText(judul);
            tvRilis.setText(rilis);
            tvRating.setText(rating);
            tvDeskripsi.setText(deskripsi);

            Glide.with(this).load(poster).into(imgPoster);
            Glide.with(this).load(bg).into(imgBg);
        } else {

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        this.menu = menu;

        setIconFavorite();
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == favorite) {
            favoriteButtonPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    private void favoriteButtonPressed(){
        if (isFavorite) {
            movieHelper.deleteMovie(movie.getId());

        } else {
            movieHelper.insertMovie(movie);
        }
        isFavorite = !isFavorite;
        setIconFavorite();
    }

    private void setIconFavorite(){
        if (isFavorite) {
            menu.getItem(0).setIcon(getResources().getDrawable(R.drawable.ic_favorite_black_24dp));
        } else {
            menu.getItem(0).setIcon(getResources().getDrawable(R.drawable.ic_favorite_grey_24dp));
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        movieHelper.close();
    }
}
