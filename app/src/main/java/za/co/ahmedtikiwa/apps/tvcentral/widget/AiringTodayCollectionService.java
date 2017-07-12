package za.co.ahmedtikiwa.apps.tvcentral.widget;

import android.content.Intent;
import android.database.Cursor;
import android.os.Binder;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import za.co.ahmedtikiwa.apps.tvcentral.R;
import za.co.ahmedtikiwa.apps.tvcentral.data.TvCentralContract;

public class AiringTodayCollectionService extends RemoteViewsService {

    private static final String TAG = AiringTodayCollectionService.class.getSimpleName();
    private Cursor data;

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new RemoteViewsFactory() {
            @Override
            public void onCreate() {
                Log.d(TAG, "onCreate");
                if (data != null) {
                    data.close();
                }
                final long identityToken = Binder.clearCallingIdentity();
                data = getContentResolver().query(TvCentralContract.TvAiringTodayEntry.CONTENT_URI, null, null, null, null);
                Binder.restoreCallingIdentity(identityToken);
                if (data != null) {
                    data.moveToFirst();
                }
            }

            @Override
            public void onDataSetChanged() {
                Log.d(TAG, "onDataSetChanged");
            }

            @Override
            public void onDestroy() {
                Log.d(TAG, "onDestroy");
            }

            @Override
            public int getCount() {
                Log.d(TAG, "getCount");
                return data.getCount();
            }

            @Override
            public RemoteViews getViewAt(int position) {
                Log.d(TAG, "getViewAt");
                RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.airing_today_collection_item_widget);
                data.moveToPosition(position);

                String name = data.getString(data.getColumnIndex(TvCentralContract.TvAiringTodayEntry.COLUMN_NAME));

                remoteViews.setTextViewText(R.id.widget_show_name, name);

                return remoteViews;
            }

            @Override
            public RemoteViews getLoadingView() {
                Log.d(TAG, "getLoadingView");
                return null;
            }

            @Override
            public int getViewTypeCount() {
                Log.d(TAG, "getViewTypeCount");
                return 1;
            }

            @Override
            public long getItemId(int position) {
                Log.d(TAG, "getItemId");
                return position;
            }

            @Override
            public boolean hasStableIds() {
                Log.d(TAG, "hasStableIds");
                return true;
            }
        };
    }
}
