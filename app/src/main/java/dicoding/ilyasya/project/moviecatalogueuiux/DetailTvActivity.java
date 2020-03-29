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

import dicoding.ilyasya.project.moviecatalogueuiux.db.TvShowHelper;

import static dicoding.ilyasya.project.moviecatalogueuiux.R.id.favorite;

public class DetailTvActivity extends AppCompatActivity {

    Integer id;
    String judul, rating, rilis, deskripsi, poster, bg;
    TextView tvJudul, tvRilis, tvRating, tvDeskripsi, tvid;
    ImageView imgPoster;
    ImageView imgBg;
    private TvShow tvshow;

    private Menu menu;
    private boolean isFavorite;
    private TvShowHelper tvShowHelper;

    public static final String EXTRA_MOVIE = "extra_movie";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_tv);

        // set Textview
        tvJudul = findViewById(R.id.txt_judul);
        tvRilis = findViewById(R.id.txt_rilis);
        tvRating = findViewById(R.id.txt_rating);
        tvDeskripsi = findViewById(R.id.txt_deskripsi);
        imgPoster = findViewById(R.id.img_photo);
        imgBg = findViewById(R.id.img_bg);

        //get data Intent dari Activity
        Intent intent = getIntent();
        tvshow = intent.getParcelableExtra(EXTRA_MOVIE);
        if (tvshow != null) {
            judul = tvshow.getJudul();
            rilis = tvshow.getTanggal();
            rating = tvshow.getRating();
            deskripsi = tvshow.getDeskripsi();
            poster = tvshow.getPoster();
            bg = tvshow.getBg();
            id = tvshow.getId();


        }
        setDetailMovieView();
        tvShowHelper = TvShowHelper.getInstance(getApplicationContext());
        tvShowHelper.open();

        isFavorite = false;
        checkFavorite();

    }

    private void setDetailMovieView() {
        if (tvshow != null){
            // set Data
            tvJudul.setText(judul);
            tvRilis.setText(rilis);
            tvRating.setText(rating);
            tvDeskripsi.setText(deskripsi);

            Glide.with(this).load(poster).into(imgPoster);
            Glide.with(this).load(bg).into(imgBg);
        }
        else {

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

    private void checkFavorite() {
        ArrayList<TvShow> tvShowsInDatabase = tvShowHelper.getAllTvShows();

        for (TvShow tvShow: tvShowsInDatabase){

            if (this.tvshow.getId() == tvShow.getId()){
                isFavorite = true;
            }

            if (isFavorite == true) {
                break;
            }
        }
    }

    private void setIconFavorite(){
        if (isFavorite) {
            menu.getItem(0).setIcon(getResources().getDrawable(R.drawable.ic_favorite_black_24dp));
        } else {
            menu.getItem(0).setIcon(getResources().getDrawable(R.drawable.ic_favorite_grey_24dp));
        }
    }

    private void favoriteButtonPressed(){
        if (isFavorite) {
            tvShowHelper.deleteTvShow(tvshow.getId());

        } else {
            tvShowHelper.insertTvShow(tvshow);
        }
        isFavorite = !isFavorite;
        setIconFavorite();
    }


}
