-- MySQL dump 10.13  Distrib 5.7.17, for Linux (x86_64)
--
-- Host: 10.101.15.212    Database: idapi
-- ------------------------------------------------------
-- Server version	5.5.5-10.1.17-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `C3P0_TEST_TABLE`
--

DROP TABLE IF EXISTS `C3P0_TEST_TABLE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `C3P0_TEST_TABLE` (
  `a` char(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `client`
--

DROP TABLE IF EXISTS `client`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `client` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `EMAIL` varchar(256) NOT NULL,
  `REGISTERED_ON` date NOT NULL,
  `USER_NAME` varchar(45) NOT NULL,
  `PASSWORD` varchar(512) NOT NULL,
  `ENABLED` bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `EMAIL_UNIQUE` (`EMAIL`),
  UNIQUE KEY `USER_NAME_UNIQUE` (`USER_NAME`),
  UNIQUE KEY `UK71jccfcj21ifcli4a0qa590d5` (`EMAIL`),
  UNIQUE KEY `UKs3t9dk48yubrtv97fu74e5q5p` (`USER_NAME`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `license`
--

DROP TABLE IF EXISTS `license`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `license` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `START_DATE` date DEFAULT NULL,
  `END_DATE` date DEFAULT NULL,
  `CURRENT_REQUEST_COUNT` int(4) DEFAULT NULL,
  `MAXIMUM_REQUEST_COUNT` int(4) DEFAULT NULL,
  `CLIENT` int(11) DEFAULT NULL,
  `ENABLED` bit(1) DEFAULT NULL,
  `STATUS` bit(1) DEFAULT NULL,
  `LICENSE_KEY` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `fk_license_client1_idx` (`CLIENT`),
  CONSTRAINT `fk_license_client1` FOREIGN KEY (`CLIENT`) REFERENCES `client` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `oauth_access_token`
--

DROP TABLE IF EXISTS `oauth_access_token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `oauth_access_token` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `token_id` varchar(256) DEFAULT NULL,
  `token` blob,
  `authentication_id` varchar(256) DEFAULT NULL,
  `user_name` varchar(256) DEFAULT NULL,
  `client_id` varchar(256) DEFAULT NULL,
  `authentication` blob,
  `refresh_token` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `oauth_client_details`
--

DROP TABLE IF EXISTS `oauth_client_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `oauth_client_details` (
  `client_id` varchar(256) NOT NULL,
  `resource_ids` varchar(256) DEFAULT NULL,
  `client_secret` varchar(256) DEFAULT NULL,
  `scope` varchar(256) DEFAULT NULL,
  `authorized_grant_types` varchar(256) DEFAULT NULL,
  `web_server_redirect_uri` varchar(256) DEFAULT NULL,
  `authorities` varchar(256) DEFAULT NULL,
  `access_token_validity` int(11) DEFAULT NULL,
  `refresh_token_validity` int(11) DEFAULT NULL,
  `additional_information` varchar(4096) DEFAULT NULL,
  `autoapprove` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`client_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `oauth_refresh_token`
--

DROP TABLE IF EXISTS `oauth_refresh_token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `oauth_refresh_token` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `token_id` varchar(256) DEFAULT NULL,
  `token` blob,
  `authentication` blob,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ocr_extraction_field`
--

DROP TABLE IF EXISTS `ocr_extraction_field`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ocr_extraction_field` (
  `ID` int(11) NOT NULL,
  `FIELD` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ocr_process`
--

DROP TABLE IF EXISTS `ocr_process`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ocr_process` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `PROCESS_TYPE` int(11) DEFAULT NULL,
  `PROCESSING_REQUEST` int(11) DEFAULT NULL,
  `PROCESSING_STATUS` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `fk_ocr_process_ocr_processing_request1_idx` (`PROCESSING_REQUEST`),
  KEY `fk_ocr_process_ocr_processing_status1_idx` (`PROCESSING_STATUS`),
  KEY `fk_ocr_process_ocr_process_type_idx` (`PROCESS_TYPE`),
  CONSTRAINT `fk_ocr_process_ocr_process_type` FOREIGN KEY (`PROCESS_TYPE`) REFERENCES `ocr_process_type` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_ocr_process_ocr_processing_request1` FOREIGN KEY (`PROCESSING_REQUEST`) REFERENCES `ocr_processing_request` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_ocr_process_ocr_processing_status1` FOREIGN KEY (`PROCESSING_STATUS`) REFERENCES `ocr_processing_status` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ocr_process_type`
--

DROP TABLE IF EXISTS `ocr_process_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ocr_process_type` (
  `ID` int(11) NOT NULL,
  `TYPE` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ocr_processing_request`
--

DROP TABLE IF EXISTS `ocr_processing_request`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ocr_processing_request` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `RECEIVED_ON` timestamp NULL DEFAULT NULL,
  `PROCESSING_REQUEST_CODE` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ocr_processing_status`
--

DROP TABLE IF EXISTS `ocr_processing_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ocr_processing_status` (
  `ID` int(11) NOT NULL,
  `STATUS` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ocr_result`
--

DROP TABLE IF EXISTS `ocr_result`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ocr_result` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `VALUE` varchar(256) DEFAULT NULL,
  `OCR_CONFIDENCE` double DEFAULT NULL,
  `RESULT_NAME` varchar(45) DEFAULT NULL,
  `OCR_EXTRACTION_FIELD` int(11) DEFAULT NULL,
  `OCR_PROCESS` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `fk_ocr_result_ocr_process1_idx` (`OCR_PROCESS`),
  KEY `fk_ocr_result_resource_name_ocr_extraction_field1_idx` (`OCR_EXTRACTION_FIELD`),
  CONSTRAINT `fk_ocr_result_ocr_process1` FOREIGN KEY (`OCR_PROCESS`) REFERENCES `ocr_process` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_ocr_result_resource_name_ocr_extraction_field1` FOREIGN KEY (`OCR_EXTRACTION_FIELD`) REFERENCES `resource_name_ocr_extraction_field` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `process`
--

DROP TABLE IF EXISTS `process`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `process` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `REQUEST_SENT_ON` timestamp NULL DEFAULT NULL,
  `RESPONSE_RECEIVED_ON` timestamp NULL DEFAULT NULL,
  `PROCESSING_REQUEST` int(11) DEFAULT NULL,
  `PROCESS_TYPE` int(11) DEFAULT NULL,
  `PROCESS_IDENTIFICATION_CODE` varchar(40) DEFAULT NULL,
  `PROCESSING_STATUS` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `fk_process_process_type1_idx` (`PROCESS_TYPE`),
  KEY `fk_process_processing_status1_idx` (`PROCESSING_STATUS`),
  KEY `fk_process_processing_request1_idx` (`PROCESSING_REQUEST`),
  CONSTRAINT `fk_process_process_type1` FOREIGN KEY (`PROCESS_TYPE`) REFERENCES `process_type` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_process_processing_request1` FOREIGN KEY (`PROCESSING_REQUEST`) REFERENCES `processing_request` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_process_processing_status1` FOREIGN KEY (`PROCESSING_STATUS`) REFERENCES `processing_status` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `process_config`
--

DROP TABLE IF EXISTS `process_config`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `process_config` (
  `PROCESS_TYPE` int(11) NOT NULL,
  `KEY` varchar(45) NOT NULL,
  `VALUE` varchar(150) NOT NULL,
  PRIMARY KEY (`PROCESS_TYPE`,`KEY`),
  CONSTRAINT `fk_process_config_process_type1` FOREIGN KEY (`PROCESS_TYPE`) REFERENCES `process_type` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `process_type`
--

DROP TABLE IF EXISTS `process_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `process_type` (
  `ID` int(11) NOT NULL,
  `TYPE` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `process_type_license`
--

DROP TABLE IF EXISTS `process_type_license`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `process_type_license` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `PROCESS_TYPE` int(11) NOT NULL,
  `LICENSE` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `fk_process_type_has_license_license1_idx` (`LICENSE`),
  KEY `fk_process_type_has_license_process_type1_idx` (`PROCESS_TYPE`),
  CONSTRAINT `fk_process_type_has_license_license1` FOREIGN KEY (`LICENSE`) REFERENCES `license` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_process_type_has_license_process_type1` FOREIGN KEY (`PROCESS_TYPE`) REFERENCES `process_type` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `processing_request`
--

DROP TABLE IF EXISTS `processing_request`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `processing_request` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `RECEIVED_ON` timestamp NULL DEFAULT NULL,
  `CLIENT` int(11) DEFAULT NULL,
  `PROCESSING_REQUEST_IDENTIFICATION_CODE` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `fk_processing_request_client1_idx` (`CLIENT`),
  CONSTRAINT `fk_processing_request_client1` FOREIGN KEY (`CLIENT`) REFERENCES `client` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `processing_status`
--

DROP TABLE IF EXISTS `processing_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `processing_status` (
  `ID` int(11) NOT NULL,
  `STATUS` varchar(45) NOT NULL,
  `DESCRIPTION` varchar(100) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `resource`
--

DROP TABLE IF EXISTS `resource`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `resource` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `LOCATION` varchar(128) DEFAULT NULL,
  `RESOURCE_TYPE` int(11) DEFAULT NULL,
  `CLIENT` int(11) DEFAULT NULL,
  `RESOURCE_IDENTIFICATION_CODE` varchar(40) DEFAULT NULL,
  `PROCESS` int(11) DEFAULT NULL,
  `OCR_PROCESS` int(11) DEFAULT NULL,
  `RESOURCE_NAME` int(11) DEFAULT NULL,
  `OCR_PROCESSING_STATUS` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `fk_resource_client1_idx` (`CLIENT`),
  KEY `fk_resource_resource_type1_idx` (`RESOURCE_TYPE`),
  KEY `fk_resource_process1_idx` (`PROCESS`),
  KEY `fk_resource_ocr_process1_idx` (`OCR_PROCESS`),
  KEY `fk_resource_resource_name1_idx` (`RESOURCE_NAME`),
  KEY `fk_resource_ocr_processing_status1_idx` (`OCR_PROCESSING_STATUS`),
  CONSTRAINT `fk_resource_client1` FOREIGN KEY (`CLIENT`) REFERENCES `client` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_resource_ocr_process1` FOREIGN KEY (`OCR_PROCESS`) REFERENCES `ocr_process` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_resource_ocr_processing_status1` FOREIGN KEY (`OCR_PROCESSING_STATUS`) REFERENCES `ocr_processing_status` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_resource_process1` FOREIGN KEY (`PROCESS`) REFERENCES `process` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_resource_resource_name1` FOREIGN KEY (`RESOURCE_NAME`) REFERENCES `resource_name` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_resource_resource_type1` FOREIGN KEY (`RESOURCE_TYPE`) REFERENCES `resource_type` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `resource_metadata`
--

DROP TABLE IF EXISTS `resource_metadata`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `resource_metadata` (
  `RESOURCE` int(11) NOT NULL,
  `KEY` varchar(45) NOT NULL,
  `VALUE` varchar(45) NOT NULL,
  PRIMARY KEY (`RESOURCE`,`KEY`),
  CONSTRAINT `fk_resource_metadata_resource1` FOREIGN KEY (`RESOURCE`) REFERENCES `resource` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `resource_name`
--

DROP TABLE IF EXISTS `resource_name`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `resource_name` (
  `ID` int(11) NOT NULL,
  `NAME` varchar(50) DEFAULT NULL,
  `CONFIG_FILE_PATH` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `resource_name_ocr_extraction_field`
--

DROP TABLE IF EXISTS `resource_name_ocr_extraction_field`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `resource_name_ocr_extraction_field` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `resource_name_ID` int(11) DEFAULT NULL,
  `ocr_extraction_field_ID` int(11) DEFAULT NULL,
  `minimum_acceptable_ocr_confidence` int(3) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `fk_resource_name_has_ocr_extraction_field_resource_name1_idx` (`resource_name_ID`),
  KEY `fk_resource_name_has_ocr_extraction_field_ocr_extraction_field1` (`ocr_extraction_field_ID`),
  CONSTRAINT `fk_resource_name_has_ocr_extraction_field_ocr_extraction_field1` FOREIGN KEY (`ocr_extraction_field_ID`) REFERENCES `ocr_extraction_field` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_resource_name_has_ocr_extraction_field_resource_name1` FOREIGN KEY (`resource_name_ID`) REFERENCES `resource_name` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `resource_type`
--

DROP TABLE IF EXISTS `resource_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `resource_type` (
  `ID` int(11) NOT NULL,
  `TYPE` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `response`
--

DROP TABLE IF EXISTS `response`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `response` (
  `PROCESS_ID` int(11) NOT NULL,
  `RAW_JSON` longtext,
  `EXTRACTED_JSON` longtext,
  PRIMARY KEY (`PROCESS_ID`),
  CONSTRAINT `fk_response_process1` FOREIGN KEY (`PROCESS_ID`) REFERENCES `process` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-02-04  9:57:22
