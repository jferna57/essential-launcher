/*
 * Copyright (C) 2015  Clemens Bartz
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package de.clemensbartz.android.launcher.models;

import android.app.Activity;
import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import de.clemensbartz.android.launcher.db.ApplicationUsageDbHelper;
import de.clemensbartz.android.launcher.db.ApplicationUsageModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Model class for HomeActivity.
 *
 * @author Clemens Bartz
 * @since 1.0
 */
public final class HomeModel {
    /** The tag for the log. */
    public static final String TAG = "HomeModel";
    /** The total cached number of apps. */
    public static final int NUMBER_OF_APPS = 6;
    /** Constant for descending sorting. */
    public static final String _DESC = " DESC";
    /** Columns of ApplicationUsage. */
    public static final String[] COLUMNS = {
            ApplicationUsageModel.ApplicationUsage.COLUMN_NAME_PACKAGE_NAME,
            ApplicationUsageModel.ApplicationUsage.COLUMN_NAME_CLASS_NAME,
            ApplicationUsageModel.ApplicationUsage.COLUMN_NAME_USAGE
    };
    /** Order by usage DESC, package name DESC, class name DESC constant. */
    public static final String ORDER_BY =
            ApplicationUsageModel.ApplicationUsage.COLUMN_NAME_USAGE
                    + _DESC
            + ", "
            + ApplicationUsageModel.ApplicationUsage.COLUMN_NAME_PACKAGE_NAME
                    + _DESC
            + ", "
            + ApplicationUsageModel.ApplicationUsage.COLUMN_NAME_CLASS_NAME
                    + _DESC;
    /** Filter for package name and class name constant. */
    public static final String SELECTION =
            ApplicationUsageModel.ApplicationUsage.COLUMN_NAME_PACKAGE_NAME
                    + "=? AND "
            + ApplicationUsageModel.ApplicationUsage.COLUMN_NAME_CLASS_NAME
                    + "=?";

    /** Database helper. */
    private final ApplicationUsageDbHelper dbHelper;
    /** Package manager. */
    private final PackageManager pm;

    /** Preferences value. */
    private final SharedPreferences preferences;

    /** Key for the appWidgetId property. */
    private static final String KEY_APPWIDGET_ID = "appWidgetId";
    /** Value for the appWidgetId property. */
    private int appWidgetId = -1;

    /** Cache for most used applications. */
    private List<ApplicationModel> mostUsedApplications =
            new ArrayList<>(NUMBER_OF_APPS);

    /**
     * Create a new model in a context.
     * @param context the context
     */
    public HomeModel(final Activity context) {
        preferences = context.getPreferences(Context.MODE_PRIVATE);
        dbHelper = new ApplicationUsageDbHelper(context);
        pm = context.getPackageManager();
    }

    /**
     * Load all model values, including basic preferences and applications.
     * <p/>
     * This method has to be called from an async task.
     */
    public void loadValues() {
        updateApplications();

        appWidgetId = preferences.getInt(KEY_APPWIDGET_ID, -1);
    }

    /**
     *
     * @return the most used applications as an unmodifiable list
     */
    public List<ApplicationModel> getMostUsedApplications() {
        return Collections.unmodifiableList(mostUsedApplications);
    }

    /**
     * Update the list of applications.
     * <p/>
     * This method has to be called from an async task.
     */
    public void updateApplications() {
        final SQLiteDatabase db = dbHelper.getWritableDatabase();

        mostUsedApplications.clear();

        boolean success;

        do {
            success = true;

            Cursor c = null;
            try {
                c = db.query(ApplicationUsageModel.ApplicationUsage.TABLE_NAME,
                        COLUMNS, null, null, null, null,
                        ORDER_BY, Integer.toString(NUMBER_OF_APPS));

                if (c != null) {
                    for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
                        String packageName = null;
                        String className = null;
                        try {
                            packageName = c.getString(c.getColumnIndexOrThrow(ApplicationUsageModel.ApplicationUsage.COLUMN_NAME_PACKAGE_NAME));
                            className = c.getString(c.getColumnIndexOrThrow(ApplicationUsageModel.ApplicationUsage.COLUMN_NAME_CLASS_NAME));
                        } catch (IllegalArgumentException e) {
                            continue;
                        }
                        try {
                            final ComponentName componentName = new ComponentName(packageName, className);
                            final ActivityInfo info = pm.getActivityInfo(componentName, 0);
                            if (!info.enabled) {
                                delete(packageName, className);
                            }
                            final ApplicationModel applicationModel = new ApplicationModel(info.loadLabel(pm), info.loadIcon(pm), packageName, className);
                            mostUsedApplications.add(applicationModel);
                        } catch (PackageManager.NameNotFoundException e) {
                            if (packageName != null && className != null) {
                                delete(packageName, className);
                            }
                            success = false;
                            break;
                        }
                    }
                }
            } catch (final IllegalArgumentException e) {
                success = true;
            } finally {
                if (c != null) {
                    c.close();
                }
            }
        } while (!success);
    }

    /**
     * Add one usage counter for the package and class name.
     * Also updates the internal list.
     * <p/>
     * This method has to be called from an async task.
     *
     * @param packageName the package name
     * @param className the class name
     */
    public void addUsage(final String packageName, final String className) {
        final SQLiteDatabase db = dbHelper.getWritableDatabase();

        db.beginTransaction();
        Cursor c = null;
        try {
            // Get entry
            c = db.query(ApplicationUsageModel.ApplicationUsage.TABLE_NAME,
                    new String[]{
                            ApplicationUsageModel.ApplicationUsage.COLUMN_NAME_USAGE
                    },
                    SELECTION, new String[]{packageName, className},
                    null, null, null);
            if (c != null) {
                if (c.getCount() > 1) {
                    delete(packageName, className);
                }
                if (c.moveToFirst()) {
                    // update
                    final int usage = c.getInt(c.getColumnIndexOrThrow(ApplicationUsageModel.ApplicationUsage.COLUMN_NAME_USAGE));
                    final ContentValues values = new ContentValues(3);
                    values.put(ApplicationUsageModel.ApplicationUsage.COLUMN_NAME_CLASS_NAME, className);
                    values.put(ApplicationUsageModel.ApplicationUsage.COLUMN_NAME_PACKAGE_NAME, packageName);
                    values.put(ApplicationUsageModel.ApplicationUsage.COLUMN_NAME_USAGE, usage + 1);

                    db.update(ApplicationUsageModel.ApplicationUsage.TABLE_NAME,
                            values, SELECTION, new String[]{packageName, className});
                    db.setTransactionSuccessful();
                } else {
                    // insert
                    final ContentValues values = new ContentValues(3);
                    values.put(ApplicationUsageModel.ApplicationUsage.COLUMN_NAME_CLASS_NAME, className);
                    values.put(ApplicationUsageModel.ApplicationUsage.COLUMN_NAME_PACKAGE_NAME, packageName);
                    values.put(ApplicationUsageModel.ApplicationUsage.COLUMN_NAME_USAGE, 1);

                    db.insertOrThrow(ApplicationUsageModel.ApplicationUsage.TABLE_NAME, null, values);
                    db.setTransactionSuccessful();
                }
            }
        } finally {
            db.endTransaction();
            if (c != null) {
                c.close();
            }
        }

        updateApplications();
    }

    /**
     * Delete all entries for packageName and className.
     * @param packageName the package name
     * @param className the class name
     */
    private void delete(final String packageName, final String className) {
        final SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(ApplicationUsageModel.ApplicationUsage.TABLE_NAME,
                SELECTION, new String[]{packageName, className});
    }

    /**
     *
     * @return the app widget id
     */
    public int getAppWidgetId() {
        return appWidgetId;
    }

    /**
     * Set the new app widget id.
     * @param appWidgetId the app widget id
     */
    public void setAppWidgetId(final int appWidgetId) {
        preferences.edit().putInt(KEY_APPWIDGET_ID, appWidgetId).apply();
        this.appWidgetId = appWidgetId;
    }

    /**
     * Close all used databases.
     */
    public void close() {
        if (dbHelper.getWritableDatabase().isOpen()) {
            dbHelper.getWritableDatabase().close();
        }
    }
}
