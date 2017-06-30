package za.co.ahmedtikiwa.apps.tvcentral.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

// TODO: 2017/06/30 to be replaced with GCMNetworkManager
public class NetworkConnectivityReceiver extends BroadcastReceiver {

    public static NetworkConnectivityListener connectivityListener;

    @Override
    public void onReceive(Context context, Intent intent) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        boolean hasNetworkConnection = networkInfo != null && networkInfo.isConnectedOrConnecting();

        if (connectivityListener != null) {
            connectivityListener.onConnectionChanged(hasNetworkConnection);
        }
    }

    public interface NetworkConnectivityListener {
        void onConnectionChanged(boolean hasNetworkConnection);
    }
}
