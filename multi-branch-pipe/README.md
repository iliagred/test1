# Prepare infrastructure
1. Deploy jenkins via docker compose
(make sure controller-agent setting is respected)
2. Install recommended plugins. Consider installing some other important plugins
like `AnsiColor` and `Dark Theme`. Restart Jenkins.
3. Enable 'Dark Theme' (search 'theme' under Manage Jenkins > Configure System)

# Create Multibranch Pipeline
1. Tab: General -> Section: Branch Sources -> Select *Git* (leave defaults)
2. Provide the URL of the repository. Scroll down
-> notice `Scan Multibranch Pipeline Triggers` (view help)
3. Click Save button and *surf* the scan log. What has happened?
4. Back to 'Multibranch Pipeline' item. Notice phrasing:
`Multibranch job = a folder of pipeline jobs`
5. Explore the repo: existing branch(es), Jenkinsfile `01_declarative.jenkins`.
Think about it..

# Point Jenkins to the Jenkinsfile
1. For now consider just `01_declarative.jenkins` (make it work)
2. Tab: General -> Section: Build Coniguration -> Provide the right path
(create and scan may work out as well)
3. Explore scan log. What has changed?
4. Back to the Multibranch Pipeline item -> Follow the branch -> Explore the
job. Notice `declarative` gives 'Checkout SCM' stage by default.
5. Take a look around: Console output (do we have expected text there?)

# Explore the mechanism
1. Commit something to the main branch. Rescan 'Multibranch Pipeline'.
Explore the scan log.
2. Check the build history. How is it affected?
3. Create and push another branch:

```
git checkout -b $BRANCH_NAME && git push --set-upstream origin $BRANCH_NAME
```
3. Rescan Multibranch Pipeline. What has happened?
4. Create branches (until all conditions are met)

# Scripted vs Declarative Pipelines
Syntax comparison from [jenkins.io](https://www.jenkins.io/doc/book/pipeline/syntax/#compare):
> When Jenkins Pipeline was first created, Groovy was selected as the foundation. Jenkins has long shipped with an embedded Groovy engine to provide advanced scripting capabilities for admins and users alike. Additionally, the implementors of Jenkins Pipeline found Groovy to be a solid foundation upon which to build what is now referred to as the "Scripted Pipeline" DSL.
> As it is a fully-featured programming environment, Scripted Pipeline offers a tremendous amount of flexibility and extensibility to Jenkins users. The Groovy learning-curve isn’t typically desirable for all members of a given team, so Declarative Pipeline was created to offer a simpler and more opinionated syntax for authoring Jenkins Pipeline.
> Both are fundamentally the same Pipeline sub-system underneath. They are both durable implementations of "Pipeline as code." They are both able to use steps built into Pipeline or provided by plugins. Both are able to utilize Shared Libraries
> Where they differ however is in syntax and flexibility. Declarative limits what is available to the user with a more strict and pre-defined structure, making it an ideal choice for simpler continuous delivery pipelines. Scripted provides very few limits, insofar that the only limits on structure and syntax tend to be defined by Groovy itself, rather than any Pipeline-specific systems, making it an ideal choice for power-users and those with more complex requirements. As the name implies, Declarative Pipeline encourages a declarative programming model. [3] Whereas Scripted Pipelines follow a more imperative programming model.
_Given article references wikipedia links on declarative/imperative programming, which are nice to surf as well._

It's [show](https://github.com/4crt/multi-branch-pipe/tree/main/show) time. Feel free to surf more there (compare [01_declarative.jenkins](https://github.com/4crt/multi-branch-pipe/blob/main/show/01_declarative.jenkins) and [02_scripted.jenkins](https://github.com/4crt/multi-branch-pipe/blob/main/show/02_scripted.jenkins))

# Complexity grows
1. From now on use the Jenkinsfile called [03_mix.jenkins](https://github.com/4crt/multi-branch-pipe/blob/main/show/03_mix.jenkins) or at least feed it to Jenkins
2. Think about options directive. Try to get the idea of what is happening there..
3. Notice syntax differences between the stages..
4. Run some tests (consider 3 branches: `main, dec-*, other` and modified README.md). Explore the behavior.
5. Modify pipeline inside any of existing branches. Notice effects on the other existing branches.

###### About the app from the given repo: this is a [start.spring.io](https://start.spring.io/) example (feel free to update it if needed)
###### Tidy up the repo when the exploration is completed
To delete branch:
```
git branch -d $BRANCH_NAME && git push -d origin $BRANCH_NAME
```

Use your own branch name instead of 'fix-01' :)
