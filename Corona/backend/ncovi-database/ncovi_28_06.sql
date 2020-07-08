-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               10.4.6-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win64
-- HeidiSQL Version:             9.5.0.5196
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Dumping structure for table ncovi-hdv.account
CREATE TABLE IF NOT EXISTS `account` (
  `account_id` int(11) NOT NULL AUTO_INCREMENT,
  `account_username` varchar(45) NOT NULL,
  `account_password` varchar(100) DEFAULT NULL,
  `account_name` varchar(45) NOT NULL,
  `account_status` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `account_type` int(11) NOT NULL,
  `account_created_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  PRIMARY KEY (`account_id`),
  KEY `fk_account_user_idx` (`user_id`),
  CONSTRAINT `fk_account_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

-- Dumping data for table ncovi-hdv.account: ~2 rows (approximately)
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` (`account_id`, `account_username`, `account_password`, `account_name`, `account_status`, `user_id`, `account_type`, `account_created_at`) VALUES
	(1, '2397626507022598', NULL, 'Hoàng Mạnh Tùng', 1, 6, 2, '2020-06-06 21:20:09'),
	(2, 'ducmp', '$2a$10$qjV/0FBb53eRYPqt33DF1OQe0Ael9NPTVT8y1ei3T2u2Zzb4Gu8j.', 'Pham Minh Duc', 1, 12, 0, '2020-06-11 15:47:48');
/*!40000 ALTER TABLE `account` ENABLE KEYS */;

-- Dumping structure for table ncovi-hdv.health_monitor
CREATE TABLE IF NOT EXISTS `health_monitor` (
  `health_monitor_id` int(11) NOT NULL AUTO_INCREMENT,
  `health_monitor_sick` int(6) DEFAULT NULL,
  `health_monitor_cough` int(6) DEFAULT NULL,
  `health_monitor_sultry` int(6) DEFAULT NULL,
  `health_monitor_tired` int(6) DEFAULT NULL,
  `health_monitor_good` int(6) DEFAULT NULL,
  `health_monitor_status` int(6) DEFAULT NULL,
  `health_monitor_description` varchar(50) DEFAULT NULL,
  `health_monitor_create_at` timestamp NULL DEFAULT NULL,
  `user` int(11) NOT NULL,
  PRIMARY KEY (`health_monitor_id`),
  KEY `FK_health_monitor_user` (`user`),
  CONSTRAINT `FK_health_monitor_user` FOREIGN KEY (`user`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

-- Dumping data for table ncovi-hdv.health_monitor: ~13 rows (approximately)
/*!40000 ALTER TABLE `health_monitor` DISABLE KEYS */;
INSERT INTO `health_monitor` (`health_monitor_id`, `health_monitor_sick`, `health_monitor_cough`, `health_monitor_sultry`, `health_monitor_tired`, `health_monitor_good`, `health_monitor_status`, `health_monitor_description`, `health_monitor_create_at`, `user`) VALUES
	(8, 0, 0, 0, 0, 1, 1, 'Bình thường', '2020-06-13 09:21:38', 12),
	(9, 0, 1, 0, 1, 0, 0, 'Ho, Đau người, mệt mỏi', '2020-06-13 09:27:55', 12),
	(10, 0, 1, 0, 1, 0, 0, 'Ho, Đau người, mệt mỏi', '2020-06-13 10:42:48', 12),
	(11, 0, 1, 0, 1, 0, 0, 'Ho, Đau người, mệt mỏi', '2020-06-13 10:42:51', 12),
	(12, 1, 1, 0, 1, 0, 0, 'Sốt, Ho, Đau người, mệt mỏi', '2020-06-13 10:42:55', 12),
	(13, 1, 1, 1, 1, 0, 0, 'Sốt, Ho, Khó thở, Đau người, mệt mỏi', '2020-06-13 10:42:59', 12),
	(14, 1, 1, 0, 1, 0, 0, 'Sốt, Ho, Đau người, mệt mỏi', '2020-06-13 10:43:03', 12),
	(15, 1, 1, 0, 1, 0, 0, 'Sốt, Ho, Đau người, mệt mỏi', '2020-06-13 10:43:05', 12),
	(16, 1, 1, 0, 1, 0, 0, 'Sốt, Ho, Đau người, mệt mỏi', '2020-06-13 10:43:06', 12),
	(17, 1, 1, 0, 1, 0, 0, 'Sốt, Ho, Đau người, mệt mỏi', '2020-06-13 10:43:07', 12),
	(18, 1, 1, 0, 1, 0, 0, 'Sốt, Ho, Đau người, mệt mỏi', '2020-06-13 10:43:07', 12),
	(19, 1, 0, 0, 1, 0, 0, 'Sốt, Đau người, mệt mỏi', '2020-06-13 10:43:14', 12),
	(20, 1, 0, 0, 1, 0, 0, 'Sốt, Đau người, mệt mỏi', '2020-06-13 10:43:20', 12);
/*!40000 ALTER TABLE `health_monitor` ENABLE KEYS */;

-- Dumping structure for table ncovi-hdv.hibernate_sequence
CREATE TABLE IF NOT EXISTS `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- Dumping data for table ncovi-hdv.hibernate_sequence: 15 rows
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` (`next_val`) VALUES
	(2),
	(2),
	(2),
	(2),
	(2),
	(2),
	(2),
	(2),
	(2),
	(2),
	(2),
	(2),
	(2),
	(2),
	(2);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;

-- Dumping structure for table ncovi-hdv.notification
CREATE TABLE IF NOT EXISTS `notification` (
  `notification_id` int(11) NOT NULL AUTO_INCREMENT,
  `notification_title` varchar(100) NOT NULL,
  `notification_body` varchar(1000) NOT NULL,
  `notification_data` varchar(1000) DEFAULT NULL,
  `notification_created_at` timestamp(6) NOT NULL DEFAULT current_timestamp(6) ON UPDATE current_timestamp(6),
  `notification_status` int(11) NOT NULL,
  `notification_type` int(11) NOT NULL,
  `notification_user` varchar(255) DEFAULT NULL,
  `notification_send_at` timestamp(6) NULL DEFAULT NULL,
  PRIMARY KEY (`notification_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4;

-- Dumping data for table ncovi-hdv.notification: ~5 rows (approximately)
/*!40000 ALTER TABLE `notification` DISABLE KEYS */;
INSERT INTO `notification` (`notification_id`, `notification_title`, `notification_body`, `notification_data`, `notification_created_at`, `notification_status`, `notification_type`, `notification_user`, `notification_send_at`) VALUES
	(9, 'Thống kê cuối ngày', '23/06/2020, Việt Nam có tổng 349 bệnh nhân nhiễm Covid, 327 bệnh nhân hồi phục, 22 bệnh nhân đang điều trị, 0 bệnh nhân tử vong', '', '2020-06-22 23:22:47.598000', 1, 2, NULL, '2020-06-22 23:25:34.863000'),
	(10, 'Thống kê tuần', 'Trong tuần qua, Việt Nam có thêm 15 ca nhiễm mới, có 2 bệnh nhân hồi phục và không có ca tử vong nào', '', '2020-06-22 23:22:47.598000', 0, 2, NULL, '2020-06-23 02:00:00.000000'),
	(11, 'Cảnh báo ca nhiễm mới', 'Việt Nam phát hiện 3 ca nhiễm mới', '', '2020-06-28 01:45:13.938000', 0, 2, NULL, '2020-06-28 02:05:13.937000'),
	(12, 'Thông báo hồi phục', 'Việt Nam thêm 1 bệnh nhân hồi phục', '', '2020-06-28 01:45:13.938000', 0, 2, NULL, '2020-06-28 02:20:13.938000'),
	(13, 'Thống kê cuối ngày', '28/06/2020, Việt Nam có tổng 355 bệnh nhân nhiễm Covid, 330 bệnh nhân hồi phục, 25 bệnh nhân đang điều trị, 0 bệnh nhân tử vong', '', '2020-06-28 01:45:13.939000', 0, 2, NULL, '2020-06-28 05:00:00.000000');
/*!40000 ALTER TABLE `notification` ENABLE KEYS */;

-- Dumping structure for table ncovi-hdv.question
CREATE TABLE IF NOT EXISTS `question` (
  `question_id` int(11) NOT NULL,
  `question_name` varchar(1000) DEFAULT NULL,
  `question_result` int(6) DEFAULT NULL,
  PRIMARY KEY (`question_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table ncovi-hdv.question: ~0 rows (approximately)
/*!40000 ALTER TABLE `question` DISABLE KEYS */;
/*!40000 ALTER TABLE `question` ENABLE KEYS */;

-- Dumping structure for table ncovi-hdv.reflection
CREATE TABLE IF NOT EXISTS `reflection` (
  `reflection_id` int(11) NOT NULL AUTO_INCREMENT,
  `reflection_contact_question_1` int(11) DEFAULT NULL,
  `reflection_contact_question_2` int(11) DEFAULT NULL,
  `reflection_contact_question_3` int(11) DEFAULT NULL,
  `reflection_info_question_1` int(11) DEFAULT NULL,
  `reflection_info_question_2` int(11) DEFAULT NULL,
  `reflection_info_question_3` int(11) DEFAULT NULL,
  `reflection_info_description` varchar(256) DEFAULT NULL,
  `reflection_info_time` timestamp NULL DEFAULT NULL,
  `reflection_info_address` varchar(100) DEFAULT NULL,
  `reflection_type` int(11) DEFAULT NULL,
  `reflection_created_at` timestamp(6) NULL DEFAULT NULL,
  `user` int(11) NOT NULL,
  PRIMARY KEY (`reflection_id`),
  KEY `FK6l87jpeoy8ccpwuvs7w789kmb` (`user`),
  CONSTRAINT `FK6l87jpeoy8ccpwuvs7w789kmb` FOREIGN KEY (`user`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- Dumping data for table ncovi-hdv.reflection: ~4 rows (approximately)
/*!40000 ALTER TABLE `reflection` DISABLE KEYS */;
INSERT INTO `reflection` (`reflection_id`, `reflection_contact_question_1`, `reflection_contact_question_2`, `reflection_contact_question_3`, `reflection_info_question_1`, `reflection_info_question_2`, `reflection_info_question_3`, `reflection_info_description`, `reflection_info_time`, `reflection_info_address`, `reflection_type`, `reflection_created_at`, `user`) VALUES
	(1, 1, 0, 0, NULL, NULL, NULL, NULL, NULL, NULL, 1, '2020-06-13 10:58:54.257000', 12),
	(2, NULL, NULL, NULL, 0, 1, 1, 'ahihi do\' ngok', '2020-06-13 10:58:54', NULL, 0, '2020-06-13 11:02:16.536000', 12),
	(3, 1, 1, 0, NULL, NULL, NULL, NULL, NULL, NULL, 1, '2020-06-13 11:05:25.050000', 12),
	(4, NULL, NULL, NULL, 0, 1, 1, 'ahihi do\' ngok', '2020-06-13 10:58:54', NULL, 0, '2020-06-13 11:05:54.865000', 12),
	(5, NULL, NULL, NULL, 0, 1, 1, 'ahihi do\' ngok', '2020-06-13 10:58:54', 'ha noi', 0, '2020-06-28 01:50:06.905000', 12),
	(6, NULL, NULL, NULL, 0, 1, 1, 'ahihi do\' ngok', '2020-06-13 10:58:54', 'Hà Nội', 0, '2020-06-28 01:50:35.204000', 12);
/*!40000 ALTER TABLE `reflection` ENABLE KEYS */;

-- Dumping structure for table ncovi-hdv.role
CREATE TABLE IF NOT EXISTS `role` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT,
  `role_code` varchar(50) NOT NULL,
  `role_name` varchar(50) DEFAULT NULL,
  `role_description` varchar(50) DEFAULT NULL,
  `role_created_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`role_id`),
  UNIQUE KEY `Index 2` (`role_code`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- Dumping data for table ncovi-hdv.role: ~2 rows (approximately)
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` (`role_id`, `role_code`, `role_name`, `role_description`, `role_created_at`) VALUES
	(1, 'ROLE_ADMIN', 'ROLE_ADMIN', NULL, NULL),
	(2, 'ROLE_USER', 'ROLE_USER', NULL, NULL);
/*!40000 ALTER TABLE `role` ENABLE KEYS */;

-- Dumping structure for table ncovi-hdv.user
CREATE TABLE IF NOT EXISTS `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_email` varchar(100) DEFAULT NULL,
  `user_fullname` varchar(100) DEFAULT NULL,
  `user_birthday` timestamp NULL DEFAULT NULL,
  `user_address` varchar(100) DEFAULT NULL,
  `user_phone_no` varchar(100) DEFAULT NULL,
  `user_cmt` varchar(100) DEFAULT NULL,
  `user_bhxh` varchar(100) DEFAULT NULL,
  `user_gender` int(6) DEFAULT NULL,
  `user_created_at` timestamp NULL DEFAULT NULL,
  `role` int(11) NOT NULL,
  `user_device_token` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  KEY `FK_user_role` (`role`),
  CONSTRAINT `FK_user_role` FOREIGN KEY (`role`) REFERENCES `role` (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- Dumping data for table ncovi-hdv.user: ~2 rows (approximately)
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`user_id`, `user_email`, `user_fullname`, `user_birthday`, `user_address`, `user_phone_no`, `user_cmt`, `user_bhxh`, `user_gender`, `user_created_at`, `role`, `user_device_token`) VALUES
	(6, 'tung@gmail.com', 'Hoang Manh Tung', '2020-06-11 17:00:00', NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL),
	(12, 'duc123@gmail.com', 'Pham Minh Duc', '2020-06-11 17:00:00', 'ha Noi', '0395592104', '1724595952', '123123123', 0, NULL, 2, NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
