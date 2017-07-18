package za.co.ahmedtikiwa.apps.tvcentral.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import za.co.ahmedtikiwa.apps.tvcentral.R;
import za.co.ahmedtikiwa.apps.tvcentral.sync.TvCentralSyncAdapter;

/**
 * Created by Ahmed on 2017/06/15.
 */

public class PrefHelper {

    @SuppressWarnings("ResourceType")
    static public
    @TvCentralSyncAdapter.SyncStatus
    int getSyncStatus(Context c) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(c);
        return preferences.getInt(c.getString(R.string.pref_sync_status_key), TvCentralSyncAdapter.SYNC_STATUS_OK);
    }
}
