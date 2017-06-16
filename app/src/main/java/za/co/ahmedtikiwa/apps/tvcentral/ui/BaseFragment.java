package za.co.ahmedtikiwa.apps.tvcentral.ui;

import android.net.Uri;
import android.support.v4.app.Fragment;

public class BaseFragment extends Fragment {


    public interface Callback {
        public void onItemSelected(Uri showUri);
    }
}
