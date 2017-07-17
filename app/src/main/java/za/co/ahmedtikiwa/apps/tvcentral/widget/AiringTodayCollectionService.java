package za.co.ahmedtikiwa.apps.tvcentral.widget;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Binder;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;

import java.util.concurrent.ExecutionException;

import za.co.ahmedtikiwa.apps.tvcentral.R;
import za.co.ahmedtikiwa.apps.tvcentral.data.TvCentralContract;
import za.co.ahmedtikiwa.apps.tvcentral.ui.BaseFragment;
import za.co.ahmedtikiwa.apps.tvcentral.utils.Constants;

public class AiringTodayCollectionService extends RemoteViewsService {

    private Cursor data;

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new RemoteViewsFactory() {
            @Override
            public void onCreate() {
                final long identityToken = Binder.clearCallingIdentity();
                data = getContentResolver().query(TvCentralContract.TvAiringTodayEntry.CONTENT_URI, null, null, null, null);
                Binder.restoreCallingIdentity(identityToken);
                if (data != null) {
                    data.moveToFirst();
                }
            }

            @Override
            public void onDataSetChanged() {
                final long identityToken = Binder.clearCallingIdentity();
                data = getContentResolver().query(TvCentralContract.TvAiringTodayEntry.CONTENT_URI, null, null, null, null);
                Binder.restoreCallingIdentity(identityToken);
                if (data != null) {
                    data.moveToFirst();
                }
            }

            @Override
            public void onDestroy() {
                if (data != null) {
                    data.close();
                }
            }

            @Override
            public int getCount() {
                return data.getCount();
            }

            @Override
            public RemoteViews getViewAt(int position) {
                RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.widget_airing_today_collection_item);
                data.moveToPosition(position);

                String name = data.getString(data.getColumnIndex(TvCentralContract.TvAiringTodayEntry.COLUMN_NAME));
                String posterUrl = Constants.TMDB_IMAGE_BASE_URL + Constants.TMDB_IMAGE_RECOMMENDED_SIZE + data.getString(BaseFragment.COLUMN_POSTER_PATH);
                long showId = data.getLong(BaseFragment.COLUMN_SHOW_ID);

                Bitmap bitmap = null;
                try {
                    bitmap = Glide.with(getApplicationContext())
                            .load(posterUrl)
                            .asBitmap()
                            .into(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                            .get();
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }

                remoteViews.setImageViewBitmap(R.id.widget_show_poster, bitmap);
                remoteViews.setTextViewText(R.id.widget_show_name, name);
                remoteViews.setContentDescription(R.id.widget_show_poster, String.format(getString(R.string.show_poster), name));

                // define the intent for clicking a show item
                final Intent fillInIntent = new Intent();
                Uri collectionItem = TvCentralContract.TvAiringTodayEntry.buildShowUri(showId);
                fillInIntent.setData(collectionItem);
                remoteViews.setOnClickFillInIntent(R.id.widget_show_collection_item, fillInIntent);

                return remoteViews;
            }

            @Override
            public RemoteViews getLoadingView() {
                return null;
            }

            @Override
            public int getViewTypeCount() {
                return 1;
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public boolean hasStableIds() {
                return true;
            }
        };
    }
}