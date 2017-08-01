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

package de.clemensbartz.android.launcher.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import de.clemensbartz.android.launcher.Launcher;

/**
 * Broadcast receiver for package change.
 *
 * @author Clemens Bartz
 */
public final class PackageChangedBroadcastReceiver extends BroadcastReceiver {

    /** The Launcher. */
    private final Launcher launcher;

    /**
     * Create a broadcast receiver for a launcher.
     * @param launcher the launcher
     */
    public PackageChangedBroadcastReceiver(final Launcher launcher) {
        this.launcher = launcher;
    }

    @Override
    public void onReceive(final Context context, final Intent intent) {
        launcher.updateApplications();
    }
}
