package dicoding.ilyasya.project.moviecatalogueuiux.db;

import android.provider.BaseColumns;

public class DatabaseContractTv {

    static final class TvColumns implements BaseColumns {
        static final String TV_TABLE_NAME = "tvshow";

        static final String ID = "id";
        static final String JUDUL = "judul";
        static final String TANGGAL = "tanggal";
        static final String RATING = "rating";
        static final String DESKRIPSI = "deskripsi";
        static final String POSTER = "poster";
        static final String BG = "bg";
    }
}
