# JakeWhartonRepos
🎯 JakeWhartonRepos consider a POC with Kotlin, MVVM applying clean architecture, modern android stack.

:point_right: Clean Architecture:
-----------------
<div align="center">
<img src="https://user-images.githubusercontent.com/20733292/191065631-db93241e-db85-4b52-a247-44abd7825be8.png">
</div>

:point_right: What's JakeWharton Repos POC Achieve ? :
-----------------
- Caching concept Applied.
- Persistence data stored for Repo items.
- Android Architecture Components.
- Separation of concerns.
- App Test-ability.
- Loose Coupling.
- Applying SOLID principles.

:point_right: Tech Stack :
-----------------
- [Kotlin](https://kotlinlang.org/) - First class and official programming language for Android development.
- [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html) and [Flow](https://kotlinlang.org/docs/reference/coroutines/flow.html#asynchronous-flow) - Official Kotlin's tooling for performing asynchronous work.
- [MVVM Architecture](https://developer.android.com/jetpack/guide) - Official recommended architecture for building robust, Repoion-quality apps.
- [Navigation component](https://developer.android.com/guide/navigation?gclid=CjwKCAjwpqCZBhAbEiwAa7pXeZjk0QE0wCj3xe9GKngJ9UurROkHznEj2I_mT6hT1dmTUm95WmVONBoCeQ8QAvD_BwE&gclsrc=aw.ds) - navigation graph for navigating and replacing screens/fragments
- [Android Jetpack](https://developer.android.com/jetpack) - Jetpack is a suite of libraries to help developers build state-of-the-art applications.
- [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - The ViewModel is designed to store and manage UI-related data in a lifecycle conscious way.
- [StateFlow](https://developer.android.com/kotlin/flow/stateflow-and-sharedflow#stateflow) - StateFlow is a state-holder observable flow that emits the current and new state updates to its collectors.
- [Room DB] - The Room library provides an abstraction layer over SQLite to allow for more robust database access.
- [Dagger Hilt](https://developer.android.com/training/dependency-injection/hilt-android) - Hilt is a dependency injection library for Android.
- [OkHttp](https://github.com/square/okhttp) - An HTTP client for making network calls.
- [Retrofit](https://github.com/square/retrofit) - A library for building REST API clients.
- [KotlinX Serialization](https://github.com/Kotlin/kotlinx.serialization) - A multiplatform Kotlin serialization library.
- [UseCases]
- [mapperPattern] -  to isolate the logic and making it easier to test and reuse the conversion logic in other classes if necessary.


:point_right: Maybe added based on used case needed  and requirements:
-----------
- [ ] Apply buildSrc + Kotlin DSL for better dependency management.
- [ ] Add unit tests.
- [ ] Apply Modularization.
- [ ] Apply ktlint for checking code style.
- [ ] Apply CI|CD and automate some tasks using github actions.
- [ ] Use git hooks to automate code checking and styling before any new commit.
- [ ] Handle Different Build Variants.
- [ ] Use Data Store as a local DB.
- [ ] Apply SafetyNet for protection against security threats.
- [ ] Apply ssl certificate concept.

:point_right: You can also check my repo for advanced modularization and fully-fledged architecture:
-----------
- [InstaCrypto](https://github.com/Ahabdelhak/InstaCrypto)
