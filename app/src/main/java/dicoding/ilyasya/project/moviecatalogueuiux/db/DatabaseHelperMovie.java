package dicoding.ilyasya.project.moviecatalogueuiux.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static dicoding.ilyasya.project.moviecatalogueuiux.db.DatabaseContractMovie.MovieColumns.MOVIE_TABLE_NAME;

public class DatabaseHelperMovie extends SQLiteOpenHelper {

    private static final String MOVIE_DATABASE_NAME = "dbmovie";

    private static final int MOVIE_DATABASE_VERSION = 1;

    private static final String SQL_CREATE_TABLE_MOVIE = String.format("CREATE TABLE %s" +
                    " (%s TEXT NULL," +
                    " %s TEXT NULL," +
                    " %s TEXT NULL," +
                    " %s TEXT NULL," +
                    " %s TEXT NULL," +
                    " %s TEXT NULL," +
                    " %s TEXT NULL)",
            MOVIE_TABLE_NAME,
//            MovieColumns._ID,
            DatabaseContractMovie.MovieColumns.ID,
            DatabaseContractMovie.MovieColumns.JUDUL,
            DatabaseContractMovie.MovieColumns.TANGGAL,
            DatabaseContractMovie.MovieColumns.RATING,
            DatabaseContractMovie.MovieColumns.DESKRIPSI,
            DatabaseContractMovie.MovieColumns.POSTER,
            DatabaseContractMovie.MovieColumns.BG
    );

    DatabaseHelperMovie(Context context) {
        super(context, MOVIE_DATABASE_NAME, null, MOVIE_DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE_MOVIE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + MOVIE_TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
