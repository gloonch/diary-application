package diary.tehranqolhak.diary.Utils;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import diary.tehranqolhak.diary.MainActivity;
import diary.tehranqolhak.diary.R;

public class WidgetProvider extends AppWidgetProvider {
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);


        for (int appWidgetId : appWidgetIds) {

//            Intent intent = new Intent(context, MainActivity.class);
//            intent.putExtra("id", appWidgetId);
//            PendingIntent pi = PendingIntent
//                    .getActivity(context, 100, intent, PendingIntent.FLAG_UPDATE_CURRENT);
//
//
//            RemoteViews rv = new RemoteViews("diary.tehranqolhak.diary.Utils", R.layout.widget);
//            rv.setOnClickPendingIntent(R.id.savebtnID, pi);
//            appWidgetManager.updateAppWidget(appWidgetId, rv);


        }
    }
}
