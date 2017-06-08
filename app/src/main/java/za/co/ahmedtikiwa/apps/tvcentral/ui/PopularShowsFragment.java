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
import za.co.ahmedtikiwa.apps.tvcentral.adapters.PopularShowsAdapter;
import za.co.ahmedtikiwa.apps.tvcentral.data.TvCentralContract;

/**
 * A placeholder fragment containing a simple view.
 */
public class PopularShowsFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {

    public static final String TAG = PopularShowsFragment.class.getSimpleName();
    private PopularShowsAdapter adapter;
    @BindView(R.id.popular_shows)
    RecyclerView recyclerView;
    public static final String[] SHOW_COLUMNS = {
            TvCentralContract.TvPopularEntry.TABLE_NAME + "." + TvCentralContract.TvPopularEntry._ID,
            TvCentralContract.TvPopularEntry.COLUMN_POSTER_PATH,
            TvCentralContract.TvPopularEntry.COLUMN_SHOW_ID,
            TvCentralContract.TvPopularEntry.COLUMN_POPULARITY,
            TvCentralContract.TvPopularEntry.COLUMN_BACKDROP_PATH,
            TvCentralContract.TvPopularEntry.COLUMN_VOTE_AVERAGE,
            TvCentralContract.TvPopularEntry.COLUMN_OVERVIEW,
            TvCentralContract.TvPopularEntry.COLUMN_FIRST_AIR_DATE,
            TvCentralContract.TvPopularEntry.COLUMN_ORIGINAL_LANGUAGE,
            TvCentralContract.TvPopularEntry.COLUMN_VOTE_COUNT,
            TvCentralContract.TvPopularEntry.COLUMN_NAME,
            TvCentralContract.TvPopularEntry.COLUMN_ORIGINAL_NAME,
    };

    public static final int COLUMN_POSTER_PATH = 1;

    public PopularShowsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_popular_shows, container, false);

        ButterKnife.bind(this, rootView);

        adapter = new PopularShowsAdapter(getActivity(), null);
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
                TvCentralContract.TvPopularEntry.CONTENT_URI,
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
