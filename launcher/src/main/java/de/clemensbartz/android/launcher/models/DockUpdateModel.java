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

import android.widget.ImageView;

/**
 * Model for updating the dock asynchronously.
 *
 * @author Clemens Bartz
 * @since 1.0
 */
public final class DockUpdateModel {
    /** The image view. */
    private final ImageView imageView;
    /** The application model. */
    private final ApplicationModel applicationModel;

    /**
     * Create a new dock update model for an image view and an
     * application model.
     * @param imageView the image view
     * @param applicationModel the application model
     */
    public DockUpdateModel(
            final ImageView imageView,
            final ApplicationModel applicationModel) {
        this.imageView = imageView;
        this.applicationModel = applicationModel;
    }

    /**
     *
     * @return the imageview
     */
    public ImageView getImageView() {
        return imageView;
    }

    /**
     *
     * @return the application model
     */
    public ApplicationModel getApplicationModel() {
        return applicationModel;
    }
}
