package za.co.ahmedtikiwa.apps.tvcentral.adapters;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import za.co.ahmedtikiwa.apps.tvcentral.R;
import za.co.ahmedtikiwa.apps.tvcentral.ui.AiringTodayFragment;
import za.co.ahmedtikiwa.apps.tvcentral.utils.Constants;

public class PopularShowsAdapter extends RecyclerView.Adapter<PopularShowsAdapter.ViewHolder> {

    private Cursor mCursor;
    private Context mContext;

    public PopularShowsAdapter(Context context, Cursor cursor) {
        mCursor = cursor;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.show_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        mCursor.moveToPosition(position);

        if (mCursor.getString(AiringTodayFragment.COLUMN_POSTER_PATH) != null) {
            String posterUrl = Constants.TMDB_IMAGE_BASE_URL + Constants.TMDB_IMAGE_RECOMMENDED_SIZE + mCursor.getString(AiringTodayFragment.COLUMN_POSTER_PATH);

            Glide.with(mContext)
                    .load(posterUrl)
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

    public class ViewHolder extends RecyclerView.ViewHolder {

        public final ImageView poster;

        public ViewHolder(View itemView) {
            super(itemView);

            poster = (ImageView)itemView.findViewById(R.id.poster);
        }
    }
}
