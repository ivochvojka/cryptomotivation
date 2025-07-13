# This is a Crypto Motivation Kotlin Multiplatform project targeting Android, iOS.

## The aim of this application is to enable basic operations with cryptocurrencies.

## Important information to build the application

* **coinlayer.api.key=** with corresponding API key must be added in local.properties.

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

### Previews

<img width="432" height="960" alt="cryptocurrencies" src="https://github.com/user-attachments/assets/cea2c74b-32c3-4a98-9413-ce44ed3448b1" />
<img width="432" height="960" alt="favorites" src="https://github.com/user-attachments/assets/531f8233-7f94-462d-b757-04aa3c79e64b" />
<img width="432" height="960" alt="Detail" src="https://github.com/user-attachments/assets/44e74bcb-44f6-4b0b-91e3-2c3a52717e6c" />
