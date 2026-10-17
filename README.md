# My Recipe Library

My Recipe Library is an Android application built with Kotlin and Jetpack Compose. It displays a small collection of recipes and allows users to add new recipes by entering the recipe name, category, difficulty, and ingredients. Each recipe is shown with a difficulty message to make the information easy to understand.

## Instructions for Build and Use

Steps to build and/or run the software:

1. Open the project folder in Android Studio and allow Gradle to finish syncing the project.
2. Connect an Android device with developer options enabled or start an Android Emulator using API 24 or higher.
3. Select the `app` run configuration and click the Run button, or run `./gradlew.bat assembleDebug` from the project folder to build the debug APK.

Instructions for using the software:

1. Launch the app to view the starter recipes, including Tacos and Pancakes.
2. Enter a recipe name and category, then optionally enter a difficulty and a comma-separated list of ingredients.
3. Select Save Recipe to add the recipe to the list; the form clears after a successful save.

## Development Environment

To recreate the development environment, you need the following software and/or libraries with the specified versions:

* Android Studio with Android SDK support
* JDK 11 or newer, Kotlin 2.2.10, and Gradle 9.6.0
* Android SDK with compileSdk 37 and an emulator or device running Android API 24 or higher

## Useful Websites to Learn More

I found these websites useful in developing this software:

* [Jetpack Compose Documentation](https://developer.android.com/jetpack/compose)
* [Android Developers: Build Your First Android App](https://developer.android.com/codelabs/basic-android-kotlin-compose)
* [Kotlin Documentation](https://kotlinlang.org/docs/home.html)

## Future Work

The following items I plan to fix, improve, and/or add to this project in the future:

* [ ] Add persistent storage so recipes remain available after the app is closed.
* [ ] Add editing and deleting actions for saved recipes.
* [ ] Add recipe search, category filtering, and validation for difficulty values and ingredients.
