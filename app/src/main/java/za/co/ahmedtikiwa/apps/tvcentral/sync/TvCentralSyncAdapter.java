package za.co.ahmedtikiwa.apps.tvcentral.sync;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.AbstractThreadedSyncAdapter;
import android.content.ContentProviderClient;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.SyncRequest;
import android.content.SyncResult;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.Vector;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import za.co.ahmedtikiwa.apps.tvcentral.BuildConfig;
import za.co.ahmedtikiwa.apps.tvcentral.R;
import za.co.ahmedtikiwa.apps.tvcentral.api.TmdbApi;
import za.co.ahmedtikiwa.apps.tvcentral.data.TvCentralContract;
import za.co.ahmedtikiwa.apps.tvcentral.models.Show;
import za.co.ahmedtikiwa.apps.tvcentral.models.ShowsResponse;

public class TvCentralSyncAdapter extends AbstractThreadedSyncAdapter {
    public static final String TAG = TvCentralSyncAdapter.class.getSimpleName();
    public static final String ACTION_DATA_UPDATED = "za.co.ahmedtikiwa.apps.tvcentral.ACTION_DATA_UPDATED";
    public static final int SYNC_INTERVAL = 60 * 180; // 3 hours
    public static final int SYNC_FLEXTIME = SYNC_INTERVAL / 3;

    public TvCentralSyncAdapter(Context context, boolean autoInitialize) {
        super(context, autoInitialize);
    }

    @Override
    public void onPerformSync(Account account, Bundle extras, String authority, ContentProviderClient provider, SyncResult syncResult) {
        loadTvAiringTodayData();
        loadTvPopularData();
        loadTvUpcomingWeekData();
    }

    private void loadTvAiringTodayData() {
        Call<ShowsResponse> showsResponseCall = TmdbApi.getTmdbApiClient().airingToday(BuildConfig.TMDB_API_KEY);
        showsResponseCall.enqueue(new Callback<ShowsResponse>() {
            @Override
            public void onResponse(Call<ShowsResponse> call, Response<ShowsResponse> response) {
                if (response.isSuccessful()) {
                    ShowsResponse showsResponse = response.body();
                    ArrayList<Show> shows = showsResponse.getResults();

                    Vector<ContentValues> cVVector = new Vector<ContentValues>(shows.size());

                    for (Show show : shows) {
                        ContentValues showsValues = new ContentValues();

                        showsValues.put(TvCentralContract.TvAiringTodayEntry.COLUMN_NAME, show.getName());
                        showsValues.put(TvCentralContract.TvAiringTodayEntry.COLUMN_POSTER_PATH, show.getPosterPath());
                        showsValues.put(TvCentralContract.TvAiringTodayEntry.COLUMN_SHOW_ID, show.getId());
                        showsValues.put(TvCentralContract.TvAiringTodayEntry.COLUMN_POPULARITY, show.getPopularity());
                        showsValues.put(TvCentralContract.TvAiringTodayEntry.COLUMN_BACKDROP_PATH, show.getBackdropPath());
                        showsValues.put(TvCentralContract.TvAiringTodayEntry.COLUMN_VOTE_AVERAGE, show.getVoteAverage());
                        showsValues.put(TvCentralContract.TvAiringTodayEntry.COLUMN_OVERVIEW, show.getOverview());
                        showsValues.put(TvCentralContract.TvAiringTodayEntry.COLUMN_FIRST_AIR_DATE, show.getFirstAirDate());
                        showsValues.put(TvCentralContract.TvAiringTodayEntry.COLUMN_ORIGINAL_LANGUAGE, show.getOriginalLanguage());
                        showsValues.put(TvCentralContract.TvAiringTodayEntry.COLUMN_VOTE_COUNT, show.getVoteCount());
                        showsValues.put(TvCentralContract.TvAiringTodayEntry.COLUMN_ORIGINAL_NAME, show.getOriginalName());

                        cVVector.add(showsValues);
                    }

                    if (cVVector.size() > 0) {
                        ContentValues[] contentValues = new ContentValues[cVVector.size()];
                        cVVector.toArray(contentValues);

                        // remove all the shows that are currently there to be replaced with the new list
                        getContext().getContentResolver().delete(TvCentralContract.TvAiringTodayEntry.CONTENT_URI, null, null);

                        getContext().getContentResolver().bulkInsert(TvCentralContract.TvAiringTodayEntry.CONTENT_URI, contentValues);

                    }

                    Log.d(TAG, "Shows airing today sync service complete. " + cVVector.size() + " inserted.");

                } else {
                    Log.d(TAG, "Error encountered with background airing today sync: " + response.message());
                }

            }

            @Override
            public void onFailure(Call<ShowsResponse> call, Throwable t) {
                Log.d(TAG, "Error encountered with background airing today sync: " + t.getMessage());

            }
        });
    }

    private void loadTvPopularData() {
        Call<ShowsResponse> showsResponseCall = TmdbApi.getTmdbApiClient().popularShows(BuildConfig.TMDB_API_KEY);
        showsResponseCall.enqueue(new Callback<ShowsResponse>() {
            @Override
            public void onResponse(Call<ShowsResponse> call, Response<ShowsResponse> response) {
                if (response.isSuccessful()) {
                    ShowsResponse showsResponse = response.body();
                    ArrayList<Show> shows = showsResponse.getResults();

                    Vector<ContentValues> cVVector = new Vector<ContentValues>(shows.size());

                    for (Show show : shows) {
                        ContentValues showsValues = new ContentValues();

                        showsValues.put(TvCentralContract.TvPopularEntry.COLUMN_NAME, show.getName());
                        showsValues.put(TvCentralContract.TvPopularEntry.COLUMN_POSTER_PATH, show.getPosterPath());
                        showsValues.put(TvCentralContract.TvPopularEntry.COLUMN_SHOW_ID, show.getId());
                        showsValues.put(TvCentralContract.TvPopularEntry.COLUMN_POPULARITY, show.getPopularity());
                        showsValues.put(TvCentralContract.TvPopularEntry.COLUMN_BACKDROP_PATH, show.getBackdropPath());
                        showsValues.put(TvCentralContract.TvPopularEntry.COLUMN_VOTE_AVERAGE, show.getVoteAverage());
                        showsValues.put(TvCentralContract.TvPopularEntry.COLUMN_OVERVIEW, show.getOverview());
                        showsValues.put(TvCentralContract.TvPopularEntry.COLUMN_FIRST_AIR_DATE, show.getFirstAirDate());
                        showsValues.put(TvCentralContract.TvPopularEntry.COLUMN_ORIGINAL_LANGUAGE, show.getOriginalLanguage());
                        showsValues.put(TvCentralContract.TvPopularEntry.COLUMN_VOTE_COUNT, show.getVoteCount());
                        showsValues.put(TvCentralContract.TvPopularEntry.COLUMN_ORIGINAL_NAME, show.getOriginalName());

                        cVVector.add(showsValues);
                    }

                    if (cVVector.size() > 0) {
                        ContentValues[] contentValues = new ContentValues[cVVector.size()];
                        cVVector.toArray(contentValues);

                        // remove all the shows that are currently there to be replaced with the new list
                        getContext().getContentResolver().delete(TvCentralContract.TvPopularEntry.CONTENT_URI, null, null);

                        getContext().getContentResolver().bulkInsert(TvCentralContract.TvPopularEntry.CONTENT_URI, contentValues);
                    }

                    Log.d(TAG, "Popular shows sync service complete. " + cVVector.size() + " inserted.");

                } else {
                    Log.d(TAG, "Error encountered with background popular show sync: " + response.message());
                }

            }

            @Override
            public void onFailure(Call<ShowsResponse> call, Throwable t) {
                Log.d(TAG, "Error encountered with background popular show sync: " + t.getMessage());

            }
        });
    }

    private void loadTvUpcomingWeekData() {
        Call<ShowsResponse> showsResponseCall = TmdbApi.getTmdbApiClient().upcomingWeek(BuildConfig.TMDB_API_KEY);
        showsResponseCall.enqueue(new Callback<ShowsResponse>() {
            @Override
            public void onResponse(Call<ShowsResponse> call, Response<ShowsResponse> response) {
                if (response.isSuccessful()) {
                    ShowsResponse showsResponse = response.body();
                    ArrayList<Show> shows = showsResponse.getResults();

                    Vector<ContentValues> cVVector = new Vector<ContentValues>(shows.size());

                    for (Show show : shows) {
                        ContentValues showsValues = new ContentValues();

                        showsValues.put(TvCentralContract.TvUpcomingWeekEntry.COLUMN_NAME, show.getName());
                        showsValues.put(TvCentralContract.TvUpcomingWeekEntry.COLUMN_POSTER_PATH, show.getPosterPath());
                        showsValues.put(TvCentralContract.TvUpcomingWeekEntry.COLUMN_SHOW_ID, show.getId());
                        showsValues.put(TvCentralContract.TvUpcomingWeekEntry.COLUMN_POPULARITY, show.getPopularity());
                        showsValues.put(TvCentralContract.TvUpcomingWeekEntry.COLUMN_BACKDROP_PATH, show.getBackdropPath());
                        showsValues.put(TvCentralContract.TvUpcomingWeekEntry.COLUMN_VOTE_AVERAGE, show.getVoteAverage());
                        showsValues.put(TvCentralContract.TvUpcomingWeekEntry.COLUMN_OVERVIEW, show.getOverview());
                        showsValues.put(TvCentralContract.TvUpcomingWeekEntry.COLUMN_FIRST_AIR_DATE, show.getFirstAirDate());
                        showsValues.put(TvCentralContract.TvUpcomingWeekEntry.COLUMN_ORIGINAL_LANGUAGE, show.getOriginalLanguage());
                        showsValues.put(TvCentralContract.TvUpcomingWeekEntry.COLUMN_VOTE_COUNT, show.getVoteCount());
                        showsValues.put(TvCentralContract.TvUpcomingWeekEntry.COLUMN_ORIGINAL_NAME, show.getOriginalName());

                        cVVector.add(showsValues);
                    }

                    if (cVVector.size() > 0) {
                        ContentValues[] contentValues = new ContentValues[cVVector.size()];
                        cVVector.toArray(contentValues);

                        // remove all the shows that are currently there to be replaced with the new list
                        getContext().getContentResolver().delete(TvCentralContract.TvUpcomingWeekEntry.CONTENT_URI, null, null);

                        getContext().getContentResolver().bulkInsert(TvCentralContract.TvUpcomingWeekEntry.CONTENT_URI, contentValues);

                    }

                    Log.d(TAG, "Upcoming week shows sync service complete. " + cVVector.size() + " inserted.");

                } else {
                    Log.d(TAG, "Error encountered with background upcoming week show sync: " + response.message());
                }

            }

            @Override
            public void onFailure(Call<ShowsResponse> call, Throwable t) {
                Log.d(TAG, "Error encountered with background upcoming week show sync: " + t.getMessage());

            }
        });
    }

    public static void initializeSyncAdapter(Context context) {
        getSyncAccount(context);
    }

    public static Account getSyncAccount(Context context) {
        AccountManager manager = (AccountManager) context.getSystemService(Context.ACCOUNT_SERVICE);

        // creating a default account for the sync service
        Account defaultAccount = new Account(context.getString(R.string.app_name), context.getString(R.string.sync_account_type));

        if (null == manager.getPassword(defaultAccount)) {
            if (!manager.addAccountExplicitly(defaultAccount, "", null)) {
                return null;
            }

            onDefaultAccountCreated(defaultAccount, context);
        }

        return defaultAccount;
    }

    private static void onDefaultAccountCreated(Account account, Context context) {
        // scheduling the sync adapter
        String contentAuthority = context.getString(R.string.content_authority);
        SyncRequest syncRequest = new SyncRequest.Builder().
                syncPeriodic(SYNC_INTERVAL, SYNC_FLEXTIME).
                setSyncAdapter(account, contentAuthority).
                setExtras(new Bundle()).build();
        ContentResolver.requestSync(syncRequest);

        ContentResolver.setSyncAutomatically(account, contentAuthority, true);

        TvCentralSyncAdapter.beginSync(context);
    }

    public static void beginSync(Context context) {
        Bundle bundle = new Bundle();
        bundle.putBoolean(ContentResolver.SYNC_EXTRAS_EXPEDITED, true);
        bundle.putBoolean(ContentResolver.SYNC_EXTRAS_MANUAL, true);
        ContentResolver.requestSync(getSyncAccount(context), context.getString(R.string.content_authority), bundle);

    }
}
