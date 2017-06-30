package za.co.ahmedtikiwa.apps.tvcentral.ui;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import butterknife.BindView;
import butterknife.ButterKnife;
import za.co.ahmedtikiwa.apps.tvcentral.App;
import za.co.ahmedtikiwa.apps.tvcentral.R;
import za.co.ahmedtikiwa.apps.tvcentral.receivers.NetworkConnectivityReceiver;
import za.co.ahmedtikiwa.apps.tvcentral.sync.TvCentralSyncAdapter;

public class DashboardActivity extends AppCompatActivity implements BaseFragment.Callback, NetworkConnectivityReceiver.NetworkConnectivityListener {

    @BindView(R.id.main_content)
    CoordinatorLayout mainContent;

    private NetworkConnectivityReceiver mReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        ButterKnife.bind(this);

        mReceiver = new NetworkConnectivityReceiver();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TvCentralSyncAdapter.initializeSyncAdapter(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_dashboard, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemSelected(Uri showUri) {
        Intent showDetail = new Intent(DashboardActivity.this, ShowDetailActivity.class);
        showDetail.setData(showUri);
        startActivity(showDetail);
    }

    @Override
    protected void onStart() {
        super.onStart();
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(mReceiver, filter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(mReceiver);
    }

    @Override
    protected void onResume() {
        super.onResume();
        App.instance.initConnectivityListener(this);
    }

    @Override
    public void onConnectionChanged(boolean hasNetworkConnection) {
        if (!hasNetworkConnection) {
            Snackbar.make(mainContent, getString(R.string.no_network_connection), Snackbar.LENGTH_LONG).show();
        }
    }
}