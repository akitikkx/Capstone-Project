package za.co.ahmedtikiwa.apps.tvcentral.ui;

import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import butterknife.BindView;
import butterknife.ButterKnife;
import za.co.ahmedtikiwa.apps.tvcentral.App;
import za.co.ahmedtikiwa.apps.tvcentral.R;
import za.co.ahmedtikiwa.apps.tvcentral.receivers.NetworkConnectivityReceiver;

public class ShowDetailActivity extends AppCompatActivity implements NetworkConnectivityReceiver.NetworkConnectivityListener {

    @BindView(R.id.main_content)
    CoordinatorLayout mainContent;

    private BroadcastReceiver mReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_detail);

        mReceiver = new NetworkConnectivityReceiver();

        ButterKnife.bind(this);

        if (savedInstanceState == null) {
            Bundle args = new Bundle();
            args.putParcelable(BaseFragment.SHOW_DETAIL_URI, getIntent().getData());

            ShowDetailFragment fragment = new ShowDetailFragment();
            fragment.setArguments(args);

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.show_detail_container, fragment)
                    .commit();
        }


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void onStart() {
        super.onStart();

        IntentFilter filter = new IntentFilter();
        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
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