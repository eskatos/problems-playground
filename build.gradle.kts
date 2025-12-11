@file:Suppress("UnstableApiUsage")

interface Services {
    @get:Inject
    val problems: Problems
}

val problems = objects.newInstance(Services::class).problems

// ============================================================================
// ROOT GROUPS
// ============================================================================
val compilationGroup = ProblemGroup.create("compilation", "Compilation")
val verificationGroup = ProblemGroup.create("verification", "Verification")
val dependenciesGroup = ProblemGroup.create("dependencies", "Dependencies")
val configurationGroup = ProblemGroup.create("configuration", "Build Configuration")
val environmentGroup = ProblemGroup.create("environment", "Environment")
val deprecationGroup = ProblemGroup.create("deprecation", "Build Deprecation")
val invocationGroup = ProblemGroup.create("invocation", "Invocation")
val miscellaneousGroup = ProblemGroup.create("miscellaneous", "Miscellaneous")


// Sub groups
val javaGroup = ProblemGroup.create("java", "Java", compilationGroup)
val kotlinGroup = ProblemGroup.create("kotlin", "Kotlin", compilationGroup)
val cppGroup = ProblemGroup.create("cpp", "C++", compilationGroup)

val unitTestGroup = ProblemGroup.create("unit-test", "Unit Tests", verificationGroup)
val integrationTestGroup = ProblemGroup.create("integration-test", "Integration Tests", verificationGroup)
val checkstyleGroup = ProblemGroup.create("checkstyle", "Checkstyle", verificationGroup)
val spotbugsGroup = ProblemGroup.create("spotbugs", "SpotBugs", verificationGroup)
val codeCoverageGroup = ProblemGroup.create("code-coverage", "Code Coverage", verificationGroup)
val lintingGroup = ProblemGroup.create("linting", "Linting", verificationGroup)
val securityScanGroup = ProblemGroup.create("security-scan", "Security Scan", verificationGroup)

val dependencyResolutionGroup = ProblemGroup.create("resolution", "Dependency Resolution", dependenciesGroup)
val dependencyVerificationGroup = ProblemGroup.create("verification", "Dependency Verification", dependenciesGroup)
val versionCatalogGroup = ProblemGroup.create("version-catalog", "Version Catalog", dependenciesGroup)
val variantResolutionGroup = ProblemGroup.create("variant-resolution", "Variant Resolution", dependenciesGroup)

val buildScriptGroup = ProblemGroup.create("build-script", "Build Script", configurationGroup)
val propertyValidationGroup = ProblemGroup.create("property-validation", "Property Validation", configurationGroup)

val missingToolsGroup = ProblemGroup.create("jdk", "JDK", environmentGroup)
val networkGroup = ProblemGroup.create("network", "Network", environmentGroup)
val filesystemGroup = ProblemGroup.create("filesystem", "Filesystem", environmentGroup)


// ============================================================================
// COMPILATION PROBLEMS
// ============================================================================

//Compilation: Java: Unused variable
repeat(12) { idx ->
    problems.reporter.report(
        ProblemId.create("unused-variable", "Unused variable", javaGroup)
    ) {
        contextualLabel("Variable 'x$idx' is never used")
        solution("Remove the unused variable")
        lineInFileLocation("src/main/java/com/example/MyClass.java", 42 + idx, 8)
    }
}

problems.reporter.report(
    ProblemId.create("unused-variable", "Unused variable", javaGroup)
) {
    contextualLabel("Variable 'b' is never used")
    solution("Remove the unused variable")
    lineInFileLocation("src/main/java/com/example/MyOtherClass.java", 42, 8)
}

problems.reporter.report(
    ProblemId.create("incompatible-types", "Incompatible types", javaGroup)
) {
    contextualLabel("Incompatible types: String cannot be converted to int")
    solution("Change the type or convert the value appropriately")
    lineInFileLocation("src/main/java/com/example/Calculator.java", 15, 12)
}

// Compilation: Kotlin
problems.reporter.report(
    ProblemId.create("unresolved-reference", "Unresolved reference", kotlinGroup)
) {
    contextualLabel("Unresolved reference: myFunction")
    solution("Import the missing function or check the spelling")
    lineInFileLocation("src/main/kotlin/com/example/App.kt", 28, 4)
}

// Compilation: C++
problems.reporter.report(
    ProblemId.create("missing-header", "Missing header file", cppGroup)
) {
    contextualLabel("Fatal error: 'iostream' file not found")
    solution("Ensure the C++ standard library is properly configured")
    lineInFileLocation("src/main/cpp/main.cpp", 1, 0)
}

// ============================================================================
// VERIFICATION PROBLEMS
// ============================================================================

// Verification: Unit Tests
problems.reporter.report(
    ProblemId.create("test-failure", "Test failure", unitTestGroup)
) {
    contextualLabel("Test 'shouldCalculateCorrectly' failed: expected 42 but was 43")
    solution("Fix the test implementation or the code under test")
    lineInFileLocation("src/test/java/com/example/CalculatorTest.java", 35, 8)
}

// Verification: Integration Tests
problems.reporter.report(
    ProblemId.create("integration-test-timeout", "Integration test timeout", integrationTestGroup)
) {
    contextualLabel("Test 'shouldConnectToDatabase' timed out after 30 seconds")
    solution("Increase timeout or optimize the test")
    lineInFileLocation("src/test/java/com/example/DatabaseTest.java", 50, 4)
}

// Verification: Static Analysis (Checkstyle)

// should the tool name be in a group?
// is that already namespacing?
problems.reporter.report(
    ProblemId.create("missing-javadoc", "Missing Javadoc", checkstyleGroup)
) {
    contextualLabel("Missing Javadoc comment for public method 'calculate'")
    solution("Add Javadoc comment to the method")
    lineInFileLocation("src/main/java/com/example/Calculator.java", 20, 4)
}

// Verification: Static Analysis (SpotBugs)
problems.reporter.report(
    ProblemId.create("null-pointer-dereference", "Possible null pointer dereference", spotbugsGroup)
) {
    contextualLabel("Possible null pointer dereference in com.example.MyClass.process()")
    solution("Add null check before accessing the variable")
    lineInFileLocation("src/main/java/com/example/MyClass.java", 67, 12)
}

// Verification: Code Coverage
problems.reporter.report(
    ProblemId.create("coverage-threshold-not-met", "Coverage threshold not met", codeCoverageGroup)
) {
    contextualLabel("Code coverage is 65% but minimum required is 80%")
    solution("Add more tests to increase coverage")
}

// Verification: Linting
problems.reporter.report(
    ProblemId.create("unused-import", "Unused import", lintingGroup)
) {
    contextualLabel("Unused import: java.util.ArrayList")
    solution("Remove the unused import")
    lineInFileLocation("src/main/java/com/example/MyClass.java", 3, 0)
}

// Verification: Security Scan
problems.reporter.report(
    ProblemId.create("vulnerability-detected", "Security vulnerability detected", securityScanGroup)
) {
    contextualLabel("High severity vulnerability in dependency org.example:vulnerable-lib:1.0.0 (CVE-2023-12345)")
    solution("Update to version 1.0.1 or higher")
}

// ============================================================================
// DEPENDENCIES PROBLEMS
// ============================================================================

// Dependencies: Resolution
problems.reporter.report(
    ProblemId.create("artifact-not-found", "Artifact not found", dependencyResolutionGroup)
) {
    contextualLabel("Could not find com.example:missing-library:1.0.0")
    solution("Check that the artifact exists and the repository is configured correctly")
    lineInFileLocation("build.gradle.kts", 45, 4)
}

// Dependencies: Verification
problems.reporter.report(
    ProblemId.create("checksum-mismatch", "Checksum mismatch", dependencyVerificationGroup)
) {
    contextualLabel("Checksum verification failed for com.example:library:1.0.0")
    solution("Verify the artifact integrity or update verification metadata")
}

// Dependencies: Version Catalog
problems.reporter.report(
    ProblemId.create("alias-not-finished", "Dependency alias builder not finished", versionCatalogGroup)
) {
    contextualLabel("In version catalog libs, dependency alias builder 'myLib' was not finished")
    solution("Call `.version()` to give the alias a version")
    lineInFileLocation("gradle/libs.versions.toml", 23, 0)
}

// Dependencies: Variant Resolution
problems.reporter.report(
    ProblemId.create("no-matching-variant", "No matching variant found", variantResolutionGroup)
) {
    contextualLabel("No matching variant of org:test:1.0 was found.")
    solution("Check that the dependency provides compatible variants")
}

// ============================================================================
// CONFIGURATION PROBLEMS
// ============================================================================

// Configuration: Build Script
problems.reporter.report(
    ProblemId.create("invalid-syntax", "Script compilation error", buildScriptGroup)
) {
    contextualLabel("Unresolved reference foo")
    lineInFileLocation("build.gradle.kts", 18, 12)
}

// Configuration: Property Validation
problems.reporter.report(
    ProblemId.create(
        "cannot-use-optional-on-primitive-types",
        "Property should be annotated with @Optional",
        propertyValidationGroup
    )
) {
    contextualLabel("Property 'myProperty' of primitive type should be annotated with @Optional")
    solution("Add @Optional annotation to the property")
    lineInFileLocation("src/main/java/com/example/MyTask.java", 15, 4)
}

// Existing: Dependency version catalog: Alias not finished
problems.reporter.report(
    ProblemId.create("alias-not-finished", "Dependency alias builder was not finished", versionCatalogGroup)
) {
    contextualLabel("Dependency alias 'myLib' was not finished with a version or reference")
    solution("Call .version() or .versionRef() on the alias builder")
    lineInFileLocation("gradle/libs.versions.toml", 23, 0)
}

// Existing: Task selection: Ambiguous matches
problems.reporter.report(
    ProblemId.create("ambiguous-matches", "Ambiguous task matches", invocationGroup)
) {
    contextualLabel("Task name 'test' matches multiple tasks: ':test', ':integrationTest'")
    solution("Use the full task path to disambiguate")
}
problems.reporter.report(
    ProblemId.create("invalid-task-option-value", "Invalid task option value", invocationGroup)
) {
    contextualLabel("Task ':test' option 'tests' expects a test name glob")
    solution("Use a valid test name glob as the 'tests' option for the ':test' task")
}

// ============================================================================
// ENVIRONMENT PROBLEMS
// ============================================================================

// Environment: Missing Tools
problems.reporter.report(
    ProblemId.create(
        "jdk-not-found",
        "Could not execute build using connection to Gradle installation",
        missingToolsGroup
    )
) {
    contextualLabel(
        "Could not execute build using connection to Gradle installation '/Users/reinholddegenfellner/IdeaProjects/gradle-resilient/packaging/distributions-full/build/bin distribution'." +
                "Gradle requires JVM 17 or later to run. Your build is currently configured to use JVM 8."
    )
    solution("Install JDK 17 or configure the path to an existing installation")
}

// Environment: Network
problems.reporter.report(
    ProblemId.create("connection-timeout", "Connection timeout", networkGroup)
) {
    contextualLabel("Connection timeout connecting to repo.maven.apache.org")
    solution("Check your network connection and firewall settings")
}

// Environment: Filesystem
problems.reporter.report(
    ProblemId.create("permission-denied", "Permission denied", filesystemGroup)
) {
    contextualLabel("Permission denied writing to /opt/app/output")
    solution("Ensure the user has write permissions to the directory")
}

// ============================================================================
// DEPRECATION PROBLEMS
// ============================================================================

problems.reporter.report(
    ProblemId.create("org.gradle.api.tasks.Wrapper#getAvailableDistributionTypes()", "org.gradle.api.tasks.Wrapper#getAvailableDistributionTypes() is deprecated", deprecationGroup)
) {
    contextualLabel("Using deprecated API: org.gradle.api.tasks.Wrapper#getAvailableDistributionTypes() will be deprecated and removed in Gradle 10")
    solution("Use DistributionType#values() directly")
    stackLocation() // the problem is exactly at the place in the codebase the place where the report is reported
}

// Existing: Dependency variant resolution: Unknown artifact selection failure
problems.reporter.report(
    ProblemId.create(
        "unknown-artifact-selection-failure",
        "Unknown artifact selection failure",
        dependencyResolutionGroup
    )
) {
    contextualLabel("Failed to resolve artifact for dependency 'com.example:library:1.0'")
    solution("Check that the dependency exists and the repository is configured correctly")
    lineInFileLocation("build.gradle.kts", 45, 4)
    lineInFileLocation("build.gradle.kts", 23, 12)
}

// Existing Deprecation: Executing Gradle on JVM versions and lower
problems.reporter.report(
    ProblemId.create(
        "executing-gradle-on-jvm-versions-and-lower",
        "Executing Gradle on JVM versions and lower is deprecated",
        deprecationGroup
    )
) {
    contextualLabel("Executing Gradle on JVM 8 is deprecated and will be removed in Gradle 10.0")
    solution("Update to JVM 11 or higher")
}

// ============================================================================
// MISCELLANEOUS PROBLEMS
// ============================================================================

problems.reporter.report(
    ProblemId.create("unknown-error", "Unknown error", miscellaneousGroup)
) {
    contextualLabel("An unexpected error occurred during build execution")
    solution("Check the build logs for more details")
}

problems.reporter.report(
    ProblemId.create("task-selection-ambiguous", "Ambiguous matches", invocationGroup)
) {
    contextualLabel("Cannot locate tasks that match ':ba' as task 'ba' is ambiguous in root project 'root'. Candidates are: 'bar', 'baz'.")
    solution("Use the full task path to disambiguate")
}

problems.reporter.report(
    ProblemId.create("custom-failure", "Custom failure", miscellaneousGroup)
) {
    contextualLabel("Custom build validation failed: Project must have a README.md file")
    solution("Add a README.md file to the project root")
}

