package app.movie.tutorial.com.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import app.movie.tutorial.com.R;
import app.movie.tutorial.com.model.Search;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder>
{
    private List<Search> movies;
    private int rowLayout;
    private Context context;
    public static final String IMAGE_URL_BASE_PATH="http://image.tmdb.org/t/p/w342//";

    public SearchAdapter(List<Search> movies, int rowLayout, Context context)
    {
        this.movies = movies;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    //A view holder inner class where we get reference to the views in the layout using their ID
    public static class SearchViewHolder extends RecyclerView.ViewHolder
    {
        LinearLayout moviesLayout;
        TextView movieTitle;
        ImageView movieImage;

        public SearchViewHolder(View v)
        {
            super(v);
            moviesLayout = (LinearLayout) v.findViewById(R.id.search_layout);
            movieImage = (ImageView) v.findViewById(R.id.search_image);
            movieTitle = (TextView) v.findViewById(R.id.search_title);
        }
    }


    @Override
    public SearchAdapter.SearchViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new SearchViewHolder(view);

    }

    @Override
    public void onBindViewHolder(SearchViewHolder holder, final int position) {
        String image_url = IMAGE_URL_BASE_PATH + movies.get(position).getPosterPath();
        Picasso.with(context).load(image_url)
                .placeholder(android.R.drawable.sym_def_app_icon)
                .error(android.R.drawable.sym_def_app_icon)
                .into(holder.movieImage);
        holder.movieTitle.setText(movies.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }
}
