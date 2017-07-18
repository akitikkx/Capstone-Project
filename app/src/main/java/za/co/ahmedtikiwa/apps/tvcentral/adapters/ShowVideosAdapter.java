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
import za.co.ahmedtikiwa.apps.tvcentral.models.ShowVideo;

public class ShowVideosAdapter extends RecyclerView.Adapter<ShowVideosAdapter.ViewHolder> {

    private ArrayList<ShowVideo> mData;
    private Context mContext;
    private ShowVideoAdapterClickHandler mClickHandler;

    public ShowVideosAdapter(Context context, ArrayList<ShowVideo> showVideoArrayList, ShowVideoAdapterClickHandler handler) {
        mData = showVideoArrayList;
        mContext = context;
        mClickHandler = handler;
    }

    public interface ShowVideoAdapterClickHandler {
        public void onVideoItemClick(String videoKey);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.show_video_list_item, parent, false);
        final ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final ShowVideo showVideo = mData.get(position);

        String videoUrl = "http://img.youtube.com/vi/" + showVideo.getKey() + "/hqdefault.jpg";

        Glide.with(mContext)
                .load(videoUrl)
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                        holder.videoProgress.setVisibility(View.GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        holder.videoProgress.setVisibility(View.GONE);
                        return false;
                    }
                })
                .into(holder.videoItem);

        // set the content description for the show poster
        holder.videoItem.setContentDescription(String.format(mContext.getString(R.string.show_video), showVideo.getName()));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView videoItem;
        ProgressBar videoProgress;

        ViewHolder(View itemView) {
            super(itemView);

            videoItem = (ImageView) itemView.findViewById(R.id.video_item);
            videoProgress = (ProgressBar) itemView.findViewById(R.id.show_video_progress);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            final ShowVideo showVideo = mData.get(position);
            mClickHandler.onVideoItemClick(showVideo.getKey());
        }
    }
}