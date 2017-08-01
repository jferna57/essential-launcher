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

package de.clemensbartz.android.launcher.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import de.clemensbartz.android.launcher.R;
import de.clemensbartz.android.launcher.models.ApplicationModel;

/**
 * Array adapter for the drawer. Takes an @{ApplicationModel}.
 *
 * @author Clemens Bartz
 * @since 1.0
 */
public final class DrawerListAdapter extends ArrayAdapter<ApplicationModel> {

    /** The resource id. */
    private final int resource;

    /**
     * Initializes a new adapter.
     * @param context the activity
     * @param resource the resource id
     * @param objects the list of application models
     */
    public DrawerListAdapter(
            final Context context,
            final int resource,
            final List<ApplicationModel> objects) {

        super(context, resource, objects);
        this.resource = resource;
    }

    @Override
    public View getView(final int position,
                        final View convertView,
                        final ViewGroup parent) {

        ViewHolder viewHolder;
        View v = convertView;

        if (convertView == null) {
            v = LayoutInflater.from(getContext()).inflate(resource, null);

            viewHolder = new ViewHolder();
            viewHolder.icon = (ImageView) v.findViewById(R.id.icon);
            viewHolder.name = (TextView) v.findViewById(R.id.name);

            v.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) v.getTag();
        }

        final ApplicationModel resolveInfo = getItem(position);

        if (resolveInfo != null && viewHolder != null) {
            viewHolder.icon.setContentDescription(resolveInfo.getLabel());
            viewHolder.icon.setImageDrawable(resolveInfo.getIcon());
            viewHolder.name.setText(resolveInfo.getLabel());
        }

        return v;
    }

    /**
     * View holder class.
     */
    static class ViewHolder {
        /** The view for the icon. */
        private ImageView icon;
        /** The view for the label. */
        private TextView name;
    }
}
