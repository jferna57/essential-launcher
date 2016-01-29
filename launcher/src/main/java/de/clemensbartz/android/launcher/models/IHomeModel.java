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

import java.util.List;

/**
 * Interface for the home model.
 */
public interface IHomeModel {
    /**
     * Load all model values, including basic preferences and applications.
     * <p/>
     * This method has to be called from an async task.
     */
    void loadValues();
    /**
     *
     * @return the most used applications as an unmodifiable list
     */
    List<IApplicationModel> getMostUsedApplications();
    /**
     * Update the list of applications.
     * <p/>
     * This method has to be called from an async task.
     */
    void updateApplications();
    /**
     * Toggle disabled state of an application.
     *
     * @param packageName the package name of the app
     * @param className the class name of the app
     */
    void toggleDisabled(final String packageName, final String className);
    /**
     * Determines if an application is disabled.
     *
     * @param packageName the package name of the app
     * @param className the class name of the app
     * @return if it is disabled
     */
    boolean isDisabled(final String packageName, final String className);
    /**
     * Reset usage to 0 for package and class.
     *
     * @param packageName the package name
     * @param className the class name
     */
    void resetUsage(final String packageName, final String className);
    /**
     * Add one usage counter for the package and class name.
     * Also updates the internal list.
     * <p/>
     * This method has to be called from an async task.
     *
     * @param packageName the package name
     * @param className the class name
     */
    void addUsage(final String packageName, final String className);
    /**
     *
     * @return the app widget id
     */
    int getAppWidgetId();
    /**
     * Set the new app widget id.
     * @param appWidgetId the app widget id
     */
    void setAppWidgetId(final int appWidgetId);
    /**
     *
     * @return if the overlay should be hidden
     */
    boolean getHideOverlay();
    /**
     * Set to hide the overlay permanently.
     */
    void setHideOverlay();
    /**
     * Close all used databases.
     */
    void close();
}
