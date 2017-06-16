package za.co.ahmedtikiwa.apps.tvcentral.ui;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import butterknife.ButterKnife;
import za.co.ahmedtikiwa.apps.tvcentral.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class ShowDetailFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor>{

    private Uri mUri;
    public static final int DETAIL_LOADER = 0;

    public ShowDetailFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.fragment_show_detail, container, false);

        ButterKnife.bind(getActivity(), rootView);

        Bundle bundle = getArguments();
        mUri = bundle.getParcelable(BaseFragment.SHOW_DETAIL_URI);

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        getLoaderManager().initLoader(DETAIL_LOADER, null, this);
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        if (mUri != null) {
            return new CursorLoader(getActivity(), mUri, null, null, null, null);
        }
        return null;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        if (data != null && data.moveToNext()) {
            Toast.makeText(getActivity(), data.getString(BaseFragment.COLUMN_POSTER_PATH), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
