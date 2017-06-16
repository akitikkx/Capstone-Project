package za.co.ahmedtikiwa.apps.tvcentral.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import butterknife.ButterKnife;
import za.co.ahmedtikiwa.apps.tvcentral.R;

public class ShowDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_detail);

        ButterKnife.bind(this);

        if (savedInstanceState == null) {
            Bundle args = new Bundle();
            args.putParcelable(BaseFragment.SHOW_DETAIL_URI, getIntent().getData());

            ShowDetailFragment fragment = new ShowDetailFragment();
            fragment.setArguments(args);

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.show_detail_container, fragment)
                    .commit();
        }


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
