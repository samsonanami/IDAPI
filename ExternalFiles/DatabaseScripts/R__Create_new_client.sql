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
*                                     2017/02/06      Console output after adding a user
*									                    2017/02/09 	  Validating input to avoid empty values
*
*													Copyright (c) 2016 zone24x7
*
*/

DROP PROCEDURE IF EXISTS `orion_createClient`;
DELIMITER //

CREATE PROCEDURE orion_createClient
  (
    IN client_email    VARCHAR(255),
    IN client_name     VARCHAR(45),
    IN client_password VARCHAR(512)
  )
    client_add_process:BEGIN

    DECLARE today DATETIME DEFAULT CURDATE();
    DECLARE is_input_valid BOOL DEFAULT FALSE;

    SET is_input_valid = validate_add_client_input(client_email, client_name, client_password);
    IF !is_input_valid THEN
      SIGNAL SQLSTATE VALUE '99999'
      SET MESSAGE_TEXT = 'One ore more input is null or emphty. Please check the input and try again';
      LEAVE client_add_process;
    ELSE
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
      SELECT 'Successfully added a new user with following details' AS 'MESSAGE',
        EMAIL, USER_NAME, REGISTERED_ON, ENABLED FROM client WHERE EMAIL = client_email AND USER_NAME = client_name;
    END IF;
  END //
DELIMITER ;

DROP FUNCTION IF EXISTS `validate_add_client_input`;
DELIMITER //
CREATE FUNCTION validate_add_client_input(client_email VARCHAR(256),  client_name VARCHAR(45), client_password VARCHAR(512))
  RETURNS INT
  BEGIN
    IF client_email IS NULL OR client_email = '' THEN
      RETURN FALSE;
    END IF;
    IF client_name IS NULL OR client_name = '' THEN
      RETURN FALSE;
    END IF;
    IF	client_password IS NULL OR client_password = '' THEN
      RETURN FALSE;
    END IF;
    RETURN TRUE;
  END //
DELIMITER ;
