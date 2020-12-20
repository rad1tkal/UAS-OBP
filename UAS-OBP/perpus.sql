-- phpMyAdmin SQL Dump
-- version 5.0.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 20, 2020 at 07:32 AM
-- Server version: 10.4.14-MariaDB
-- PHP Version: 7.4.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `perpus`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `staffId` char(15) NOT NULL,
  `fullName` varchar(30) DEFAULT NULL,
  `username` varchar(30) DEFAULT NULL,
  `adminpassword` varchar(30) DEFAULT 'admin'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`staffId`, `fullName`, `username`, `adminpassword`) VALUES
('1', 'Cory Ander', 'admin', 'admin'),
('2', 'Terry Aki', 'admin', 'admin'),
('3', 'Barry Wine', 'admin', 'admin'),
('4', 'Radit', 'radit', 'radit');

-- --------------------------------------------------------

--
-- Table structure for table `buku`
--

CREATE TABLE `buku` (
  `isbn` char(13) NOT NULL,
  `namaBuku` varchar(50) NOT NULL,
  `penerbit` varchar(50) DEFAULT NULL,
  `pengarang` varchar(50) DEFAULT NULL,
  `tahunTerbit` int(11) DEFAULT NULL,
  `statusPinjam` varchar(50) NOT NULL,
  `fotobuku` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `buku`
--

INSERT INTO `buku` (`isbn`, `namaBuku`, `penerbit`, `pengarang`, `tahunTerbit`, `statusPinjam`, `fotobuku`) VALUES
('0000000000000', 'Bintang', 'Gramedia', 'Tere Liye', 2016, 'Available', 'bintang.jpg'),
('2010000768150', 'Koala Kumal', 'Gagas Media', 'Raditya Dika', 2015, 'Available', 'koalakumal.jpg'),
('9786020332116', 'Matahari', 'Gramedia', 'Tere Liye', 2016, 'Available', 'matahari.jpeg'),
('9789794338483', 'The Maze Runner', 'Mizan Fantasi', 'James Dashner', 2009, 'Dipinjam', 'gengargengar.jpg'),
('9789794338490', 'The Death Cure', 'Mizan Fantasi', 'James Dashner', 2011, 'Available', 'deathcure.jpg'),
('9789794338506', 'The Scorch Trials', 'Mizan Fantasi', 'James Dashner', 2009, 'Available', 'scorchtrials.jpg'),
('979780531X', 'Manusia Setengah Salmon', 'Gagas Media', 'Raditya Dika', 2011, 'Available', 'manusiasalmon.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `logpinjam`
--

CREATE TABLE `logpinjam` (
  `orderNo` int(11) NOT NULL,
  `isbn` char(13) DEFAULT NULL,
  `nimNidn` varchar(9) DEFAULT NULL,
  `tanggalPeminjaman` date DEFAULT NULL,
  `tanggalPengembalian` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `logpinjam`
--

INSERT INTO `logpinjam` (`orderNo`, `isbn`, `nimNidn`, `tanggalPeminjaman`, `tanggalPengembalian`) VALUES
(1, '9786020332116', 'test', '2020-12-20', '2020-12-20'),
(2, '979780531X', 'test', '2020-12-19', '2020-12-20'),
(15, '0000000000000', '825190108', '2020-12-20', '2020-12-20'),
(16, '9789794338483', '825190108', '2020-12-20', '2020-12-20'),
(17, '9789794338483', '825190108', '2020-12-20', '2020-12-20'),
(18, '9789794338483', '000000000', '2020-12-20', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `peminjam`
--

CREATE TABLE `peminjam` (
  `nimNidn` varchar(9) NOT NULL,
  `namaPeminjam` varchar(30) NOT NULL,
  `peminjampassword` varchar(30) NOT NULL,
  `email` varchar(50) NOT NULL,
  `phone` char(12) NOT NULL,
  `faculty` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `peminjam`
--

INSERT INTO `peminjam` (`nimNidn`, `namaPeminjam`, `peminjampassword`, `email`, `phone`, `faculty`) VALUES
('000000000', 'guest', 'guest', 'guest', '', ''),
('825190001', 'Ramadhony', 'rama', 'sugiarto@gmail.com', '085123124122', 'FTI'),
('825190058', 'Carlene Lim', 'carlene', 'carlene.825190058@stu.untar.ac.id', '081296575272', 'FTI'),
('825190070', 'Hansenn Dustin Keane', 'hansenn', 'hansenn.825190070@stu.untar.ac.id', '081296575273', 'FTI'),
('825190108', 'Muhammad Raditya Vivaldy', 'radit', 'muhammad.825190108@stu.untar.ac.id', '081296575274', 'FTI');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`staffId`);

--
-- Indexes for table `buku`
--
ALTER TABLE `buku`
  ADD PRIMARY KEY (`isbn`);

--
-- Indexes for table `logpinjam`
--
ALTER TABLE `logpinjam`
  ADD PRIMARY KEY (`orderNo`);

--
-- Indexes for table `peminjam`
--
ALTER TABLE `peminjam`
  ADD PRIMARY KEY (`nimNidn`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `logpinjam`
--
ALTER TABLE `logpinjam`
  MODIFY `orderNo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
