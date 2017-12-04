ALTER TABLE `idapi`.`processing_request` 
ADD COLUMN `FINAL_VERIFICATION_STATUS` INT(11) NULL AFTER `PROCESSING_COMPLETED_ON`,
ADD INDEX `fk_processing_request_final_verification_status1_idx` (`FINAL_VERIFICATION_STATUS` ASC);
ALTER TABLE `idapi`.`processing_request` 
ADD CONSTRAINT `fk_processing_request_final_verification_status1`
  FOREIGN KEY (`FINAL_VERIFICATION_STATUS`)
  REFERENCES `idapi`.`processing_status` (`ID`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;
