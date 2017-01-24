Orion - Changelog
=================

Unreleased
----------
(2017-01-20 11:39:05 +0530	) removed thread sleep from verification orchestrator
 removed thread sleep and added asynch method call and return
feature object to wait untill the processing is completed

 (Sasitha Gunadasa)(sasithag@zone24x7.com)
(2017-01-19 13:06:37 +0530	) Accepting hermes agent spring context file name as a java system veriable
 -DcontextFileName="applicationContext.xml" can be used to specify the file name of the
application context file.

 (Sasitha Gunadasa)(sasithag@zone24x7.com)
(2017-01-19 12:29:11 +0530	) add hermese deployment scripts
 
 (Sasitha Gunadasa)(sasithag@zone24x7.com)
(2017-01-18 18:44:48 +0530	) Merge branch 'sonar-issue-fixes' into 'develop'
 [ci-skip] Sonar issue fixes



See merge request !38
 (Sasitha Gunadasa)(sasithag@zone24x7.com)
(2017-01-18 18:43:48 +0530	) Merge branch 'develop' of git.zone24x7.lk:IDAPI/IDAPI into sonar-issue-fixes
 
 (Sasitha Gunadasa)(sasithag@zone24x7.com)
(2017-01-18 18:43:40 +0530	) [ci-skip] build error fixes in test classes
 
 (Sasitha Gunadasa)(sasithag@zone24x7.com)
(2017-01-18 18:32:42 +0530	) Merge branch 'sonar-issue-fixes' into 'develop'
 [ci-skip] Sonar issue fixes



See merge request !37
 (Sasitha Gunadasa)(sasithag@zone24x7.com)
(2017-01-18 18:31:49 +0530	) Merge branch 'develop' of git.zone24x7.lk:IDAPI/IDAPI into sonar-issue-fixes
 
 (Sasitha Gunadasa)(sasithag@zone24x7.com)
(2017-01-18 18:31:41 +0530	) [ci-skip] sonar code smell fixes
 
 (Sasitha Gunadasa)(sasithag@zone24x7.com)
(2017-01-18 18:00:44 +0530	) Merge branch 'sonar-issue-fixes' into 'develop'
 Sonar issue fixes



See merge request !36
 (Sasitha Gunadasa)(sasithag@zone24x7.com)
(2017-01-18 18:00:17 +0530	) Merge branch 'develop' of git.zone24x7.lk:IDAPI/IDAPI into sonar-issue-fixes
 
 (Sasitha Gunadasa)(sasithag@zone24x7.com)
(2017-01-18 17:59:11 +0530	) Sonar defect fixes
 
 (Sasitha Gunadasa)(sasithag@zone24x7.com)
(2017-01-18 17:23:56 +0530	) Merge branch 'sonar-issue-fixes' into 'develop'
 [ci-skip] Sonar issue fixes



See merge request !35
 (Sasitha Gunadasa)(sasithag@zone24x7.com)
(2017-01-18 17:22:53 +0530	) Merge branch 'develop' of git.zone24x7.lk:IDAPI/IDAPI into sonar-issue-fixes
 
 (Sasitha Gunadasa)(sasithag@zone24x7.com)
(2017-01-18 17:22:21 +0530	) [ci-skip] fixed build errors in test cases
 
 (Sasitha Gunadasa)(sasithag@zone24x7.com)
(2017-01-18 17:08:01 +0530	) Merge branch 'sonar-issue-fixes' into 'develop'
 Sonar issue fixes

Fixed sonar issues in document verification module

See merge request !34
 (Sasitha Gunadasa)(sasithag@zone24x7.com)
(2017-01-18 17:06:06 +0530	) Sonar issue fixes
 Fixed sonar issues in document verification module

 (Sasitha Gunadasa)(sasithag@zone24x7.com)
(2017-01-18 13:06:22 +0530	) [ci-skip] updated changelog files
 
 (Sasitha Gunadasa)(sasithag@zone24x7.com)

1.0.1-0.0.2 (2017-01-18 13:00:02 +0530)
---------------------------------------
(2017-01-18 13:00:02 +0530	) Update gender verification to handle null values
 Null pointer exeption was occured if there are no value was there for the gender value

 (Sasitha Gunadasa)(sasithag@zone24x7.com)
(2017-01-16 12:48:36 +0530	) [ci-skip] updated changelog
 
 (Sasitha Gunadasa)(sasithag@zone24x7.com)

1.0.1-0.0.1 (2017-01-15 13:05:05 +0530)
---------------------------------------
(2017-01-15 13:05:05 +0530	) Taking license handler implementation to a service layer class
 This class will be handling the license of a client based on the result of the extranal processing service.

 (Sasitha Gunadasa)(sasithag@zone24x7.com)
(2017-01-15 12:19:08 +0530	) Enable gender verification with single document
 Gender verificaiton can be done with a single document. If gender field could be found
only in one document provided, the validation will pass adding gender of that document
to the remarks. However if more than one document contains a gender field all must match
else the validation will fail.

 (Sasitha Gunadasa)(sasithag@zone24x7.com)
(2017-01-15 11:48:31 +0530	) updated change log files
 
 (Sasitha Gunadasa)(sasithag@zone24x7.com)

1.0.1-0.0.0 (2017-01-15 11:41:20 +0530)
---------------------------------------
(2017-01-15 11:41:20 +0530	) First change log files html and md
 First change log files were created. The html file will be added as a custom report
to jenkins and the md file will be refered in the README.md file

 (Sasitha Gunadasa)(sasithag@zone24x7.com)
