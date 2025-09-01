-- MySQL dump 10.13  Distrib 8.0.43, for Win64 (x86_64)
--
-- Host: localhost    Database: loyalty_program
-- ------------------------------------------------------
-- Server version	8.0.43

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `ciudades`
--

DROP TABLE IF EXISTS `ciudades`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ciudades` (
  `id_ciudad` int NOT NULL AUTO_INCREMENT,
  `nombre_ciudad` varchar(100) NOT NULL,
  `id_departamento` int NOT NULL,
  PRIMARY KEY (`id_ciudad`),
  KEY `id_departamento` (`id_departamento`),
  CONSTRAINT `ciudades_ibfk_1` FOREIGN KEY (`id_departamento`) REFERENCES `departamentos` (`id_departamento`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ciudades`
--

LOCK TABLES `ciudades` WRITE;
/*!40000 ALTER TABLE `ciudades` DISABLE KEYS */;
INSERT INTO `ciudades` VALUES (1,'Medellín',1),(2,'Bello',1),(3,'Itagüí',1),(4,'Envigado',1),(5,'Sabaneta',1),(6,'La Estrella',1),(7,'Bogotá',2),(8,'Cali',3),(9,'Palmira',3),(10,'Buenaventura',3),(11,'Cartago',3),(12,'Barranquilla',4),(13,'Soledad',4),(14,'Malambo',4),(15,'Bucaramanga',5),(16,'Floridablanca',5),(17,'Girón',5),(18,'Piedecuesta',5),(19,'Cartagena',6),(20,'Magangué',6),(21,'Soacha',7),(22,'Facatativá',7),(23,'Zipaquirá',7),(24,'Chía',7),(25,'Mosquera',7),(26,'Los Angeles',34),(27,'San Francisco',34),(28,'San Diego',34),(29,'Sacramento',34),(30,'Miami',35),(31,'Orlando',35),(32,'Tampa',35),(33,'Jacksonville',35),(34,'Houston',36),(35,'Dallas',36),(36,'Austin',36),(37,'San Antonio',36),(38,'New York City',37),(39,'Albany',37),(40,'Buffalo',37),(41,'Chicago',38),(42,'Aurora',38),(43,'Rockford',38);
/*!40000 ALTER TABLE `ciudades` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `clientes`
--

DROP TABLE IF EXISTS `clientes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `clientes` (
  `id_cliente` int NOT NULL AUTO_INCREMENT,
  `tipo_identificacion` int NOT NULL,
  `numero_identificacion` varchar(20) NOT NULL,
  `nombre_cliente` varchar(50) NOT NULL,
  `apellidos_cliente` varchar(50) NOT NULL,
  `fecha_nacimiento` date NOT NULL,
  `direccion` varchar(200) NOT NULL,
  `ciudad` int NOT NULL,
  PRIMARY KEY (`id_cliente`),
  UNIQUE KEY `numero_identificacion` (`numero_identificacion`),
  KEY `tipo_identificacion` (`tipo_identificacion`),
  KEY `ciudad` (`ciudad`),
  CONSTRAINT `clientes_ibfk_1` FOREIGN KEY (`tipo_identificacion`) REFERENCES `tipos_identificacion` (`id_tipo_identificacion`),
  CONSTRAINT `clientes_ibfk_2` FOREIGN KEY (`ciudad`) REFERENCES `ciudades` (`id_ciudad`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clientes`
--

LOCK TABLES `clientes` WRITE;
/*!40000 ALTER TABLE `clientes` DISABLE KEYS */;
INSERT INTO `clientes` VALUES (1,1,'1029720617','Alderney','Ramirez Hurtado','2025-08-25','calle 104c',2),(2,1,'1038095042','mariana','Ramirez Hurtado','2025-08-07','calle 104 21',4),(3,1,'102972067','marianaa','luz','2025-08-05','callae 106',7),(4,1,'1029726556','marlos','martcadavid','2025-08-13','calle 266',7),(5,1,'21','ased','rarri','2025-07-28','calle 104 21',1),(6,5,'9','asedca','marcos','2025-07-08','calle 104 29',1),(7,2,'25678','chamo','zuela','2025-08-11','calle 214',26);
/*!40000 ALTER TABLE `clientes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `departamentos`
--

DROP TABLE IF EXISTS `departamentos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `departamentos` (
  `id_departamento` int NOT NULL AUTO_INCREMENT,
  `nombre_departamento` varchar(100) NOT NULL,
  `id_pais` int NOT NULL,
  PRIMARY KEY (`id_departamento`),
  KEY `id_pais` (`id_pais`),
  CONSTRAINT `departamentos_ibfk_1` FOREIGN KEY (`id_pais`) REFERENCES `paises` (`id_pais`)
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `departamentos`
--

LOCK TABLES `departamentos` WRITE;
/*!40000 ALTER TABLE `departamentos` DISABLE KEYS */;
INSERT INTO `departamentos` VALUES (1,'Antioquia',1),(2,'Bogotá D.C.',1),(3,'Valle del Cauca',1),(4,'Atlántico',1),(5,'Santander',1),(6,'Bolívar',1),(7,'Cundinamarca',1),(8,'Norte de Santander',1),(9,'Córdoba',1),(10,'Tolima',1),(11,'Huila',1),(12,'Nariño',1),(13,'Meta',1),(14,'Boyacá',1),(15,'Caldas',1),(16,'Magdalena',1),(17,'Cauca',1),(18,'La Guajira',1),(19,'Cesar',1),(20,'Sucre',1),(21,'Quindío',1),(22,'Risaralda',1),(23,'Casanare',1),(24,'Chocó',1),(25,'Caquetá',1),(26,'Putumayo',1),(27,'San Andrés y Providencia',1),(28,'Arauca',1),(29,'Amazonas',1),(30,'Guainía',1),(31,'Guaviare',1),(32,'Vaupés',1),(33,'Vichada',1),(34,'California',2),(35,'Florida',2),(36,'Texas',2),(37,'New York',2),(38,'Illinois',2),(39,'Pennsylvania',2),(40,'Ohio',2),(41,'Georgia',2),(42,'North Carolina',2),(43,'Michigan',2),(44,'New Jersey',2),(45,'Virginia',2),(46,'Washington',2),(47,'Arizona',2),(48,'Massachusetts',2),(49,'Tennessee',2),(50,'Indiana',2),(51,'Missouri',2),(52,'Maryland',2),(53,'Wisconsin',2);
/*!40000 ALTER TABLE `departamentos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fidelizacion`
--

DROP TABLE IF EXISTS `fidelizacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `fidelizacion` (
  `id_fidelizacion` int NOT NULL AUTO_INCREMENT,
  `cliente` int NOT NULL,
  `marca` int NOT NULL,
  `fecha_fidelizacion` date DEFAULT (curdate()),
  PRIMARY KEY (`id_fidelizacion`),
  UNIQUE KEY `unique_cliente_marca` (`cliente`,`marca`),
  KEY `marca` (`marca`),
  CONSTRAINT `fidelizacion_ibfk_1` FOREIGN KEY (`cliente`) REFERENCES `clientes` (`id_cliente`),
  CONSTRAINT `fidelizacion_ibfk_2` FOREIGN KEY (`marca`) REFERENCES `marcas` (`id_marca`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fidelizacion`
--

LOCK TABLES `fidelizacion` WRITE;
/*!40000 ALTER TABLE `fidelizacion` DISABLE KEYS */;
INSERT INTO `fidelizacion` VALUES (1,1,1,'2025-08-31'),(2,2,1,'2025-08-31'),(3,6,5,'2025-08-31'),(4,7,4,'2025-08-31');
/*!40000 ALTER TABLE `fidelizacion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `marcas`
--

DROP TABLE IF EXISTS `marcas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `marcas` (
  `id_marca` int NOT NULL AUTO_INCREMENT,
  `marca` varchar(100) NOT NULL,
  PRIMARY KEY (`id_marca`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `marcas`
--

LOCK TABLES `marcas` WRITE;
/*!40000 ALTER TABLE `marcas` DISABLE KEYS */;
INSERT INTO `marcas` VALUES (1,'Americanino'),(2,'American Eagle'),(3,'Chevignon'),(4,'Esprit'),(5,'Naf Naf'),(6,'Rifle');
/*!40000 ALTER TABLE `marcas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `paises`
--

DROP TABLE IF EXISTS `paises`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `paises` (
  `id_pais` int NOT NULL AUTO_INCREMENT,
  `nombre_pais` varchar(100) NOT NULL,
  PRIMARY KEY (`id_pais`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `paises`
--

LOCK TABLES `paises` WRITE;
/*!40000 ALTER TABLE `paises` DISABLE KEYS */;
INSERT INTO `paises` VALUES (1,'Colombia'),(2,'Estados Unidos');
/*!40000 ALTER TABLE `paises` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipos_identificacion`
--

DROP TABLE IF EXISTS `tipos_identificacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tipos_identificacion` (
  `id_tipo_identificacion` int NOT NULL AUTO_INCREMENT,
  `tipo_identificacion` varchar(100) NOT NULL,
  PRIMARY KEY (`id_tipo_identificacion`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipos_identificacion`
--

LOCK TABLES `tipos_identificacion` WRITE;
/*!40000 ALTER TABLE `tipos_identificacion` DISABLE KEYS */;
INSERT INTO `tipos_identificacion` VALUES (1,'Cédula de Ciudadanía'),(2,'Cédula de Extranjería'),(3,'Pasaporte'),(4,'Tarjeta de Identidad'),(5,'NIT'),(6,'SSN');
/*!40000 ALTER TABLE `tipos_identificacion` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-08-31 19:52:57
