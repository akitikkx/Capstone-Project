package za.co.ahmedtikiwa.apps.tvcentral.ui;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import za.co.ahmedtikiwa.apps.tvcentral.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class ShowDetailActivityFragment extends Fragment {

    public ShowDetailActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_show_detail, container, false);
    }
}
