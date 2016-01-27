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

import android.graphics.drawable.Drawable;

/**
 * Model for applications.
 *
 * @author Clemens Bartz
 * @since 1.0
 */
public final class ApplicationModel {
    /** The localized label. */
    private final CharSequence label;
    /** The icon. */
    private final Drawable icon;
    /** The package name. */
    private final String packageName;
    /** The full class name. */
    private final String className;
    /** The disabled flag. */
    private final boolean disabled;

    /**
     * Create a new application.
     * @param label the label
     * @param icon the icon
     * @param packageName the package name
     * @param className the class name
     */
    public ApplicationModel(
            final CharSequence label,
            final Drawable icon,
            final String packageName,
            final String className,
            final boolean disabled) {

        this.label = label;
        this.icon = icon;
        this.packageName = packageName;
        this.className = className;
        this.disabled = disabled;
    }

    /**
     *
     * @return the label
     */
    public CharSequence getLabel() {
        return label;
    }

    /**
     *
     * @return the icon
     */
    public Drawable getIcon() {
        return icon;
    }

    /**
     *
     * @return the package name
     */
    public String getPackageName() {
        return packageName;
    }

    /**
     *
     * @return the class name
     */
    public String getClassName() {
        return className;
    }

    @Override
    public String toString() {
        return getClassName();
    }
}
