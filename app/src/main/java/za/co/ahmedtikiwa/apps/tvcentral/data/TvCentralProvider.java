package za.co.ahmedtikiwa.apps.tvcentral.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class TvCentralProvider extends ContentProvider {

    private TvCentralDbHelper mTvCentralDbHelper;
    private UriMatcher sUriMatcher = buildUriMatcher();
    private final int TV_POPULAR = 100;
    private final int TV_POPULAR_WITH_ID = 101;
    private final int TV_AIRING_TODAY = 200;
    private final int TV_AIRING_TODAY_WITH_ID = 201;
    private final int TV_UPCOMING_WEEK = 300;
    private final int TV_UPCOMING_WEEK_WITH_ID = 301;

    private UriMatcher buildUriMatcher() {
        final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

        uriMatcher.addURI(TvCentralContract.CONTENT_AUTHORITY, TvCentralContract.PATH_TV_AIRING_TODAY, TV_AIRING_TODAY);
        uriMatcher.addURI(TvCentralContract.CONTENT_AUTHORITY, TvCentralContract.PATH_TV_AIRING_TODAY + "/#", TV_AIRING_TODAY_WITH_ID);
        uriMatcher.addURI(TvCentralContract.CONTENT_AUTHORITY, TvCentralContract.PATH_TV_POPULAR, TV_POPULAR);
        uriMatcher.addURI(TvCentralContract.CONTENT_AUTHORITY, TvCentralContract.PATH_TV_POPULAR + "/#", TV_POPULAR_WITH_ID);
        uriMatcher.addURI(TvCentralContract.CONTENT_AUTHORITY, TvCentralContract.PATH_TV_UPCOMING_WEEK, TV_UPCOMING_WEEK);
        uriMatcher.addURI(TvCentralContract.CONTENT_AUTHORITY, TvCentralContract.PATH_TV_UPCOMING_WEEK + "/#", TV_UPCOMING_WEEK_WITH_ID);

        return uriMatcher;
    }

    @Override
    public boolean onCreate() {
        mTvCentralDbHelper = new TvCentralDbHelper(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Cursor cursor;

        switch (sUriMatcher.match(uri)) {
            case TV_AIRING_TODAY:
                cursor = mTvCentralDbHelper.getReadableDatabase().query(
                        TvCentralContract.TvAiringTodayEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder
                );
                break;
            case TV_AIRING_TODAY_WITH_ID:
                cursor = mTvCentralDbHelper.getReadableDatabase().query(
                        TvCentralContract.TvAiringTodayEntry.TABLE_NAME,
                        projection,
                        TvCentralContract.TvAiringTodayEntry.COLUMN_SHOW_ID + " = ?",
                        new String[]{String.valueOf(ContentUris.parseId(uri))},
                        null,
                        null,
                        sortOrder
                );
                break;
            case TV_POPULAR:
                cursor = mTvCentralDbHelper.getReadableDatabase().query(
                        TvCentralContract.TvPopularEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder
                );
                break;
            case TV_POPULAR_WITH_ID:
                cursor = mTvCentralDbHelper.getReadableDatabase().query(
                        TvCentralContract.TvPopularEntry.TABLE_NAME,
                        projection,
                        TvCentralContract.TvPopularEntry.COLUMN_SHOW_ID + " = ?",
                        new String[]{String.valueOf(ContentUris.parseId(uri))},
                        null,
                        null,
                        sortOrder
                );
                break;
            case TV_UPCOMING_WEEK:
                cursor = mTvCentralDbHelper.getReadableDatabase().query(
                        TvCentralContract.TvUpcomingWeekEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder
                );
                break;
            case TV_UPCOMING_WEEK_WITH_ID:
                cursor = mTvCentralDbHelper.getReadableDatabase().query(
                        TvCentralContract.TvUpcomingWeekEntry.TABLE_NAME,
                        projection,
                        TvCentralContract.TvUpcomingWeekEntry.COLUMN_SHOW_ID + " = ?",
                        new String[]{String.valueOf(ContentUris.parseId(uri))},
                        null,
                        null,
                        sortOrder
                );
                break;
            default:
                throw new UnsupportedOperationException("Unknown URI: " + uri);
        }

        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

    @Override
    public String getType(Uri uri) {
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case TV_AIRING_TODAY:
                return TvCentralContract.TvAiringTodayEntry.CONTENT_TYPE;
            case TV_POPULAR:
                return TvCentralContract.TvPopularEntry.CONTENT_TYPE;
            case TV_UPCOMING_WEEK:
                return TvCentralContract.TvUpcomingWeekEntry.CONTENT_TYPE;
            case TV_AIRING_TODAY_WITH_ID:
                return TvCentralContract.TvAiringTodayEntry.CONTENT_ITEM_TYPE;
            case TV_POPULAR_WITH_ID:
                return TvCentralContract.TvPopularEntry.CONTENT_ITEM_TYPE;
            case TV_UPCOMING_WEEK_WITH_ID:
                return TvCentralContract.TvUpcomingWeekEntry.CONTENT_ITEM_TYPE;
            default:
                throw new UnsupportedOperationException("Unknown URI: " + uri);
        }
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        final SQLiteDatabase database = mTvCentralDbHelper.getWritableDatabase();
        final int match = sUriMatcher.match(uri);
        Uri returnUri;

        long _id;

        switch (match) {
            case TV_AIRING_TODAY:
                _id = database.insert(TvCentralContract.TvAiringTodayEntry.TABLE_NAME, null, values);
                if (_id > 0) {
                    returnUri = TvCentralContract.TvAiringTodayEntry.buildShowUri(_id);
                } else {
                    throw new SQLException("Failed to insert row into: " + uri);
                }
                break;
            case TV_POPULAR:
                _id = database.insert(TvCentralContract.TvPopularEntry.TABLE_NAME, null, values);
                if (_id > 0) {
                    returnUri = TvCentralContract.TvPopularEntry.buildShowUri(_id);
                } else {
                    throw new SQLException("Failed to insert row into: " + uri);
                }
                break;
            case TV_UPCOMING_WEEK:
                _id = database.insert(TvCentralContract.TvUpcomingWeekEntry.TABLE_NAME, null, values);
                if (_id > 0) {
                    returnUri = TvCentralContract.TvUpcomingWeekEntry.buildShowUri(_id);
                } else {
                    throw new SQLException("Failed to insert row into: " + uri);
                }
                break;
            default:
                throw new UnsupportedOperationException("Unknown URI: " + uri);
        }

        getContext().getContentResolver().notifyChange(uri, null);
        database.close();

        return returnUri;

    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        final SQLiteDatabase database = mTvCentralDbHelper.getWritableDatabase();
        final int match = sUriMatcher.match(uri);
        int deletedRows;

        if (null == selection) selection = "1";
        switch (match) {
            case TV_AIRING_TODAY:
                deletedRows = database.delete(TvCentralContract.TvAiringTodayEntry.TABLE_NAME, selection, selectionArgs);
                break;
            case TV_POPULAR:
                deletedRows = database.delete(TvCentralContract.TvPopularEntry.TABLE_NAME, selection, selectionArgs);
                break;
            case TV_UPCOMING_WEEK:
                deletedRows = database.delete(TvCentralContract.TvUpcomingWeekEntry.TABLE_NAME, selection, selectionArgs);
                break;
            default:
                throw new UnsupportedOperationException("Unknown URI: " + uri);
        }

        if (deletedRows != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        database.close();
        return deletedRows;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        final SQLiteDatabase database = mTvCentralDbHelper.getWritableDatabase();
        final int match = sUriMatcher.match(uri);
        int updatedRows;

        switch (match) {
            case TV_AIRING_TODAY:
                updatedRows = database.update(TvCentralContract.TvAiringTodayEntry.TABLE_NAME, values, selection, selectionArgs);
                break;
            case TV_POPULAR:
                updatedRows = database.update(TvCentralContract.TvPopularEntry.TABLE_NAME, values, selection, selectionArgs);
                break;
            case TV_UPCOMING_WEEK:
                updatedRows = database.update(TvCentralContract.TvUpcomingWeekEntry.TABLE_NAME, values, selection, selectionArgs);
                break;
            default:
                throw new UnsupportedOperationException("Unknown URI: " + uri);
        }

        if (updatedRows > 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        database.close();
        return updatedRows;
    }

    @Override
    public int bulkInsert(Uri uri, ContentValues[] values) {
        final SQLiteDatabase database = mTvCentralDbHelper.getWritableDatabase();
        final int match = sUriMatcher.match(uri);
        int returnCount = 0;

        switch (match) {
            case TV_AIRING_TODAY:
                database.beginTransaction();
                try {
                    for (ContentValues value : values) {
                        long _id = database.insert(TvCentralContract.TvAiringTodayEntry.TABLE_NAME, null, value);
                        if (_id != -1) {
                            returnCount++;
                        }
                    }
                    database.setTransactionSuccessful();
                } finally {
                    database.endTransaction();
                }
                getContext().getContentResolver().notifyChange(uri, null);
                database.close();
                return returnCount;
            case TV_POPULAR:
                database.beginTransaction();
                try {
                    for (ContentValues value : values) {
                        long _id = database.insert(TvCentralContract.TvPopularEntry.TABLE_NAME, null, value);
                        if (_id != -1) {
                            returnCount++;
                        }
                    }
                    database.setTransactionSuccessful();
                } finally {
                    database.endTransaction();
                }
                getContext().getContentResolver().notifyChange(uri, null);
                database.close();
                return returnCount;
            case TV_UPCOMING_WEEK:
                database.beginTransaction();
                try {
                    for (ContentValues value : values) {
                        long _id = database.insert(TvCentralContract.TvUpcomingWeekEntry.TABLE_NAME, null, value);
                        if (_id != -1) {
                            returnCount++;
                        }
                    }
                    database.setTransactionSuccessful();
                } finally {
                    database.endTransaction();
                }
                getContext().getContentResolver().notifyChange(uri, null);
                database.close();
                return returnCount;
            default:
                database.close();
                return super.bulkInsert(uri, values);
        }

    }
}
