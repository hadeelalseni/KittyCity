package hadeel.com.kittycity.Widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.RemoteViews;

import hadeel.com.kittycity.R;
import hadeel.com.kittycity.UI.MainActivity;

import android.content.Intent;
import android.widget.Toast;

/**
 * Implementation of App Widget functionality.
 */
public class KittyWidget extends AppWidgetProvider {

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for (int appWidgetId : appWidgetIds) {
            String widgetTxt = "bla bla bla";

            SharedPreferences sharedPreferences = context.getSharedPreferences(WidgetInfo.kittyNameWidget, context.MODE_PRIVATE);

            widgetTxt = sharedPreferences.getString(WidgetInfo.kittyNameWidget , widgetTxt);

            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.kitty_widget);
            views.setTextViewText(R.id.appwidget_text, widgetTxt);

            Intent intent = new Intent(context, MainActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(context,0,intent,0);

            views.setOnClickPendingIntent(R.id.appwidget_text, pendingIntent);
            appWidgetManager.updateAppWidget(appWidgetId, views);


        }
    }

}


