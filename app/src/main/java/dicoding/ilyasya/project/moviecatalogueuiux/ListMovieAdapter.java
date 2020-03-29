package dicoding.ilyasya.project.moviecatalogueuiux;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class ListMovieAdapter extends RecyclerView.Adapter<ListMovieAdapter.ListViewHolder> {

    private OnItemClickCallback onItemClickCallback;
    private ArrayList<Movie> mData = new ArrayList<>();

    public void setData(ArrayList<Movie> items) {
        mData.clear();
        mData.addAll(items);
        notifyDataSetChanged();
    }

    //public ListMovieAdapter(ArrayList<Movie> list){
    //    this.listMovie = list;
    //}


    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_row_movies, viewGroup, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ListViewHolder holder, int position) {
        holder.bind(mData.get(position));

        // final Movie movie = mData.get(position);

        //Glide.with(holder.itemView.getContext())
        //        .load((movie.getPoster()))
        //        .apply(new RequestOptions().override(55, 55))
        //        .into(holder.imgPhoto);


        // holder.imgPhoto.setImageResource(movie.getPoster());
        // holder.txtJudul.setText(movie.getJudul());
        // holder.txtRilis.setText(movie.getTanggal());
        // holder.txtRating.setText(movie.getRating());

        // holder.itemView.setOnClickListener(new View.OnClickListener() {
         //   @Override
         //   public void onClick(View v) {
         //       onItemClickCallback.onItemClicked(mData.get(holder.getAdapterPosition()));
        //    }
        //});

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {
        private TextView txtJudul;
        private TextView txtDeskripsi;
        private ImageView imgPhoto;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            txtJudul = itemView.findViewById(R.id.txt_judul);
            txtDeskripsi = itemView.findViewById(R.id.txt_deskripsi);
            imgPhoto = itemView.findViewById(R.id.img_photo);
        }

        void bind(Movie movie) {
            //String imgurl = BuildConfig.BASE_IMAGE_URL + movie.getPoster();
           // Glide.with(itemView.getContext())
             //       .load(imgurl)
             //       .placeholder(R.drawable.ic_filter_tilt_shift_black_24dp)
             //       .error(R.drawable.ic_clear_black_24dp)
             //       .into(imgPhoto);

            txtJudul.setText(movie.getJudul());
            txtDeskripsi.setText(movie.getDeskripsi());
            Glide.with(itemView.getContext()).load(movie.getPoster()).into(imgPhoto);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickCallback.onItemClicked(mData.get(getAdapterPosition()));
                }
            });
        }
    }

    public interface OnItemClickCallback {
        void onItemClicked(Movie data);
    }
}
