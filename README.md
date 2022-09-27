# Hotpots
[![Kotlin Version](https://img.shields.io/badge/Kotlin-1.7.x-blue.svg)](https://kotlinlang.org)
[![AGP](https://img.shields.io/badge/AGP-7.x-blue?style=flat)](https://developer.android.com/studio/releases/gradle-plugin)
[![Gradle](https://img.shields.io/badge/Gradle-7.x-blue?style=flat)](https://gradle.org)

> Sample work with `GoogleMaps` and `ClusterManager`

## Application Scope

The `Hotpots` displays information about WiFi points on the map near you. 

You can select one of five categories: `home, gym, coffee, mall, or office`. 

![Image1](/images/image1.jpg "Image1")

We have extensive data with over 1 million WiFi(`assets/hotpots.csv`) points after the application starts, the background process with push notification will start to load all data to DB. If the process ends push notification will be closed. That means all data was transferred to DB and we can enjoy application with the actual data.

![Image2](/images/image2.jpg "Image2")

## Tech-Stack
- Tech-stack
  - Kotlin
  - Google Maps
  - Coroutines
  - Jetpack Compose
  - Jetpack ViewModel
  - Jetpack Room
  - Jetpack WorkManager
  - Koin
- Modern Architecture
  - Clean Architecture
  - Single activity architecture
  - MVI(presentation layer)