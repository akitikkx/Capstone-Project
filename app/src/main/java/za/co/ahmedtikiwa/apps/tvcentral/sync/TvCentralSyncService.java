package za.co.ahmedtikiwa.apps.tvcentral.sync;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class TvCentralSyncService extends Service {

    private static final Object sSyncAdapterLock = new Object();
    private static TvCentralSyncAdapter sTvCentralSyncAdapter = null;

    @Override
    public void onCreate() {
        synchronized (sSyncAdapterLock) {
            if (sTvCentralSyncAdapter == null) {
                sTvCentralSyncAdapter = new TvCentralSyncAdapter(getApplicationContext(), true);
            }
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return sTvCentralSyncAdapter.getSyncAdapterBinder();
    }
}