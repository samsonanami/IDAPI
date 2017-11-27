CREATE TABLE `idapi`.`user_type` (
  `ID` INT NOT NULL,
  `TYPE` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE INDEX `TYPE_UNIQUE` (`TYPE` ASC),
  UNIQUE INDEX `ID_UNIQUE` (`ID` ASC));
 
INSERT INTO `idapi`.`user_type` (`ID`,`TYPE`) VALUES(1,'Service');
INSERT INTO `idapi`.`user_type` (`ID`,`TYPE`) VALUES(2,'User');

ALTER TABLE `idapi`.`client`
ADD COLUMN `USER_TYPE_ID` INT NOT NULL DEFAULT '1' AFTER `ENABLED`;
 
SET foreign_key_checks = 0;
 
ALTER TABLE idapi.client
ADD FOREIGN KEY (USER_TYPE_ID) REFERENCES idapi.user_type(ID);
 
SET foreign_key_checks = 1;
 
CREATE TABLE `idapi`.`account_mapping` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `PARENT` INT NOT NULL,
  `CHILD` INT NOT NULL,
 PRIMARY KEY (`ID`),
 CONSTRAINT `PARENT`
    FOREIGN KEY (`PARENT`)
    REFERENCES `idapi`.`client` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `CHILD`
    FOREIGN KEY (`CHILD`)
    REFERENCES `idapi`.`client` (`ID`)
    ON DELETE CASCADE
ON UPDATE CASCADE);