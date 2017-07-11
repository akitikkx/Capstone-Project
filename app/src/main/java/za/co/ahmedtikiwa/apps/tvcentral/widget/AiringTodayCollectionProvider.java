package za.co.ahmedtikiwa.apps.tvcentral.widget;


import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Binder;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import za.co.ahmedtikiwa.apps.tvcentral.R;
import za.co.ahmedtikiwa.apps.tvcentral.data.TvCentralContract;

public class AiringTodayCollectionProvider implements RemoteViewsService.RemoteViewsFactory {

    private static final String TAG = AiringTodayCollectionProvider.class.getSimpleName();
    private Cursor data;
    private Context context;
    private Intent intent;

    public AiringTodayCollectionProvider(Context context, Intent intent) {
        this.context = context;
        this.intent = intent;
    }

    @Override
    public void onCreate() {
        Log.d(TAG, "onCreate");
        loadCollectionData();
        if (data != null) {
            Log.d(TAG, "Data: " + data.getCount());
            data.moveToFirst();
        }
    }

    @Override
    public void onDataSetChanged() {
        Log.d(TAG, "onDataSetChanged");
        loadCollectionData();
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy");
        if (data != null) {
            data.close();
        }
    }

    @Override
    public int getCount() {
        Log.d(TAG, "getCount");
        return data.getCount();
    }

    @Override
    public RemoteViews getViewAt(int position) {
        Log.d(TAG, "getViewAt");
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.airing_today_collection_item_widget);
        data.moveToPosition(position);

        String name = data.getString(data.getColumnIndex(TvCentralContract.TvAiringTodayEntry.COLUMN_NAME));
        Log.d(TAG, "Data: name " + name);

        remoteViews.setTextViewText(R.id.widget_show_name, data.getString(data.getColumnIndex(TvCentralContract.TvAiringTodayEntry.COLUMN_NAME)));

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

    private void loadCollectionData() {
        Log.d(TAG, "loadCollectionData");
        if (data != null) {
            Log.d(TAG, "Data: " + data.getCount());
            data.close();
        }
        final long identityToken = Binder.clearCallingIdentity();
        data = context.getContentResolver().query(TvCentralContract.TvAiringTodayEntry.CONTENT_URI, null, null, null, null);
        Log.d(TAG, "Data: " + data.getCount());
        Binder.restoreCallingIdentity(identityToken);
    }
}
