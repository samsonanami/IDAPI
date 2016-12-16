/*
*
*	Procedure Name	    :     orion_createLicense
*
*	Description		      :	    procedure to create a license. when creating a license
*						                it should get assigned to a client. a license without
*						                a client will be useless.
*
*	Tables Impacted     :	    license
*
*	Params			        :	    client_email 			      -      the email address of the client
*						                start_date				      -	     start date of the license period
*                           end_date				        -	     end date of the license period
*                           maximum_request_count	  -	     maximum request allowed by the license
*
*	Revision History    :
*	
*		                        Date:			      Comment:
*		                        2016/10/26		  Initial script
*
*
*	                          Copyright (c) 2016 zone24x7
*
*/
USE orion;
DROP PROCEDURE IF EXISTS `orion_createLicense`              ;
DELIMITER //

CREATE PROCEDURE orion_createLicense
	(
		IN	client_email				    VARCHAR(255)	              ,
    IN	start_date					    VARCHAR(10)		              ,
    IN	end_date					      VARCHAR(10)		              ,
    IN	maximum_request_count		INT
	)
license_process:BEGIN

    DECLARE s_date DATETIME DEFAULT DATE(start_date)        ;
    DECLARE e_date DATETIME DEFAULT DATE(end_date)          ;
	  DECLARE is_start_date_valid BOOL DEFAULT FALSE          ;
    DECLARE is_end_date_valid BOOL DEFAULT FALSE            ;
    
    SET is_start_date_valid = validate_date(start_date);
    SET is_end_date_valid = validate_date(end_date);
	IF !is_start_date_valid || !is_end_date_valid
    THEN 
			SIGNAL SQLSTATE VALUE '99999'
				SET MESSAGE_TEXT = 'Start date or End date is not in correct format, The correct format is YYYY-MM-DD.
				                    Please check the date format and try again';
			LEAVE license_process;
    ELSE
      DROP TABLE IF EXISTS `tmp_inputs`;

      CREATE TEMPORARY TABLE tmp_inputs
      (
        ID 			    INT 		  NOT NULL 	AUTO_INCREMENT		    ,
        S_DATE 		  DATETIME 	NOT NULL						            ,
        E_DATE 		  DATETIME 	NOT NULL						            ,
        C_R_COUNT  	INT			NOT NULL						              ,
        M_R_COUNT 	INT		 	NOT NULL						              ,
        C_EMAIL		  VARCHAR(256)								              ,
        ENABLED		BIT(1)										                  ,
        `STATUS`	BIT(1)										                  ,
        LICENSE_KEY VARCHAR(50)														    ,
        PRIMARY KEY (ID)
      );

      INSERT INTO tmp_inputs
      (
        S_DATE						                                    ,
        E_DATE						                                    ,
        M_R_COUNT					                                    ,
        C_EMAIL						                                    ,
        ENABLED						                                    ,
        `STATUS`															                ,
        LICENSE_KEY
      )
    VALUES
      (
        s_date						                                    ,
        e_date						                                    ,
        maximum_request_count		                              ,
        client_email				                                  ,
        0							                                        ,
        0																	                    ,
        UUID()
      );

      INSERT INTO license
      (
        START_DATE					                                  ,
        END_DATE					                                    ,
        CURRENT_REQUEST_COUNT		                              ,
        MAXIMUM_REQUEST_COUNT		                              ,
        `CLIENT`					                                    ,
        ENABLED						                                    ,
        `STATUS`											                        ,
        LICENSE_KEY
      )
      SELECT S_DATE, E_DATE, C_R_COUNT, M_R_COUNT, c.ID, t.ENABLED, `STATUS`, LICENSE_KEY
      FROM tmp_inputs t
      INNER JOIN `client` c ON c.EMAIL = t.C_EMAIL;
    END IF;
END //
DELIMITER ;

DROP FUNCTION IF EXISTS `orion`.`validate_date`;
DELIMITER //
CREATE FUNCTION validate_date(d VARCHAR(255))
	RETURNS INT
BEGIN
	DECLARE date_year  VARCHAR(255) DEFAULT '';
   DECLARE date_month VARCHAR(255) DEFAULT '';
   DECLARE date_day   VARCHAR(255) DEFAULT '';
   DECLARE ym_delim   INT DEFAULT 0;
   DECLARE md_delim   INT DEFAULT 0;
   -- First, if it's just not xxx-yyy-zzz format:
   SET ym_delim = LOCATE('-', d);
   SET md_delim = LOCATE('-', d, ym_delim+1);
   IF !ym_delim || !md_delim THEN

      RETURN FALSE;
   END IF;
   -- Second, if resulted members are not YYYY, MM or DD:
   SET date_year  = SUBSTR(d, 1, ym_delim-1);
   SET date_month = SUBSTR(d, ym_delim+1, md_delim-ym_delim-1);
   SET date_day   = SUBSTR(d, md_delim+1);
   IF  date_year  NOT REGEXP '^[0-9]{4}$'
    || date_month NOT REGEXP '^[0-9]{2}$'
    || date_day   NOT REGEXP '^[0-9]{2}$' THEN
      RETURN FALSE;
   END IF;
   -- Finally, check if date itself is ok, like 2014-02-30 isn't ok:
   IF DATE(CONCAT(date_year, '-', date_month, '-', date_day)) IS NULL THEN
      RETURN FALSE;
   END IF;
   RETURN TRUE;
END //
DELIMITER ;
