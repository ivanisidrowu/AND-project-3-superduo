package barqsoft.footballscores.service;

import android.annotation.TargetApi;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import barqsoft.footballscores.R;
import barqsoft.footballscores.database.DatabaseContract;

/**
 * Created by ivan on 2/10/16.
 */
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class WidgetDataProvider implements RemoteViewsService.RemoteViewsFactory {

    private static final String TAG = "WidgetDataProvider";

    private Context mContext;
    private ContentResolver mContentResolver;
    private List<MatchInfo> matchInfoList = new ArrayList<>();

    public WidgetDataProvider(Context context, Intent intent) {
        this.mContext = context;
        this.mContentResolver = mContext.getContentResolver();
    }

    @Override
    public void onCreate() {
        long currentTime = System.currentTimeMillis();
        Date today = new Date(currentTime);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        String todayString = dateFormat.format(today);
        String todayString = "2016-02-13";
        Log.d(TAG, "onCreate: Date string = " + todayString);

        Cursor cursor = mContentResolver.query(
                DatabaseContract.scores_table.buildScoreWithDate(), null, null, new String[]{todayString}, null
        );

        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();

            while (!cursor.isAfterLast()) {
                matchInfoList.add(new MatchInfo().fromCursor(cursor));
                cursor.moveToNext();
            }
        }
    }

    @Override
    public void onDataSetChanged() {

    }

    @Override
    public void onDestroy() {
        matchInfoList.clear();
    }

    @Override
    public int getCount() {
        return matchInfoList.size();
    }

    @Override
    public RemoteViews getViewAt(int position) {
        MatchInfo info = matchInfoList.get(position);

        RemoteViews remoteViews = new RemoteViews(mContext.getPackageName(), R.layout.widget_list_item);
        remoteViews.setTextViewText(R.id.home_name, info.getHomeName());
        remoteViews.setTextViewText(R.id.away_name, info.getAwayName());
        remoteViews.setTextViewText(R.id.score_textview, info.getScore());

        return remoteViews;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }
}
