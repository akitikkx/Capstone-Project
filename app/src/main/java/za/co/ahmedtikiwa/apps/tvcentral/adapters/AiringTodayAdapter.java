package za.co.ahmedtikiwa.apps.tvcentral.adapters;

import android.content.Context;
import android.database.Cursor;
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

import za.co.ahmedtikiwa.apps.tvcentral.R;
import za.co.ahmedtikiwa.apps.tvcentral.ui.AiringTodayFragment;
import za.co.ahmedtikiwa.apps.tvcentral.ui.BaseFragment;
import za.co.ahmedtikiwa.apps.tvcentral.utils.Constants;

public class AiringTodayAdapter extends RecyclerView.Adapter<AiringTodayAdapter.ViewHolder> {

    private Cursor mCursor;
    private Context mContext;
    private AiringTodayAdapterOnClickHandler mOnClickHandler;

    public AiringTodayAdapter(Context context, Cursor cursor, AiringTodayAdapterOnClickHandler onClickHandler) {
        mCursor = cursor;
        mContext = context;
        mOnClickHandler = onClickHandler;
    }

    public interface AiringTodayAdapterOnClickHandler {
        void onClick(long showId);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.show_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        mCursor.moveToPosition(position);

        if (mCursor.getString(AiringTodayFragment.COLUMN_POSTER_PATH) != null) {
            String posterUrl = Constants.TMDB_IMAGE_BASE_URL + Constants.TMDB_IMAGE_RECOMMENDED_SIZE + mCursor.getString(AiringTodayFragment.COLUMN_POSTER_PATH);

            holder.poster.setContentDescription(mCursor.getString(BaseFragment.COLUMN_NAME_PATH));

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
    }



    @Override
    public int getItemCount() {
        return null == mCursor ? 0 : mCursor.getCount();
    }

    public void swapCursor(Cursor newCursor) {
        mCursor = newCursor;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public final ImageView poster;
        public final ProgressBar posterProgress;;

        public ViewHolder(View itemView) {
            super(itemView);

            poster = (ImageView) itemView.findViewById(R.id.poster);
            posterProgress = (ProgressBar) itemView.findViewById(R.id.poster_progress);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            mCursor.moveToPosition(position);
            mOnClickHandler.onClick(mCursor.getLong(BaseFragment.COLUMN_SHOW_ID));
        }
    }
}
