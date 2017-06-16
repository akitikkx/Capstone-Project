package za.co.ahmedtikiwa.apps.tvcentral.adapters;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.util.ArrayList;

import za.co.ahmedtikiwa.apps.tvcentral.R;
import za.co.ahmedtikiwa.apps.tvcentral.models.Show;
import za.co.ahmedtikiwa.apps.tvcentral.utils.Constants;

public class SimilarShowsAdapter extends RecyclerView.Adapter<SimilarShowsAdapter.ViewHolder> {

    private ArrayList<Show> mData;
    private Context mContext;

    public SimilarShowsAdapter(Context context, ArrayList<Show> showArrayList) {
        mData = showArrayList;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.show_list_item, parent, false);
        final ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Show show = mData.get(position);

        String posterUrl = Constants.TMDB_IMAGE_BASE_URL + Constants.TMDB_IMAGE_RECOMMENDED_SIZE + show.getPosterPath();

        Glide.with(mContext)
                .load(posterUrl)
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                        holder.posterProgress.setVisibility(View.GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        holder.posterProgress.setVisibility(View.GONE);
                        return false;
                    }
                })
                .into(holder.poster);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView poster;
        public ProgressBar posterProgress;

        public ViewHolder(View itemView) {
            super(itemView);

            poster = (ImageView) itemView.findViewById(R.id.poster);
            posterProgress = (ProgressBar) itemView.findViewById(R.id.poster_progress);
        }
    }
}