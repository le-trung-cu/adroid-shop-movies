package funix.prm.prm391x_shopmovies_cultfx02223funixeduvn.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

import funix.prm.prm391x_shopmovies_cultfx02223funixeduvn.R;
import funix.prm.prm391x_shopmovies_cultfx02223funixeduvn.models.Movie;

public class MovieAdapter extends ArrayAdapter<Movie> {
    public MovieAdapter(@NonNull Context context, ArrayList<Movie> resource) {
        super(context, 0, resource);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final Movie movie = getItem(position);
        ViewHolder viewHolder;
        if(convertView == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) getContext()
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_movie, parent, false);

            viewHolder.ivImage = convertView.findViewById(R.id.iv_image);
            viewHolder.tvTitle = convertView.findViewById(R.id.tv_title);
            viewHolder.tvPrice = convertView.findViewById(R.id.tv_price);

            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tvTitle.setText(movie.getTitle());
        viewHolder.tvPrice.setText(movie.getPrice());

        return convertView;
    }

    private static class ViewHolder {
        public ImageView ivImage;
        public TextView tvTitle;
        public TextView tvPrice;
    }
}
