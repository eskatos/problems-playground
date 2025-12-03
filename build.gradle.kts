
interface Services {
    @get:Inject val problems: Problems
}

val problems = objects.newInstance(Services::class).problems

val rootVerification = ProblemGroup.create("verification", "Verification")
val rootDependencies = ProblemGroup.create("dependencies", "Dependencies")


problems.reporter.report(
    ProblemId.create("foo", "Some verif", rootVerification)
) {
    contextualLabel("This is wrong")
}

problems.reporter.report(
    ProblemId.create("foo", "Some dep", rootDependencies)
) {
    contextualLabel("This is not a dependency")
}

//// Compilation

// Java compilation - warning related to code being compiled

// Java compilation - error related to code being compiled

// Java compilation - compiler configured to use deprecated feature

// Java compilation - compiler cannot be invoked (binary not found, process segfaults,etc.)

// Kotlin compilation - warning related to code being compiled

// Kotlin compilation - error related to code being compiled

// Kotlin compilation - compiler configured to use deprecated feature

// Kotlin compilation - compiler cannot be invoked (binary not found, process segfaults,etc.)


//// Plugins

// Shadow plugin - cannot merge services files

// Version plugin (com.github.ben-manes.versions) unsupported/invalid version formats

//// Deprecation

// Gradle invoked with deprecated feature enabled

// build logic (build script, buildSrc, build-logic, local plugin, binary plugin) uses deprecated Gradle API

// build logic (build script, buildSrc, build-logic, local plugin, binary plugin) uses deprecated plugin API

// build logic (build script, buildSrc, build-logic, local plugin, binary plugin) uses plugin API that uses deprecated Gradle API

//// Incubation

// incubating Gradle features is being used

//// Dependency management

//// Publications

//// Signing

//// Static checkers and linting

// checkstyle

// codenarc error

// android linter reports

// pmd 

//// Testing

// Junit 4 test failure

// TestNG test failure

// Junit platform test failure

//// Packaging

