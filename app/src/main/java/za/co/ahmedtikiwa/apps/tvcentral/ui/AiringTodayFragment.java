package za.co.ahmedtikiwa.apps.tvcentral.ui;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import za.co.ahmedtikiwa.apps.tvcentral.App;
import za.co.ahmedtikiwa.apps.tvcentral.R;
import za.co.ahmedtikiwa.apps.tvcentral.adapters.AiringTodayAdapter;
import za.co.ahmedtikiwa.apps.tvcentral.data.TvCentralContract;
import za.co.ahmedtikiwa.apps.tvcentral.sync.TvCentralSyncAdapter;
import za.co.ahmedtikiwa.apps.tvcentral.utils.PrefHelper;

public class AiringTodayFragment extends BaseFragment implements LoaderManager.LoaderCallbacks<Cursor> {

    public static final String TAG = AiringTodayFragment.class.getSimpleName();
    private AiringTodayAdapter adapter;
    @BindView(R.id.airing_today)
    RecyclerView recyclerView;
    @BindView(R.id.airing_today_progress)
    ProgressBar progressBar;
    @BindView(R.id.empty_state)
    TextView emptyState;

    public AiringTodayFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_airing_today, container, false);

        ButterKnife.bind(this, rootView);

        adapter = new AiringTodayAdapter(getActivity(), null, new AiringTodayAdapter.AiringTodayAdapterOnClickHandler() {
            @Override
            public void onClick(long showId, View view) {
                ((ShowItemClickCallback)getActivity()).onItemSelected(TvCentralContract.TvAiringTodayEntry.buildShowUri(showId), view);
            }
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        getLoaderManager().initLoader(0, null, this);
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        progressBar.setVisibility(ProgressBar.VISIBLE);

        return new CursorLoader(getActivity(),
                TvCentralContract.TvAiringTodayEntry.CONTENT_URI,
                null,
                null,
                null,
                null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        progressBar.setVisibility(ProgressBar.GONE);
        recyclerView.setVisibility(View.VISIBLE);
        adapter.swapCursor(data);

        @TvCentralSyncAdapter.SyncStatus int syncStatus = PrefHelper.getSyncStatus(getActivity());
        if (syncStatus != TvCentralSyncAdapter.SYNC_STATUS_OK) {
            int emptyStateMsg = R.string.empty_show_list;
            switch (syncStatus) {
                case TvCentralSyncAdapter.AIRING_TODAY_SYNC_STATUS_SERVER_DOWN:
                    emptyStateMsg = R.string.empty_show_list_server_down;
                    break;
                case TvCentralSyncAdapter.AIRING_TODAY_SYNC_STATUS_SERVER_INVALID:
                    emptyStateMsg = R.string.empty_show_list_server_error;
                    break;
                default:
                    if (!App.hasNetworkConnection()) {
                        Toast.makeText(getActivity(), R.string.empty_show_list_no_network, Toast.LENGTH_LONG).show();
                    }
            }
            emptyState.setText(emptyStateMsg);
            emptyState.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
