package za.co.ahmedtikiwa.apps.tvcentral.ui;

import android.net.Uri;
import android.support.v4.app.Fragment;

public class BaseFragment extends Fragment {

    public static final String SHOW_DETAIL_URI = "URI";

    public static final int COLUMN_POSTER_PATH = 1;
    public static final int COLUMN_SHOW_ID = 3;
    public static final int COLUMN_BACKDROP_PATH = 4;
    public static final int COLUMN_OVERVIEW_PATH = 6;
    public static final int COLUMN_NAME_PATH = 10;

    public interface Callback {
        public void onItemSelected(Uri showUri);
    }
}
