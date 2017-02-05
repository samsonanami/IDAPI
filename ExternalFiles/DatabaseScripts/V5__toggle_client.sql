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
*
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
  END //
DELIMITER ;