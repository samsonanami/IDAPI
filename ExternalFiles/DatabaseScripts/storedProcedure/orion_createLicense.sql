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
BEGIN

	  DECLARE s_date DATETIME DEFAULT DATE(start_date)        ;
    DECLARE e_date DATETIME DEFAULT DATE(end_date)          ;
    
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
      PRIMARY KEY (ID)
		);
    
    INSERT INTO tmp_inputs
		(
      S_DATE						                                    ,
      E_DATE						                                    ,
      M_R_COUNT					                                    ,
      C_EMAIL						                                    ,
      ENABLED						                                    ,
      `STATUS`
		)
	VALUES
		(
			s_date						                                    ,
      e_date						                                    ,
      maximum_request_count		                              ,
      client_email				                                  ,
      0							                                        ,
      0
		);
    
    INSERT INTO license
		(
			START_DATE					                                  ,
      END_DATE					                                    ,
      CURRENT_REQUEST_COUNT		                              ,
      MAXIMUM_REQUEST_COUNT		                              ,
      `CLIENT`					                                    ,
      ENABLED						                                    ,
			`STATUS`
    )
    SELECT S_DATE, E_DATE, C_R_COUNT, M_R_COUNT, c.ID, ENABLED, `STATUS`
    FROM tmp_inputs t
    INNER JOIN `client` c ON c.EMAIL = t.C_EMAIL;

END //
DELIMITER ;
