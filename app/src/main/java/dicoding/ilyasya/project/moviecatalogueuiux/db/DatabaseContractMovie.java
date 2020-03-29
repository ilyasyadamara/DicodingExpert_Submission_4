package dicoding.ilyasya.project.moviecatalogueuiux.db;

import android.provider.BaseColumns;

public class DatabaseContractMovie {

    static final class MovieColumns implements BaseColumns {
        static final String MOVIE_TABLE_NAME = "movie";

        static final String ID = "id";
        static final String JUDUL = "judul";
        static final String TANGGAL = "tanggal";
        static final String RATING = "rating";
        static final String DESKRIPSI = "deskripsi";
        static final String POSTER = "poster";
        static final String BG = "bg";
    }
}
