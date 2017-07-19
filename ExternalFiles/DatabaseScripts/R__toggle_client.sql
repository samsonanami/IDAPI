/*
* Procedure Name	    :     orion_toggleClient
*
* Description         :     this procedure will toggle client enable or disable
*
* Tables Impacted     :     client
*
* Params              :     client_email_address      -     email address of the client
*                           toggle                    -     toggle state
*
*
* Revision History    :
*
*                           Date:             Comment:
*                           2017/02/03        Initial Script
*                           2017/02/06        Console output after script
*/


DROP PROCEDURE IF EXISTS orion_toggleClient;
DELIMITER //
CREATE PROCEDURE orion_toggleClient
  (
    IN client_email_address   VARCHAR(256),
    IN toggle BOOLEAN
  )
  BEGIN
    SET SQL_SAFE_UPDATES = 0;
    UPDATE `client`
    SET ENABLED = toggle
    WHERE EMAIL = client_email_address;
    SET SQL_SAFE_UPDATES = 1;
    SELECT
      'Toggled client enable / disable status' AS 'MESSAGE',
      EMAIL AS 'CLIENT EMAIL ADDRESS', USER_NAME AS 'CLIENT USER NAME', ENABLED AS 'NEW STATUS'
      FROM `client` where `client`.EMAIL = client_email_address;
  END //
DELIMITER ;