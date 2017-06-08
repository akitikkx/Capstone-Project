package za.co.ahmedtikiwa.apps.tvcentral.ui;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import za.co.ahmedtikiwa.apps.tvcentral.R;
import za.co.ahmedtikiwa.apps.tvcentral.adapters.UpcomingWeekShowsAdapter;
import za.co.ahmedtikiwa.apps.tvcentral.data.TvCentralContract;

/**
 * A placeholder fragment containing a simple view.
 */
public class UpcomingWeekShowsFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {

    public static final String TAG = UpcomingWeekShowsFragment.class.getSimpleName();
    private UpcomingWeekShowsAdapter adapter;
    @BindView(R.id.upcoming_week_shows)
    RecyclerView recyclerView;
    public static final String[] SHOW_COLUMNS = {
            TvCentralContract.TvUpcomingWeekEntry.TABLE_NAME + "." + TvCentralContract.TvUpcomingWeekEntry._ID,
            TvCentralContract.TvUpcomingWeekEntry.COLUMN_POSTER_PATH,
            TvCentralContract.TvUpcomingWeekEntry.COLUMN_SHOW_ID,
            TvCentralContract.TvUpcomingWeekEntry.COLUMN_POPULARITY,
            TvCentralContract.TvUpcomingWeekEntry.COLUMN_BACKDROP_PATH,
            TvCentralContract.TvUpcomingWeekEntry.COLUMN_VOTE_AVERAGE,
            TvCentralContract.TvUpcomingWeekEntry.COLUMN_OVERVIEW,
            TvCentralContract.TvUpcomingWeekEntry.COLUMN_FIRST_AIR_DATE,
            TvCentralContract.TvUpcomingWeekEntry.COLUMN_ORIGINAL_LANGUAGE,
            TvCentralContract.TvUpcomingWeekEntry.COLUMN_VOTE_COUNT,
            TvCentralContract.TvUpcomingWeekEntry.COLUMN_NAME,
            TvCentralContract.TvUpcomingWeekEntry.COLUMN_ORIGINAL_NAME,
    };

    public static final int COLUMN_POSTER_PATH = 1;

    public UpcomingWeekShowsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_upcoming_week_shows, container, false);

        ButterKnife.bind(this, rootView);

        adapter = new UpcomingWeekShowsAdapter(getActivity(), null);
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
        return new CursorLoader(getActivity(),
                TvCentralContract.TvUpcomingWeekEntry.CONTENT_URI,
                null,
                null,
                null,
                null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        adapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
