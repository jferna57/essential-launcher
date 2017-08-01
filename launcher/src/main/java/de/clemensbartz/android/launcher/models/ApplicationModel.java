/*
 * Copyright (C) 2017  Clemens Bartz
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
    public CharSequence label;
    /** The icon. */
    public Drawable icon;
    /** The package name. */
    public String packageName;
    /** The full class name. */
    public String className;
    /** The disabled flag. */
    public boolean disabled;
}
