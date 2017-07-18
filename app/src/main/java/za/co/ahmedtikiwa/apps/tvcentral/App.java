package za.co.ahmedtikiwa.apps.tvcentral;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.crashlytics.android.Crashlytics;
import com.google.firebase.analytics.FirebaseAnalytics;

import io.fabric.sdk.android.Fabric;
import za.co.ahmedtikiwa.apps.tvcentral.receivers.NetworkConnectivityReceiver;

public class App extends Application {

    public static App instance;

    public App() {
        instance = this;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Crashlytics());

        FirebaseAnalytics.getInstance(this);
    }

    public static synchronized Context getContext() {
        return instance;
    }

    public static boolean hasNetworkConnection() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnectedOrConnecting();
    }

    public void initConnectivityListener(NetworkConnectivityReceiver.NetworkConnectivityListener connectivityListener){
        NetworkConnectivityReceiver.connectivityListener = connectivityListener;
    }
}
