package za.co.ahmedtikiwa.apps.tvcentral.widget;

import android.content.Intent;
import android.widget.RemoteViewsService;

public class AiringTodayCollectionService extends RemoteViewsService {

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new AiringTodayCollectionProvider(this, intent);
    }
}
