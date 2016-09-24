-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Dec 12, 2014 at 06:52 AM
-- Server version: 5.6.17
-- PHP Version: 5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `travel`
--

-- --------------------------------------------------------

--
-- Table structure for table `booking`
--

CREATE TABLE IF NOT EXISTS `booking` (
  `sl` int(11) NOT NULL AUTO_INCREMENT,
  `id` varchar(8) NOT NULL,
  `name` varchar(20) NOT NULL,
  `loc_to` varchar(20) NOT NULL,
  `loc_from` varchar(20) NOT NULL,
  `purpose` varchar(20) NOT NULL,
  `vehicle_type` varchar(2) NOT NULL,
  `drop_time` time NOT NULL,
  `drop_date` date NOT NULL,
  PRIMARY KEY (`sl`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=45 ;

--
-- Dumping data for table `booking`
--

INSERT INTO `booking` (`sl`, `id`, `name`, `loc_to`, `loc_from`, `purpose`, `vehicle_type`, `drop_time`, `drop_date`) VALUES
(16, '36446', 'anisha', 'sector22', 'office', 'working late', 'no', '21:00:00', '2014-12-10'),
(17, '36446', 'anisha', 'sector22', 'office', 'working late', 'no', '21:00:00', '2014-12-10'),
(18, '36446', 'anisha', 'sector22', 'office', 'working late', 'no', '21:00:00', '2014-12-10'),
(19, '36446', 'anisha', 'sector22', 'office', 'working late', 'no', '21:00:00', '2014-12-10'),
(20, '36446', 'anisha', 'sector22', 'office', 'working late', 'no', '21:00:00', '2014-12-10'),
(21, '36446', 'anisha', 'sector22', 'office', 'working late', 'no', '21:00:00', '2014-12-10'),
(22, '36446', 'anisha', 'sector22', 'office', 'working late', 'no', '21:00:00', '2014-12-10'),
(23, '36446', 'anisha', 'sector22', 'office', 'working late', 'no', '21:00:00', '2014-12-10'),
(24, '36446', 'anisha', '', '', 'working late', 'no', '21:00:00', '2014-12-10'),
(25, '36446', 'anisha', 'sector22', 'office', 'working late', 'no', '21:00:00', '2014-12-10'),
(26, '36446', 'anisha', 'sector22', 'office', 'working late', 'ac', '00:04:00', '2014-12-11'),
(27, '36447', 'anuradha', 'office', 'plot 31', 'working', 'ac', '00:05:00', '2014-12-11'),
(28, '36446', 'anisha', 'sector22', 'plot31', 'working weekend', 'no', '00:15:00', '2014-12-11'),
(29, '36446', 'anisha', 'sector22', 'plot31', 'working weekend', 'no', '00:19:00', '2014-12-11'),
(30, '36446', 'anisha', 'sector22', 'plot31', 'working weekend', 'no', '01:25:00', '2014-12-11'),
(31, '36446', 'anisha', 'sector22', 'plot31', 'working weekend', 'no', '02:30:00', '2014-12-11'),
(32, '36446', 'anisha', 'sector22', 'plot31', 'working weekend', 'no', '02:35:00', '2014-12-11'),
(33, '36446', 'anisha', 'sector22', 'plot31', 'working weekend', 'no', '02:41:00', '2014-12-11'),
(34, '36446', 'anisha', 'sector22', 'plot31', 'working weekend', 'no', '02:51:00', '2014-12-11'),
(35, '36446', 'anisha', 'sector22', 'plot31', 'working weekend', 'no', '02:59:00', '2014-12-11'),
(36, '36446', 'anisha', 'sector22', 'office', 'working late', 'ac', '03:03:00', '2014-12-11'),
(37, '36446', 'anisha', 'sector22', 'office', 'working late', 'ac', '03:16:00', '2014-12-11'),
(38, '36446', 'anisha', 'sector22', 'plot31', 'working weekend', 'no', '03:20:00', '2014-12-11'),
(39, '36446', 'anisha', 'sector22', 'plot31', 'working weekend', 'no', '03:21:00', '2014-12-11'),
(40, '36446', 'anisha', 'sector22', 'plot31', 'working weekend', 'no', '03:27:00', '2014-12-11'),
(41, '36446', 'anisha', 'sector22', 'plot31', 'working weekend', 'no', '03:30:00', '2014-12-11'),
(42, '36446', 'anisha', 'sector22', 'plot31', 'working weekend', 'no', '03:31:00', '2014-12-11'),
(43, '36446', 'anisha', 'sector22', 'plot31', 'working weekend', 'no', '03:42:00', '2014-12-11'),
(44, '36446', 'anisha', 'sector22', 'plot31', 'working weekend', 'no', '03:46:00', '2014-12-11');

-- --------------------------------------------------------

--
-- Table structure for table `favorite`
--

CREATE TABLE IF NOT EXISTS `favorite` (
  `sl_no` int(20) NOT NULL AUTO_INCREMENT,
  `id` varchar(8) NOT NULL,
  `fav_name` varchar(20) NOT NULL,
  `loc_to` varchar(20) NOT NULL,
  `loc_from` varchar(20) NOT NULL,
  `purpose` varchar(50) NOT NULL,
  `vehicle_type` varchar(10) NOT NULL,
  PRIMARY KEY (`sl_no`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `favorite`
--

INSERT INTO `favorite` (`sl_no`, `id`, `fav_name`, `loc_to`, `loc_from`, `purpose`, `vehicle_type`) VALUES
(1, '36446', 'fav1', 'sector22', 'office', 'working late', 'ac'),
(2, '36446', 'fav2', 'sector22', 'plot31', 'working weekend', 'non ac');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE IF NOT EXISTS `users` (
  `id` varchar(8) NOT NULL,
  `username` varchar(20) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  `managerid` int(8) DEFAULT NULL,
  `charge_code` int(20) NOT NULL,
  `plot` varchar(20) NOT NULL,
  `level` varchar(2) NOT NULL,
  `address` varchar(30) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `username`, `password`, `managerid`, `charge_code`, `plot`, `level`, `address`) VALUES
('1234', 'manoj', 'manoj', 1111, 0, 'presidency', 'e5', 'huda'),
('36446', 'anisha', 'anisha', 1234, 0, 'precidency', 'e2', 'sector22'),
('36447', 'anuradha', 'anuradha', 1234, 0, 'presidency', 'e2', 'sector 22');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
