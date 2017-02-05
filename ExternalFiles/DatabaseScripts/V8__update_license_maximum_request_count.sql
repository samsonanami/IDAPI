/*
* Procedure Name	    :     orion_updateLicenseMaximumRequestCount
*
* Description         :     this procedure will update the maximum number of the given license
*
* Tables Impacted     :     license
*
* Params              :     license_key               -     key of the license that need to be updated
*                           maximum_request_count     -     maximum number of request need to be set
*
*
* Revision History    :
*
*                           Date:             Comment:
*                           2017/02/03        Initial Script
*
*/



DROP PROCEDURE IF EXISTS orion_updateLicenseMaximumRequestCount;
DELIMITER //
CREATE PROCEDURE orion_updateLicenseMaximumRequestCount
  (
    IN  license_key  VARCHAR(256),
    IN  maximum_request_count INT(5)
  )
  BEGIN
    SET SQL_SAFE_UPDATES = 0;
      UPDATE license
        SET MAXIMUM_REQUEST_COUNT = maximum_request_count
      WHERE LICENSE_KEY = license_key;
    SET SQL_SAFE_UPDATES = 1;
  END //
DELIMITER ;
