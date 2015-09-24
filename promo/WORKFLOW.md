# Workflow

This is the [Launch Checklist](https://developer.android.com/distribute/tools/launch-checklist.html) for Essential Launcher:

## Test for Quality

- Test for handsets: [Core App Qualities](https://developer.android.com/distribute/essentials/quality/core.html)
- Test for tablets: [Tablet App Qualities](https://developer.android.com/distribute/essentials/quality/tablets.html)

## App's Content Rating

Determined content rating: Everyone

## Determine Country Distribution

[Localization Checklist](https://developer.android.com/distribute/tools/localization-checklist.html)

- App supports the following locales: EN, DE
- App description supports the following locales: EN, DE
- App supports the followings text flows: LTR, RTL

## Confirm the App's Overall Size
 
- App size does not exceed 50 KB

## Confirm the App's Platform and Screen Compatibility Ranges

- Min. SDK Version: 21
- Supported Screen sizes: all screens

## Decide Whether your App will be Free or Priced

- App will be free

## Consider using In-app Billing or Android Pay
 
- Considered, but declined

## Set Prices for your Products 

- Not applicable

## Prepare Promotional Graphics, Screenshots, and Videos

### Screenshots

- for Phone: 3 Screenshots
- for Tablet: 3 Screenshots
- for TV/Wear: not applicable

### High-res icon

- provided 512x512 icon as PNG

### Feature graphic

- provided a 1024x500 graphic as JPG

### Promo graphic, Banner asset, Promo video

- Not applicable

## Build and Upload the Release-ready APK

[Prepare for Release](https://developer.android.com/tools/publishing/preparing.html)

### Gathering Materials and Resources

- Cryptographic keys: gathered
- Application icons: gathered
- EULA: application is GPL-Licensed
- Choose a good package name: chosen
- Turn off logging and debugging: all Logs removed, imports optimized
- Clean up your project directories: cleaned
- Review and update your manifest and Gradle build settings
    - no permissions used
    - icon and label attribute set in build.gradle
    - versionCode and versionName set in build.grade
    - minSdkVersion, targetSdkVersion and compileSdkVersion set in build.gradle
- App compatibility
    - app tested for different screen sizes
    - app optimized for tablets
    - no support library used
- Update URLs for servers and services: not necessary
- Implement Licencing: not a paid application
- Building Your Application for Release: built