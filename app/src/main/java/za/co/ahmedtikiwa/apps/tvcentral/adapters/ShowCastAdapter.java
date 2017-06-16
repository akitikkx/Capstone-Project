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
import za.co.ahmedtikiwa.apps.tvcentral.models.ShowCast;
import za.co.ahmedtikiwa.apps.tvcentral.utils.Constants;

public class ShowCastAdapter extends RecyclerView.Adapter<ShowCastAdapter.ViewHolder> {

    private ArrayList<ShowCast> mData;
    private Context mContext;

    public ShowCastAdapter(Context context, ArrayList<ShowCast> castArrayList) {
        mData = castArrayList;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cast_list_item, parent, false);
        final ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final ShowCast cast = mData.get(position);

        String castImageUrl = Constants.TMDB_IMAGE_BASE_URL + Constants.TMDB_IMAGE_RECOMMENDED_SIZE + cast.getProfilePath();

        Glide.with(mContext)
                .load(castImageUrl)
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
                .into(holder.castImage);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView castImage;
        public ProgressBar posterProgress;

        public ViewHolder(View itemView) {
            super(itemView);

            castImage = (ImageView) itemView.findViewById(R.id.show_cast_image);
            posterProgress = (ProgressBar) itemView.findViewById(R.id.show_cast_poster_progress);
        }
    }
}