package com.psx.appwidgetsample;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.RemoteViews;

import java.text.DateFormat;
import java.util.Date;

/**
 * Implementation of App Widget functionality.
 */
public class NewAppWidget extends AppWidgetProvider {

    private static final String sharedPrefFile = "com.psx.app_widget";
    private static final String COUNT_KEY = "count";

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        SharedPreferences sharedPreferences = context.getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE);
        int count = sharedPreferences.getInt(COUNT_KEY + appWidgetId, 0);
        count++;
        String dateStr = DateFormat.getTimeInstance(DateFormat.SHORT).format(new Date());
        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.new_app_widget);
        views.setTextViewText(R.id.app_widget_id, appWidgetId+"");
        views.setTextViewText(R.id.update_date, context.getResources().getString(R.string.date_count_format, count, dateStr));
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(COUNT_KEY + appWidgetId, count);
        editor.apply();
        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }
    
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

