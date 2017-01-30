/*
*
*	Procedure Name							: 			orion_createClient
*
*	Description									:				procedure to use when creating a new client
*
*	Tables Impacted 						:				client
*
*	Params											:				client_email 			-   the email address of the client
*																			client_name				- 	the user name of the client
*																			client_password		-		the password of the client generate the hash from
*																														https://www.dailycred.com/article/bcrypt-calculator
*                                                           and insert value as the password
*	Revision History						:
*	
*																			Date:						Comment:
*																			2016/10/26			Initial script
*                                     2017/01/24      AES ENCRYPTION to password
*
*													Copyright (c) 2016 zone24x7
*
*/
USE idapi;
DROP PROCEDURE IF EXISTS `orion_createClient`;
DELIMITER //

CREATE PROCEDURE orion_createClient
  (
    IN client_email    VARCHAR(255),
    IN client_name     VARCHAR(45),
    IN client_password VARCHAR(512)
  )
  BEGIN

    DECLARE today DATETIME DEFAULT CURDATE();
    INSERT INTO client
    (
      EMAIL,
      REGISTERED_ON,
      USER_NAME,
      `PASSWORD`,
      ENABLED
    )
    VALUES
      (
        client_email,
        today,
        client_name,
        client_password,
        0
      );
  END //
DELIMITER ;
            