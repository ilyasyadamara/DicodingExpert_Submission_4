package dicoding.ilyasya.project.moviecatalogueuiux.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import dicoding.ilyasya.project.moviecatalogueuiux.TvShow;

import static dicoding.ilyasya.project.moviecatalogueuiux.db.DatabaseContractTv.TvColumns.ID;
import static dicoding.ilyasya.project.moviecatalogueuiux.db.DatabaseContractTv.TvColumns.TV_TABLE_NAME;

public class TvShowHelper {

    private static final String DATABASE_TABLE = TV_TABLE_NAME;
    private static DatabaseHelperTv DatabaseHelpertv;
    private static TvShowHelper INSTANCE;

    private static SQLiteDatabase database;

    private TvShowHelper(Context context) {
        DatabaseHelpertv = new DatabaseHelperTv(context);
    }

    public static TvShowHelper getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (SQLiteOpenHelper.class) {
                if (INSTANCE == null) {
                    INSTANCE = new TvShowHelper(context);
                }
            }
        }
        return INSTANCE;
    }

    public void open() throws SQLException {
        database = DatabaseHelpertv.getWritableDatabase();
    }

    public void close() {
        DatabaseHelpertv.close();

        if (database.isOpen())
            database.close();
    }

    public ArrayList<TvShow> getAllTvShows() {
        ArrayList<TvShow> arrayList = new ArrayList<>();
        Cursor cursor = database.query(DATABASE_TABLE, null,
                null,
                null,
                null,
                null,
                ID + " ASC",
                null);
        cursor.moveToFirst();
        TvShow tvShow;
        if (cursor.getCount() > 0) {
            do {
                tvShow = new TvShow();
                tvShow.setId(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseContractTv.TvColumns.ID)));
                tvShow.setJudul(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContractTv.TvColumns.JUDUL)));
                tvShow.setTanggal(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContractTv.TvColumns.TANGGAL)));
                tvShow.setRating(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContractTv.TvColumns.RATING)));
                tvShow.setDeskripsi(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContractTv.TvColumns.DESKRIPSI)));
                tvShow.setPoster(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContractTv.TvColumns.POSTER)));
                tvShow.setBg(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContractTv.TvColumns.BG)));

                arrayList.add(tvShow);
                cursor.moveToNext();

            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }

    public long insertTvShow(TvShow tvShow) {
        ContentValues args = new ContentValues();
        args.put(DatabaseContractTv.TvColumns.ID, tvShow.getId());
        args.put(DatabaseContractTv.TvColumns.JUDUL, tvShow.getJudul());
        args.put(DatabaseContractTv.TvColumns.TANGGAL, tvShow.getTanggal());
        args.put(DatabaseContractTv.TvColumns.RATING, tvShow.getRating());
        args.put(DatabaseContractTv.TvColumns.DESKRIPSI, tvShow.getDeskripsi());
        args.put(DatabaseContractTv.TvColumns.POSTER, tvShow.getPoster());
        args.put(DatabaseContractTv.TvColumns.BG, tvShow.getBg());

        return database.insert(DATABASE_TABLE, null, args);
    }

    public int deleteTvShow(int id) {
        return database.delete(TV_TABLE_NAME, ID + " = '" + id + "'", null);
    }
}
