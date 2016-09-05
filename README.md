# IDAPI (Orion)
## Introduction
**Orion** is the nick name of this project the term **IDAPI** will be the
official name of the project *don't get confused with the name*.

Orion is a simple **REST API** which will aggregate lot of other services and
gives an one point of access to all the aggregated services. These aggregated
services will be in the domain of **KYC** or simply know as
**Know Your Customer**

All the documentation related to **Orion's** domain and what are the services
it will aggregate is defined in the [**SRS**](https://docs.zone24x7.lk/sites/IDAPI/Shared%20Documents/02%20Requirements/IDAPI-SRS.docx). and all details related to
design and other technical matters you can find in the [**Design Document**.](https://docs.zone24x7.lk/sites/IDAPI/Shared%20Documents/03%20Technical/Design%20Specification/IDAPI-DS.docx)
It is important you understand the content of the above documents before
you start the development.

This Document will only provide you some details regarding the practicle matters
which you will encounter during your implementation.

## Using GIT
All development should follow the [**Git Flow**](https://www.atlassian.com/git/tutorials/comparing-workflows/gitflow-workflow). `master` and `develop` branches
will be protected. Developers can create feature branches at their will

Please pay attention to the following when creating feature branches.
* Always create the feature branch form the current develop branch
* Try to be informative as possible when selecting a name to the feature branch
* You can push breaking changes to your remote feature branch but **do not**
 merge it with the `develop` or `master` branches.
* Once you are done implementing `do not` right away merge your changes to `develop`
branch rather send a `merge request` from the **gitlab** web interface. This will
give a chance for a peer developer to have a simple peer review on the features
developed.
* When merging a feature branch with `develop` branch always **delete** the
feature branch after merging with the `develop`

Also please pay attention to the following regarding the committing.

* Take updates from `remote` as often as you can *at least each day, before you
 start development*
* Commit with small changes. Don't wait till the end to commit with large
number of changes rather commit with small changes, With each commit take
an update from `remote`

`git branch -b <<Feature>> develop`

## Build and Dependency Management
**Orion** will use [**Gradle**](https://gradle.org/) as the dependency management and build tool.
The *build.gradle* file you find in the root of the project will be the root
build file and the sub modules will contain their own *build.gradle* file.

#### Dependency Management
If a new dependency needs to be added update the following section in the
*build.gradle* file
```
dependencies {
    testCompile group: 'junit', name: 'junit', version: '4.11'
}
```
#### Build Management and Versioning
**Orion** use the [**Semantic Versioning**](http://semver.org/). To make the build and version
management easy we will be using [**Semantic Version Plugging**](https://github.com/vivin/gradle-semantic-build-versioning). It will
expose number of task which can be used to build and automatically change the
version number based on the semantic version logic.

Also it will also create the proper `tag` for the release.

A gradle task may look like this

`gradle clean build release tag bumpMajor`

Go through the plugin documentation and semantic versioning before start
a release phase.

