package za.co.ahmedtikiwa.apps.tvcentral.ui;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import za.co.ahmedtikiwa.apps.tvcentral.BuildConfig;
import za.co.ahmedtikiwa.apps.tvcentral.R;
import za.co.ahmedtikiwa.apps.tvcentral.adapters.ShowVideosAdapter;
import za.co.ahmedtikiwa.apps.tvcentral.adapters.SimilarShowsAdapter;
import za.co.ahmedtikiwa.apps.tvcentral.api.TmdbApi;
import za.co.ahmedtikiwa.apps.tvcentral.models.Show;
import za.co.ahmedtikiwa.apps.tvcentral.models.ShowInfoResponse;
import za.co.ahmedtikiwa.apps.tvcentral.models.ShowNetwork;
import za.co.ahmedtikiwa.apps.tvcentral.models.ShowVideo;
import za.co.ahmedtikiwa.apps.tvcentral.models.ShowVideoResponse;
import za.co.ahmedtikiwa.apps.tvcentral.models.ShowsResponse;
import za.co.ahmedtikiwa.apps.tvcentral.receivers.NetworkConnectivityReceiver;
import za.co.ahmedtikiwa.apps.tvcentral.utils.Constants;

public class ShowDetailFragment extends BaseFragment implements LoaderManager.LoaderCallbacks<Cursor>, NetworkConnectivityReceiver.NetworkConnectivityListener {

    private Uri mUri;
    public static final int DETAIL_LOADER = 0;
    private ArrayList<Show> similarShowsArrayList;
    private ArrayList<ShowVideo> showVideoArrayList;
    private SimilarShowsAdapter similarShowsAdapter;
    private ShowVideosAdapter showVideosAdapter;
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
    @BindView(R.id.show_status)
    TextView showStatus;
    @BindView(R.id.show_network)
    TextView showNetwork;
    @BindView(R.id.show_seasons_info)
    TextView showSeasonsInfo;
    @BindView(R.id.similar_shows)
    RecyclerView similarShows;
    @BindView(R.id.similar_shows_layout)
    LinearLayout similarShowsLayout;
    @BindView(R.id.show_videos)
    RecyclerView showVideos;
    @BindView(R.id.show_videos_layout)
    LinearLayout showVideosLayout;

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

        LinearLayoutManager showCastLayoutManager = new LinearLayoutManager(getContext());
        showCastLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        LinearLayoutManager similarShowsLayoutManager = new LinearLayoutManager(getContext());
        similarShowsLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        LinearLayoutManager showVideosLayoutManager = new LinearLayoutManager(getContext());
        showVideosLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        similarShowsArrayList = new ArrayList<>();
        similarShowsAdapter = new SimilarShowsAdapter(getContext(), similarShowsArrayList);
        similarShows.setLayoutManager(similarShowsLayoutManager);
        similarShows.setNestedScrollingEnabled(false);
        similarShows.setAdapter(similarShowsAdapter);

        showVideoArrayList = new ArrayList<>();
        showVideosAdapter = new ShowVideosAdapter(getContext(), showVideoArrayList, new ShowVideosAdapter.ShowVideoAdapterClickHandler() {
            @Override
            public void onVideoItemClick(String videoKey) {
                ((ShowVideoItemClickCallback)getActivity()).onVideoItemSelected(videoKey);
            }
        });
        showVideos.setLayoutManager(showVideosLayoutManager);
        showVideos.setNestedScrollingEnabled(false);
        showVideos.setAdapter(showVideosAdapter);

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
                    .load(posterUrl)
                    .listener(new RequestListener<String, GlideDrawable>() {
                        @Override
                        public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                            posterProgress.setVisibility(View.GONE);
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                            posterProgress.setVisibility(View.GONE);
                            return false;
                        }
                    })
                    .into(showPoster);

            // set the content description for the show poster
            showPoster.setContentDescription(String.format(getString(R.string.show_poster), data.getString(BaseFragment.COLUMN_NAME_PATH)));

            AppCompatActivity activity = (AppCompatActivity) getActivity();

            if (activity instanceof ShowDetailActivity) {
                activity.supportStartPostponedEnterTransition();
            }

            loadMoreInfo(data);

            Glide.with(getContext())
                    .load(backdropUrl)
                    .listener(new RequestListener<String, GlideDrawable>() {
                        @Override
                        public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                            backdropProgress.setVisibility(View.GONE);
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                            backdropProgress.setVisibility(View.GONE);
                            return false;
                        }
                    })
                    .into(showBackdrop);

            // set the content description for the show backdrop
            showBackdrop.setContentDescription(String.format(getString(R.string.show_backdrop_image), data.getString(BaseFragment.COLUMN_NAME_PATH)));
        }
    }

    /**
     * Requests additional information to be loaded for the selected show
     *
     * @param data Cursor data
     */
    private void loadMoreInfo(Cursor data) {
        loadAdditionalGeneralInfo(data);
        loadSimilarShows(data);
        loadVideos(data);
    }

    /**
     * Loads the general information about the show
     * such as status, networks, season info
     *
     * @param data Cursor data
     */
    private void loadAdditionalGeneralInfo(Cursor data) {
        Call<ShowInfoResponse> showInfoResponseCall = TmdbApi.getTmdbApiClient().getDetails(data.getLong(BaseFragment.COLUMN_SHOW_ID), BuildConfig.TMDB_API_KEY);
        showInfoResponseCall.enqueue(new Callback<ShowInfoResponse>() {
            @Override
            public void onResponse(Call<ShowInfoResponse> call, Response<ShowInfoResponse> response) {
                if (response.isSuccessful() && isAdded()) {
                    ShowInfoResponse showInfoResponse = response.body();

                    showStatus.setText(String.format(getString(R.string.show_status), showInfoResponse.getStatus(), String.format(getString(R.string.show_runtime), showInfoResponse.getEpisodeRunTime())));

                    if (showInfoResponse.getNetworks().size() > 0) {
                        List<ShowNetwork> showNetworks = showInfoResponse.getNetworks();
                        String networks = "";
                        for (ShowNetwork showNetwork : showNetworks) {
                            networks += showNetwork.getName();
                        }
                        showNetwork.setText(networks);
                    }

                    showSeasonsInfo.setText(String.format(getString(R.string.show_seasons_info), showInfoResponse.getNumberOfSeasons(), showInfoResponse.getNumberOfEpisodes()));
                }
            }

            @Override
            public void onFailure(Call<ShowInfoResponse> call, Throwable t) {

            }
        });
    }

    private void loadSimilarShows(Cursor data) {
        Call<ShowsResponse> showsResponseCall = TmdbApi.getTmdbApiClient().getSimilar(data.getLong(BaseFragment.COLUMN_SHOW_ID), BuildConfig.TMDB_API_KEY);
        showsResponseCall.enqueue(new Callback<ShowsResponse>() {
            @Override
            public void onResponse(Call<ShowsResponse> call, Response<ShowsResponse> response) {
                if (response.isSuccessful() && isAdded()) {
                    ShowsResponse showsResponse = response.body();
                    if (showsResponse.getResults().size() > 0) {
                        updateSimilarShowsData(showsResponse.getResults());
                    } else {
                        similarShowsLayout.setVisibility(View.GONE);
                    }
                }
            }

            @Override
            public void onFailure(Call<ShowsResponse> call, Throwable t) {
                similarShowsLayout.setVisibility(View.GONE);
            }
        });
    }

    private void loadVideos(Cursor data) {
        Call<ShowVideoResponse> showVideoResponseCall = TmdbApi.getTmdbApiClient().getVideos(data.getLong(BaseFragment.COLUMN_SHOW_ID), BuildConfig.TMDB_API_KEY);
        showVideoResponseCall.enqueue(new Callback<ShowVideoResponse>() {
            @Override
            public void onResponse(Call<ShowVideoResponse> call, Response<ShowVideoResponse> response) {
                if (response.isSuccessful() && isAdded()) {
                    ShowVideoResponse showVideoResponse = response.body();
                    if (showVideoResponse.getResults().size() > 0) {
                        updateShowVideosData(showVideoResponse.getResults());
                    } else {
                        showVideosLayout.setVisibility(View.GONE);
                    }
                }
            }

            @Override
            public void onFailure(Call<ShowVideoResponse> call, Throwable t) {
                showVideosLayout.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    /**
     * Updates the similar shows adapter with updated data
     *
     * @param arrayList ArrayList containing similar shows data
     */
    private void updateSimilarShowsData(ArrayList arrayList) {
        if (arrayList != null) {
            similarShowsArrayList.clear();
            similarShowsArrayList.addAll(arrayList);
            similarShowsAdapter.notifyDataSetChanged();
        }
    }

    /**
     * Updates the show videos adapter with updated data
     *
     * @param arrayList ArrayList containing show videos data
     */
    private void updateShowVideosData(ArrayList arrayList) {
        if (arrayList != null) {
            showVideoArrayList.clear();
            showVideoArrayList.addAll(arrayList);
            showVideosAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onConnectionChanged(boolean hasNetworkConnection) {
        getLoaderManager().restartLoader(DETAIL_LOADER, null, this);
    }
}