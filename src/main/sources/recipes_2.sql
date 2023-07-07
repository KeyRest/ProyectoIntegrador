-- MySQL dump 10.13  Distrib 8.0.33, for Win64 (x86_64)
--
-- Host: localhost    Database: recipes
-- ------------------------------------------------------
-- Server version	8.0.33

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
-- Table structure for table `categories`
--

DROP TABLE IF EXISTS `categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categories` (
  `id` int NOT NULL AUTO_INCREMENT,
  `category` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categories`
--

LOCK TABLES `categories` WRITE;
/*!40000 ALTER TABLE `categories` DISABLE KEYS */;
INSERT INTO `categories` VALUES (1,'Almuerzo'),(2,'Buffete');
/*!40000 ALTER TABLE `categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `featured_recipe`
--

DROP TABLE IF EXISTS `featured_recipe`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `featured_recipe` (
  `id` int NOT NULL AUTO_INCREMENT,
  `start_date` date NOT NULL,
  `end_date` date NOT NULL,
  `recipes_id` int NOT NULL,
  PRIMARY KEY (`id`,`recipes_id`),
  KEY `fk_featured_recipe_recipes1_idx` (`recipes_id`),
  CONSTRAINT `fk_featured_recipe_recipes1` FOREIGN KEY (`recipes_id`) REFERENCES `recipes` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `featured_recipe`
--

LOCK TABLES `featured_recipe` WRITE;
/*!40000 ALTER TABLE `featured_recipe` DISABLE KEYS */;
/*!40000 ALTER TABLE `featured_recipe` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ingredients`
--

DROP TABLE IF EXISTS `ingredients`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ingredients` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `description` varchar(150) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ingredients`
--

LOCK TABLES `ingredients` WRITE;
/*!40000 ALTER TABLE `ingredients` DISABLE KEYS */;
INSERT INTO `ingredients` VALUES (1,'azucar','2 tazas'),(2,'leche','1 taza'),(3,'chicharrones','1 taza'),(4,'chicheme','1 taza');
/*!40000 ALTER TABLE `ingredients` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `levels`
--

DROP TABLE IF EXISTS `levels`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `levels` (
  `id` int NOT NULL AUTO_INCREMENT,
  `level` varchar(15) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `levels`
--

LOCK TABLES `levels` WRITE;
/*!40000 ALTER TABLE `levels` DISABLE KEYS */;
INSERT INTO `levels` VALUES (1,'Fácil'),(2,'Difícil'),(3,'Muy dificil');
/*!40000 ALTER TABLE `levels` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `measurement_units`
--

DROP TABLE IF EXISTS `measurement_units`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `measurement_units` (
  `id` int NOT NULL AUTO_INCREMENT,
  `measurement_unit` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `measurement_units`
--

LOCK TABLES `measurement_units` WRITE;
/*!40000 ALTER TABLE `measurement_units` DISABLE KEYS */;
INSERT INTO `measurement_units` VALUES (1,'kilogramos');
/*!40000 ALTER TABLE `measurement_units` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `occasions`
--

DROP TABLE IF EXISTS `occasions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `occasions` (
  `id` int NOT NULL AUTO_INCREMENT,
  `occasion` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `occasions`
--

LOCK TABLES `occasions` WRITE;
/*!40000 ALTER TABLE `occasions` DISABLE KEYS */;
INSERT INTO `occasions` VALUES (1,'Todos los días'),(2,'Noche');
/*!40000 ALTER TABLE `occasions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `profile`
--

DROP TABLE IF EXISTS `profile`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `profile` (
  `id` int NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(45) NOT NULL,
  `user_type` varchar(15) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `profile`
--

LOCK TABLES `profile` WRITE;
/*!40000 ALTER TABLE `profile` DISABLE KEYS */;
/*!40000 ALTER TABLE `profile` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `recipes`
--

DROP TABLE IF EXISTS `recipes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `recipes` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `image` varchar(45) NOT NULL,
  `preparation_time` float NOT NULL,
  `cooking_time` float NOT NULL,
  `description` tinytext NOT NULL,
  `preparation_instructions` varchar(45) NOT NULL,
  `portions` int NOT NULL,
  `total_time` float NOT NULL,
  `levels_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_recipes_levels1_idx` (`levels_id`),
  CONSTRAINT `fk_recipes_levels1` FOREIGN KEY (`levels_id`) REFERENCES `levels` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recipes`
--

LOCK TABLES `recipes` WRITE;
/*!40000 ALTER TABLE `recipes` DISABLE KEYS */;
INSERT INTO `recipes` VALUES (1,'pan','img.png',120,30,'pan delicioso','Haga el pan',3,1,1),(2,'pan tico','img.png',120,30,'pan delicioso','Haga el pan',3,1,1),(3,'gallo pinto','img.png',120,30,'pan delicioso','Haga el pan',3,1,1),(4,'arroz con pollo','img.png',120,30,'pan delicioso','Haga el pan',3,1,1),(5,'Queque','imgQueqeu.png',120,30,'pan delicioso','Haga el pan',3,1,1),(6,'arroz con pollo','impollo.png',60,30,'arroz con pollo delicioso','Haga el arroz y el pollo',4,2,2);
/*!40000 ALTER TABLE `recipes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `recipes_has_categories`
--

DROP TABLE IF EXISTS `recipes_has_categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `recipes_has_categories` (
  `recipes_id` int NOT NULL,
  `categories_id` int NOT NULL,
  PRIMARY KEY (`recipes_id`,`categories_id`),
  KEY `fk_recipes_has_categories_categories1_idx` (`categories_id`),
  KEY `fk_recipes_has_categories_recipes1_idx` (`recipes_id`),
  CONSTRAINT `fk_recipes_has_categories_categories1` FOREIGN KEY (`categories_id`) REFERENCES `categories` (`id`),
  CONSTRAINT `fk_recipes_has_categories_recipes1` FOREIGN KEY (`recipes_id`) REFERENCES `recipes` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recipes_has_categories`
--

LOCK TABLES `recipes_has_categories` WRITE;
/*!40000 ALTER TABLE `recipes_has_categories` DISABLE KEYS */;
/*!40000 ALTER TABLE `recipes_has_categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `recipes_has_ingredients`
--

DROP TABLE IF EXISTS `recipes_has_ingredients`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `recipes_has_ingredients` (
  `recipes_id` int NOT NULL,
  `ingredients_id` int NOT NULL,
  `measurement_units_id` int NOT NULL,
  `amount` float NOT NULL,
  PRIMARY KEY (`recipes_id`,`ingredients_id`,`measurement_units_id`),
  KEY `fk_recipes_has_ingredients_ingredients1_idx` (`ingredients_id`),
  KEY `fk_recipes_has_ingredients_recipes_idx` (`recipes_id`),
  KEY `fk_recipes_has_ingredients_measurement_units1_idx` (`measurement_units_id`),
  CONSTRAINT `fk_recipes_has_ingredients_ingredients1` FOREIGN KEY (`ingredients_id`) REFERENCES `ingredients` (`id`),
  CONSTRAINT `fk_recipes_has_ingredients_measurement_units1` FOREIGN KEY (`measurement_units_id`) REFERENCES `measurement_units` (`id`),
  CONSTRAINT `fk_recipes_has_ingredients_recipes` FOREIGN KEY (`recipes_id`) REFERENCES `recipes` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recipes_has_ingredients`
--

LOCK TABLES `recipes_has_ingredients` WRITE;
/*!40000 ALTER TABLE `recipes_has_ingredients` DISABLE KEYS */;
INSERT INTO `recipes_has_ingredients` VALUES (1,1,1,3);
/*!40000 ALTER TABLE `recipes_has_ingredients` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `recipes_has_occasions`
--

DROP TABLE IF EXISTS `recipes_has_occasions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `recipes_has_occasions` (
  `recipes_id` int NOT NULL,
  `occasions_id` int NOT NULL,
  PRIMARY KEY (`recipes_id`,`occasions_id`),
  KEY `fk_recipes_has_occasions_occasions1_idx` (`occasions_id`),
  KEY `fk_recipes_has_occasions_recipes1_idx` (`recipes_id`),
  CONSTRAINT `fk_recipes_has_occasions_occasions1` FOREIGN KEY (`occasions_id`) REFERENCES `occasions` (`id`),
  CONSTRAINT `fk_recipes_has_occasions_recipes1` FOREIGN KEY (`recipes_id`) REFERENCES `recipes` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recipes_has_occasions`
--

LOCK TABLES `recipes_has_occasions` WRITE;
/*!40000 ALTER TABLE `recipes_has_occasions` DISABLE KEYS */;
INSERT INTO `recipes_has_occasions` VALUES (1,1);
/*!40000 ALTER TABLE `recipes_has_occasions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `last_name` varchar(45) NOT NULL,
  `country` varchar(45) NOT NULL,
  `email` varchar(50) NOT NULL,
  `password` varchar(40) NOT NULL,
  `profile_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_users_profile1_idx` (`profile_id`),
  CONSTRAINT `fk_users_profile1` FOREIGN KEY (`profile_id`) REFERENCES `profile` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users_save_recipes`
--

DROP TABLE IF EXISTS `users_save_recipes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users_save_recipes` (
  `users_id` int NOT NULL,
  `recipes_id` int NOT NULL,
  `date` date DEFAULT NULL,
  PRIMARY KEY (`users_id`,`recipes_id`),
  KEY `fk_users_has_recipes_recipes2_idx` (`recipes_id`),
  KEY `fk_users_has_recipes_users2_idx` (`users_id`),
  CONSTRAINT `fk_users_has_recipes_recipes2` FOREIGN KEY (`recipes_id`) REFERENCES `recipes` (`id`),
  CONSTRAINT `fk_users_has_recipes_users2` FOREIGN KEY (`users_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users_save_recipes`
--

LOCK TABLES `users_save_recipes` WRITE;
/*!40000 ALTER TABLE `users_save_recipes` DISABLE KEYS */;
/*!40000 ALTER TABLE `users_save_recipes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users_vote_recipes`
--

DROP TABLE IF EXISTS `users_vote_recipes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users_vote_recipes` (
  `users_id` int NOT NULL,
  `recipes_id` int NOT NULL,
  `date` date NOT NULL,
  PRIMARY KEY (`users_id`,`recipes_id`),
  KEY `fk_users_has_recipes_recipes1_idx` (`recipes_id`),
  KEY `fk_users_has_recipes_users1_idx` (`users_id`),
  CONSTRAINT `fk_users_has_recipes_recipes1` FOREIGN KEY (`recipes_id`) REFERENCES `recipes` (`id`),
  CONSTRAINT `fk_users_has_recipes_users1` FOREIGN KEY (`users_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users_vote_recipes`
--

LOCK TABLES `users_vote_recipes` WRITE;
/*!40000 ALTER TABLE `users_vote_recipes` DISABLE KEYS */;
/*!40000 ALTER TABLE `users_vote_recipes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `vista_recipes_with_levels`
--

DROP TABLE IF EXISTS `vista_recipes_with_levels`;
/*!50001 DROP VIEW IF EXISTS `vista_recipes_with_levels`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `vista_recipes_with_levels` AS SELECT 
 1 AS `id`,
 1 AS `name`,
 1 AS `image`,
 1 AS `preparation_time`,
 1 AS `cooking_time`,
 1 AS `description`,
 1 AS `preparation_instructions`,
 1 AS `portions`,
 1 AS `total_time`,
 1 AS `level`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `vistis`
--

DROP TABLE IF EXISTS `vistis`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vistis` (
  `id` int NOT NULL AUTO_INCREMENT,
  `date` datetime NOT NULL,
  `recipes_id` int NOT NULL,
  `users_id1` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_vistis_recipes1_idx` (`recipes_id`),
  CONSTRAINT `fk_vistis_recipes1` FOREIGN KEY (`recipes_id`) REFERENCES `recipes` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vistis`
--

LOCK TABLES `vistis` WRITE;
/*!40000 ALTER TABLE `vistis` DISABLE KEYS */;
/*!40000 ALTER TABLE `vistis` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Final view structure for view `vista_recipes_with_levels`
--

/*!50001 DROP VIEW IF EXISTS `vista_recipes_with_levels`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `vista_recipes_with_levels` AS select `r`.`id` AS `id`,`r`.`name` AS `name`,`r`.`image` AS `image`,`r`.`preparation_time` AS `preparation_time`,`r`.`cooking_time` AS `cooking_time`,`r`.`description` AS `description`,`r`.`preparation_instructions` AS `preparation_instructions`,`r`.`portions` AS `portions`,`r`.`total_time` AS `total_time`,`l`.`level` AS `level` from (`recipes` `r` join `levels` `l` on((`r`.`levels_id` = `l`.`id`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-07-05 11:43:38
