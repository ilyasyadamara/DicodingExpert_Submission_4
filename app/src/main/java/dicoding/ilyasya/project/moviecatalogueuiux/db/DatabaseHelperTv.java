package dicoding.ilyasya.project.moviecatalogueuiux.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static dicoding.ilyasya.project.moviecatalogueuiux.db.DatabaseContractTv.TvColumns.TV_TABLE_NAME;

public class DatabaseHelperTv extends SQLiteOpenHelper {

    private static final String TV_DATABASE_NAME = "dbtv";

    private static final int TV_DATABASE_VERSION = 1;

    private static final String SQL_CREATE_TABLE_TV = String.format("CREATE TABLE %s" +
                    " (%s TEXT NULL," +
                    " %s TEXT NULL," +
                    " %s TEXT NULL," +
                    " %s TEXT NULL," +
                    " %s TEXT NULL," +
                    " %s TEXT NULL," +
                    " %s TEXT NULL)",
            TV_TABLE_NAME,
//            TvColumns._ID,
            DatabaseContractTv.TvColumns.ID,
            DatabaseContractTv.TvColumns.JUDUL,
            DatabaseContractTv.TvColumns.TANGGAL,
            DatabaseContractTv.TvColumns.RATING,
            DatabaseContractTv.TvColumns.DESKRIPSI,
            DatabaseContractTv.TvColumns.POSTER,
            DatabaseContractTv.TvColumns.BG
    );

    DatabaseHelperTv(Context context) {
        super(context, TV_DATABASE_NAME, null, TV_DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE_TV);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TV_TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}