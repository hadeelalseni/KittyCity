package hadeel.com.kittycity.Widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.RemoteViews;

import hadeel.com.kittycity.R;
import hadeel.com.kittycity.UI.MainActivity;

import android.content.Intent;
/**
 * Implementation of App Widget functionality.
 */
public class KittyWidget extends AppWidgetProvider {

    /*
    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        CharSequence widgetText = context.getString(R.string.appwidget_text);
        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.kitty_widget);
        views.setTextViewText(R.id.appwidget_text, widgetText);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }
    */

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            //updateAppWidget(context, appWidgetManager, appWidgetId);

            /*
            String widgetTxt = "bla bla bla";
            SharedPreferences sharedPreferences = context.getSharedPreferences(WidgetInfo.kittyImageWidget, context.MODE_PRIVATE);
            widgetTxt = sharedPreferences.getString(WidgetInfo.kittyImageWidget , widgetTxt);
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.kitty_widget);
            views.setTextViewText(R.id.appwidget_image, widgetTxt);
            Intent intent = new Intent(context, MainActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(context,0,intent,0);
            views.setOnClickPendingIntent(R.id.appwidget_text, pendingIntent);
            appWidgetManager.updateAppWidget(appWidgetId, views);
            */

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

    /*
    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
    */
}

