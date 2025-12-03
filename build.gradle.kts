@file:Suppress("UnstableApiUsage")

interface Services {
    @get:Inject val problems: Problems
}

val problems = objects.newInstance(Services::class).problems

// Root groups
val rootVerification = ProblemGroup.create("verification", "Verification")
val rootDependencies = ProblemGroup.create("dependencies", "Dependencies")

val deprecationGroup = ProblemGroup.create("deprecation", "Deprecation")

// Compilation groups

val dependencyVariantResolutionGroup = ProblemGroup.create("dependency-variant-resolution", "Dependency variant resolution")

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

// 1. Compilation: Java: Unused variable
val compilationGroup = ProblemGroup.create("compilation", "compilation problems")
val javaGroup = ProblemGroup.create("java", "Java", compilationGroup)
problems.reporter.report(
    ProblemId.create("unused-variable", "Unused variable", javaGroup)
) {
    contextualLabel("Variable 'x' is never used")
    solution("Remove the unused variable")
    lineInFileLocation("src/main/java/com/example/MyClass.java", 42, 8)
}

// 2. Validation: Property validation: Cannot use Optional on primitive types
val validationGroup = ProblemGroup.create("validation", "Validation")
val propertyValidationGroup = ProblemGroup.create("property-validation", "Gradle property validation", validationGroup)
problems.reporter.report(
    ProblemId.create("cannot-use-optional-on-primitive-types", "Property should be annotated with @Optional", propertyValidationGroup)
) {
    contextualLabel("Property 'myProperty' of primitive type should be annotated with @Optional")
    solution("Add @Optional annotation to the property")
    lineInFileLocation("src/main/java/com/example/MyTask.java", 15, 4)
}

// 3. Dependency version catalog: Alias not finished
val dependencyVersionCatalogGroup = ProblemGroup.create("dependency-version-catalog", "version catalog")
problems.reporter.report(
    ProblemId.create("alias-not-finished", "Dependency alias builder was not finished", dependencyVersionCatalogGroup)
) {
    contextualLabel("Dependency alias 'myLib' was not finished with a version or reference")
    solution("Call .version() or .versionRef() on the alias builder")
    lineInFileLocation("gradle/libs.versions.toml", 23, 0)
}

// 4. Task selection: Ambiguous matches
val taskSelectionGroup = ProblemGroup.create("task-selection", "Task selection")
problems.reporter.report(
    ProblemId.create("ambiguous-matches", "Ambiguous task matches", taskSelectionGroup)
) {
    contextualLabel("Task name 'test' matches multiple tasks: ':test', ':integrationTest'")
    solution("Use the full task path to disambiguate")
}

// 5. Validation: Configuration cache: Invocation of Task.project at execution time is unsupported
val configurationCacheGroup = ProblemGroup.create("configuration-cache", "Configuration cache validation", validationGroup)
problems.reporter.report(
    ProblemId.create("invocation-of-task-project-at-execution-time-is-unsupported", "Invocation of Task.project at execution time is unsupported", configurationCacheGroup)
) {
    contextualLabel("Task.project was accessed at execution time")
    solution("Access project properties at configuration time instead")
    lineInFileLocation("build.gradle.kts", 78, 12)
}

// 6. Dependency variant resolution: Unknown artifact selection failure
problems.reporter.report(
    ProblemId.create("unknown-artifact-selection-failure", "Unknown artifact selection failure", dependencyVariantResolutionGroup)
) {
    contextualLabel("Failed to resolve artifact for dependency 'com.example:library:1.0'")
    solution("Check that the dependency exists and the repository is configured correctly")
    lineInFileLocation("build.gradle.kts", 45, 4)
}

// 7. Deprecation: Executing Gradle on JVM versions and lower
problems.reporter.report(
    ProblemId.create("executing-gradle-on-jvm-versions-and-lower", "Executing Gradle on JVM versions and lower is deprecated", deprecationGroup)
) {
    contextualLabel("Executing Gradle on JVM 8 is deprecated")
    solution("Update to JVM 11 or higher")
}
