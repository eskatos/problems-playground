
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

// Java compilation - warning related to code being compiled

// Java compilation - error related to code being compiled

// Java compilation - compiler configured to use deprecated feature

// Java compilation - compiler cannot be invoked (binary not found, process segfaults,etc.)

// Kotlin compilation - warning related to code being compiled

// Kotlin compilation - error related to code being compiled

// Kotlin compilation - compiler configured to use deprecated feature

// Kotlin compilation - compiler cannot be invoked (binary not found, process segfaults,etc.)


