/*
* Procedure Name	    :     orion_setClientLicense
*
* Description         :     procedure to set a license to a client. Both license and client must be defined prior
*                           to this.
*
* Tables Impacted     :     process_type_license
*
* Params              :     license_key               -     key of the license that need to be updated
*                           verification_process_type -     verification type which should be added to the given license
*
*
* Revision History    :
*
*                           Date:             Comment:
*                           2017/02/03        Initial Script
*
*/

USE idapi;
DROP PROCEDURE IF EXISTS  `orion_addVerificationTypeForLicense`           ;

DELIMITER //
CREATE PROCEDURE orion_addVerificationTypeForLicense
  (
    IN licnese_key                  VARCHAR(256),
    IN verification_process_type    VARCHAR(256)
  )
  license_process:BEGIN
    DECLARE license_id				  INT(11)		DEFAULT 		'';
    DECLARE process_type_id			INT(1)		DEFAULT			'';

    SELECT ID INTO license_id 					FROM 	license 		  WHERE 	LICENSE_KEY 	        = 	licnese_key;
    SELECT ID INTO process_type_id 	    FROM 	process_type 	WHERE 	process_type.`TYPE`   = 	verification_process_type;
    INSERT INTO process_type_license
    (
      `PROCESS_TYPE`,
      `LICENSE`
    )
    VALUES
      (
        process_type_id,
        license_id
      );

  END //
DELIMITER ;