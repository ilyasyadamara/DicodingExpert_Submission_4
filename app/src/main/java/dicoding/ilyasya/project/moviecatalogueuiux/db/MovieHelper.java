package dicoding.ilyasya.project.moviecatalogueuiux.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import dicoding.ilyasya.project.moviecatalogueuiux.Movie;

import static dicoding.ilyasya.project.moviecatalogueuiux.db.DatabaseContractMovie.MovieColumns.ID;
import static dicoding.ilyasya.project.moviecatalogueuiux.db.DatabaseContractMovie.MovieColumns.MOVIE_TABLE_NAME;

public class MovieHelper {

    private static final String DATABASE_TABLE = MOVIE_TABLE_NAME;
    private static DatabaseHelperMovie DatabaseHelpermovie;
    private static MovieHelper INSTANCE;

    private static SQLiteDatabase database;

    private MovieHelper(Context context) { DatabaseHelpermovie = new DatabaseHelperMovie(context);
    }

    public static MovieHelper getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (SQLiteOpenHelper.class) {
                if (INSTANCE == null) {
                    INSTANCE = new MovieHelper(context);
                }
            }
        }
        return INSTANCE;
    }

    public void open() throws SQLException {
        database = DatabaseHelpermovie.getWritableDatabase();
    }

    public void close() {
        DatabaseHelpermovie.close();

        if (database.isOpen())
            database.close();
    }

    public ArrayList<Movie> getAllMovies() {
        ArrayList<Movie> arrayList = new ArrayList<>();
        Cursor cursor = database.query(DATABASE_TABLE, null,
                null,
                null,
                null,
                null,
                ID + " ASC",
                null);
        cursor.moveToFirst();
        Movie movie;
        if (cursor.getCount() > 0) {
            do {
                movie = new Movie();
                movie.setId(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseContractMovie.MovieColumns.ID)));
                movie.setJudul(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContractMovie.MovieColumns.JUDUL)));
                movie.setTanggal(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContractMovie.MovieColumns.TANGGAL)));
                movie.setRating(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContractMovie.MovieColumns.RATING)));
                movie.setDeskripsi(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContractMovie.MovieColumns.DESKRIPSI)));
                movie.setPoster(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContractMovie.MovieColumns.POSTER)));
                movie.setBg(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContractMovie.MovieColumns.BG)));

                arrayList.add(movie);
                cursor.moveToNext();

            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }

    public long insertMovie(Movie movie) {
        ContentValues args = new ContentValues();
        args.put(DatabaseContractMovie.MovieColumns.ID, movie.getId());
        args.put(DatabaseContractMovie.MovieColumns.JUDUL, movie.getJudul());
        args.put(DatabaseContractMovie.MovieColumns.TANGGAL, movie.getTanggal());
        args.put(DatabaseContractMovie.MovieColumns.RATING, movie.getRating());
        args.put(DatabaseContractMovie.MovieColumns.DESKRIPSI, movie.getDeskripsi());
        args.put(DatabaseContractMovie.MovieColumns.POSTER, movie.getPoster());
        args.put(DatabaseContractMovie.MovieColumns.BG, movie.getBg());

        return database.insert(DATABASE_TABLE, null, args);
    }

    public int deleteMovie(int id) {
        return database.delete(MOVIE_TABLE_NAME, ID + " = '" + id + "'", null);
    }
}
