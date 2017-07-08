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
import za.co.ahmedtikiwa.apps.tvcentral.adapters.TopRatedShowsAdapter;
import za.co.ahmedtikiwa.apps.tvcentral.data.TvCentralContract;
import za.co.ahmedtikiwa.apps.tvcentral.sync.TvCentralSyncAdapter;
import za.co.ahmedtikiwa.apps.tvcentral.utils.PrefHelper;

public class TopRatedShowsFragment extends BaseFragment implements LoaderManager.LoaderCallbacks<Cursor> {

    public static final String TAG = TopRatedShowsFragment.class.getSimpleName();
    private TopRatedShowsAdapter adapter;
    @BindView(R.id.top_rated_shows)
    RecyclerView recyclerView;
    @BindView(R.id.top_rated_shows_progress)
    ProgressBar progressBar;
    @BindView(R.id.empty_state)
    TextView emptyState;

    public static final int COLUMN_POSTER_PATH = 1;

    public TopRatedShowsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_top_rated_shows, container, false);

        ButterKnife.bind(this, rootView);

        adapter = new TopRatedShowsAdapter(getActivity(), null, new TopRatedShowsAdapter.TopRatedShowsAdapterOnClickHandler() {
            @Override
            public void onClick(long showId) {
                ((BaseFragment.Callback)getActivity()).onItemSelected(TvCentralContract.TvTopRatedEntry.buildShowUri(showId));
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
                TvCentralContract.TvTopRatedEntry.CONTENT_URI,
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
                case TvCentralSyncAdapter.TOP_RATED_SYNC_STATUS_SERVER_DOWN:
                    emptyStateMsg = R.string.empty_show_list_server_down;
                    break;
                case TvCentralSyncAdapter.TOP_RATED_SYNC_STATUS_SERVER_INVALID:
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
