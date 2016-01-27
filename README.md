# Essential Launcher

[![Build Status](https://travis-ci.org/clemensbartz/essential-launcher.svg?branch=release%2Fv1.0)](https://travis-ci.org/clemensbartz/essential-launcher)
[![Codacy Badge](https://api.codacy.com/project/badge/grade/1e17dc4e83d748a7bf35231ed7fa9528)](https://www.codacy.com/app/clemens-bartz/essential-launcher)

## Overview

Essential Launcher is a small launcher for Android. It provides a minimum of functionality.

## Features

- Small:
    - <50 KB APK size (without [Minify](http://developer.android.com/tools/help/proguard.html))
    - <150 KB application size on a device
- App drawer to launch, uninstall, and show information about that app.
- Home screen with ability to add a widget.
- Dock with four frequently used applications.
- Transparent background.
- LTR as well as RTL support.
- Accessibility enabled.

## Development

If you want to support development, please make sure:

- You use Android Studio or IntelliJ IDEA.
- You must not enable minify.
- You use SDK Version >= 21.
- You use Java >= 7.
- The maximum size for the APK is 50 KB.
- You add your own `local.properties` file pointing to your android sdk: `sdk.dir=/opt/android-sdk-linux/`

## Not yet supported features

- Widgets are not automatically advanced.
- Filter in Drawer

## License

Copyright (C) 2015 Clemens Bartz

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program.  If not, see <http://www.gnu.org/licenses/>.
