-- phpMyAdmin SQL Dump
-- version 5.1.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 02, 2022 at 03:30 PM
-- Server version: 10.4.24-MariaDB
-- PHP Version: 8.1.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";

--
-- Database: `parking`
--

-- --------------------------------------------------------

--
-- Table structure for table `activity`
--

CREATE TABLE `activity` (
  `id` int(32) NOT NULL,
  `in` datetime NOT NULL DEFAULT current_timestamp(),
  `out` datetime DEFAULT '1970-01-01 00:00:01',
  `ak` varchar(32) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `activity`
--

INSERT INTO `activity` (`id`, `in`, `out`, `ak`) VALUES
(1, '2022-05-12 15:31:03', '1970-01-01 00:00:01', 'YXZ4500'),
(2, '2022-05-12 15:33:49', '2022-05-12 18:07:23', 'ΑΣΔ5655'),
(3, '2022-05-12 18:19:53', '1970-01-01 00:00:01', 'ΔΕΦ3445'),
(4, '2022-06-02 16:26:40', '1970-01-01 00:00:01', 'AMA34600');

-- --------------------------------------------------------

--
-- Table structure for table `analytiki`
--

CREATE TABLE `analytiki` (
  `ak` varchar(32) NOT NULL,
  `ochima` varchar(64) NOT NULL,
  `odigos` varchar(32) NOT NULL,
  `phone` varchar(32) NOT NULL,
  `email` varchar(32) NOT NULL,
  `at` varchar(32) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `analytiki`
--

INSERT INTO `analytiki` (`ak`, `ochima`, `odigos`, `phone`, `email`, `at`) VALUES
('AMA34600', 'Ford Focus 2001', 'Γιάννης Παπαδάς', '6982234567', 'giannis@gian.com', 'ΑΡ234567'),
('YXZ4500', 'Audi A4 2008', 'Nikolaos Hadjopoulos', '6987654567', 'nikos@mail.com', 'ΑΕ 235678'),
('ΑΜΙ1278', 'Volkswagen Polo S 2003', 'Ανδρέας Άνεργος', '6984434567', 'anan@as.com', 'ΔΡ32223'),
('ΑΣ3344556', 'Renault Clio 2010', 'Panagiotis Papadopoulos', '6923456787', 'pan@pan.gr', 'DT456786'),
('ΕΓΗ34567', ' Mazda Mx 5 2000', 'Giorgos Dilos', '6934234567', 'giorgos@g.com', 'ΕΕ345676'),
('ΕΔΦ3454', 'Audi A8 2004', 'Nikos Papadopoulos', '6948394956', 'nikos@n.gr', 'AE345432');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `activity`
--
ALTER TABLE `activity`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `analytiki`
--
ALTER TABLE `analytiki`
  ADD PRIMARY KEY (`ak`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `activity`
--
ALTER TABLE `activity`
  MODIFY `id` int(32) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
COMMIT;
