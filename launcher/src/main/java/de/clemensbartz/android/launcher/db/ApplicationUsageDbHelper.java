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

package de.clemensbartz.android.launcher.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Helper class for opening a SQLite database.
 *
 * @author Clemens Bartz
 * @since 1.0
 */
public final class ApplicationUsageDbHelper extends SQLiteOpenHelper {
    /** The version of the database. */
    public static final int DATABASE_VERSION = 2;
    /** The database name. */
    public static final String DATABASE_NAME = "ApplicationUsage.db";

    /**
     * Create a new helper class in a context.
     * @param context the context
     */
    public ApplicationUsageDbHelper(final Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(final SQLiteDatabase db) {
        db.execSQL(ApplicationUsageModel.CREATE_SQL);
    }

    @Override
    public void onUpgrade(final SQLiteDatabase db,
                          final int oldVersion,
                          final int newVersion) {

        db.execSQL(ApplicationUsageModel.DROP_SQL);
        onCreate(db);
    }
}
