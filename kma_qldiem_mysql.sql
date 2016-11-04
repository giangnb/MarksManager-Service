-- phpMyAdmin SQL Dump
-- version 4.2.11
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Nov 03, 2016 at 02:40 PM
-- Server version: 5.6.21
-- PHP Version: 5.6.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+07:00";

CREATE DATABASE marksmanadb DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci;

CREATE USER 'dbuser' IDENTIFIED BY '@kFu004o';
GRANT ALL PRIVILEGES ON marksmanadb.* TO 'dbuser';

USE marksmanadb;

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `kma_qldiem`
--

-- --------------------------------------------------------

--
-- Table structure for table `t_admin`
--

CREATE TABLE IF NOT EXISTS `t_admin` (
  `id` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  `pass` text COLLATE utf8_unicode_ci NOT NULL,
  `prohibited` text COLLATE utf8_unicode_ci NOT NULL,
  `last_login` bigint(20) NOT NULL,
  `last_change` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `t_bulk`
--

CREATE TABLE IF NOT EXISTS `t_bulk` (
`id` int(11) NOT NULL,
  `name` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `info` text COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `t_bulk_subject`
--

CREATE TABLE IF NOT EXISTS `t_bulk_subject` (
  `bulkId` int(11) NOT NULL,
  `subjectId` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `t_class`
--

CREATE TABLE IF NOT EXISTS `t_class` (
`id` int(11) NOT NULL,
  `name` varchar(16) COLLATE utf8_unicode_ci NOT NULL,
  `teacherId` int(11) NOT NULL,
  `bulkId` int(11) NOT NULL,
  `info` text COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `t_properties`
--

CREATE TABLE IF NOT EXISTS `t_properties` (
  `_key` varchar(128) COLLATE utf8_unicode_ci NOT NULL,
  `_value` mediumtext COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `t_score`
--

CREATE TABLE IF NOT EXISTS `t_score` (
`id` bigint(20) NOT NULL,
  `subjectId` int(11) NOT NULL,
  `studentId` int(11) NOT NULL,
  `teacherId` int(11) NOT NULL,
  `score` float NOT NULL,
  `coefficient` smallint(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `t_scorelog`
--

CREATE TABLE IF NOT EXISTS `t_scorelog` (
`id` int(11) NOT NULL,
  `studentId` int(11) NOT NULL,
  `scores` text COLLATE utf8_unicode_ci NOT NULL,
  `remarks` text COLLATE utf8_unicode_ci NOT NULL,
  `school_year` varchar(64) COLLATE utf8_unicode_ci NOT NULL DEFAULT ''
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `t_student`
--

CREATE TABLE IF NOT EXISTS `t_student` (
`id` int(11) NOT NULL,
  `name` varchar(128) COLLATE utf8_unicode_ci NOT NULL,
  `classId` int(11) NOT NULL,
  `info` mediumtext COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `t_subject`
--

CREATE TABLE IF NOT EXISTS `t_subject` (
`id` int(11) NOT NULL,
  `name` varchar(128) COLLATE utf8_unicode_ci NOT NULL,
  `info` text COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `t_teacher`
--

CREATE TABLE IF NOT EXISTS `t_teacher` (
`id` int(11) NOT NULL,
  `username` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `pass` text COLLATE utf8_unicode_ci,
  `name` varchar(128) COLLATE utf8_unicode_ci NOT NULL,
  `info` mediumtext COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `t_teacher_subject`
--

CREATE TABLE IF NOT EXISTS `t_teacher_subject` (
  `teacherId` int(11) NOT NULL,
  `subjectId` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `t_admin`
--
ALTER TABLE `t_admin`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `t_bulk`
--
ALTER TABLE `t_bulk`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `t_bulk_subject`
--
ALTER TABLE `t_bulk_subject`
 ADD PRIMARY KEY (`bulkId`,`subjectId`), ADD KEY `FK_bulksubject_subj` (`subjectId`);

--
-- Indexes for table `t_class`
--
ALTER TABLE `t_class`
 ADD PRIMARY KEY (`id`), ADD KEY `FK_class_teacher` (`teacherId`), ADD KEY `FK_class_bulk` (`bulkId`);

--
-- Indexes for table `t_properties`
--
ALTER TABLE `t_properties`
 ADD PRIMARY KEY (`_key`);

--
-- Indexes for table `t_score`
--
ALTER TABLE `t_score`
 ADD PRIMARY KEY (`id`), ADD KEY `FK_score_subject` (`subjectId`), ADD KEY `FK_score_student` (`studentId`), ADD KEY `FK_score_teacher` (`teacherId`);

--
-- Indexes for table `t_scorelog`
--
ALTER TABLE `t_scorelog`
 ADD PRIMARY KEY (`id`), ADD KEY `FK_log_student` (`studentId`);

--
-- Indexes for table `t_student`
--
ALTER TABLE `t_student`
 ADD PRIMARY KEY (`id`), ADD KEY `FK_student_class` (`classId`), ADD FULLTEXT KEY `info` (`info`), ADD FULLTEXT KEY `name` (`name`);

--
-- Indexes for table `t_subject`
--
ALTER TABLE `t_subject`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `t_teacher`
--
ALTER TABLE `t_teacher`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `t_teacher_subject`
--
ALTER TABLE `t_teacher_subject`
 ADD PRIMARY KEY (`teacherId`,`subjectId`), ADD KEY `FK_teachsubj_subject` (`subjectId`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `t_bulk`
--
ALTER TABLE `t_bulk`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `t_class`
--
ALTER TABLE `t_class`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `t_score`
--
ALTER TABLE `t_score`
MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `t_scorelog`
--
ALTER TABLE `t_scorelog`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `t_student`
--
ALTER TABLE `t_student`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `t_subject`
--
ALTER TABLE `t_subject`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `t_teacher`
--
ALTER TABLE `t_teacher`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `t_bulk_subject`
--
ALTER TABLE `t_bulk_subject`
ADD CONSTRAINT `FK_bulksubject_bulk` FOREIGN KEY (`bulkId`) REFERENCES `t_bulk` (`id`),
ADD CONSTRAINT `FK_bulksubject_subj` FOREIGN KEY (`subjectId`) REFERENCES `t_subject` (`id`);

--
-- Constraints for table `t_class`
--
ALTER TABLE `t_class`
ADD CONSTRAINT `FK_class_bulk` FOREIGN KEY (`bulkId`) REFERENCES `t_bulk` (`id`),
ADD CONSTRAINT `FK_class_teacher` FOREIGN KEY (`teacherId`) REFERENCES `t_teacher` (`id`);

--
-- Constraints for table `t_score`
--
ALTER TABLE `t_score`
ADD CONSTRAINT `FK_score_student` FOREIGN KEY (`studentId`) REFERENCES `t_student` (`id`),
ADD CONSTRAINT `FK_score_subject` FOREIGN KEY (`subjectId`) REFERENCES `t_subject` (`id`),
ADD CONSTRAINT `FK_score_teacher` FOREIGN KEY (`teacherId`) REFERENCES `t_teacher` (`id`);

--
-- Constraints for table `t_scorelog`
--
ALTER TABLE `t_scorelog`
ADD CONSTRAINT `FK_log_student` FOREIGN KEY (`studentId`) REFERENCES `t_student` (`id`);

--
-- Constraints for table `t_student`
--
ALTER TABLE `t_student`
ADD CONSTRAINT `FK_student_class` FOREIGN KEY (`classId`) REFERENCES `t_class` (`id`);

--
-- Constraints for table `t_teacher_subject`
--
ALTER TABLE `t_teacher_subject`
ADD CONSTRAINT `FK_teachsubj_subject` FOREIGN KEY (`subjectId`) REFERENCES `t_subject` (`id`),
ADD CONSTRAINT `FK_teachsubj_teacher` FOREIGN KEY (`teacherId`) REFERENCES `t_teacher` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
