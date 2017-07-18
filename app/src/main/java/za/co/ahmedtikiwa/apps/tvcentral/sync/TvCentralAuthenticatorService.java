package za.co.ahmedtikiwa.apps.tvcentral.sync;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class TvCentralAuthenticatorService extends Service {
    private TvCentralAuthenticator mTvCentralAuthenticator;

    @Override
    public void onCreate() {
        mTvCentralAuthenticator = new TvCentralAuthenticator(this);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mTvCentralAuthenticator.getIBinder();
    }
}