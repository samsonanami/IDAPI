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
-- Dumping data for table `ocr_process_type`
--

LOCK TABLES `ocr_process_type` WRITE;
/*!40000 ALTER TABLE `ocr_process_type` DISABLE KEYS */;
INSERT INTO `ocr_process_type` VALUES (1,'idVerification'),(2,'addressVerification');
/*!40000 ALTER TABLE `ocr_process_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `ocr_processing_status`
--

LOCK TABLES `ocr_processing_status` WRITE;
/*!40000 ALTER TABLE `ocr_processing_status` DISABLE KEYS */;
INSERT INTO `ocr_processing_status` VALUES (1,'pending'),(2,'processing_started'),(3,'processing_failed'),(4,'processing_successful');
/*!40000 ALTER TABLE `ocr_processing_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `process_config`
--

LOCK TABLES `process_config` WRITE;
/*!40000 ALTER TABLE `process_config` DISABLE KEYS */;
INSERT INTO `process_config` VALUES (1,'body.clientID','127.0.01'),(1,'body.description','IDAPI'),(1,'body.inputImages.type','visible'),(1,'header.authorization','Basic RmludGVjaDp4R0gyMjk3OUhvczJ3eDRL'),(1,'header.contentType','application/json'),(1,'InspectionImageUrl','https://www.checkid.online:15045/inspectionim'),(1,'Password','xGH22979Hos2wx4K'),(1,'queryString.synchronous','true'),(1,'url','https://www.checkid.online:15045/inspectionjob/create'),(1,'Username','Fintech');
/*!40000 ALTER TABLE `process_config` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `process_type`
--

LOCK TABLES `process_type` WRITE;
/*!40000 ALTER TABLE `process_type` DISABLE KEYS */;
INSERT INTO `process_type` VALUES (1,'idVerification'),(2,'addressVerification');
/*!40000 ALTER TABLE `process_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `processing_status`
--

LOCK TABLES `processing_status` WRITE;
/*!40000 ALTER TABLE `processing_status` DISABLE KEYS */;
INSERT INTO `processing_status` VALUES (1,'ProcessingComplete','Processing Complete'),(2,'ProcessingPending','Processing Pending'),(3,'ProcessingRequested','Processing Requested'),(4,'ProcessingFailed','Processing Failed');
/*!40000 ALTER TABLE `processing_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `resource_name`
--

LOCK TABLES `resource_name` WRITE;
/*!40000 ALTER TABLE `resource_name` DISABLE KEYS */;
INSERT INTO `resource_name` VALUES (1,'drivingLicenseFront','Driving_License_Front'),(2,'drivingLicenseBack','Driving_License_Back'),(3,'passport','Passport'),(4,'utilityBill','Utility_Bill'),(5,'id','Identity_Card'),(6,'keyedData','KeyedData');
/*!40000 ALTER TABLE `resource_name` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `resource_name_ocr_extraction_field`
--

LOCK TABLES `resource_name_ocr_extraction_field` WRITE;
/*!40000 ALTER TABLE `resource_name_ocr_extraction_field` DISABLE KEYS */;
INSERT INTO `resource_name_ocr_extraction_field` VALUES (1,1,15,NULL),(2,1,1,NULL),(3,1,2,NULL),(4,1,11,NULL),(5,1,3,NULL),(6,1,7,NULL),(7,1,6,NULL),(8,1,4,NULL),(9,1,5,NULL),(10,1,17,NULL),(11,1,19,NULL),(12,1,18,NULL),(13,3,1,10),(14,3,2,NULL),(15,3,12,10),(16,3,13,10),(17,3,11,10),(18,3,3,10),(19,3,7,10),(20,3,6,10),(21,3,4,10),(22,3,14,10),(23,3,5,10),(24,3,18,NULL),(25,4,21,NULL),(26,4,22,NULL),(27,4,17,NULL),(28,4,19,NULL),(29,4,20,NULL),(30,4,24,NULL),(31,4,23,NULL),(32,6,1,NULL),(33,6,2,NULL),(34,6,13,NULL),(35,6,11,NULL),(36,6,3,NULL),(37,6,21,NULL),(38,6,17,NULL),(39,3,15,10),(40,3,16,10),(41,4,18,NULL);
/*!40000 ALTER TABLE `resource_name_ocr_extraction_field` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `resource_type`
--

LOCK TABLES `resource_type` WRITE;
/*!40000 ALTER TABLE `resource_type` DISABLE KEYS */;
INSERT INTO `resource_type` VALUES (1,'image'),(3,'json');
/*!40000 ALTER TABLE `resource_type` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-02-05  9:53:51
