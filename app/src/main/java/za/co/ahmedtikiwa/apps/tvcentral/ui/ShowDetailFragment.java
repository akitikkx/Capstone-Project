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
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import butterknife.BindView;
import butterknife.ButterKnife;
import za.co.ahmedtikiwa.apps.tvcentral.R;
import za.co.ahmedtikiwa.apps.tvcentral.utils.Constants;

import static za.co.ahmedtikiwa.apps.tvcentral.ui.BaseFragment.COLUMN_BACKDROP_PATH;
import static za.co.ahmedtikiwa.apps.tvcentral.ui.BaseFragment.COLUMN_POSTER_PATH;

public class ShowDetailFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {

    private Uri mUri;
    public static final int DETAIL_LOADER = 0;
    @BindView(R.id.show_backdrop)
    ImageView showBackdrop;
    @BindView(R.id.backdrop_progress)
    ProgressBar backdropProgress;
    @BindView(R.id.poster_progress)
    ProgressBar posterProgress;
    @BindView(R.id.show_poster)
    ImageView showPoster;
    @BindView(R.id.show_name)
    TextView showName;
    @BindView(R.id.show_info)
    TextView showInfo;

    public ShowDetailFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_show_detail, container, false);

        ButterKnife.bind(this, rootView);

        getActivity().setTitle(null);

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

            showName.setText(data.getString(BaseFragment.COLUMN_NAME_PATH));
            showInfo.setText(data.getString(BaseFragment.COLUMN_OVERVIEW_PATH));

            String backdropUrl = Constants.TMDB_IMAGE_BASE_URL + Constants.TMDB_IMAGE_BACKDROP_SIZE + data.getString(COLUMN_BACKDROP_PATH);
            String posterUrl = Constants.TMDB_IMAGE_BASE_URL + Constants.TMDB_IMAGE_BACKDROP_SIZE + data.getString(COLUMN_POSTER_PATH);
            Glide.with(getContext())
                    .load(backdropUrl)
                    .listener(new RequestListener<String, GlideDrawable>() {
                        @Override
                        public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                            backdropProgress.setVisibility(View.GONE);
                            return false;
                        }
                    })
                    .into(showBackdrop);

            Glide.with(getContext())
                    .load(posterUrl)
                    .listener(new RequestListener<String, GlideDrawable>() {
                        @Override
                        public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                            posterProgress.setVisibility(View.GONE);
                            return false;
                        }
                    })
                    .into(showPoster);
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}