
-- Initial database script
-- includes schema creation and table creation



SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';

CREATE SCHEMA IF NOT EXISTS `orion`;

USE `orion`;

CREATE TABLE IF NOT EXISTS `orion`.`response` (
  `ID` INT(11) NOT NULL,
  `RAW_JSON` LONGTEXT NULL DEFAULT NULL,
  `EXTRACTED_JSON` LONGTEXT NULL DEFAULT NULL,
  `PROCESS` INT(11) NOT NULL,
  PRIMARY KEY (`ID`),
  INDEX `fk_response_process1_idx` (`PROCESS` ASC),
  CONSTRAINT `fk_response_process1`
    FOREIGN KEY (`PROCESS`)
    REFERENCES `orion`.`process` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

CREATE TABLE IF NOT EXISTS `orion`.`processing_request` (
  `ID` INT(11) NOT NULL,
  `RECEIVED_ON` TIMESTAMP NULL DEFAULT NULL,
  `CLIENT` INT(11) NOT NULL,
  PRIMARY KEY (`ID`),
  INDEX `fk_processing_request_client1_idx` (`CLIENT` ASC),
  CONSTRAINT `fk_processing_request_client1`
    FOREIGN KEY (`CLIENT`)
    REFERENCES `orion`.`client` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

CREATE TABLE IF NOT EXISTS `orion`.`client` (
  `ID` INT(11) NOT NULL AUTO_INCREMENT,
  `EMAIL` VARCHAR(256) NULL DEFAULT NULL,
  `AUTH_TOKEN` VARCHAR(50) NULL DEFAULT NULL,
  `REGISTERED_ON` DATE NULL DEFAULT NULL,
  PRIMARY KEY (`ID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

CREATE TABLE IF NOT EXISTS `orion`.`resource_type` (
  `ID` INT(11) NOT NULL AUTO_INCREMENT,
  `TYPE` VARCHAR(10) NULL DEFAULT NULL,
  PRIMARY KEY (`ID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

CREATE TABLE IF NOT EXISTS `orion`.`resource` (
  `ID` INT(11) NOT NULL,
  `LOCATION` VARCHAR(128) NULL DEFAULT NULL,
  `RESOURCE_TYPE` INT(11) NOT NULL,
  `CLIENT` INT(11) NOT NULL,
  PRIMARY KEY (`ID`),
  INDEX `fk_resource_resource_type1_idx` (`RESOURCE_TYPE` ASC),
  INDEX `fk_resource_client1_idx` (`CLIENT` ASC),
  CONSTRAINT `fk_resource_resource_type1`
    FOREIGN KEY (`RESOURCE_TYPE`)
    REFERENCES `orion`.`resource_type` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_resource_client1`
    FOREIGN KEY (`CLIENT`)
    REFERENCES `orion`.`client` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

CREATE TABLE IF NOT EXISTS `orion`.`license` (
  `ID` INT(11) NOT NULL,
  `START_DATE` DATE NULL DEFAULT NULL,
  `END_DATE` DATE NULL DEFAULT NULL,
  `REQUEST_COUNT` INT(4) NULL DEFAULT NULL,
  PRIMARY KEY (`ID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

CREATE TABLE IF NOT EXISTS `orion`.`process_type` (
  `ID` INT(11) NOT NULL,
  `TYPE` VARCHAR(20) NULL DEFAULT NULL,
  PRIMARY KEY (`ID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

CREATE TABLE IF NOT EXISTS `orion`.`process` (
  `ID` INT(11) NOT NULL AUTO_INCREMENT,
  `REQUEST_SENT_ON` TIMESTAMP NULL DEFAULT NULL,
  `RESPONSE_RECEIVED_ON` TIMESTAMP NULL DEFAULT NULL,
  `PROCESSING_REQUEST` INT(11) NOT NULL,
  `PROCESS_TYPE` INT(11) NOT NULL,
  PRIMARY KEY (`ID`),
  INDEX `fk_process_processing_request1_idx` (`PROCESSING_REQUEST` ASC),
  INDEX `fk_process_process_type1_idx` (`PROCESS_TYPE` ASC),
  CONSTRAINT `fk_process_processing_request1`
    FOREIGN KEY (`PROCESSING_REQUEST`)
    REFERENCES `orion`.`processing_request` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_process_process_type1`
    FOREIGN KEY (`PROCESS_TYPE`)
    REFERENCES `orion`.`process_type` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

CREATE TABLE IF NOT EXISTS `orion`.`process_resource` (
  `ID` INT(11) NOT NULL AUTO_INCREMENT,
  `PROCESS` INT(11) NOT NULL,
  `RESOURCE` INT(11) NOT NULL,
  PRIMARY KEY (`ID`),
  INDEX `fk_process_has_resource_resource1_idx` (`RESOURCE` ASC),
  INDEX `fk_process_has_resource_process1_idx` (`PROCESS` ASC),
  CONSTRAINT `fk_process_has_resource_process1`
    FOREIGN KEY (`PROCESS`)
    REFERENCES `orion`.`process` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_process_has_resource_resource1`
    FOREIGN KEY (`RESOURCE`)
    REFERENCES `orion`.`resource` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

CREATE TABLE IF NOT EXISTS `orion`.`process_type_license` (
  `ID` INT(11) NOT NULL AUTO_INCREMENT,
  `process_type_ID` INT(11) NOT NULL,
  `license_ID` INT(11) NOT NULL,
  PRIMARY KEY (`ID`),
  INDEX `fk_process_type_has_license_license1_idx` (`license_ID` ASC),
  INDEX `fk_process_type_has_license_process_type1_idx` (`process_type_ID` ASC),
  CONSTRAINT `fk_process_type_has_license_process_type1`
    FOREIGN KEY (`process_type_ID`)
    REFERENCES `orion`.`process_type` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_process_type_has_license_license1`
    FOREIGN KEY (`license_ID`)
    REFERENCES `orion`.`license` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

CREATE TABLE IF NOT EXISTS `orion`.`client_license` (
  `ID` INT(11) NOT NULL AUTO_INCREMENT,
  `client_ID` INT(11) NOT NULL,
  `license_ID` INT(11) NOT NULL,
  PRIMARY KEY (`ID`),
  INDEX `fk_client_has_license_license1_idx` (`license_ID` ASC),
  INDEX `fk_client_has_license_client1_idx` (`client_ID` ASC),
  CONSTRAINT `fk_client_has_license_client1`
    FOREIGN KEY (`client_ID`)
    REFERENCES `orion`.`client` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_client_has_license_license1`
    FOREIGN KEY (`license_ID`)
    REFERENCES `orion`.`license` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;