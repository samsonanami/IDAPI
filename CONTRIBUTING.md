# IDAPI (Orion)
## Welcome
Welcome to the **ORION** development circle. Please spend few minutes of your precious time to read this contribution 
guide. Over the years we have seen many projects created by many engineers. Those talented engineers indeed made those 
projects a success without a doubt, and we have our utmost hopes on you that you will do your very best to make this 
project a success. While having no doubt on the talent of our engineers, we noticed that some minor differences in the 
way which we do certain things have affected the projects over the years.

For an example take the ***commit messages***. In the world of software engineering, we highly value the commit message. 
It's not just a simple string rather it contain a great deal of information about what you have done. Those messages 
will be there forever even though you are not engaged with the project anymore. It tells the story of how you managed 
things during your days and it will help others to understand what you have created. Though this is a valuable piece 
of information lot of engineers tend to miss use it. Over the years it will be nearly impossible for someone to 
understand what was happened by looking at those messages. 

Because of this and so many other reasons we decided that we should bring all the engineers who work on a project 
to a common ground. Hence we derived this contribution guide. Now you have seen why we have put so much effort into 
this please do read the rest of this guide before you start your journey around this project.

## Requirements and Design

Understanding requirements and design of the project is a must. It's not just for this project but to all the project 
you will ever work on. These two documents will describe what is the actual problem that we are going to solve through
this solution and how we are going to solve it. 

**SRS** or **Software Requirement Specification** for this project can be found 
[here](https://docs.zone24x7.lk/sites/IDAPI/Shared%20Documents/02%20Requirements/IDAPI-SRS.docx). 

**DS** or **Design Specification** for this project can be found 
[here]()

If you are not granted permissions to view any of the above documents please contact your project manger and ask for 
permissions. It is a must that you read those documents and have a good understand about what are you going to do.

## Using GIT

**GIT** is the version control system we are using. If you are not familiarised with git and if you are not familiarised
with version controlling don't worry much about it. There are many good articles and blog posts which explain what are 
these concepts and why we use them. To get started with just read 
[this awesome article](https://www.atlassian.com/git/tutorials/what-is-version-control) by 
[*atlassian*](https://www.atlassian.com/). familiarised around git and confident that you can manage it 
feel free to skp this but if you have some time read those articles.

[**Git Flow**](https://www.atlassian.com/git/tutorials/comparing-workflows/gitflow-workflow) is the workflow that we
have selected to go with from many other possible workflow. Inorder to keep everyone in the same page it is a must that 
you read about git flow and understand it. If you have any doubts about the workflow or any problem on how to use it, we
urge you to sort them out before moving ahead with your work.

### Creating feature branches.

Since you have a good understanding about git flow, please pay attention to following things when you are creating new 
*feature branches* 

* Try to be informative as possible when selecting a name to the feature branch
* You can push breaking changes to your remote feature branch but **do not**
 merge it with the `develop` or `master` branches.
* Once you are done implementing `do not` right away push your changes to `develop`
branch rather send a `merge request` from the **gitlab** web interface. This will
give a chance for a peer developer to have a simple peer review on the features
developed.
* When merging a feature branch with `develop` branch always **delete** the
feature branch after merging with the `develop`

`git branch -b <<Feature>> develop`

### Commits and commit messages

As we described at the very beginning commit messages will tell the tale of your story around the project. 
It is the place where you can tell others what changes you have made and what are the reasons that forced you to do so.

However, many of us miss use it making the commit message just another worthless string. To maintain the clarity on 
the changes happened in the project we urge you to keep a keen eye on your commit messages.

The way you commit your changes also matters. Most of the times engineers tend to keep all the changes to himself till 
the very last moment then commit everything at once. This is a bad practice indeed.

Please pay your attention to these points when you are making commits and writing commit messages.

* Take updates from `remote` as often as you can *at least each day, before you
 start development*
* Commit with small changes. Don't wait till the end to commit with large
number of changes rather commit with small changes, With each commit take
an update from `remote`
* Be descriptive about the change that you have made in your *commit message*. This might seems a hart thing to do 
when you have lot of changes to be committed. However, if you break your commits to small changes this will be an easy 
task.

### Changelog

We will be generating a change log in each build. This change log will be generated from the commit messages that you
have provided. However if you wishes to keep a specific commit out of from the *changelog* you can start each line in 
your commit message with `[ci skip]` string.

 
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


## Project Sub-Modules
**Orion** has the following project sub module structure

```
├── DataTransferObjects
│   
├── Deployment
│   
├── DocumentVerification
│   
├── ExternalFiles
│   └── DatabaseScripts
│       
├── HermeseAgent
│   
├── JobMessagingChannelHandler
│   
├── OrionAPI
│   
├── OrionAuthAPI
│  
├── OrionDataAbstraction
│  
├── SCRIPTS
│   
├── SERVER_CONFIGS
│   
├── SERVER_KEYS
│   
├── Services
│   ├── APIServices
│   │   
│   ├── CoreServices
│   │   
│   └── HermesAgentServices
│      
     
```
##### Orion
This is the root project. All the sub modules are included to this root project.

##### OrionDataAbstraction
This sub module will contain all the components related to the data abstraction
including all the *Entity* classes generated.

