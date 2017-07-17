package za.co.ahmedtikiwa.apps.tvcentral.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class TvCentralDbHelper extends SQLiteOpenHelper {

    private static TvCentralDbHelper sInstance;
    private static final int DATABASE_VERSION = 1;
    static final String DATABASE_NAME = "tv_central.db";

    public static synchronized TvCentralDbHelper getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new TvCentralDbHelper(context.getApplicationContext());
        }
        return sInstance;
    }

    public TvCentralDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_TV_POPULAR_TABLE = "CREATE TABLE IF NOT EXISTS " + TvCentralContract.TvPopularEntry.TABLE_NAME + " (" +
                TvCentralContract.TvPopularEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                TvCentralContract.TvPopularEntry.COLUMN_POSTER_PATH + " TEXT DEFAULT NULL, " +
                TvCentralContract.TvPopularEntry.COLUMN_POPULARITY + " REAL DEFAULT NULL, " +
                TvCentralContract.TvPopularEntry.COLUMN_SHOW_ID + " INTEGER NOT NULL, " +
                TvCentralContract.TvPopularEntry.COLUMN_BACKDROP_PATH + " TEXT DEFAULT NULL, " +
                TvCentralContract.TvPopularEntry.COLUMN_VOTE_AVERAGE + " REAL DEFAULT NULL, " +
                TvCentralContract.TvPopularEntry.COLUMN_OVERVIEW + " TEXT DEFAULT NULL, " +
                TvCentralContract.TvPopularEntry.COLUMN_VOTE_COUNT + " REAL DEFAULT NULL, " +
                TvCentralContract.TvPopularEntry.COLUMN_FIRST_AIR_DATE + " TEXT DEFAULT NULL, " +
                TvCentralContract.TvPopularEntry.COLUMN_ORIGINAL_LANGUAGE + " TEXT DEFAULT NULL, " +
                TvCentralContract.TvPopularEntry.COLUMN_ORIGINAL_NAME + " TEXT DEFAULT NULL, " +
                TvCentralContract.TvPopularEntry.COLUMN_NAME + " TEXT DEFAULT NULL, " +
                " UNIQUE (" + TvCentralContract.TvPopularEntry.COLUMN_SHOW_ID + ") ON CONFLICT REPLACE);";

        final String SQL_CREATE_TV_AIRING_TODAY_TABLE = "CREATE TABLE IF NOT EXISTS " + TvCentralContract.TvAiringTodayEntry.TABLE_NAME + " (" +
                TvCentralContract.TvAiringTodayEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                TvCentralContract.TvAiringTodayEntry.COLUMN_POSTER_PATH + " TEXT DEFAULT NULL, " +
                TvCentralContract.TvAiringTodayEntry.COLUMN_POPULARITY + " REAL DEFAULT NULL, " +
                TvCentralContract.TvAiringTodayEntry.COLUMN_SHOW_ID + " INTEGER NOT NULL, " +
                TvCentralContract.TvAiringTodayEntry.COLUMN_BACKDROP_PATH + " TEXT DEFAULT NULL, " +
                TvCentralContract.TvAiringTodayEntry.COLUMN_VOTE_AVERAGE + " REAL DEFAULT NULL, " +
                TvCentralContract.TvAiringTodayEntry.COLUMN_OVERVIEW + " TEXT DEFAULT NULL, " +
                TvCentralContract.TvAiringTodayEntry.COLUMN_VOTE_COUNT + " REAL DEFAULT NULL, " +
                TvCentralContract.TvAiringTodayEntry.COLUMN_FIRST_AIR_DATE + " TEXT DEFAULT NULL, " +
                TvCentralContract.TvAiringTodayEntry.COLUMN_ORIGINAL_LANGUAGE + " TEXT DEFAULT NULL, " +
                TvCentralContract.TvAiringTodayEntry.COLUMN_ORIGINAL_NAME + " TEXT DEFAULT NULL, " +
                TvCentralContract.TvAiringTodayEntry.COLUMN_NAME + " TEXT DEFAULT NULL, " +
                " UNIQUE (" + TvCentralContract.TvAiringTodayEntry.COLUMN_SHOW_ID + ") ON CONFLICT REPLACE);";

        final String SQL_CREATE_TV_UPCOMING_WEEK_TABLE = "CREATE TABLE IF NOT EXISTS " + TvCentralContract.TvUpcomingWeekEntry.TABLE_NAME + " (" +
                TvCentralContract.TvUpcomingWeekEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                TvCentralContract.TvUpcomingWeekEntry.COLUMN_POSTER_PATH + " TEXT DEFAULT NULL, " +
                TvCentralContract.TvUpcomingWeekEntry.COLUMN_POPULARITY + " REAL DEFAULT NULL, " +
                TvCentralContract.TvUpcomingWeekEntry.COLUMN_SHOW_ID + " INTEGER NOT NULL, " +
                TvCentralContract.TvUpcomingWeekEntry.COLUMN_BACKDROP_PATH + " TEXT DEFAULT NULL, " +
                TvCentralContract.TvUpcomingWeekEntry.COLUMN_VOTE_AVERAGE + " REAL DEFAULT NULL, " +
                TvCentralContract.TvUpcomingWeekEntry.COLUMN_OVERVIEW + " TEXT DEFAULT NULL, " +
                TvCentralContract.TvUpcomingWeekEntry.COLUMN_VOTE_COUNT + " REAL DEFAULT NULL, " +
                TvCentralContract.TvUpcomingWeekEntry.COLUMN_FIRST_AIR_DATE + " TEXT DEFAULT NULL, " +
                TvCentralContract.TvUpcomingWeekEntry.COLUMN_ORIGINAL_LANGUAGE + " TEXT DEFAULT NULL, " +
                TvCentralContract.TvUpcomingWeekEntry.COLUMN_ORIGINAL_NAME + " TEXT DEFAULT NULL, " +
                TvCentralContract.TvUpcomingWeekEntry.COLUMN_NAME + " TEXT DEFAULT NULL, " +
                " UNIQUE (" + TvCentralContract.TvUpcomingWeekEntry.COLUMN_SHOW_ID + ") ON CONFLICT REPLACE);";

        final String SQL_CREATE_TV_TOP_RATED_TABLE = "CREATE TABLE IF NOT EXISTS " + TvCentralContract.TvTopRatedEntry.TABLE_NAME + " (" +
                TvCentralContract.TvUpcomingWeekEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                TvCentralContract.TvUpcomingWeekEntry.COLUMN_POSTER_PATH + " TEXT DEFAULT NULL, " +
                TvCentralContract.TvUpcomingWeekEntry.COLUMN_POPULARITY + " REAL DEFAULT NULL, " +
                TvCentralContract.TvUpcomingWeekEntry.COLUMN_SHOW_ID + " INTEGER NOT NULL, " +
                TvCentralContract.TvUpcomingWeekEntry.COLUMN_BACKDROP_PATH + " TEXT DEFAULT NULL, " +
                TvCentralContract.TvUpcomingWeekEntry.COLUMN_VOTE_AVERAGE + " REAL DEFAULT NULL, " +
                TvCentralContract.TvUpcomingWeekEntry.COLUMN_OVERVIEW + " TEXT DEFAULT NULL, " +
                TvCentralContract.TvUpcomingWeekEntry.COLUMN_VOTE_COUNT + " REAL DEFAULT NULL, " +
                TvCentralContract.TvUpcomingWeekEntry.COLUMN_FIRST_AIR_DATE + " TEXT DEFAULT NULL, " +
                TvCentralContract.TvUpcomingWeekEntry.COLUMN_ORIGINAL_LANGUAGE + " TEXT DEFAULT NULL, " +
                TvCentralContract.TvUpcomingWeekEntry.COLUMN_ORIGINAL_NAME + " TEXT DEFAULT NULL, " +
                TvCentralContract.TvUpcomingWeekEntry.COLUMN_NAME + " TEXT DEFAULT NULL, " +
                " UNIQUE (" + TvCentralContract.TvUpcomingWeekEntry.COLUMN_SHOW_ID + ") ON CONFLICT REPLACE);";

        db.execSQL(SQL_CREATE_TV_POPULAR_TABLE);
        db.execSQL(SQL_CREATE_TV_AIRING_TODAY_TABLE);
        db.execSQL(SQL_CREATE_TV_UPCOMING_WEEK_TABLE);
        db.execSQL(SQL_CREATE_TV_TOP_RATED_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
