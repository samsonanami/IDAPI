/*
* Procedure Name	    :     orion_toggleLicense
*
* Description         :     this procedure will toggle enable and disable a given license.
*
* Tables Impacted     :     license
*
* Params              :     license_key               -     key of the license that need to be updated
*                           toggle status             -     toggle status need to be updated
*
*
* Revision History    :
*
*                           Date:             Comment:
*                           2017/02/03        Initial Script
*
*/



DROP PROCEDURE IF EXISTS orion_toggleLicense;
DELIMITER //
CREATE PROCEDURE orion_toggleLicense
  (
    IN license_key     VARCHAR(256),
    IN togle          BOOLEAN
  )
  BEGIN
    SET SQL_SAFE_UPDATES = 0;
      UPDATE license
        SET ENABLED = togle
      WHERE LICENSE_KEY = license_key;
    SET SQL_SAFE_UPDATES = 1;
  END //
DELIMITER ;