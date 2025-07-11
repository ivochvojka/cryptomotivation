# This is a Crypto Motivation Kotlin Multiplatform project targeting Android, iOS.

## The aim of this application is to enable basic operations with cryptocurrencies.

### Project contains three modules

* `/composeApp` with Crypto specific features.
* `/core` as shared module with basic logic and utilities.
* `/iosApp` containing iOS application.

### Project is not complete. Still missing:

* Features to operate with cryptocurrencies.
* Tests (JUnit, Screenshot tests (like Paparazzi)).
* Correct Clean Architecture.
* Product flavours like release with Proguard etc.
* 3rd party integrations (Crashlytics, Analytics, etc.).
* Detekt, SonarQube etc.
* CI/CD workflows.
* Accessibility.

### Terrible things

* Themes - especially light color scheme.
* Fonts.
* UX must be improved.

### Problems

* AsyncImage on iOS - loads images on main thread - iOS app is stuck for several second at the beginning.