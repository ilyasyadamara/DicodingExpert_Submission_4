package dicoding.ilyasya.project.moviecatalogueuiux;

import android.os.Parcel;
import android.os.Parcelable;


public class Movie implements Parcelable {


    private int id;
    private String judul;
    private String tanggal;
    private String rating;
    private String deskripsi;
    private String poster;
    private String bg;

    private Movie(Parcel in) {
        judul = in.readString();
        tanggal = in.readString();
        rating = in.readString();
        deskripsi = in.readString();
        poster = in.readString();
        bg = in.readString();
        id = in.readInt();
    }

    public Movie() {

    }

    public Movie(int id, String judul, String tanggal, String rating, String deskripsi, String poster, String bg) {
        this.id = id;
        this.judul = judul;
        this.tanggal = tanggal;
        this.rating = rating;
        this.deskripsi = deskripsi;
        this.poster = poster;
        this.bg = bg;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(judul);
        dest.writeString(tanggal);
        dest.writeString(rating);
        dest.writeString(deskripsi);
        dest.writeString(poster);
        dest.writeString(bg);
        dest.writeInt(id);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Parcelable.Creator<Movie> CREATOR = new Parcelable.Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel source) {
            return new Movie(source);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    public String getJudul() {
        return this.judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getDeskripsi() { return deskripsi; }

    public void setDeskripsi(String deskripsi) { this.deskripsi = deskripsi; }

    public String getPoster() { return poster; }

    public void setPoster(String poster) { this.poster = poster; }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getBg() { return bg; }

    public void setBg(String bg) { this.bg = bg; }
}
