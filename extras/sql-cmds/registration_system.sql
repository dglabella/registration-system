-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3308
-- Generation Time: Aug 27, 2022 at 10:17 AM
-- Server version: 8.0.18
-- PHP Version: 7.3.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `registration_system`
--

-- --------------------------------------------------------

--
-- Table structure for table `accesses`
--

DROP TABLE IF EXISTS `accesses`;
CREATE TABLE IF NOT EXISTS `accesses` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `active` bit(1) NOT NULL,
  `description` varchar(100) DEFAULT NULL,
  `name` varchar(30) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `accesses`
--

INSERT INTO `accesses` (`id`, `active`, `description`, `name`) VALUES
(1, b'1', 'ZOOM INGRESO, PLANTA BAJA DE RECTORADO', 'RECTORADO'),
(2, b'1', 'BLINDEX FRENTE AL ANFITEATRO II', 'FCFMYN');

-- --------------------------------------------------------

--
-- Table structure for table `dependencies`
--

DROP TABLE IF EXISTS `dependencies`;
CREATE TABLE IF NOT EXISTS `dependencies` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `active` bit(1) NOT NULL,
  `description` varchar(100) DEFAULT NULL,
  `name` varchar(30) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `dependencies`
--

INSERT INTO `dependencies` (`id`, `active`, `description`, `name`) VALUES
(1, b'1', NULL, 'RECTORADO'),
(2, b'1', NULL, 'FCFMYN');

-- --------------------------------------------------------

--
-- Table structure for table `persons`
--

DROP TABLE IF EXISTS `persons`;
CREATE TABLE IF NOT EXISTS `persons` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `active` bit(1) NOT NULL,
  `dni` varchar(10) NOT NULL,
  `last_name` varchar(30) NOT NULL,
  `name` varchar(30) NOT NULL,
  `dependency_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_t0tma5e5ec4leolu2gaqpc9v7` (`dni`),
  KEY `FKn89hqn6601ert5dl80ks2wmdc` (`dependency_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `persons`
--

INSERT INTO `persons` (`id`, `active`, `dni`, `last_name`, `name`, `dependency_id`) VALUES
(1, b'1', '35069255', 'LABELLA', 'DANILO', 2),
(2, b'1', '25029754', 'TISSERA', 'CRISTIAN', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `registers`
--

DROP TABLE IF EXISTS `registers`;
CREATE TABLE IF NOT EXISTS `registers` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `active` bit(1) NOT NULL,
  `check_in` datetime(6) NOT NULL,
  `check_out` datetime(6) DEFAULT NULL,
  `access_id` int(11) DEFAULT NULL,
  `person_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKniv3qtb3q1613nqmu0du6jfq8` (`access_id`),
  KEY `FKh67wxpqn6cwvcydc6u364r19l` (`person_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE IF NOT EXISTS `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `active` bit(1) NOT NULL,
  `dni` varchar(10) NOT NULL,
  `last_name` varchar(30) NOT NULL,
  `name` varchar(30) NOT NULL,
  `password` varchar(20) NOT NULL,
  `user_name` varchar(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_6aphui3g30h49muho4c91n0yl` (`dni`),
  UNIQUE KEY `UK_k8d0f2n7n88w1a16yhua64onx` (`user_name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `active`, `dni`, `last_name`, `name`, `password`, `user_name`) VALUES
(1, b'1', '35069255', 'LABELLA', 'DANILO', 'indulecyen', 'dglabella'),
(2, b'1', '25029754', 'TISSERA', 'CRISTIAN', '876pqqj234', 'ptissera');

-- --------------------------------------------------------

--
-- Table structure for table `weeklies`
--

DROP TABLE IF EXISTS `weeklies`;
CREATE TABLE IF NOT EXISTS `weeklies` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `active` bit(1) NOT NULL,
  `expiration` datetime(6) DEFAULT NULL,
  `friday` bit(1) NOT NULL,
  `monday` bit(1) NOT NULL,
  `saturday` bit(1) NOT NULL,
  `sunday` bit(1) NOT NULL,
  `thursday` bit(1) NOT NULL,
  `tuesday` bit(1) NOT NULL,
  `validity` datetime(6) NOT NULL,
  `wednesday` bit(1) NOT NULL,
  `person_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKros1a1hbuvrxpex6m3qsjb8op` (`person_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `persons`
--
ALTER TABLE `persons`
  ADD CONSTRAINT `FKn89hqn6601ert5dl80ks2wmdc` FOREIGN KEY (`dependency_id`) REFERENCES `dependencies` (`id`);

--
-- Constraints for table `registers`
--
ALTER TABLE `registers`
  ADD CONSTRAINT `FKh67wxpqn6cwvcydc6u364r19l` FOREIGN KEY (`person_id`) REFERENCES `persons` (`id`),
  ADD CONSTRAINT `FKniv3qtb3q1613nqmu0du6jfq8` FOREIGN KEY (`access_id`) REFERENCES `accesses` (`id`);

--
-- Constraints for table `weeklies`
--
ALTER TABLE `weeklies`
  ADD CONSTRAINT `FKros1a1hbuvrxpex6m3qsjb8op` FOREIGN KEY (`person_id`) REFERENCES `persons` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
