
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
