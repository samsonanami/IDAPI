Orion - Changelog
=================

Unreleased
----------
add id doucment suport db script  (Sasitha Gunadasa)(sasitha@Sasitha-G.Zone24x7.lk)

Merge branch 'manual-verification-api-update' of git.zone24x7.lk:IDAPI/IDAPI into manual-verification-api-update  (Sasitha Gunadasa)(sasitha@Sasitha-G.Zone24x7.lk)

fixed facial validation not working issue  (Sasitha Gunadasa)(sasitha@Sasitha-G.Zone24x7.lk)

Added uniqueid_1.cgi perl script file  (Nagarjuna Avula)(navula@metanoiasolutions.net)

messages.properties file with the overrided default bad credentials error message  (Nagarjuna Avula)(navula@metanoiasolutions.net)

Overriding default 'bad credentials' error message to 'invalid credentials'  (Nagarjuna Avula)(navula@metanoiasolutions.net)

Added lock/unlock funcationality updates and lock expiration  (Nagarjuna Avula)(navula@metanoiasolutions.net)

update final verification status calculation logic  (Sasitha Gunadasa)(sasitha@Sasitha-G.Zone24x7.lk)

Re verification 1. Facial and liveness test re verification update 2. after re verification status change accordingly  (Sasitha Gunadasa)(sasitha@Sasitha-G.Zone24x7.lk)

added client name related changes  (Nagarjuna Avula)(navula@metanoiasolutions.net)

Merge branch 'manual-verification-api-update' of git.zone24x7.lk:IDAPI/IDAPI into manual-verification-api-update  Conflicts:
	HermeseAgent/src/main/java/com/fintech/orion/hermes/orchestrator/VerificationOrchestrator.java
	Services/HermesAgentServices/src/main/java/com/fintech/orion/common/service/VerificationRequestDetailService.java
	Services/HermesAgentServices/src/main/java/com/fintech/orion/common/service/VerificationRequestDetailServiceInterface.java
 (Sasitha Gunadasa)(sasitha@Sasitha-G.Zone24x7.lk)

re verification implementation  (Sasitha Gunadasa)(sasitha@Sasitha-G.Zone24x7.lk)

added new API realted to locked/unlocked status and changes in history API  (Nagarjuna Avula)(navula@metanoiasolutions.net)

added un committed changes  (Nagarjuna Avula)(navula@metanoiasolutions.net)

Modified update verification data API functionality and fixed minor issues in history API  (Nagarjuna Avula)(navula@metanoiasolutions.net)

updated the alter processing statuses database script with the latest status codes  (Nagarjuna Avula)(navula@metanoiasolutions.net)

code quality changes in the ProcessingRequestService file  (Nagarjuna Avula)(navula@metanoiasolutions.net)

Updated History, Image, MRZ Generation and Multiple Accounts Api's  (Nagarjuna Avula)(navula@metanoiasolutions.net)

updated update verification API and added get image API  (Nagarjuna Avula)(navula@metanoiasolutions.net)

Added functionality for Updating Processing Request ID data in processing_request table  (Nagarjuna Avula)(navula@metanoiasolutions.net)

update verification data API added  (Nagarjuna Avula)(navula@metanoiasolutions.net)

Merge branch 'master' of git.zone24x7.lk:IDAPI/IDAPI into develop  (Nagarjuna Avula)(navula@metanoiasolutions.net)

Merge branch 'multi-value-verification-support' into 'develop' Multi value verification support



See merge request !77 (Sasitha Gunadasa)(sasithag@zone24x7.com)

IDAPI support UK DL template 3 date format UK DL 3 has a date format MM-DD-YYYY which is not supported form the generic UK DL format DD/MM/YYYY
 (Sasitha Gunadasa)(sasithag@zone24x7.com)

Merge branch 'develop' of git.zone24x7.lk:IDAPI/IDAPI into multi-value-verification-support  (Sasitha Gunadasa)(sasithag@zone24x7.com)

Test case updated  (Sasitha Gunadasa)(sasithag@zone24x7.com)

1.0.3 (2017-07-19 10:20:52 +0530)
---------------------------------
Merge branch 'develop' into 'master' Develop



See merge request !76 (Sasitha Gunadasa)(sasithag@zone24x7.com)

Merge branch 'multi-value-verification-support' into 'develop' mrz check digit validation update



See merge request !75 (Sasitha Gunadasa)(sasithag@zone24x7.com)

mrz check digit validation update  (Sasitha Gunadasa)(sasithag@zone24x7.com)

Merge branch 'utility-bill-fullname-validation' into 'develop' Utility bill fullname validation



See merge request !74 (Sasitha Gunadasa)(sasithag@zone24x7.com)

Merge branch 'address-date-verification-change' into 'develop' issue date validation of utility bills



See merge request !73 (Sasitha Gunadasa)(sasithag@zone24x7.com)

null value fix in return response  (Thilina)(Thilinaj@zone24x7.com)

change validation logic to compare cross document full-name field  (Thilina)(Thilinaj@zone24x7.com)

return gender value on success verification  (Thilina)(Thilinaj@zone24x7.com)

return first successful value on first successful validation  (Thilina)(Thilinaj@zone24x7.com)

issue date validation of utility bills  (Thilina)(Thilinaj@zone24x7.com)

Merge branch 'multi-value-verification-support' into 'develop' Multi value verification support



See merge request !72 (Sasitha Gunadasa)(sasithag@zone24x7.com)

Merge branch 'develop' of git.zone24x7.lk:IDAPI/IDAPI into multi-value-verification-support  (Sasitha Gunadasa)(sasithag@zone24x7.com)

comparison value holder implemented  (Sasitha Gunadasa)(sasithag@zone24x7.com)

Merge branch 'multi-value-verification-support' into 'develop' Multi value verification support



See merge request !71 (Sasitha Gunadasa)(sasithag@zone24x7.com)

DL mrz decoding strategy handling invalid number formats  (Sasitha Gunadasa)(sasithag@zone24x7.com)

Merge branch 'develop' of git.zone24x7.lk:IDAPI/IDAPI into multi-value-verification-support  (Sasitha Gunadasa)(sasithag@zone24x7.com)

Merge branch 'dob-validation-fortwo-documents' into 'develop' Dob validation for two documents



See merge request !70 (Sasitha Gunadasa)(sasithag@zone24x7.com)

update DoB validation logic to support preprocess(PP) and NPP docs  (Thilina)(Thilinaj@zone24x7.com)

Merge branch 'develop' of git.zone24x7.lk:IDAPI/IDAPI into multi-value-verification-support  (Sasitha Gunadasa)(sasithag@zone24x7.com)

test classes update with new data structure from OCRAPI  (Sasitha Gunadasa)(sasithag@zone24x7.com)

data validation with multiple values return by abbyy  (Sasitha Gunadasa)(sasithag@zone24x7.com)

Merge branch 'cross-field-verification-of-two-documents' into 'develop' update data Comparator to cross validate fields of two documents



See merge request !69 (Sasitha Gunadasa)(sasithag@zone24x7.com)

change age limit validation to process preprocessed(PP) and NPP documents  (Thilina)(Thilinaj@zone24x7.com)

update data Comparator to cross validate fields of two documents  (Thilina)(Thilinaj@zone24x7.com)

mrz/date decoder configuration update with new template names  (Sasitha Gunadasa)(sasithag@zone24x7.com)

Surname sanitizer implemented  (Sasitha Gunadasa)(sasithag@zone24x7.com)

Configuration mismatch fixed Custom verifications aggregated to document level  (Sasitha Gunadasa)(sasithag@zone24x7.com)

fixed MRZ check digit validation  (Sasitha Gunadasa)(sasithag@zone24x7.com)

Merge branch 'json_structure_change' into 'develop' Json structure change



See merge request !68 (Sasitha Gunadasa)(sasithag@zone24x7.com)

Merge branch 'develop' of git.zone24x7.lk:IDAPI/IDAPI into json_structure_change  (Sasitha Gunadasa)(sasithag@zone24x7.com)

Handling possible null values for facialVerification status Facial verification status might be null based on the availability of the compression labs api. When calculating the final verification status this is handled.

If the facial verification or liveness test status is null wile a facial verification is requested it will be considered as a failure
 (Sasitha Gunadasa)(sasithag@zone24x7.com)

Merge branch 'json_structure_change' into 'develop' Json structure change



See merge request !67 (Sasitha Gunadasa)(sasithag@zone24x7.com)

Merge branch 'develop' of git.zone24x7.lk:IDAPI/IDAPI into json_structure_change  (Sasitha Gunadasa)(sasithag@zone24x7.com)

Merge branch 'feature/pdf-support' into 'develop' PDF Support Update



See merge request !66 (Sasitha Gunadasa)(sasithag@zone24x7.com)

IDAPI : Orion : Fixed Filename Return + Cleaned Unused Classes  (Tharindu)(tharindump@zone24x7.com)

Update README.md  (Tharindu Piyasiri)(tharindump@zone24x7.com)


Merge branch 'feature/pdf-support' into 'develop' Feature - PDF Support

Feature - PDF Support

See merge request !65 (Sasitha Gunadasa)(sasithag@zone24x7.com)

IDAPI : Orion : Fixed Multi Page PDF Exception  (Tharindu)(tharindump@zone24x7.com)

processing failure data displaying as a separate section details regarding processing failures are displayed as a separate section in the JSON output.
 (Sasitha Gunadasa)(sasithag@zone24x7.com)

IDAPI : Orion : Added PDF Support + Refactor Content Upload Endpoint  (Tharindu)(tharindump@zone24x7.com)

API JSON structure change Changing API output JSON format
 (Sasitha Gunadasa)(sasithag@zone24x7.com)

Merge branch 'ocr-result-decoding-factory' into 'develop' Ocr value translator

Set of translators to translate a given ocr value in to either a string or a date.

See merge request !64 (Sasitha Gunadasa)(sasithag@zone24x7.com)

Ocr value translator Set of translators to translate a given ocr value in to either a string or a date.
 (Sasitha Gunadasa)(sasithag@zone24x7.com)

data validations runs on a defined template category a given data validation will only run on a configured resource list.
 (Sasitha Gunadasa)(sasithag@zone24x7.com)

Template name ocr-extraction field database configurations  (Sasitha Gunadasa)(sasithag@zone24x7.com)

fix errors in AddressBuilderTest  (Sasitha Gunadasa)(sasithag@zone24x7.com)

Ireland document support Integration issue fixes
 (Sasitha Gunadasa)(sasithag@zone24x7.com)

Merge branch 'develop' of git.zone24x7.lk:IDAPI/IDAPI into develop  (Sasitha Gunadasa)(sasithag@zone24x7.com)

Merge branch 'feature/irish-passport-mrz' into 'develop' Feature/irish passport mrz

Irish passport mrz + Fixes

See merge request !63 (Sasitha Gunadasa)(sasithag@zone24x7.com)

Merge branch 'develop' into feature/irish-passport-mrz # Conflicts:
#	HermeseAgent/src/main/resources/document-verification-config.xml
 (Tharindu)(tharindump@zone24x7.com)

IDAPI : ICAO Standard MRZ Support, Ireland DL Support + Bug Fixes  (Tharindu)(tharindump@zone24x7.com)

fixed invalid bean reference errors  (Sasitha Gunadasa)(sasithag@zone24x7.com)

Adding Ireland Passport date decoding strategy  (Chamil Kirieldeniyage)(chamilk@zone24x7.com)

Fixed failing test cases  (Sasitha Gunadasa)(sasithag@zone24x7.com)

Unit Tests fixed except AddressValidationTest  (Chamil Kirieldeniyage)(chamilk@zone24x7.com)

Implement the template based date decoding  (Chamil Kirieldeniyage)(chamilk@zone24x7.com)

Implement the template based date decoding  (Chamil Kirieldeniyage)(chamilk@zone24x7.com)

Passport MRZ and VIZ expire date validation  (Sasitha Gunadasa)(sasithag@zone24x7.com)

Merge branch 'support-single-address-proof-document' into 'develop' Address validation support for single resource



See merge request !62 (Sasitha Gunadasa)(sasithag@zone24x7.com)

Address validation support for single resource  (Sasitha Gunadasa)(sasithag@zone24x7.com)

Merge branch 'api-update-to-get-histry-data' into 'develop' History data access API endpoint implementation

New API endpoint is exposed to access the history details.

See merge request !61 (Sasitha Gunadasa)(sasithag@zone24x7.com)

History data access update and default verification status Enable CORS for history data API request
Sorting history data by the ID
Setting default verification status to false.
 (Sasitha Gunadasa)(sasithag@zone24x7.com)

Merge branch 'api-update-to-get-histry-data' into 'develop' Api update to get histry data



See merge request !60 (Sasitha Gunadasa)(sasithag@zone24x7.com)

Merge branch 'develop' of git.zone24x7.lk:IDAPI/IDAPI into api-update-to-get-histry-data  (Sasitha Gunadasa)(sasithag@zone24x7.com)

sql script rename  (Sasitha Gunadasa)(sasithag@zone24x7.com)

Merge branch 'saving_processe_level_api_output' into 'develop' Save individual process level output to the database.

Save individual output received from different third party services in to the database

See merge request !59 (Sasitha Gunadasa)(sasithag@zone24x7.com)

Save individual process level output to the database. Save individual output received from different third party services in to the database
 (Sasitha Gunadasa)(sasithag@zone24x7.com)

Enable history data Enable history data to be accessed
 (Sasitha Gunadasa)(sasithag@zone24x7.com)

update contribution guide  (Sasitha Gunadasa)(sasithag@zone24x7.com)

Merge branch 'correction-to-invalid-ocr-readings' into 'develop' Add sanitizer configuration



See merge request !58 (Sasitha Gunadasa)(sasithag@zone24x7.com)

Add sanitizer configuration  (Sasitha Gunadasa)(sasithag@zone24x7.com)

Increase video upload limit Video upload limit is set to 40 MB
 (Sasitha Gunadasa)(sasithag@zone24x7.com)




See merge request !57 (Sasitha Gunadasa)(sasithag@zone24x7.com)

Merge branch 'develop' of git.zone24x7.lk:IDAPI/IDAPI into correction-to-invalid-ocr-readings  (Sasitha Gunadasa)(sasithag@zone24x7.com)

Add sanitizer logic to correct OCR results Sanitizer chain is introduced to correct any possible errors in the
result set received from OCR verification
 (Sasitha Gunadasa)(sasithag@zone24x7.com)

Add jacoco coverage report add jacoco plug-in to generate a code coverage report on build.
 (Sasitha Gunadasa)(sasithag@zone24x7.com)


Merge branch 'develop' of git.zone24x7.lk:IDAPI/IDAPI into correction-to-invalid-ocr-readings  (Sasitha Gunadasa)(sasithag@zone24x7.com)

Address line replace "." with "," OCR gives "." instead of ",". Add logic to correct that from Java side
 (Sasitha Gunadasa)(sasithag@zone24x7.com)

Update README.md  (Sasitha Gunadasa)(sasithag@zone24x7.com)

updated readme file  (Sasitha Gunadasa)(sasithag@zone24x7.com)



Add contribution guide  (Sasitha Gunadasa)(sasithag@zone24x7.com)



added custom validations 1. age limit validation 18-90 2. utility bill valid month limit 3 3. age at date of issue  (Sasitha Gunadasa)(sasithag@zone24x7.com)

compression labs multipart request issue fixed emphty files were sent to the compression labs api at the initial integration.
 (Sasitha Gunadasa)(sasithag@zone24x7.com)

add configuration key sql script  (Sasitha Gunadasa)(sasithag@zone24x7.com)

Merge branch 'compression-labs-integration' into 'develop' Compression labs integration



See merge request !56 (Sasitha Gunadasa)(sasithag@zone24x7.com)

sonar issue fixes  (Sasitha Gunadasa)(sasithag@zone24x7.com)

removed unused reference  (Sasitha Gunadasa)(sasithag@zone24x7.com)

async task excecutor configuration enable async task excecutor with xml configurations
 (Sasitha Gunadasa)(sasithag@zone24x7.com)

client level configuration provider implementation  (Sasitha Gunadasa)(sasithag@zone24x7.com)

compression labs integration compression labs v1 integration.
 (Sasitha Gunadasa)(sasithag@zone24x7.com)

Merge branch 'develop' of git.zone24x7.lk:IDAPI/IDAPI into compression-labs-integration  (Sasitha Gunadasa)(sasithag@zone24x7.com)

response processor update response processor implemented as a chain of commands
so that the response processing can be chained
 (Sasitha Gunadasa)(sasithag@zone24x7.com)

Merge branch 'qa-defect-fixes' into 'develop' Qa defect fixes



See merge request !55 (Sasitha Gunadasa)(sasithag@zone24x7.com)

Merge branch 'develop' of git.zone24x7.lk:IDAPI/IDAPI into qa-defect-fixes  (Sasitha Gunadasa)(sasithag@zone24x7.com)

stored procedure updates fixed issues reported in sotred procedures
 (Sasitha Gunadasa)(sasithag@zone24x7.com)

verification process facial verification new verification type for facial verification is added.
 (Sasitha Gunadasa)(sasithag@zone24x7.com)

Merge branch 'qa-defect-fixes' into 'develop' Qa defect fixes



See merge request !54 (Sasitha Gunadasa)(sasithag@zone24x7.com)

validating resource ownership resource ownership is validated when submitting a verification request.
 (Sasitha Gunadasa)(sasithag@zone24x7.com)

Merge branch 'develop' of git.zone24x7.lk:IDAPI/IDAPI into qa-defect-fixes  (Sasitha Gunadasa)(sasithag@zone24x7.com)

Merge branch 'mrz-decoding' into 'develop' Change passport config values for sex and surname



See merge request !53 (Sasitha Gunadasa)(sasithag@zone24x7.com)

resource can be only accessed by the uploaded user  (Sasitha Gunadasa)(sasithag@zone24x7.com)

Change passport config values for sex and surname  (Muditha Jayawardana)(MudithaJ@Zone24x7.lk)

Merge branch 'verification-implementation' into 'develop' Verification implementation



See merge request !52 (Sasitha Gunadasa)(sasithag@zone24x7.com)

Merge branch 'develop' of git.zone24x7.lk:IDAPI/IDAPI into verification-implementation  (Sasitha Gunadasa)(sasithag@zone24x7.com)

test file updated  (Sasitha Gunadasa)(sasithag@zone24x7.com)

Merge branch 'verification-implementation' into 'develop' Verification implementation



See merge request !51 (Sasitha Gunadasa)(sasithag@zone24x7.com)

passport mrz date of birth support date of birth decoded from passport mrz has a different
format. added new date decoding congigurations to decode
the date.
 (Sasitha Gunadasa)(sasithag@zone24x7.com)

Merge branch 'develop' of git.zone24x7.lk:IDAPI/IDAPI into verification-implementation  (Sasitha Gunadasa)(sasithag@zone24x7.com)

Update war_deployer.sh  (Dinuka Jayamaha)(dinukaj@zone24x7.com)

Update war_deployer.sh  (Dinuka Jayamaha)(dinukaj@zone24x7.com)

Merge branch 'develop' of git.zone24x7.lk:IDAPI/IDAPI into verification-implementation  (Sasitha Gunadasa)(sasithag@zone24x7.com)

Merge branch 'code-review-updates-by-muditha' into 'develop' Code review updates by muditha



See merge request !50 (Sasitha Gunadasa)(sasithag@zone24x7.com)

Correcting syntax error  (Muditha Jayawardana)(MudithaJ@Zone24x7.lk)

Merge branch 'develop' of git.zone24x7.lk:IDAPI/IDAPI into code-review-updates-by-muditha  (Muditha Jayawardana)(MudithaJ@Zone24x7.lk)

Merge branch 'develop' of git.zone24x7.lk:IDAPI/IDAPI into verification-implementation  (Sasitha Gunadasa)(sasithag@zone24x7.com)

validating mrz before decoding mrz are validated before decoding it to ensure the integrity of the mrz
 (Sasitha Gunadasa)(sasithag@zone24x7.com)

Update war_deployer.sh  (Dinuka Jayamaha)(dinukaj@zone24x7.com)

Update war_deployer.sh  (Dinuka Jayamaha)(dinukaj@zone24x7.com)

Update war_deployer.sh  (Dinuka Jayamaha)(dinukaj@zone24x7.com)

Update war_deployer.sh  (Dinuka Jayamaha)(dinukaj@zone24x7.com)

Update war_deployer.sh  (Dinuka Jayamaha)(dinukaj@zone24x7.com)

Update war_deployer.sh  (Dinuka Jayamaha)(dinukaj@zone24x7.com)

Update war_deployer.sh  (Dinuka Jayamaha)(dinukaj@zone24x7.com)

Update war_deployer.sh  (Dinuka Jayamaha)(dinukaj@zone24x7.com)

Update war_deployer.sh  (Dinuka Jayamaha)(dinukaj@zone24x7.com)

Update war_deployer.sh  (Dinuka Jayamaha)(dinukaj@zone24x7.com)

Update war_deployer.sh  (Dinuka Jayamaha)(dinukaj@zone24x7.com)

Delete spring-datasource.xml  (Dinuka Jayamaha)(dinukaj@zone24x7.com)

Delete spring-config.xml  (Dinuka Jayamaha)(dinukaj@zone24x7.com)

Delete spring-beans.xml  (Dinuka Jayamaha)(dinukaj@zone24x7.com)

Delete logback.xml  (Dinuka Jayamaha)(dinukaj@zone24x7.com)

Delete applicationContext.xml  (Dinuka Jayamaha)(dinukaj@zone24x7.com)

Delete Hermese.sh  (Dinuka Jayamaha)(dinukaj@zone24x7.com)

Upload new file  (Dinuka Jayamaha)(dinukaj@zone24x7.com)

Upload new file  (Dinuka Jayamaha)(dinukaj@zone24x7.com)

Upload new file  (Dinuka Jayamaha)(dinukaj@zone24x7.com)

Upload new file  (Dinuka Jayamaha)(dinukaj@zone24x7.com)

Upload new file  (Dinuka Jayamaha)(dinukaj@zone24x7.com)

Upload new file  (Dinuka Jayamaha)(dinukaj@zone24x7.com)

Add new directory  (Dinuka Jayamaha)(dinukaj@zone24x7.com)

Update war_deployer.sh  (Dinuka Jayamaha)(dinukaj@zone24x7.com)

Upload new file  (Dinuka Jayamaha)(dinukaj@zone24x7.com)

Update war_deployer.sh  (Dinuka Jayamaha)(dinukaj@zone24x7.com)

Update Hermese.sh  (Dinuka Jayamaha)(dinukaj@zone24x7.com)

Update war_deployer.sh  (Dinuka Jayamaha)(dinukaj@zone24x7.com)

Update war_deployer.sh  (Dinuka Jayamaha)(dinukaj@zone24x7.com)

Update war_deployer.sh  (Dinuka Jayamaha)(dinukaj@zone24x7.com)

Update war_deployer.sh  (Dinuka Jayamaha)(dinukaj@zone24x7.com)

Update war_deployer.sh  (Dinuka Jayamaha)(dinukaj@zone24x7.com)

Update Hermese.sh  (Dinuka Jayamaha)(dinukaj@zone24x7.com)

Update war_deployer.sh  (Dinuka Jayamaha)(dinukaj@zone24x7.com)

Merge branch 'verification-implementation' into 'develop' Verification implementation



See merge request !48 (Sasitha Gunadasa)(sasithag@zone24x7.com)

Merge branch 'develop' of git.zone24x7.lk:IDAPI/IDAPI into verification-implementation  (Sasitha Gunadasa)(sasithag@zone24x7.com)

test case update  (Sasitha Gunadasa)(sasithag@zone24x7.com)

Update war_deployer.sh  (Dinuka Jayamaha)(dinukaj@zone24x7.com)

Merge branch 'verification-implementation' into 'develop' Verification implementation



See merge request !47 (Sasitha Gunadasa)(sasithag@zone24x7.com)

Merge branch 'develop' of git.zone24x7.lk:IDAPI/IDAPI into verification-implementation  (Sasitha Gunadasa)(sasithag@zone24x7.com)

build error fix  (Sasitha Gunadasa)(sasithag@zone24x7.com)

Merge branch 'verification-implementation' into 'develop' Verification implementation



See merge request !46 (Sasitha Gunadasa)(sasithag@zone24x7.com)

updated method accessing date decoder after changing the date decoder  (Sasitha Gunadasa)(sasithag@zone24x7.com)

Merge branch 'develop' of git.zone24x7.lk:IDAPI/IDAPI into verification-implementation  (Sasitha Gunadasa)(sasithag@zone24x7.com)

Merge branch 'date-format-update' into 'develop' Date format update

Updated date decoder  to handle given dates based on regular expressions

See merge request !45 (Sasitha Gunadasa)(sasithag@zone24x7.com)

Merge branch 'develop' of git.zone24x7.lk:IDAPI/IDAPI into date-format-update  (Sasitha Gunadasa)(sasithag@zone24x7.com)

Merge branch 'develop' of git.zone24x7.lk:IDAPI/IDAPI into verification-implementation  (Sasitha Gunadasa)(sasithag@zone24x7.com)

VIZ and MRZ value validation for given set of ocr extraction fields VIZ (visual inspection zone) values
and MRZ (magnetic read zone) values will be compared in given documents
 (Sasitha Gunadasa)(sasithag@zone24x7.com)

Merge branch 'mrz-decoding' into 'develop' Add Sex decoding for DL and test

Decode sex from driving license MRZ

See merge request !44 (Sasitha Gunadasa)(sasithag@zone24x7.com)

Add Sex decoding for DL and test Decode sex from driving license MRZ  (Muditha Jayawardana)(MudithaJ@Zone24x7.lk)

Merge branch 'develop' of git.zone24x7.lk:IDAPI/IDAPI into code-review-updates-by-muditha  (Muditha Jayawardana)(MudithaJ@Zone24x7.lk)

Update war_deployer.sh  (Dinuka Jayamaha)(dinukaj@zone24x7.com)

Update war_deployer.sh  (Dinuka Jayamaha)(dinukaj@zone24x7.com)

Update war_deployer.sh  (Dinuka Jayamaha)(dinukaj@zone24x7.com)

Update war_deployer.sh  (Dinuka Jayamaha)(dinukaj@zone24x7.com)

Update war_deployer.sh  (Dinuka Jayamaha)(dinukaj@zone24x7.com)

Update war_deployer.sh  (Dinuka Jayamaha)(dinukaj@zone24x7.com)

Update war_deployer.sh  (Dinuka Jayamaha)(dinukaj@zone24x7.com)

Update war_deployer.sh  (Dinuka Jayamaha)(dinukaj@zone24x7.com)

Update war_deployer.sh  (Dinuka Jayamaha)(dinukaj@zone24x7.com)

Update war_deployer.sh  (Dinuka Jayamaha)(dinukaj@zone24x7.com)

Update war_deployer.sh  (Dinuka Jayamaha)(dinukaj@zone24x7.com)

Update war_deployer.sh  (Dinuka Jayamaha)(dinukaj@zone24x7.com)

Update war_deployer.sh  (Dinuka Jayamaha)(dinukaj@zone24x7.com)

Update war_deployer.sh  (Dinuka Jayamaha)(dinukaj@zone24x7.com)

Update war_deployer.sh  (Dinuka Jayamaha)(dinukaj@zone24x7.com)

Update war_deployer.sh  (Dinuka Jayamaha)(dinukaj@zone24x7.com)

Update war_deployer.sh  (Dinuka Jayamaha)(dinukaj@zone24x7.com)

Merge branch 'develop' of git.zone24x7.lk:IDAPI/IDAPI into develop  (Sasitha Gunadasa)(sasithag@zone24x7.com)

Hermes bsx get extraction path as an argument to the script  (Sasitha Gunadasa)(sasithag@zone24x7.com)

Update war_deployer.sh  (Dinuka Jayamaha)(dinukaj@zone24x7.com)

Update war_deployer.sh  (Dinuka Jayamaha)(dinukaj@zone24x7.com)

Update war_deployer.sh  (Dinuka Jayamaha)(dinukaj@zone24x7.com)

Update war_deployer.sh  (Dinuka Jayamaha)(dinukaj@zone24x7.com)

Update war_deployer.sh  (Dinuka Jayamaha)(dinukaj@zone24x7.com)

Update war_deployer.sh  (Dinuka Jayamaha)(dinukaj@zone24x7.com)

Update war_deployer.sh  (Dinuka Jayamaha)(dinukaj@zone24x7.com)

Update war_deployer.sh  (Dinuka Jayamaha)(dinukaj@zone24x7.com)

Upload new file  (Dinuka Jayamaha)(dinukaj@zone24x7.com)

Upload new file  (Dinuka Jayamaha)(dinukaj@zone24x7.com)

Upload new file  (Dinuka Jayamaha)(dinukaj@zone24x7.com)

Upload new file  (Dinuka Jayamaha)(dinukaj@zone24x7.com)

Update war_deployer.sh  (Dinuka Jayamaha)(dinukaj@zone24x7.com)

Update war_deployer.sh  (Dinuka Jayamaha)(dinukaj@zone24x7.com)

Upload new file  (Dinuka Jayamaha)(dinukaj@zone24x7.com)

Add new directory  (Dinuka Jayamaha)(dinukaj@zone24x7.com)

Update war_deployer.sh  (Dinuka Jayamaha)(dinukaj@zone24x7.com)

Update war_deployer.sh  (Dinuka Jayamaha)(dinukaj@zone24x7.com)

Update war_deployer.sh  (Dinuka Jayamaha)(dinukaj@zone24x7.com)

Update war_deployer.sh  (Dinuka Jayamaha)(dinukaj@zone24x7.com)

Update war_deployer.sh  (Dinuka Jayamaha)(dinukaj@zone24x7.com)

Update war_deployer.sh  (Dinuka Jayamaha)(dinukaj@zone24x7.com)

Update war_deployer.sh  (Dinuka Jayamaha)(dinukaj@zone24x7.com)

Update war_deployer.sh  (Dinuka Jayamaha)(dinukaj@zone24x7.com)

Update war_deployer.sh  (Dinuka Jayamaha)(dinukaj@zone24x7.com)

Update war_deployer.sh  (Dinuka Jayamaha)(dinukaj@zone24x7.com)

Update war_deployer.sh  (Dinuka Jayamaha)(dinukaj@zone24x7.com)

Update war_deployer.sh  (Dinuka Jayamaha)(dinukaj@zone24x7.com)

Update war_deployer.sh  (Dinuka Jayamaha)(dinukaj@zone24x7.com)

Update war_deployer.sh  (Dinuka Jayamaha)(dinukaj@zone24x7.com)

Update war_deployer.sh  (Dinuka Jayamaha)(dinukaj@zone24x7.com)

Update war_deployer.sh  (Dinuka Jayamaha)(dinukaj@zone24x7.com)

Update war_deployer.sh  (Dinuka Jayamaha)(dinukaj@zone24x7.com)

Update war_deployer.sh  (Dinuka Jayamaha)(dinukaj@zone24x7.com)

Update war_deployer.sh  (Dinuka Jayamaha)(dinukaj@zone24x7.com)

Update war_deployer.sh  (Dinuka Jayamaha)(dinukaj@zone24x7.com)

Update war_deployer.sh  (Dinuka Jayamaha)(dinukaj@zone24x7.com)

Update war_deployer.sh  (Dinuka Jayamaha)(dinukaj@zone24x7.com)

Update war_deployer.sh  (Dinuka Jayamaha)(dinukaj@zone24x7.com)

Update war_deployer.sh  (Dinuka Jayamaha)(dinukaj@zone24x7.com)

Update war_deployer.sh  (Dinuka Jayamaha)(dinukaj@zone24x7.com)

Update war_deployer.sh  (Dinuka Jayamaha)(dinukaj@zone24x7.com)

Update war_deployer.sh  (Dinuka Jayamaha)(dinukaj@zone24x7.com)

Update war_deployer.sh  (Dinuka Jayamaha)(dinukaj@zone24x7.com)

Update war_deployer.sh  (Dinuka Jayamaha)(dinukaj@zone24x7.com)

Update war_deployer.sh  (Dinuka Jayamaha)(dinukaj@zone24x7.com)

Update war_deployer.sh  (Dinuka Jayamaha)(dinukaj@zone24x7.com)

Update war_deployer.sh  (Dinuka Jayamaha)(dinukaj@zone24x7.com)

Update war_deployer.sh  (Dinuka Jayamaha)(dinukaj@zone24x7.com)

Update war_deployer.sh  (Dinuka Jayamaha)(dinukaj@zone24x7.com)

Update war_deployer.sh  (Dinuka Jayamaha)(dinukaj@zone24x7.com)

Update war_deployer.sh  (Dinuka Jayamaha)(dinukaj@zone24x7.com)

Update war_deployer.sh  (Dinuka Jayamaha)(dinukaj@zone24x7.com)

Update war_deployer.sh  (Dinuka Jayamaha)(dinukaj@zone24x7.com)

Merge branch 'develop' of git.zone24x7.lk:IDAPI/IDAPI into develop  (Sasitha Gunadasa)(sasithag@zone24x7.com)

console output after sotred procedure execution  (Sasitha Gunadasa)(sasithag@zone24x7.com)

Update deployer.sh  (Dinuka Jayamaha)(dinukaj@zone24x7.com)

Update deployer.sh  (Dinuka Jayamaha)(dinukaj@zone24x7.com)

Update SERVER_KEY.pem  (Dinuka Jayamaha)(dinukaj@zone24x7.com)

Update deployer.sh  (Dinuka Jayamaha)(dinukaj@zone24x7.com)

Update deployer.sh  (Dinuka Jayamaha)(dinukaj@zone24x7.com)

Update deployer.sh  (Dinuka Jayamaha)(dinukaj@zone24x7.com)

Update deployer.sh  (Dinuka Jayamaha)(dinukaj@zone24x7.com)

Update deployer.sh  (Dinuka Jayamaha)(dinukaj@zone24x7.com)

Update deployer.sh  (Dinuka Jayamaha)(dinukaj@zone24x7.com)

Update deployer.sh  (Dinuka Jayamaha)(dinukaj@zone24x7.com)

Update deployer.sh  (Dinuka Jayamaha)(dinukaj@zone24x7.com)

Update deployer.sh  (Dinuka Jayamaha)(dinukaj@zone24x7.com)

Update deployer.sh  (Dinuka Jayamaha)(dinukaj@zone24x7.com)

Merge branch 'flyway-migration-schema' into 'develop' flyway migration

database schema management will be done by flyway. initial scripts
are re arranged to support flyway migrations

See merge request !43 (Sasitha Gunadasa)(sasithag@zone24x7.com)

flyway migration database schema management will be done by flyway. initial scripts
are re arranged to support flyway migrations
 (Sasitha Gunadasa)(sasithag@zone24x7.com)

Merge branch 'sp_update' into 'develop' Sp update



See merge request !42 (Sasitha Gunadasa)(sasithag@zone24x7.com)

Merge branch 'develop' of git.zone24x7.lk:IDAPI/IDAPI into sp_update  (Sasitha Gunadasa)(sasithag@zone24x7.com)

stored procedures created to manipulate users and license  (Sasitha Gunadasa)(sasithag@zone24x7.com)

Update deployer.sh  (Dinuka Jayamaha)(dinukaj@zone24x7.com)

Update deployer.sh  (Dinuka Jayamaha)(dinukaj@zone24x7.com)

Upload new file  (Dinuka Jayamaha)(dinukaj@zone24x7.com)

Add new directory  (Dinuka Jayamaha)(dinukaj@zone24x7.com)

Upload new file  (Dinuka Jayamaha)(dinukaj@zone24x7.com)

Add new directory  (Dinuka Jayamaha)(dinukaj@zone24x7.com)

Merge branch 'develop' of git.zone24x7.lk:IDAPI/IDAPI into code-review-updates-by-muditha  (Muditha Jayawardana)(MudithaJ@Zone24x7.lk)

Merge branch 'develop' of git.zone24x7.lk:IDAPI/IDAPI into date-format-update  (Sasitha Gunadasa)(sasithag@zone24x7.com)

Date decoder change to accept different strategy to decode the date. Date decoder will decide based on the configurations which decoding strategy must be used
to decode a given date
 (Sasitha Gunadasa)(sasithag@zone24x7.com)

Updated changelog  (Jenkins)(Jenkins app@zone24x7.com)

Merge branch 'develop' of git.zone24x7.lk:IDAPI/IDAPI into code-review-updates-by-muditha  (Muditha Jayawardana)(MudithaJ@Zone24x7.lk)

fix Issue #29 [Practices] - Don't explicitly catch NullPointerExceptions  (Muditha Jayawardana)(MudithaJ@Zone24x7.lk)

Fixed Issue #31 [Practices] - Add Java Doc comments  (Muditha Jayawardana)(MudithaJ@Zone24x7.lk)

Fix Issue #35 [Practices] - Code Duplication and sonar issues  (Muditha Jayawardana)(MudithaJ@Zone24x7.lk)

Fix Issue #40 [Practices - Naming] - Avoid general naming like "doOperation"  (Muditha Jayawardana)(MudithaJ@Zone24x7.lk)

 Fixing Issue #28  Treat Booleans as Booleans  (Muditha Jayawardana)(MudithaJ@Zone24x7.lk)

1.0.2-0.0.0 (2017-01-30 16:32:19 +0530)
---------------------------------------
changed execution permissions  (Manthila Mendis)(manthila.mendis@kohls.com)

Merge branch 'code-review-updates-by-sasitha' into 'develop' Code review updates by sasitha



See merge request !39 (Sasitha Gunadasa)(sasithag@zone24x7.com)

BCrypt encoding enabled for passwords  (Sasitha Gunadasa)(sasithag@zone24x7.com)

removed thread sleep from verification orchestrator removed thread sleep and added asynch method call and return
feature object to wait untill the processing is completed
 (Sasitha Gunadasa)(sasithag@zone24x7.com)

Accepting hermes agent spring context file name as a java system veriable -DcontextFileName="applicationContext.xml" can be used to specify the file name of the
application context file.
 (Sasitha Gunadasa)(sasithag@zone24x7.com)

add hermese deployment scripts  (Sasitha Gunadasa)(sasithag@zone24x7.com)

Merge branch 'sonar-issue-fixes' into 'develop' Sonar issue fixes



See merge request !38 (Sasitha Gunadasa)(sasithag@zone24x7.com)

Merge branch 'develop' of git.zone24x7.lk:IDAPI/IDAPI into sonar-issue-fixes  (Sasitha Gunadasa)(sasithag@zone24x7.com)

build error fixes in test classes  (Sasitha Gunadasa)(sasithag@zone24x7.com)

Merge branch 'sonar-issue-fixes' into 'develop' Sonar issue fixes



See merge request !37 (Sasitha Gunadasa)(sasithag@zone24x7.com)

Merge branch 'develop' of git.zone24x7.lk:IDAPI/IDAPI into sonar-issue-fixes  (Sasitha Gunadasa)(sasithag@zone24x7.com)

sonar code smell fixes  (Sasitha Gunadasa)(sasithag@zone24x7.com)

Merge branch 'sonar-issue-fixes' into 'develop' Sonar issue fixes



See merge request !36 (Sasitha Gunadasa)(sasithag@zone24x7.com)

Merge branch 'develop' of git.zone24x7.lk:IDAPI/IDAPI into sonar-issue-fixes  (Sasitha Gunadasa)(sasithag@zone24x7.com)

Sonar defect fixes  (Sasitha Gunadasa)(sasithag@zone24x7.com)

Merge branch 'sonar-issue-fixes' into 'develop' Sonar issue fixes



See merge request !35 (Sasitha Gunadasa)(sasithag@zone24x7.com)

Merge branch 'develop' of git.zone24x7.lk:IDAPI/IDAPI into sonar-issue-fixes  (Sasitha Gunadasa)(sasithag@zone24x7.com)

fixed build errors in test cases  (Sasitha Gunadasa)(sasithag@zone24x7.com)

Merge branch 'sonar-issue-fixes' into 'develop' Sonar issue fixes

Fixed sonar issues in document verification module

See merge request !34 (Sasitha Gunadasa)(sasithag@zone24x7.com)

Sonar issue fixes Fixed sonar issues in document verification module
 (Sasitha Gunadasa)(sasithag@zone24x7.com)

updated changelog files  (Sasitha Gunadasa)(sasithag@zone24x7.com)

1.0.1-0.0.2 (2017-01-18 13:00:02 +0530)
---------------------------------------
Update gender verification to handle null values Null pointer exeption was occured if there are no value was there for the gender value
 (Sasitha Gunadasa)(sasithag@zone24x7.com)

updated changelog  (Sasitha Gunadasa)(sasithag@zone24x7.com)

1.0.1-0.0.1 (2017-01-15 13:05:05 +0530)
---------------------------------------
Taking license handler implementation to a service layer class This class will be handling the license of a client based on the result of the extranal processing service.
 (Sasitha Gunadasa)(sasithag@zone24x7.com)

Enable gender verification with single document Gender verificaiton can be done with a single document. If gender field could be found
only in one document provided, the validation will pass adding gender of that document
to the remarks. However if more than one document contains a gender field all must match
else the validation will fail.
 (Sasitha Gunadasa)(sasithag@zone24x7.com)


1.0.1-0.0.0 (2017-01-15 11:41:20 +0530)
---------------------------------------
First change log files html and md First change log files were created. The html file will be added as a custom report
to jenkins and the md file will be refered in the README.md file
 (Sasitha Gunadasa)(sasithag@zone24x7.com)
