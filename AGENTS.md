# Agent Instructions for yam-api

## Build/Test Commands
- **Build all**: `./gradlew build`
- **Run all tests**: `./gradlew test`
- **Run JVM tests**: `./gradlew jvmTest`
- **Run Android tests**: `./gradlew testDebugUnitTest`
- **Run all target tests**: `./gradlew allTests`
- **Run single test**: `./gradlew test --tests "ClassName.testMethod"`

## Lint Commands
- **Run lint**: `./gradlew lint`
- **Fix lint issues**: `./gradlew lintFix`
- **Lint debug variant**: `./gradlew lintDebug`
- **Lint release variant**: `./gradlew lintRelease`

## Code Style Guidelines
- **Kotlin style**: Official Kotlin code style (`kotlin.code.style=official`)
- **Multiplatform**: Kotlin Multiplatform (JVM, Android, LinuxX64)
- **Imports**: Group by package, blank line between groups
- **Functions**: Use expression body when concise
- **Naming**: camelCase for functions/variables, PascalCase for classes
- **Documentation**: Russian KDoc comments for public APIs
- **Async**: Use coroutines with `suspend` functions
- **Serialization**: kotlinx.serialization with `@Serializable`
- **HTTP**: Ktor client with typed responses
- **Error handling**: Custom exceptions in `exceptions/` package
- **Testing**: `runTest` for coroutines, mock HTTP responses