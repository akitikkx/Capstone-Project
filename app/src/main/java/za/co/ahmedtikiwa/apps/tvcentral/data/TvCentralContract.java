package za.co.ahmedtikiwa.apps.tvcentral.data;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

public class TvCentralContract {

    public static final String CONTENT_AUTHORITY = "za.co.ahmedtikiwa.apps.tvcentral";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    public static final String PATH_TV_POPULAR = "tv_popular";
    public static final String PATH_TV_AIRING_TODAY = "tv_airing_today";
    public static final String PATH_TV_UPCOMING_WEEK = "tv_upcoming_week";
    public static final String PATH_TV_TOP_RATED = "tv_top_rated";

    public static final class TvPopularEntry implements BaseColumns {
        public static final String TABLE_NAME = "tv_popular";
        public static final String COLUMN_POSTER_PATH = "poster_path";
        public static final String COLUMN_SHOW_ID = "show_id";
        public static final String COLUMN_POPULARITY = "popularity";
        public static final String COLUMN_BACKDROP_PATH = "backdrop_path";
        public static final String COLUMN_VOTE_AVERAGE = "vote_average";
        public static final String COLUMN_OVERVIEW = "overview";
        public static final String COLUMN_FIRST_AIR_DATE = "first_air_date";
        public static final String COLUMN_ORIGINAL_LANGUAGE = "original_language";
        public static final String COLUMN_VOTE_COUNT = "vote_count";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_ORIGINAL_NAME = "original_name";

        public static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_TV_POPULAR;
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_TV_POPULAR;

        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_TV_POPULAR).build();

        public static Uri buildShowUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }
    }

    public static final class TvAiringTodayEntry implements BaseColumns {
        public static final String TABLE_NAME = "tv_airing_today";
        public static final String COLUMN_POSTER_PATH = "poster_path";
        public static final String COLUMN_SHOW_ID = "show_id";
        public static final String COLUMN_POPULARITY = "popularity";
        public static final String COLUMN_BACKDROP_PATH = "backdrop_path";
        public static final String COLUMN_VOTE_AVERAGE = "vote_average";
        public static final String COLUMN_OVERVIEW = "overview";
        public static final String COLUMN_FIRST_AIR_DATE = "first_air_date";
        public static final String COLUMN_ORIGINAL_LANGUAGE = "original_language";
        public static final String COLUMN_VOTE_COUNT = "vote_count";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_ORIGINAL_NAME = "original_name";

        public static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_TV_AIRING_TODAY;
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_TV_AIRING_TODAY;

        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_TV_AIRING_TODAY).build();

        public static Uri buildShowUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }
    }

    public static final class TvUpcomingWeekEntry implements BaseColumns {
        public static final String TABLE_NAME = "tv_upcoming_week";
        public static final String COLUMN_POSTER_PATH = "poster_path";
        public static final String COLUMN_SHOW_ID = "show_id";
        public static final String COLUMN_POPULARITY = "popularity";
        public static final String COLUMN_BACKDROP_PATH = "backdrop_path";
        public static final String COLUMN_VOTE_AVERAGE = "vote_average";
        public static final String COLUMN_OVERVIEW = "overview";
        public static final String COLUMN_FIRST_AIR_DATE = "first_air_date";
        public static final String COLUMN_ORIGINAL_LANGUAGE = "original_language";
        public static final String COLUMN_VOTE_COUNT = "vote_count";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_ORIGINAL_NAME = "original_name";

        public static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_TV_UPCOMING_WEEK;
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_TV_UPCOMING_WEEK;

        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_TV_UPCOMING_WEEK).build();

        public static Uri buildShowUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }
    }

    public static final class TvTopRatedEntry implements BaseColumns {
        public static final String TABLE_NAME = "tv_top_rated";
        public static final String COLUMN_POSTER_PATH = "poster_path";
        public static final String COLUMN_SHOW_ID = "show_id";
        public static final String COLUMN_POPULARITY = "popularity";
        public static final String COLUMN_BACKDROP_PATH = "backdrop_path";
        public static final String COLUMN_VOTE_AVERAGE = "vote_average";
        public static final String COLUMN_OVERVIEW = "overview";
        public static final String COLUMN_FIRST_AIR_DATE = "first_air_date";
        public static final String COLUMN_ORIGINAL_LANGUAGE = "original_language";
        public static final String COLUMN_VOTE_COUNT = "vote_count";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_ORIGINAL_NAME = "original_name";

        public static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_TV_TOP_RATED;
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_TV_TOP_RATED;

        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_TV_TOP_RATED).build();

        public static Uri buildShowUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }
    }

}
