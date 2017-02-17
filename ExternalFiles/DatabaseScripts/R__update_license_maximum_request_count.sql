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
*                           2017/02/06        Console output after script
*/



DROP PROCEDURE IF EXISTS orion_updateLicenseMaximumRequestCount;
DELIMITER //
CREATE PROCEDURE orion_updateLicenseMaximumRequestCount
  (
    IN  key_id  VARCHAR(256),
    IN  maximum_request_count INT(5)
  )
  BEGIN
    SET SQL_SAFE_UPDATES = 0;
      UPDATE license
        SET MAXIMUM_REQUEST_COUNT = maximum_request_count
      WHERE LICENSE_KEY = key_id;
    SET SQL_SAFE_UPDATES = 1;

    SELECT
      'Updated maximum request count of the license' AS 'MESSAGE',
      license.LICENSE_KEY,  license.MAXIMUM_REQUEST_COUNT AS 'MAXIMUM REQUEST COUNT ALLOWED',
      client.USER_NAME AS 'CLIENT NAME', client.EMAIL AS 'CLIENT EMAIL ADDRESS',
      license.START_DATE AS 'LICENSE STARTING DATE', license.END_DATE AS 'LICENSE ENDING DATE',
      license.ENABLED AS 'LICENSE STATUS'
    FROM license
      INNER JOIN client ON license.CLIENT = client.ID WHERE license.LICENSE_KEY = key_id;
  END //
DELIMITER ;
