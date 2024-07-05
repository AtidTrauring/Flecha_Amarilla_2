-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 05-07-2024 a las 02:29:26
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `flecha_amarilla`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `anio`
--

CREATE TABLE `anio` (
  `Id_anio` int(2) NOT NULL,
  `anio` int(4) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `anio`
--

INSERT INTO `anio` (`Id_anio`, `anio`) VALUES
(1, 1945),
(2, 1949),
(3, 1951),
(4, 1952),
(5, 1953),
(6, 1955),
(7, 1957),
(8, 1960),
(9, 1962),
(10, 1970),
(11, 1971),
(12, 1975),
(13, 1978),
(14, 1982),
(15, 1985),
(16, 1988),
(17, 1992),
(18, 1997),
(19, 1999),
(20, 2000),
(21, 2003),
(22, 2004),
(23, 2007),
(24, 2009),
(25, 2011),
(26, 2012),
(27, 2015),
(28, 2017),
(29, 2019),
(30, 2020),
(31, 2021),
(32, 2022),
(33, 2023),
(34, 2024);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `asiento`
--

CREATE TABLE `asiento` (
  `Id_asiento` int(3) NOT NULL,
  `asiento` varchar(4) DEFAULT NULL,
  `Id_autobus` int(2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `asiento`
--

INSERT INTO `asiento` (`Id_asiento`, `asiento`, `Id_autobus`) VALUES
(1, 'A1', 31),
(2, 'A2', 31),
(3, 'A3', 31),
(4, 'A4', 31),
(5, 'A5', 31),
(6, 'A6', 31),
(7, 'A7', 31),
(8, 'A8', 31),
(9, 'A9', 31),
(10, 'A10', 31),
(11, 'A11', 31),
(12, 'A12', 31),
(13, 'A13', 31),
(14, 'A14', 31),
(15, 'A15', 31),
(16, 'A16', 31),
(17, 'A17', 31),
(18, 'A18', 31),
(19, 'A19', 31),
(20, 'A20', 31),
(21, 'A21', 31),
(22, 'A22', 31),
(23, 'A23', 31),
(24, 'A24', 31),
(25, 'A25', 31),
(26, 'A26', 31),
(27, 'A27', 31),
(28, 'A28', 31),
(29, 'A29', 31),
(30, 'A30', 31),
(31, 'A1', 1),
(32, 'A2', 1),
(33, 'A3', 1),
(34, 'A4', 1),
(35, 'A5', 1),
(36, 'A6', 1),
(37, 'A7', 1),
(38, 'A8', 1),
(39, 'A9', 1),
(40, 'A10', 1),
(41, 'A11', 1),
(42, 'A12', 1),
(43, 'A13', 1),
(44, 'A14', 1),
(45, 'A15', 1),
(46, 'A16', 1),
(47, 'A17', 1),
(48, 'A18', 1),
(49, 'A19', 1),
(50, 'A20', 1),
(51, 'A21', 1),
(52, 'A22', 1),
(53, 'A23', 1),
(54, 'A24', 1),
(55, 'A25', 1),
(56, 'A26', 1),
(57, 'A27', 1),
(58, 'A28', 1),
(59, 'A29', 1),
(60, 'A30', 1),
(61, 'A1', 36),
(62, 'A2', 36),
(63, 'A3', 36),
(64, 'A4', 36),
(65, 'A5', 36),
(66, 'A6', 36),
(67, 'A7', 36),
(68, 'A8', 36),
(69, 'A9', 36),
(70, 'A10', 36),
(71, 'A11', 36),
(72, 'A12', 36),
(73, 'A13', 36),
(74, 'A14', 36),
(75, 'A15', 36),
(76, 'A16', 36),
(77, 'A17', 36),
(78, 'A18', 36),
(79, 'A19', 36),
(80, 'A20', 36),
(81, 'A21', 36),
(82, 'A22', 36),
(83, 'A23', 36),
(84, 'A24', 36),
(85, 'A25', 36),
(86, 'A26', 36),
(87, 'A27', 36),
(88, 'A28', 36),
(89, 'A29', 36),
(90, 'A30', 36),
(91, 'A31', 36),
(92, 'A32', 36),
(93, 'A33', 36),
(94, 'A34', 36),
(95, 'A35', 36),
(96, 'A36', 36),
(97, 'A1', 24),
(98, 'A2', 24),
(99, 'A3', 24),
(100, 'A4', 24),
(101, 'A5', 24),
(102, 'A6', 24),
(103, 'A7', 24),
(104, 'A8', 24),
(105, 'A9', 24),
(106, 'A10', 24),
(107, 'A11', 24),
(108, 'A12', 24),
(109, 'A13', 24),
(110, 'A14', 24),
(111, 'A15', 24),
(112, 'A16', 24),
(113, 'A17', 24),
(114, 'A18', 24),
(115, 'A19', 24),
(116, 'A20', 24),
(117, 'A21', 24),
(118, 'A22', 24),
(119, 'A23', 24),
(120, 'A24', 24),
(121, 'A25', 24),
(122, 'A26', 24),
(123, 'A27', 24),
(124, 'A28', 24),
(125, 'A29', 24),
(126, 'A30', 24),
(127, 'A31', 24),
(128, 'A32', 24),
(129, 'A1', 10),
(130, 'A2', 10),
(131, 'A3', 10),
(132, 'A4', 10),
(133, 'A5', 10),
(134, 'A6', 10),
(135, 'A7', 10),
(136, 'A8', 10),
(137, 'A9', 10),
(138, 'A10', 10),
(139, 'A11', 10),
(140, 'A12', 10),
(141, 'A13', 10),
(142, 'A14', 10),
(143, 'A15', 10),
(144, 'A16', 10),
(145, 'A17', 10),
(146, 'A18', 10),
(147, 'A19', 10),
(148, 'A20', 10),
(149, 'A21', 10),
(150, 'A22', 10),
(151, 'A23', 10),
(152, 'A24', 10),
(153, 'A25', 10),
(154, 'A26', 10),
(155, 'A27', 10),
(156, 'A28', 10),
(157, 'A29', 10),
(158, 'A30', 10),
(159, 'A1', 22),
(160, 'A2', 22),
(161, 'A3', 22),
(162, 'A4', 22),
(163, 'A5', 22),
(164, 'A6', 22),
(165, 'A7', 22),
(166, 'A8', 22),
(167, 'A9', 22),
(168, 'A10', 22),
(169, 'A11', 22),
(170, 'A12', 22),
(171, 'A13', 22),
(172, 'A14', 22),
(173, 'A15', 22),
(174, 'A16', 22),
(175, 'A17', 22),
(176, 'A18', 22),
(177, 'A19', 22),
(178, 'A20', 22),
(179, 'A21', 22),
(180, 'A22', 22),
(181, 'A23', 22),
(182, 'A24', 22),
(183, 'A25', 22),
(184, 'A26', 22),
(185, 'A27', 22),
(186, 'A28', 22),
(187, 'A29', 22),
(188, 'A30', 22),
(189, 'A31', 22),
(190, 'A32', 22);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `autobus`
--

CREATE TABLE `autobus` (
  `Id_autobus` int(2) NOT NULL,
  `capacidad` int(3) DEFAULT NULL,
  `num_economico` varchar(20) DEFAULT NULL,
  `placa` varchar(8) DEFAULT NULL,
  `Id_anio` int(2) DEFAULT NULL,
  `Id_fecha` int(2) DEFAULT NULL,
  `Id_modelo` int(2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `autobus`
--

INSERT INTO `autobus` (`Id_autobus`, `capacidad`, `num_economico`, `placa`, `Id_anio`, `Id_fecha`, `Id_modelo`) VALUES
(1, 30, '102', 'ABC-1234', 34, 1, 1),
(2, 35, '275', 'DEF-5678', 33, 1, 2),
(3, 40, '431', 'GHI-9012', 34, 2, 3),
(4, 42, '590', 'JKL-3456', 30, 31, 4),
(5, 30, '604', 'MNO-7890', 28, 31, 5),
(6, 35, '758', 'PQR-2345', 30, 31, 6),
(7, 34, '832', 'STU-6789', 34, 31, 7),
(8, 42, '901', 'VWX-0123', 33, 31, 8),
(9, 46, '1023', 'YZA-4567', 31, 31, 9),
(10, 30, '1154', 'BCD-8901', 28, 1, 10),
(11, 42, '1298', 'EFG-2341', 27, 1, 11),
(12, 40, '1402', 'HIJ-6785', 34, 31, 12),
(13, 38, '1576', 'KLM-0129', 30, 31, 13),
(14, 36, '1634', 'NOP-4563', 32, 1, 14),
(15, 30, '1789', 'QRS-8907', 30, 1, 15),
(16, 32, '1905', 'TUV-2349', 30, 1, 16),
(17, 34, '2048', 'WXY-6783', 28, 31, 17),
(18, 40, '2165', 'ZAB-0127', 29, 31, 18),
(19, 42, '2301', 'CDE-4561', 26, 31, 19),
(20, 46, '2459', 'FGH-8905', 30, 2, 20),
(21, 38, '2593', 'IJK-2347', 31, 31, 21),
(22, 32, '2708', 'LMN-6781', 34, 1, 22),
(23, 36, '2831', 'OPQ-0125', 33, 31, 23),
(24, 32, '2964', 'RST-4569', 26, 1, 24),
(25, 34, '3107', 'UVW-8903', 29, 31, 25),
(26, 38, '3245', 'XYZ-2343', 30, 31, 26),
(27, 40, '3372', 'AB1-6787', 34, 1, 27),
(28, 46, '3498', 'CD2-0121', 33, 31, 28),
(29, 40, '3621', 'EF3-4565', 31, 1, 29),
(30, 38, '3745', 'GH4-8909', 29, 31, 30),
(31, 30, '3890', 'IJ5-2349', 34, 1, 31),
(32, 32, '4001', 'KL6-6787', 32, 31, 32),
(33, 34, '4502', 'MN7-1234', 31, 3, 33),
(34, 36, '4678', 'PQ8-5678', 34, 2, 34),
(35, 40, '4823', 'RS9-9012', 29, 1, 35),
(36, 42, '4951', 'TU0-3456', 28, 2, 36),
(37, 30, '5096', 'VW1-7890', 30, 31, 37);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `autobusconductor`
--

CREATE TABLE `autobusconductor` (
  `Id_AutCon` int(2) NOT NULL,
  `Id_conductor` int(2) DEFAULT NULL,
  `Id_autobus` int(2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `autobusconductor`
--

INSERT INTO `autobusconductor` (`Id_AutCon`, `Id_conductor`, `Id_autobus`) VALUES
(1, 1, 1),
(2, 2, 1),
(3, 3, 1),
(4, 4, 1),
(5, 5, 10),
(6, 6, 10),
(7, 7, 10),
(8, 8, 10),
(9, 9, 22),
(10, 10, 22),
(11, 11, 22),
(12, 12, 24),
(13, 13, 31),
(14, 14, 36),
(15, 15, 25),
(16, 17, 25),
(17, 19, 25),
(18, 18, 25);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `baja`
--

CREATE TABLE `baja` (
  `Id_baja` int(2) NOT NULL,
  `Id_autobus` int(2) DEFAULT NULL,
  `Id_fecha` int(2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `baja`
--

INSERT INTO `baja` (`Id_baja`, `Id_autobus`, `Id_fecha`) VALUES
(1, 4, 64),
(2, 5, 64),
(3, 6, 64),
(4, 7, 64),
(5, 8, 64),
(6, 9, 64),
(7, 12, 64),
(8, 13, 64),
(9, 17, 64),
(10, 18, 64),
(11, 19, 64),
(12, 21, 64),
(13, 23, 64),
(14, 25, 64),
(15, 26, 64),
(16, 28, 64),
(17, 30, 64),
(18, 32, 64),
(19, 37, 64);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `boleto`
--

CREATE TABLE `boleto` (
  `Id_boleto` int(3) NOT NULL,
  `tipo_boleto` varchar(4) DEFAULT NULL,
  `precioDescuento` float DEFAULT NULL,
  `Id_metodo` int(1) DEFAULT NULL,
  `Id_ruta` int(2) DEFAULT NULL,
  `Id_fecha` int(2) DEFAULT NULL,
  `Id_pasajero` int(2) DEFAULT NULL,
  `Id_asiento` int(4) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `boleto`
--

INSERT INTO `boleto` (`Id_boleto`, `tipo_boleto`, `precioDescuento`, `Id_metodo`, `Id_ruta`, `Id_fecha`, `Id_pasajero`, `Id_asiento`) VALUES
(1, 'PP', 12000, 1, 33, 2, 1, 1),
(2, 'C', 1200, 2, 35, 2, 2, 2),
(3, 'C', 12588.8, 2, 1, 36, 3, 3),
(4, 'PP', 11180, 2, 29, 3, 4, 4),
(5, 'C', 1153.2, 1, 21, 3, 5, 5),
(6, 'C', 938, 1, 19, 3, 6, 6),
(7, 'C', 1125, 1, 33, 3, 7, 7),
(8, 'PP', 780, 1, 35, 2, 8, 8),
(9, 'PP', 11750, 1, 1, 3, 9, 9),
(10, 'C', 13760, 2, 29, 3, 10, 10),
(11, 'PP', 1325, 2, 21, 2, 11, 31),
(12, 'PP', 1072, 1, 19, 2, 12, 32),
(13, 'PP', 1050, 1, 33, 2, 13, 33),
(14, 'C', 900, 2, 35, 2, 14, 34),
(15, 'C', 10910.2, 1, 1, 3, 15, 35),
(16, 'C', 12900, 2, 29, 3, 16, 36),
(17, 'C', 1076.4, 1, 21, 3, 17, 37),
(18, 'PP', 938, 2, 19, 3, 18, 38),
(19, 'PP', 1500, 2, 33, 1, 19, 39),
(20, 'C', 1200, 2, 35, 2, 20, 40),
(21, 'PP', 16785, 1, 1, 2, 21, 61),
(22, 'C', 11180, 2, 29, 37, 22, 62),
(23, 'PP', 1080, 1, 21, 36, 23, 63),
(24, 'PP', 1005, 1, 19, 36, 24, 64),
(25, 'C', 1500, 1, 33, 1, 25, 65),
(26, 'C', 840, 2, 35, 2, 26, 66),
(27, 'C', 12589, 2, 1, 2, 27, 67),
(28, 'C', 11180, 2, 29, 36, 28, 68),
(29, 'C', 1656, 1, 21, 37, 29, 69),
(30, 'C', 1072, 1, 19, 37, 30, 70),
(31, 'PP', 960, 1, 35, 2, 31, 97),
(32, 'C', 1200, 2, 35, 2, 32, 98),
(33, 'C', 900, 2, 35, 2, 33, 99),
(34, 'PP', 780, 2, 35, 2, 34, 100),
(35, 'C', 840, 1, 35, 2, 35, 101),
(36, 'C', 840, 1, 35, 2, 36, 102),
(37, 'C', 900, 1, 35, 2, 37, 103),
(38, 'PP', 780, 1, 35, 2, 38, 104),
(39, 'PP', 840, 1, 35, 2, 39, 105),
(40, 'C', 960, 2, 35, 2, 40, 106),
(41, 'PP', 1325, 2, 21, 36, 41, 129),
(42, 'PP', 1072, 1, 19, 36, 42, 130),
(43, 'PP', 1050, 1, 33, 36, 43, 131),
(44, 'C', 900, 2, 35, 2, 44, 132),
(45, 'C', 10910.2, 1, 1, 37, 45, 133),
(46, 'C', 12900, 2, 29, 2, 46, 134),
(47, 'C', 1076.4, 1, 21, 1, 47, 135),
(48, 'PP', 938, 2, 19, 3, 48, 136),
(49, 'PP', 1500, 2, 33, 1, 49, 137),
(50, 'C', 1200, 2, 35, 2, 50, 138),
(51, 'PP', 16785, 1, 1, 37, 51, 159),
(52, 'C', 11180, 2, 29, 3, 52, 160),
(53, 'PP', 1080, 1, 21, 36, 53, 161),
(54, 'PP', 1005, 1, 19, 36, 54, 162),
(55, 'C', 1500, 1, 33, 37, 55, 163),
(56, 'C', 840, 2, 35, 2, 56, 164),
(57, 'C', 12589, 2, 1, 1, 57, 165),
(58, 'C', 11180, 2, 29, 36, 58, 166),
(59, 'C', 1656, 1, 21, 37, 59, 167),
(60, 'C', 1072, 1, 19, 37, 60, 168),
(61, 'PP', 1050, 1, 33, 2, 61, 169),
(62, 'C', 900, 2, 35, 2, 62, 170),
(63, 'C', 10910, 2, 1, 2, 63, 171),
(64, 'PP', 17200, 2, 29, 1, 64, 172),
(65, 'C', 1324.8, 1, 21, 1, 65, 173);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `boletocliente`
--

CREATE TABLE `boletocliente` (
  `Id_BolClie` int(3) NOT NULL,
  `total` float DEFAULT NULL,
  `Id_boleto` int(3) DEFAULT NULL,
  `Id_cliente` int(2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `boletocliente`
--

INSERT INTO `boletocliente` (`Id_BolClie`, `total`, `Id_boleto`, `Id_cliente`) VALUES
(1, 12000, 1, 1),
(2, 1200, 2, 2),
(3, 12588.8, 3, 3),
(4, 11180, 4, 4),
(5, 1153.2, 5, 5),
(6, 938, 6, 6),
(7, 1125, 7, 7),
(8, 780, 8, 8),
(9, 11750, 9, 9),
(10, 13760, 10, 10),
(11, 1325, 11, 11),
(12, 1072, 12, 12),
(13, 1050, 13, 13),
(14, 900, 14, 14),
(15, 10910.2, 15, 15),
(16, 12900, 16, 16),
(17, 1076.4, 17, 17),
(18, 938, 18, 18),
(19, 1500, 19, 19),
(20, 1200, 20, 20),
(21, 16785, 21, 21),
(22, 11180, 22, 22),
(23, 1080, 23, 23),
(24, 1005, 24, 24),
(25, 1500, 25, 25),
(26, 840, 26, 26),
(27, 12589, 27, 27),
(28, 11180, 28, 28),
(29, 1656, 29, 29),
(30, 1072, 30, 30),
(31, 12000, 31, 31),
(32, 1200, 32, 32),
(33, 12588.8, 33, 33),
(34, 11180, 34, 34),
(35, 1153.2, 35, 1),
(36, 938, 36, 2),
(37, 1125, 37, 3),
(38, 780, 38, 4),
(39, 11750, 39, 5),
(40, 13760, 40, 6),
(41, 1325, 41, 7),
(42, 1072, 42, 8),
(43, 1050, 43, 9),
(44, 900, 44, 10),
(45, 10910.2, 45, 11),
(46, 12900, 46, 12),
(47, 1076.4, 47, 13),
(48, 938, 48, 14),
(49, 1500, 49, 15),
(50, 1200, 50, 16),
(51, 16785, 51, 17),
(52, 11180, 52, 18),
(53, 1080, 53, 19),
(54, 1005, 54, 20),
(55, 1500, 55, 21),
(56, 840, 56, 22),
(57, 12589, 57, 23),
(58, 11180, 58, 24),
(59, 1656, 59, 25),
(60, 1072, 60, 26),
(61, 1050, 61, 27),
(62, 900, 62, 28),
(63, 10910, 63, 29),
(64, 17200, 64, 30),
(65, 1324.8, 65, 31);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ciudad`
--

CREATE TABLE `ciudad` (
  `Id_ciudad` int(2) NOT NULL,
  `nombre` varchar(80) DEFAULT NULL,
  `Id_estado` int(2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `ciudad`
--

INSERT INTO `ciudad` (`Id_ciudad`, `nombre`, `Id_estado`) VALUES
(1, ' Aguascalientes ', 1),
(2, 'Tijuana', 2),
(3, 'La Paz', 3),
(4, 'Campeche ', 4),
(5, 'Saltillo', 5),
(6, 'Colima ', 6),
(7, 'Tuxtla Gutiérrez', 7),
(8, 'Chihuahua ', 8),
(9, 'Ciudad de México', 9),
(10, 'Durango (ciudad)', 10),
(11, 'León', 11),
(12, 'Acapulco', 12),
(13, 'Pachuca', 13),
(14, 'Guadalajara', 14),
(15, 'Toluca', 15),
(16, 'Morelia', 16),
(17, 'Cuernavaca', 17),
(18, 'Tepic', 18),
(19, 'Monterrey', 19),
(20, 'Oaxaca de Juárez', 20),
(21, 'Puebla de Zaragoza', 21),
(22, 'Querétaro ', 22),
(23, 'Cancún', 23),
(24, 'San Luis Potosí ', 24),
(25, 'Culiacán', 25),
(26, 'Hermosillo', 26),
(27, 'Villahermosa', 27),
(28, 'Ciudad Victoria', 28),
(29, 'Tlaxcala ', 29),
(30, 'Veracruz ', 30),
(31, 'Mérida', 31),
(32, 'Zacatecas ', 32);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cliente`
--

CREATE TABLE `cliente` (
  `Id_cliente` int(2) NOT NULL,
  `correo` varchar(80) DEFAULT NULL,
  `Id_persona` int(2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `cliente`
--

INSERT INTO `cliente` (`Id_cliente`, `correo`, `Id_persona`) VALUES
(1, 'carlos.campos92@gmail.com', 21),
(2, 'isabel.rosales84@gmail.com', 22),
(3, 'hector.espinoza77@gmail.com', 23),
(4, 'monica.medina89@gmail.com', 24),
(5, 'javier.herrera65@gmail.com', 25),
(6, 'gabriela.gallardo78@gmail.com', 26),
(7, 'francisco.suarez71@gmail.com', 27),
(8, 'veronica.estrada93@gmail.com', 28),
(9, 'antonio.mendez80@gmail.com', 29),
(10, 'lorena.serrano86@gmail.com', 30),
(11, 'martin.gonzalez79@gmail.com', 31),
(12, 'alejandra.dominguez88@gmail.com', 32),
(13, 'enrique.ramos75@gmail.com', 33),
(14, 'natalia.gomez91@gmail.com', 34),
(15, 'diego.vega68@gmail.com', 35),
(16, 'Esteban.Sandoval123@gmail.com', 49),
(17, 'Rebeca.Valdez456@gmail.com', 50),
(18, 'Manuel.Zamora789@gmail.com', 51),
(19, 'Alicia.Roman321@gmail.com', 52),
(20, 'Cecilia.Hernandez83@gmail.com', 54),
(21, 'Rodrigo.Cardenas1975@gmail.com', 55),
(22, 'Susana.Gutierrez2022@gmail.com', 56),
(23, 'Luz.Salinas123@gmail.com', 60),
(24, 'Claudia.Bautista456@gmail.com', 62),
(25, 'David.Cabrera789@gmail.com', 63),
(26, 'Evelyn.Dominguez321@gmail.com', 64),
(27, 'I.Iglesias789@gmail.com', 68),
(28, 'J.Juarez456@gmail.com', 69),
(29, 'Mariana.Marquez123@gmail.com', 72),
(30, 'Nicolas.Nieves456@gmail.com', 73),
(31, 'Tania.Trujillo.Uribe789@gmail.com', 79),
(32, 'Ulises.Urbina.Valdez123@gmail.com', 80),
(33, 'Victor.Valdez.Alvarez456@gmail.com', 81),
(34, 'Ximena.Acosta.Beltran321@gmail.com', 82);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `codigo_postal`
--

CREATE TABLE `codigo_postal` (
  `Id_CP` int(2) NOT NULL,
  `codigo_postal` int(5) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `codigo_postal`
--

INSERT INTO `codigo_postal` (`Id_CP`, `codigo_postal`) VALUES
(9, 1000),
(1, 20000),
(2, 21000),
(3, 23000),
(4, 24000),
(5, 25000),
(6, 28000),
(7, 29000),
(8, 31000),
(10, 34000),
(11, 36000),
(12, 39000),
(13, 42000),
(14, 44100),
(15, 50000),
(16, 58000),
(17, 62000),
(18, 63000),
(19, 64000),
(20, 68000),
(21, 72000),
(22, 76000),
(23, 77000),
(24, 78000),
(25, 80000),
(26, 83000),
(27, 86000),
(28, 87000),
(29, 90000),
(30, 91000),
(31, 97000),
(32, 98000);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `colonia`
--

CREATE TABLE `colonia` (
  `Id_colonia` int(2) NOT NULL,
  `colonia` varchar(80) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `colonia`
--

INSERT INTO `colonia` (`Id_colonia`, `colonia`) VALUES
(14, 'Americana'),
(1, 'Bosques del Prado Norte'),
(31, 'Campestre'),
(32, 'Central'),
(8, 'Centro'),
(13, 'Centroamerica'),
(16, 'Chapu'),
(2, 'Chapulte'),
(25, 'Chapultepec'),
(15, 'Ciprés'),
(12, 'Costa Azul'),
(19, 'Del Valle'),
(10, 'El Ciprés'),
(3, 'El Esterito'),
(11, 'Granjas San Isidro'),
(6, 'La Armonía'),
(21, 'La Paz'),
(18, 'Lindavista'),
(29, 'Loma Xicohténcatl'),
(24, 'Lomas'),
(28, 'Mainero'),
(22, 'Milenio III'),
(26, 'Pitic'),
(20, 'Reforma'),
(30, 'Reforza'),
(5, 'República'),
(9, 'Roma'),
(4, 'San Francisco'),
(23, 'Supermanzana 15'),
(27, 'Tabasco 2000'),
(7, 'Terán'),
(17, 'Vista Hermosa');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `conductor`
--

CREATE TABLE `conductor` (
  `Id_conductor` int(2) NOT NULL,
  `Id_persona` int(2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `conductor`
--

INSERT INTO `conductor` (`Id_conductor`, `Id_persona`) VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 5),
(6, 6),
(7, 7),
(8, 8),
(9, 9),
(10, 10),
(11, 11),
(12, 12),
(13, 13),
(14, 14),
(15, 15),
(16, 16),
(17, 17),
(18, 18),
(19, 19),
(20, 20);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `destino`
--

CREATE TABLE `destino` (
  `Id_destino` int(2) NOT NULL,
  `Id_terminal` int(2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `destino`
--

INSERT INTO `destino` (`Id_destino`, `Id_terminal`) VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 5),
(6, 6),
(7, 7),
(8, 8),
(9, 9),
(10, 10),
(11, 11),
(12, 12),
(13, 13),
(14, 14),
(15, 15),
(16, 16),
(17, 17),
(18, 18),
(19, 19),
(20, 20),
(21, 21),
(22, 22),
(23, 23),
(24, 24),
(25, 25),
(26, 26),
(27, 27),
(28, 28),
(29, 29),
(30, 30),
(31, 31),
(32, 32);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `direccion`
--

CREATE TABLE `direccion` (
  `Id_direccion` int(2) NOT NULL,
  `nombre_calle` varchar(80) DEFAULT NULL,
  `numero` int(5) DEFAULT NULL,
  `Id_ciudad` int(2) DEFAULT NULL,
  `Id_colonia` int(2) DEFAULT NULL,
  `Id_CP` int(2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `direccion`
--

INSERT INTO `direccion` (`Id_direccion`, `nombre_calle`, `numero`, `Id_ciudad`, `Id_colonia`, `Id_CP`) VALUES
(1, 'Avenida Aguascalientes', 20230, 1, 1, 1),
(2, 'Avenida Revolución', 22000, 2, 2, 2),
(3, 'Calle Altamirano', 23000, 3, 3, 3),
(4, 'Calle 53', 25280, 4, 4, 4),
(5, 'Calle Hidalgo', 25000, 5, 5, 5),
(6, 'Calle de la Paz', 27250, 6, 6, 6),
(7, 'Calle Central', 29000, 7, 7, 7),
(8, 'Avenida Juárez', 31000, 8, 8, 8),
(9, 'Avenida Álvaro Obregón', 44100, 9, 9, 9),
(10, 'Calle Durango', 50000, 10, 10, 10),
(11, 'Calle Bosques de la Pradera', 37670, 11, 11, 11),
(12, 'Avenida Costera Miguel Alemán', 39690, 12, 12, 12),
(13, 'Calle Guerrero', 42000, 13, 13, 13),
(14, 'Avenida Vallarta', 44130, 14, 14, 14),
(15, 'Calle Heriberto Enríquez', 50130, 15, 15, 15),
(16, 'Avenida Acueducto', 58000, 16, 16, 16),
(17, 'Calle Río Mayo', 63175, 17, 17, 17),
(18, 'Avenida Insurgentes', 67150, 18, 18, 18),
(19, 'Avenida Gómez Morín', 53270, 19, 19, 19),
(20, 'Calle Amapolas', 72160, 20, 20, 20),
(21, 'Boulevard Atlixco', 72190, 21, 21, 21),
(22, 'Avenida de la Luz', 76127, 22, 22, 22),
(23, 'Avenida Nizuc', 77500, 23, 23, 23),
(24, 'Calle Cordillera de los Alpes', 78216, 24, 24, 24),
(25, 'Calle Lago', 80000, 25, 25, 25),
(26, 'Avenida Veracruz', 91897, 26, 26, 26),
(27, 'Avenida Paseo Usumacinta', 86150, 27, 27, 27),
(28, 'Calle Juárez', 87000, 28, 28, 28),
(29, 'Calle Ahuehuete', 90000, 29, 29, 29),
(30, 'Avenida Simón Bolívar', 91770, 30, 30, 30),
(31, 'Calle 1A', 97000, 31, 31, 31),
(32, 'Avenida Hidalgo', 98000, 32, 32, 32);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `efectivo`
--

CREATE TABLE `efectivo` (
  `Id_efectivo` int(2) NOT NULL,
  `Id_metodo` int(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `efectivo`
--

INSERT INTO `efectivo` (`Id_efectivo`, `Id_metodo`) VALUES
(1, 1),
(2, 1),
(3, 1),
(4, 1),
(5, 1),
(6, 1),
(7, 1),
(8, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `estado`
--

CREATE TABLE `estado` (
  `Id_estado` int(2) NOT NULL,
  `nombre` varchar(19) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `estado`
--

INSERT INTO `estado` (`Id_estado`, `nombre`) VALUES
(1, 'Aguascalientes'),
(2, 'Baja California'),
(3, 'Baja California Sur'),
(4, 'Campeche'),
(7, 'Chiapas'),
(8, 'Chihuahua'),
(9, 'Ciudad de México'),
(5, 'Coahuila'),
(6, 'Colima'),
(10, 'Durango'),
(15, 'Estado de México'),
(11, 'Guanajuato'),
(12, 'Guerrero'),
(13, 'Hidalgo'),
(14, 'Jalisco'),
(16, 'Michoacán'),
(17, 'Morelos'),
(18, 'Nayarit'),
(19, 'Nuevo León'),
(20, 'Oaxaca'),
(21, 'Puebla'),
(22, 'Querétaro'),
(23, 'Quintana Roo'),
(24, 'San Luis Potosí'),
(25, 'Sinaloa'),
(26, 'Sonora'),
(27, 'Tabasco'),
(28, 'Tamaulipas'),
(29, 'Tlaxcala'),
(30, 'Veracruz'),
(31, 'Yucatán'),
(32, 'Zacatecas');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `fecha`
--

CREATE TABLE `fecha` (
  `Id_fecha` int(2) NOT NULL,
  `dia` int(2) DEFAULT NULL,
  `Id_mes` int(2) DEFAULT NULL,
  `Id_anio` int(2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `fecha`
--

INSERT INTO `fecha` (`Id_fecha`, `dia`, `Id_mes`, `Id_anio`) VALUES
(1, 16, 6, 34),
(2, 20, 6, 34),
(3, 17, 6, 34),
(4, 7, 11, 1),
(5, 15, 10, 2),
(6, 7, 7, 3),
(7, 22, 11, 4),
(8, 4, 1, 5),
(9, 25, 5, 6),
(10, 23, 8, 7),
(11, 7, 8, 8),
(12, 7, 1, 9),
(13, 28, 7, 10),
(14, 11, 4, 11),
(15, 8, 11, 12),
(16, 16, 12, 13),
(17, 28, 8, 14),
(18, 1, 9, 15),
(19, 29, 5, 16),
(20, 13, 4, 17),
(21, 14, 7, 18),
(22, 11, 6, 19),
(23, 2, 8, 20),
(24, 20, 10, 21),
(25, 10, 6, 22),
(26, 21, 8, 23),
(27, 4, 3, 24),
(28, 23, 1, 25),
(29, 15, 12, 26),
(30, 6, 2, 27),
(31, 5, 11, 28),
(32, 16, 3, 29),
(33, 28, 3, 30),
(34, 12, 3, 31),
(35, 8, 7, 32),
(36, 15, 11, 33),
(37, 27, 6, 34),
(38, 22, 12, 1),
(39, 9, 5, 2),
(40, 5, 9, 3),
(41, 13, 8, 12),
(42, 9, 5, 13),
(43, 16, 7, 14),
(44, 29, 1, 15),
(45, 10, 4, 16),
(46, 21, 11, 17),
(47, 10, 6, 18),
(48, 13, 9, 19),
(49, 1, 10, 20),
(50, 20, 8, 21),
(51, 1, 9, 22),
(52, 25, 3, 23),
(53, 23, 5, 24),
(54, 12, 9, 25),
(55, 4, 11, 26),
(56, 17, 3, 27),
(57, 19, 4, 28),
(58, 7, 10, 29),
(59, 6, 7, 30),
(60, 18, 6, 31),
(61, 4, 12, 32),
(62, 25, 3, 33),
(63, 11, 11, 34),
(64, 5, 11, 32);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `marca`
--

CREATE TABLE `marca` (
  `Id_marca` int(2) NOT NULL,
  `nombre` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `marca`
--

INSERT INTO `marca` (`Id_marca`, `nombre`) VALUES
(1, 'CAIO'),
(6, 'DINA'),
(2, 'Marcopolo'),
(3, 'Mercedez Benz'),
(5, 'Scania'),
(4, 'Volvo');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `mes`
--

CREATE TABLE `mes` (
  `Id_mes` int(2) NOT NULL,
  `mes` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `mes`
--

INSERT INTO `mes` (`Id_mes`, `mes`) VALUES
(4, 'Abril'),
(8, 'Agosto'),
(12, 'Diciembre'),
(1, 'Enero'),
(2, 'Febrero'),
(7, 'Julio'),
(6, 'Junio'),
(3, 'Marzo'),
(5, 'Mayo'),
(11, 'Noviembre'),
(10, 'Octubre'),
(9, 'Septiembre');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `metodo_pago`
--

CREATE TABLE `metodo_pago` (
  `Id_metodo` int(1) NOT NULL,
  `Id_fecha` int(2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `metodo_pago`
--

INSERT INTO `metodo_pago` (`Id_metodo`, `Id_fecha`) VALUES
(1, 1),
(2, 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `modelo`
--

CREATE TABLE `modelo` (
  `Id_modelo` int(2) NOT NULL,
  `nombre` varchar(80) DEFAULT NULL,
  `Id_marca` int(2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `modelo`
--

INSERT INTO `modelo` (`Id_modelo`, `nombre`, `Id_marca`) VALUES
(1, 'Vitoria', 1),
(2, 'Mondego', 1),
(3, 'Allegro', 2),
(4, 'Gran viale', 2),
(5, 'Torino', 2),
(6, 'Viaggio 900', 3),
(7, 'Viaggio 1050', 3),
(8, 'Paradiso 1200', 3),
(9, 'Paradiso 1350', 3),
(10, 'Pradiso 1800 DD', 3),
(11, '9800', 4),
(12, 'B10M', 4),
(13, 'B11R', 4),
(14, 'B12', 4),
(15, 'B12R', 4),
(16, 'B12R I_SHIFT', 4),
(17, 'B9 Salf', 4),
(18, 'B9R', 4),
(19, 'E470J', 4),
(20, 'Olympian', 4),
(21, 'Px D13C 460 I_SHIFT', 4),
(22, 'BR116', 5),
(23, 'F112HL', 5),
(24, 'F113HL', 5),
(25, 'F230', 5),
(26, 'F94HB', 5),
(27, 'K112CL', 5),
(28, 'K112CL 6x2', 5),
(29, 'K113TL', 5),
(30, 'K113TL 8x2', 5),
(31, 'MONARCA', 6),
(32, 'RUNNER', 6),
(33, 'BULLER', 6),
(34, 'D.DISELS60', 6),
(35, 'BRIGNTER', 6),
(36, 'LINNER12', 6),
(37, 'RIDDER E', 6);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `origen`
--

CREATE TABLE `origen` (
  `Id_origen` int(2) NOT NULL,
  `Id_terminal` int(2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `origen`
--

INSERT INTO `origen` (`Id_origen`, `Id_terminal`) VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 5),
(6, 6),
(7, 7),
(8, 8),
(9, 9),
(10, 10),
(11, 11),
(12, 12),
(13, 13),
(14, 14),
(15, 15),
(16, 16),
(17, 17),
(18, 18),
(19, 19),
(20, 20),
(21, 21),
(22, 22),
(23, 23),
(24, 24),
(25, 25),
(26, 26),
(27, 27),
(28, 28),
(29, 29),
(30, 30),
(31, 31),
(32, 32);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pasajero`
--

CREATE TABLE `pasajero` (
  `Id_pasajero` int(3) NOT NULL,
  `tipoPasajero` varchar(7) DEFAULT NULL,
  `descuento` float DEFAULT NULL,
  `Id_persona` int(2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `pasajero`
--

INSERT INTO `pasajero` (`Id_pasajero`, `tipoPasajero`, `descuento`, `Id_persona`) VALUES
(1, 'N', 20, 36),
(2, 'A', 0, 37),
(3, 'D', 25, 38),
(4, 'E', 35, 39),
(5, 'AT', 30, 40),
(6, 'AT', 30, 41),
(7, 'D', 25, 42),
(8, 'E', 35, 43),
(9, 'AT', 30, 44),
(10, 'N', 20, 45),
(11, 'N', 20, 46),
(12, 'N', 20, 47),
(13, 'AT', 30, 48),
(14, 'D', 25, 49),
(15, 'E', 35, 50),
(16, 'D', 25, 51),
(17, 'E', 35, 52),
(18, 'AT', 30, 53),
(19, 'A', 0, 54),
(20, 'A', 0, 55),
(21, 'A', 0, 56),
(22, 'E ', 35, 57),
(23, 'E ', 35, 58),
(24, 'D', 25, 59),
(25, 'A', 0, 60),
(26, 'AT', 30, 61),
(27, 'D', 25, 62),
(28, 'E ', 35, 63),
(29, 'A', 0, 64),
(30, 'N', 20, 65),
(31, 'N', 20, 66),
(32, 'A', 0, 67),
(33, 'D', 25, 68),
(34, 'E', 35, 69),
(35, 'AT', 30, 70),
(36, 'AT', 30, 71),
(37, 'D', 25, 72),
(38, 'E', 35, 73),
(39, 'AT', 30, 74),
(40, 'N', 20, 75),
(41, 'N', 20, 76),
(42, 'N', 20, 77),
(43, 'AT', 30, 78),
(44, 'D', 25, 79),
(45, 'E', 35, 80),
(46, 'D', 25, 81),
(47, 'E', 35, 82),
(48, 'AT', 30, 83),
(49, 'A', 0, 84),
(50, 'A', 0, 85),
(51, 'A', 0, 86),
(52, 'E ', 35, 87),
(53, 'E ', 35, 88),
(54, 'D', 25, 89),
(55, 'A', 0, 90),
(56, 'AT', 30, 91),
(57, 'D', 25, 92),
(58, 'E ', 35, 93),
(59, 'A', 0, 94),
(60, 'N', 20, 95),
(61, 'AT', 30, 96),
(62, 'D', 25, 97),
(63, 'E ', 35, 98),
(64, 'A', 0, 99),
(65, 'N', 20, 100);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `persona`
--

CREATE TABLE `persona` (
  `Id_persona` int(2) NOT NULL,
  `nombre` varchar(40) DEFAULT NULL,
  `ApPat` varchar(40) DEFAULT NULL,
  `ApMat` varchar(40) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `persona`
--

INSERT INTO `persona` (`Id_persona`, `nombre`, `ApPat`, `ApMat`) VALUES
(1, 'Juan Carlos', 'Pérez', 'García'),
(2, 'María', 'López', 'Martínez'),
(3, 'Pedro', 'Hernández', 'Rodríguez'),
(4, 'Ana', 'González', 'Sánchez'),
(5, 'Luis Miguel', 'Ramírez', 'Torres'),
(6, 'Laura', 'Flores', 'Ramos'),
(7, 'José', 'Cruz', 'Hernández'),
(8, 'Karla', 'Jiménez', 'Vega'),
(9, 'Roberto', 'Morales', 'Mendoza'),
(10, 'Carmen', 'Díaz', 'Romero'),
(11, 'Daniel', 'Ortiz', 'Guerrero'),
(12, 'Sofía', 'Castillo', 'Navarro'),
(13, 'Miguel Ángel', 'Rivas', 'Reyes'),
(14, 'Elena', 'Mora', 'Vargas'),
(15, 'Fernando', 'Guzmán', 'Rangel'),
(16, 'Patricia', 'Pineda', 'Soto'),
(17, 'Raúl', 'Cortez', 'Ramos'),
(18, 'Julia', 'Pérez', 'García'),
(19, 'Ricardo', 'Moreno', 'Luna'),
(20, 'Claudia', 'Mejía', 'Fuentes'),
(21, 'Carlos', 'Campos', 'López'),
(22, 'Isabel', 'Rosales', 'Molina'),
(23, 'Héctor', 'Espinoza', 'Vega'),
(24, 'Mónica', 'Medina', 'Ortega'),
(25, 'Javier', 'Herrera', 'Soto'),
(26, 'Gabriela', 'Gallardo', 'Ponce'),
(27, 'Francisco Javier', 'Suárez', 'González'),
(28, 'Verónica', 'Estrada', 'Lara'),
(29, 'Antonio', 'Méndez', 'Guzmán'),
(30, 'Lorena', 'Serrano', 'Silva'),
(31, 'Martín', 'González', 'Ramírez'),
(32, 'Alejandra', 'Domínguez', 'Álvarez'),
(33, 'Enrique', 'Ramos', 'Vázquez'),
(34, 'Natalia', 'Gómez', 'Santos'),
(35, 'Diego', 'Vega', 'Rivas'),
(36, 'Paola', 'Duarte', 'Jiménez'),
(37, 'Felipe', 'Santos', 'Morales'),
(38, 'Victoria', 'Pacheco', 'Castro'),
(39, 'Tomás', 'Acosta', 'Núñez'),
(40, 'Adriana', 'Villalobos', 'Hernández'),
(41, 'Pablo', 'Peña', 'Gómez'),
(42, 'Liliana', 'Carrillo', 'Reyes'),
(43, 'Alberto', 'Rivera', 'Martínez'),
(44, 'Rosa', 'Montoya', 'Navarro'),
(45, 'Armando', 'Rojas', 'Cruz'),
(46, 'Teresa', 'Navarrete', 'Delgado'),
(47, 'Emanuel', 'Santiago', 'Pérez'),
(48, 'Miriam', 'Escobar', 'González'),
(49, 'Esteban', 'Sandoval', 'Guerrero'),
(50, 'Rebeca', 'Valdez', 'Ramos'),
(51, 'Manuel', 'Zamora', 'Paredes'),
(52, 'Alicia', 'Román', 'Vega'),
(53, 'Julio César', 'López', 'Ortega'),
(54, 'Cecilia', 'Hernández', 'Sánchez'),
(55, 'Rodrigo', 'Cárdenas', 'Ramírez'),
(56, 'Susana', 'Gutiérrez', 'Torres'),
(57, 'Eduardo', 'Padilla', 'Vega'),
(58, 'Rocío', 'Blanco', 'Pérez'),
(59, 'Santiago', 'Cisneros', 'Lara'),
(60, 'Luz', 'Salinas', 'Navarro'),
(61, 'Andrés', 'Ávila', 'Aguirre'),
(62, 'Claudia', 'Bautista', 'Barrera'),
(63, 'David', 'Cabrera', 'Carranza'),
(64, 'Evelyn', 'Domínguez', 'Delgado'),
(65, 'Francisco', 'Escobar', 'Espinosa'),
(66, 'Gloria', 'Fernández', 'Flores'),
(67, 'Hugo', 'Gómez', 'Gutiérrez'),
(68, 'Inés', 'Iglesias', 'Ibáñez'),
(69, 'Jorge', 'Juárez', 'Jaramillo'),
(70, 'Karina', 'Krause', 'Lara'),
(71, 'Leonardo', 'León', 'Mendoza'),
(72, 'Mariana', 'Márquez', 'Núñez'),
(73, 'Nicolás', 'Nieves', 'Ortega'),
(74, 'Olivia', 'Orozco', 'Prieto'),
(75, 'Pablo', 'Paredes', 'Quiroga'),
(76, 'Quetzal', 'Quintero', 'Rojas'),
(77, 'Raquel', 'Ruiz', 'Salas'),
(78, 'Samuel', 'Sáenz', 'Tapia'),
(79, 'Tania', 'Trujillo', 'Uribe'),
(80, 'Ulises', 'Urbina', 'Valdez'),
(81, 'Víctor', 'Valdez', 'Álvarez'),
(82, 'Ximena', 'Acosta', 'Beltrán'),
(83, 'Yolanda', 'Benítez', 'Chávez'),
(84, 'Zoé', 'Castañeda', 'Delgado'),
(85, 'Benjamín', 'Duarte', 'Escalante'),
(86, 'Cristina', 'Estrada', 'Figueroa'),
(87, 'Daniel', 'Fuentes', 'Galindo'),
(88, 'Elisa', 'Gallardo', 'Hernández'),
(89, 'Federico', 'Herrera', 'Izquierdo'),
(90, 'Gabriela', 'Ibarra', 'Juárez'),
(91, 'Hernán', 'Jiménez', 'León'),
(92, 'Irene', 'Lozano', 'Medina'),
(93, 'Jaime', 'Méndez', 'Núñez'),
(94, 'Karen', 'Navarro', 'Olivares'),
(95, 'Luis', 'Ortega', 'Peña'),
(96, 'Marta', 'Pineda', 'Quijano'),
(97, 'Norberto', 'Quintana', 'Rivera'),
(98, 'Patricia', 'Ramos', 'Sánchez'),
(99, 'Renato', 'Salazar', 'Torres'),
(100, 'Silvia', 'Torres', 'Vargas');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `reembolso`
--

CREATE TABLE `reembolso` (
  `Id_reembolso` int(2) NOT NULL,
  `cantidad` float DEFAULT NULL,
  `Id_boleto` int(3) DEFAULT NULL,
  `Id_fecha` int(2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `reembolso`
--

INSERT INTO `reembolso` (`Id_reembolso`, `cantidad`, `Id_boleto`, `Id_fecha`) VALUES
(1, 3000, 1, 2),
(2, 4196.25, 3, 36),
(3, 6020, 4, 3),
(4, 496.8, 5, 3),
(5, 402, 6, 3),
(6, 375, 7, 3),
(7, 420, 8, 2),
(8, 5035, 9, 3),
(9, 3440, 10, 3),
(10, 331, 11, 2),
(11, 268, 12, 2),
(12, 450, 13, 2),
(13, 300, 14, 2),
(14, 10910, 15, 3),
(15, 4300, 16, 3),
(16, 579.6, 17, 3),
(17, 402, 18, 3),
(18, 6020, 22, 37),
(19, 576, 23, 36),
(20, 335, 24, 36),
(21, 360, 26, 2),
(22, 4196, 27, 2),
(23, 6020, 28, 36),
(24, 268, 30, 37),
(25, 240, 31, 2),
(26, 300, 33, 2),
(27, 420, 34, 2),
(28, 360, 35, 2),
(29, 360, 36, 2),
(30, 300, 37, 2),
(31, 420, 38, 2),
(32, 360, 39, 2),
(33, 240, 40, 2),
(34, 331, 41, 36),
(35, 268, 42, 36),
(36, 450, 43, 36),
(37, 300, 44, 2),
(38, 10910, 45, 37),
(39, 4300, 46, 2),
(40, 579.6, 47, 1),
(41, 402, 48, 3),
(42, 6020, 52, 3),
(43, 576, 53, 36),
(44, 335, 54, 36),
(45, 360, 56, 2),
(46, 4196, 57, 1),
(47, 6020, 58, 36),
(48, 268, 60, 37),
(49, 450, 61, 2),
(50, 900, 62, 2),
(51, 5874.75, 63, 2),
(52, 331.2, 65, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ruta`
--

CREATE TABLE `ruta` (
  `Id_ruta` int(2) NOT NULL,
  `nombre` varchar(80) DEFAULT NULL,
  `duracion_ruta` varchar(10) DEFAULT NULL,
  `hora_salida` varchar(12) DEFAULT NULL,
  `hora_llegada` varchar(12) DEFAULT NULL,
  `precio` float DEFAULT NULL,
  `distancia` varchar(15) DEFAULT NULL,
  `Id_origen` int(2) DEFAULT NULL,
  `Id_destino` int(2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `ruta`
--

INSERT INTO `ruta` (`Id_ruta`, `nombre`, `duracion_ruta`, `hora_salida`, `hora_llegada`, `precio`, `distancia`, `Id_origen`, `Id_destino`) VALUES
(1, 'Ruta del Sol', '29 horas', '08:00 a. m.', '13:00 PM', 16785, '2350 km', 7, 26),
(2, 'Ruta del Puente', '29 horas', '08:00 a. m.', '13:00 PM', 16785, '2350 km', 26, 7),
(3, 'Ruta la Estelar', '16 horas', '12:00 p. m.', '04:00 a. m.', 13055, '1074 km', 20, 19),
(4, 'Ruta del Peregrino', '16 horas', '12:00 p. m.', '04:00 a. m.', 13055, '1074 km', 19, 20),
(5, 'Ruta los Guerreros', '15 horas', '04:00 p. m.', '07:00 a. m.', 12101, '841 km', 23, 30),
(6, 'Ruta Guanacos', '15 horas', '04:00 p. m.', '07:00 a. m.', 12101, '841 km', 30, 23),
(7, 'Ruta los Pescadores', '21 horas', '10:00 p. m.', '07:00 p. m.', 14593, '1313 km', 12, 31),
(8, 'Ruta la Lagunilla', '21horas', '10:00 p. m.', '07:00 p. m.', 14593, '1313 km', 31, 12),
(9, 'Ruta los Testigos', '23 horas', '02:00 a. m.', '01:00 a. m.', 15000, '1383.4 km', 4, 6),
(10, 'Ruta las Palmeras', '23 horas', '02:00 a. m.', '01:00 a. m.', 15000, '1383.4 km', 6, 4),
(11, 'Ruta San Antonio', '7 horas', '04:00 a. m.', '11:00 a. m.', 10365, '495 km', 16, 21),
(12, 'Ruta el Ostión', '7 horas', '04:00 a. m.', '11:00 a. m.', 10365, '495 km', 21, 16),
(13, 'Ruta Rancho Grande', '3 horas', '08:00 a. m.', '11:00 a. m.', 5880, '196 km', 17, 29),
(14, 'Ruta la Cebada', '3 horas', '08:00 a. m.', '11:00 a. m.', 5880, '196 km', 29, 17),
(15, 'Ruta los Mapogo', '5 horas', '12:00 p. m.', '05:00 p. m.', 9625, '385 km', 22, 14),
(16, 'Ruta los Pinos', '5 horas', '12:00 p. m.', '05:00 p. m.', 9625, '385 km', 14, 22),
(17, 'Ruta del Río', '17 horas', '04:00 p. m.', '09:00 a. m.', 4935, '1453.6 km', 9, 18),
(18, 'Ruta los Cachanillas', '17 horas', '04:00 p. m.', '09:00 a. m.', 4935, '1453.6 km', 18, 9),
(19, 'Ruta las Palmas', '4 horas', '08:00 p. m.', '12:00 a. m.', 1340, '334.9 km', 15, 11),
(20, 'Ruta la Cerrada', '4 horas', '08:00 p. m.', '12:00 a. m.', 1340, '334.9 km', 11, 15),
(21, 'Ruta Villada', '8 horas', '12:00 a. m.', '08:00 a. m.', 1656, '698 km', 13, 32),
(22, 'Ruta el Castillo', '8 horas', '12:00 a. m.', '08:00 a. m.', 1656, '698 km', 32, 13),
(23, 'Ruta los egidales ', '25 horas', '04:00 a. m.', '05:00 a. m.', 5925, '1977 km', 24, 3),
(24, 'Ruta del Valle', '25 horas', '04:00 a. m.', '05:00 a. m.', 5925, '1977 km', 3, 24),
(25, 'Ruta los Mercados', '17 horas', '08:00 a. m.', '01:00 a. m.', 5467, '1027 km', 28, 25),
(26, 'Ruta Escobar', '17 horas', '08:00 a. m.', '01:00 a. m.', 5467, '1027 km', 25, 28),
(27, 'Ruta Leon de los rios', '13 horas', '12:00 p. m.', '01:00 a. m.', 8514, '968.1 km', 18, 10),
(28, 'Ruta la Rumorosa', '13 horas', '12:00 p. m.', '01:00 a. m.', 8514, '968.1 km', 10, 18),
(29, 'Ruta los Angeles', '30 horas', '04:00 p. m.', '10:00 p. m.', 17200, '2400 km', 1, 3),
(30, 'Ruta la Tasqueña', '30 horas', '04:00 p. m.', '10:00 p. m.', 17200, '2400 km', 3, 1),
(31, 'Ruta la Estrella', '28 horas', '08:00 p. m.', '12:00 a. m.', 16600, '2200 km', 27, 10),
(32, 'Ruta Carmelo Perez', '28 horas', '08:00 p. m.', '12:00 a. m.', 16600, '2200 km', 10, 27),
(33, 'Ruta los Carcajillos', '21 horas', '08:00 a. m.', '06:00 a. m.', 1500, '1607 km', 9, 23),
(34, 'Ruta Porfirio Díaz', '21 horas', '08:00 a. m.', '06:00 a. m.', 1500, '1607 km', 23, 9),
(35, 'Ruta Jose Maria', '5 horas', '10:00 a. m.', '03:00 a. m.', 1200, '282 km', 9, 16),
(36, 'Ruta los Renacidos', '5 horas', '10:00 a. m.', '03:00 a. m.', 1200, '283 km', 16, 9);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `rutaautobus`
--

CREATE TABLE `rutaautobus` (
  `Id_RutAut` int(2) NOT NULL,
  `Id_ruta` int(2) DEFAULT NULL,
  `Id_autobus` int(2) DEFAULT NULL,
  `Id_fecha` int(2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `rutaautobus`
--

INSERT INTO `rutaautobus` (`Id_RutAut`, `Id_ruta`, `Id_autobus`, `Id_fecha`) VALUES
(1, 1, 1, 36),
(2, 29, 10, 37),
(3, 33, 22, 1),
(4, 35, 24, 2),
(5, 15, 31, 1),
(6, 21, 36, 3);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `rutaconductor`
--

CREATE TABLE `rutaconductor` (
  `Id_RutCon` int(2) NOT NULL,
  `Id_ruta` int(2) DEFAULT NULL,
  `Id_conductor` int(2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `rutaconductor`
--

INSERT INTO `rutaconductor` (`Id_RutCon`, `Id_ruta`, `Id_conductor`) VALUES
(1, 1, 1),
(2, 1, 2),
(3, 1, 3),
(4, 1, 4),
(5, 29, 5),
(6, 29, 6),
(7, 29, 7),
(8, 29, 8),
(9, 33, 9),
(10, 33, 10),
(11, 33, 11),
(12, 35, 12),
(13, 15, 13),
(14, 21, 14),
(15, 30, 15),
(16, 30, 16),
(17, 30, 17);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `rutaterminal`
--

CREATE TABLE `rutaterminal` (
  `Id_RutTer` int(2) NOT NULL,
  `Id_ruta` int(2) DEFAULT NULL,
  `Id_terminal` int(2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `rutaterminal`
--

INSERT INTO `rutaterminal` (`Id_RutTer`, `Id_ruta`, `Id_terminal`) VALUES
(1, 1, 30),
(2, 1, 9),
(3, 1, 14),
(4, 1, 18),
(5, 1, 25),
(6, 29, 32),
(7, 29, 10),
(8, 29, 25),
(9, 29, 26),
(10, 29, 2),
(11, 33, 21),
(12, 33, 30),
(13, 33, 27),
(14, 33, 4),
(15, 35, 15),
(16, 15, 11),
(17, 21, 11),
(18, 21, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tarjeta`
--

CREATE TABLE `tarjeta` (
  `Id_tarjeta` int(2) NOT NULL,
  `numeroCuenta` int(20) DEFAULT NULL,
  `CVV` int(3) DEFAULT NULL,
  `Id_anio` int(2) DEFAULT NULL,
  `Id_mes` int(2) DEFAULT NULL,
  `Id_metodo` int(1) DEFAULT NULL,
  `Id_cliente` int(2) DEFAULT NULL,
  `Id_tipo_tarjeta` int(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `tarjeta`
--

INSERT INTO `tarjeta` (`Id_tarjeta`, `numeroCuenta`, `CVV`, `Id_anio`, `Id_mes`, `Id_metodo`, `Id_cliente`, `Id_tipo_tarjeta`) VALUES
(1, -16912, 123, 34, 1, 2, 1, 1),
(2, -4308, 456, 33, 5, 2, 2, 1),
(3, -13468, 789, 34, 3, 2, 3, 2),
(4, -3086, 234, 32, 10, 2, 4, 2),
(5, -9134, 567, 34, 1, 2, 5, 1),
(6, -10864, 890, 31, 7, 2, 6, 2),
(7, -11346, 321, 30, 9, 2, 7, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `telefonoterminal`
--

CREATE TABLE `telefonoterminal` (
  `Id_telefonoTerminal` int(2) NOT NULL,
  `telefono` varchar(10) DEFAULT NULL,
  `Id_terminal` int(2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `telefonoterminal`
--

INSERT INTO `telefonoterminal` (`Id_telefonoTerminal`, `telefono`, `Id_terminal`) VALUES
(1, '5546814951', 1),
(2, '5512250549', 2),
(3, '5507308546', 3),
(4, '5539587385', 4),
(5, '5587319426', 5),
(6, '5531318599', 6),
(7, '5590356104', 7),
(8, '5568496300', 8),
(9, '5525326037', 9),
(10, '5517862252', 10),
(11, '5593830689', 11),
(12, '5577220204', 12),
(13, '5590737384', 13),
(14, '5513976223', 14),
(15, '5558273240', 15),
(16, '5584358527', 16),
(17, '5559726069', 17),
(18, '5559777235', 18),
(19, '5528458438', 19),
(20, '5581931691', 20),
(21, '5588747840', 21),
(22, '5594457807', 22),
(23, '5537837322', 23),
(24, '5545181574', 24),
(25, '5569789013', 25),
(26, '5506414922', 26),
(27, '5506114419', 27),
(28, '5564489642', 28),
(29, '5590226448', 29),
(30, '5502007461', 30),
(31, '5513068131', 31),
(32, '5585625370', 32);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `telefono_persona`
--

CREATE TABLE `telefono_persona` (
  `Id_telefonoPersona` int(3) NOT NULL,
  `telefono` varchar(10) DEFAULT NULL,
  `Id_persona` int(3) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `telefono_persona`
--

INSERT INTO `telefono_persona` (`Id_telefonoPersona`, `telefono`, `Id_persona`) VALUES
(1, '5564462181', 1),
(2, '5514551330', 2),
(3, '5588656230', 3),
(4, '5537547645', 4),
(5, '5503665907', 5),
(6, '5508753701', 6),
(7, '5582014347', 7),
(8, '5500515178', 8),
(9, '5574696821', 9),
(10, '5598961882', 10),
(11, '5534510980', 11),
(12, '5500968914', 12),
(13, '5588813762', 13),
(14, '5520632589', 14),
(15, '5553455316', 15),
(16, '5567723888', 16),
(17, '5585331178', 17),
(18, '5521685961', 18),
(19, '5588417791', 19),
(20, '5598375318', 20),
(21, '5530176599', 21),
(22, '5526661379', 22),
(23, '5520039237', 23),
(24, '5544641155', 24),
(25, '5575774291', 25),
(26, '5513783597', 26),
(27, '5548898860', 27),
(28, '5595695243', 28),
(29, '5592931633', 29),
(30, '5584354346', 30),
(31, '5539241978', 31),
(32, '5580695022', 32),
(33, '5544285278', 33),
(34, '5523331220', 34),
(35, '5576407560', 35),
(36, '5566965095', 36),
(37, '5556875169', 37),
(38, '5557788463', 38),
(39, '5514610435', 39),
(40, '5559145905', 40),
(41, '5510491546', 41),
(42, '5513678268', 42),
(43, '5531717049', 43),
(44, '5509066355', 44),
(45, '5563653571', 45),
(46, '5569780039', 46),
(47, '5575595135', 47),
(48, '5539535788', 48),
(49, '5529207791', 49),
(50, '5535119854', 50),
(51, '5527181237', 51),
(52, '5565211650', 52),
(53, '5581414716', 53),
(54, '5591006845', 54),
(55, '5597600398', 55),
(56, '5580738821', 56),
(57, '5568913962', 57),
(58, '5532135298', 58),
(59, '5534994769', 59),
(60, '5515836170', 60);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `terminal`
--

CREATE TABLE `terminal` (
  `Id_terminal` int(2) NOT NULL,
  `nombre` varchar(80) DEFAULT NULL,
  `Id_direccion` int(2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `terminal`
--

INSERT INTO `terminal` (`Id_terminal`, `nombre`, `Id_direccion`) VALUES
(1, 'Terminal del Mar', 1),
(2, 'Montecillos', 2),
(3, 'Catarina', 3),
(4, 'Gran Central', 4),
(5, 'Terminal la juzco', 5),
(6, 'Alajeros', 6),
(7, 'Terminal Rodalia', 7),
(8, 'Centro Busways', 8),
(9, 'TAPO', 9),
(10, 'Estación del Sol', 10),
(11, 'Rio de los remedios', 11),
(12, 'Tiernosillo', 12),
(13, 'San Pedro', 13),
(14, 'Terminal Central', 14),
(15, 'San Miguel', 15),
(16, 'Piedras verdes', 16),
(17, 'Florerias', 17),
(18, 'Jusgados', 18),
(19, 'Texcocanos', 19),
(20, 'Sancho Ortiz', 20),
(21, 'Escuadron 201', 21),
(22, 'Estación del Progreso', 22),
(23, 'Terminal Pasco', 23),
(24, 'Terminal del Milenio', 24),
(25, 'Gran Paloma', 25),
(26, 'Zapata', 26),
(27, 'Central del Viaje', 27),
(28, 'Villa', 28),
(29, 'Terminal Umbrela', 29),
(30, 'Morelos', 30),
(31, 'Arquimedez', 31),
(32, 'Leos Esquin', 32);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipo_tarjeta`
--

CREATE TABLE `tipo_tarjeta` (
  `Id_tipo_tarjeta` int(1) NOT NULL,
  `descripcion` varchar(7) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `tipo_tarjeta`
--

INSERT INTO `tipo_tarjeta` (`Id_tipo_tarjeta`, `descripcion`) VALUES
(1, 'Credito'),
(2, 'Debito');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `anio`
--
ALTER TABLE `anio`
  ADD PRIMARY KEY (`Id_anio`),
  ADD UNIQUE KEY `anio` (`anio`);

--
-- Indices de la tabla `asiento`
--
ALTER TABLE `asiento`
  ADD PRIMARY KEY (`Id_asiento`),
  ADD KEY `Id_autobus` (`Id_autobus`);

--
-- Indices de la tabla `autobus`
--
ALTER TABLE `autobus`
  ADD PRIMARY KEY (`Id_autobus`),
  ADD UNIQUE KEY `num_economico` (`num_economico`),
  ADD UNIQUE KEY `placa` (`placa`),
  ADD KEY `Id_anio` (`Id_anio`),
  ADD KEY `Id_fecha` (`Id_fecha`),
  ADD KEY `Id_modelo` (`Id_modelo`);

--
-- Indices de la tabla `autobusconductor`
--
ALTER TABLE `autobusconductor`
  ADD PRIMARY KEY (`Id_AutCon`),
  ADD KEY `Id_conductor` (`Id_conductor`),
  ADD KEY `Id_autobus` (`Id_autobus`);

--
-- Indices de la tabla `baja`
--
ALTER TABLE `baja`
  ADD PRIMARY KEY (`Id_baja`),
  ADD KEY `Id_autobus` (`Id_autobus`),
  ADD KEY `Id_fecha` (`Id_fecha`);

--
-- Indices de la tabla `boleto`
--
ALTER TABLE `boleto`
  ADD PRIMARY KEY (`Id_boleto`),
  ADD KEY `Id_metodo` (`Id_metodo`),
  ADD KEY `Id_ruta` (`Id_ruta`),
  ADD KEY `Id_fecha` (`Id_fecha`),
  ADD KEY `Id_pasajero` (`Id_pasajero`),
  ADD KEY `Id_asiento` (`Id_asiento`);

--
-- Indices de la tabla `boletocliente`
--
ALTER TABLE `boletocliente`
  ADD PRIMARY KEY (`Id_BolClie`),
  ADD KEY `Id_boleto` (`Id_boleto`),
  ADD KEY `Id_cliente` (`Id_cliente`);

--
-- Indices de la tabla `ciudad`
--
ALTER TABLE `ciudad`
  ADD PRIMARY KEY (`Id_ciudad`),
  ADD UNIQUE KEY `nombre` (`nombre`),
  ADD KEY `Id_estado` (`Id_estado`);

--
-- Indices de la tabla `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`Id_cliente`),
  ADD UNIQUE KEY `correo` (`correo`),
  ADD KEY `Id_persona` (`Id_persona`);

--
-- Indices de la tabla `codigo_postal`
--
ALTER TABLE `codigo_postal`
  ADD PRIMARY KEY (`Id_CP`),
  ADD UNIQUE KEY `codigo_postal` (`codigo_postal`);

--
-- Indices de la tabla `colonia`
--
ALTER TABLE `colonia`
  ADD PRIMARY KEY (`Id_colonia`),
  ADD UNIQUE KEY `colonia` (`colonia`);

--
-- Indices de la tabla `conductor`
--
ALTER TABLE `conductor`
  ADD PRIMARY KEY (`Id_conductor`),
  ADD KEY `Id_persona` (`Id_persona`);

--
-- Indices de la tabla `destino`
--
ALTER TABLE `destino`
  ADD PRIMARY KEY (`Id_destino`),
  ADD KEY `Id_terminal` (`Id_terminal`);

--
-- Indices de la tabla `direccion`
--
ALTER TABLE `direccion`
  ADD PRIMARY KEY (`Id_direccion`),
  ADD KEY `Id_ciudad` (`Id_ciudad`),
  ADD KEY `Id_colonia` (`Id_colonia`),
  ADD KEY `Id_CP` (`Id_CP`);

--
-- Indices de la tabla `efectivo`
--
ALTER TABLE `efectivo`
  ADD PRIMARY KEY (`Id_efectivo`),
  ADD KEY `Id_metodo` (`Id_metodo`);

--
-- Indices de la tabla `estado`
--
ALTER TABLE `estado`
  ADD PRIMARY KEY (`Id_estado`),
  ADD UNIQUE KEY `nombre` (`nombre`);

--
-- Indices de la tabla `fecha`
--
ALTER TABLE `fecha`
  ADD PRIMARY KEY (`Id_fecha`),
  ADD KEY `Id_mes` (`Id_mes`),
  ADD KEY `Id_anio` (`Id_anio`);

--
-- Indices de la tabla `marca`
--
ALTER TABLE `marca`
  ADD PRIMARY KEY (`Id_marca`),
  ADD UNIQUE KEY `nombre` (`nombre`);

--
-- Indices de la tabla `mes`
--
ALTER TABLE `mes`
  ADD PRIMARY KEY (`Id_mes`),
  ADD UNIQUE KEY `mes` (`mes`);

--
-- Indices de la tabla `metodo_pago`
--
ALTER TABLE `metodo_pago`
  ADD PRIMARY KEY (`Id_metodo`),
  ADD KEY `Id_fecha` (`Id_fecha`);

--
-- Indices de la tabla `modelo`
--
ALTER TABLE `modelo`
  ADD PRIMARY KEY (`Id_modelo`),
  ADD UNIQUE KEY `nombre` (`nombre`),
  ADD KEY `Id_marca` (`Id_marca`);

--
-- Indices de la tabla `origen`
--
ALTER TABLE `origen`
  ADD PRIMARY KEY (`Id_origen`),
  ADD KEY `Id_terminal` (`Id_terminal`);

--
-- Indices de la tabla `pasajero`
--
ALTER TABLE `pasajero`
  ADD PRIMARY KEY (`Id_pasajero`),
  ADD KEY `Id_persona` (`Id_persona`);

--
-- Indices de la tabla `persona`
--
ALTER TABLE `persona`
  ADD PRIMARY KEY (`Id_persona`);

--
-- Indices de la tabla `reembolso`
--
ALTER TABLE `reembolso`
  ADD PRIMARY KEY (`Id_reembolso`),
  ADD KEY `Id_boleto` (`Id_boleto`),
  ADD KEY `Id_fecha` (`Id_fecha`);

--
-- Indices de la tabla `ruta`
--
ALTER TABLE `ruta`
  ADD PRIMARY KEY (`Id_ruta`),
  ADD KEY `Id_origen` (`Id_origen`),
  ADD KEY `Id_destino` (`Id_destino`);

--
-- Indices de la tabla `rutaautobus`
--
ALTER TABLE `rutaautobus`
  ADD PRIMARY KEY (`Id_RutAut`),
  ADD KEY `Id_ruta` (`Id_ruta`),
  ADD KEY `Id_autobus` (`Id_autobus`),
  ADD KEY `Id_fecha` (`Id_fecha`);

--
-- Indices de la tabla `rutaconductor`
--
ALTER TABLE `rutaconductor`
  ADD PRIMARY KEY (`Id_RutCon`),
  ADD KEY `Id_ruta` (`Id_ruta`),
  ADD KEY `Id_conductor` (`Id_conductor`);

--
-- Indices de la tabla `rutaterminal`
--
ALTER TABLE `rutaterminal`
  ADD PRIMARY KEY (`Id_RutTer`),
  ADD KEY `Id_ruta` (`Id_ruta`),
  ADD KEY `Id_terminal` (`Id_terminal`);

--
-- Indices de la tabla `tarjeta`
--
ALTER TABLE `tarjeta`
  ADD PRIMARY KEY (`Id_tarjeta`),
  ADD UNIQUE KEY `numeroCuenta` (`numeroCuenta`),
  ADD KEY `Id_anio` (`Id_anio`),
  ADD KEY `Id_mes` (`Id_mes`),
  ADD KEY `Id_metodo` (`Id_metodo`),
  ADD KEY `Id_cliente` (`Id_cliente`),
  ADD KEY `Id_tipo_tarjeta` (`Id_tipo_tarjeta`);

--
-- Indices de la tabla `telefonoterminal`
--
ALTER TABLE `telefonoterminal`
  ADD PRIMARY KEY (`Id_telefonoTerminal`),
  ADD UNIQUE KEY `telefono` (`telefono`),
  ADD KEY `Id_terminal` (`Id_terminal`);

--
-- Indices de la tabla `telefono_persona`
--
ALTER TABLE `telefono_persona`
  ADD PRIMARY KEY (`Id_telefonoPersona`),
  ADD UNIQUE KEY `telefono` (`telefono`),
  ADD KEY `Id_persona` (`Id_persona`);

--
-- Indices de la tabla `terminal`
--
ALTER TABLE `terminal`
  ADD PRIMARY KEY (`Id_terminal`),
  ADD UNIQUE KEY `nombre` (`nombre`),
  ADD KEY `Id_direccion` (`Id_direccion`);

--
-- Indices de la tabla `tipo_tarjeta`
--
ALTER TABLE `tipo_tarjeta`
  ADD PRIMARY KEY (`Id_tipo_tarjeta`),
  ADD UNIQUE KEY `descripcion` (`descripcion`);

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `asiento`
--
ALTER TABLE `asiento`
  ADD CONSTRAINT `asiento_ibfk_1` FOREIGN KEY (`Id_autobus`) REFERENCES `autobus` (`Id_autobus`);

--
-- Filtros para la tabla `autobus`
--
ALTER TABLE `autobus`
  ADD CONSTRAINT `autobus_ibfk_1` FOREIGN KEY (`Id_anio`) REFERENCES `anio` (`Id_anio`),
  ADD CONSTRAINT `autobus_ibfk_2` FOREIGN KEY (`Id_fecha`) REFERENCES `fecha` (`Id_fecha`),
  ADD CONSTRAINT `autobus_ibfk_3` FOREIGN KEY (`Id_modelo`) REFERENCES `modelo` (`Id_modelo`);

--
-- Filtros para la tabla `autobusconductor`
--
ALTER TABLE `autobusconductor`
  ADD CONSTRAINT `autobusconductor_ibfk_1` FOREIGN KEY (`Id_conductor`) REFERENCES `conductor` (`Id_conductor`),
  ADD CONSTRAINT `autobusconductor_ibfk_2` FOREIGN KEY (`Id_autobus`) REFERENCES `autobus` (`Id_autobus`);

--
-- Filtros para la tabla `baja`
--
ALTER TABLE `baja`
  ADD CONSTRAINT `baja_ibfk_1` FOREIGN KEY (`Id_autobus`) REFERENCES `autobus` (`Id_autobus`),
  ADD CONSTRAINT `baja_ibfk_2` FOREIGN KEY (`Id_fecha`) REFERENCES `fecha` (`Id_fecha`);

--
-- Filtros para la tabla `boleto`
--
ALTER TABLE `boleto`
  ADD CONSTRAINT `boleto_ibfk_1` FOREIGN KEY (`Id_metodo`) REFERENCES `metodo_pago` (`Id_metodo`),
  ADD CONSTRAINT `boleto_ibfk_2` FOREIGN KEY (`Id_ruta`) REFERENCES `ruta` (`Id_ruta`),
  ADD CONSTRAINT `boleto_ibfk_3` FOREIGN KEY (`Id_fecha`) REFERENCES `fecha` (`Id_fecha`),
  ADD CONSTRAINT `boleto_ibfk_4` FOREIGN KEY (`Id_pasajero`) REFERENCES `pasajero` (`Id_pasajero`),
  ADD CONSTRAINT `boleto_ibfk_5` FOREIGN KEY (`Id_asiento`) REFERENCES `asiento` (`Id_asiento`);

--
-- Filtros para la tabla `boletocliente`
--
ALTER TABLE `boletocliente`
  ADD CONSTRAINT `boletocliente_ibfk_1` FOREIGN KEY (`Id_boleto`) REFERENCES `boleto` (`Id_boleto`),
  ADD CONSTRAINT `boletocliente_ibfk_2` FOREIGN KEY (`Id_cliente`) REFERENCES `cliente` (`Id_cliente`);

--
-- Filtros para la tabla `ciudad`
--
ALTER TABLE `ciudad`
  ADD CONSTRAINT `ciudad_ibfk_1` FOREIGN KEY (`Id_estado`) REFERENCES `estado` (`Id_estado`);

--
-- Filtros para la tabla `cliente`
--
ALTER TABLE `cliente`
  ADD CONSTRAINT `cliente_ibfk_1` FOREIGN KEY (`Id_persona`) REFERENCES `persona` (`Id_persona`);

--
-- Filtros para la tabla `conductor`
--
ALTER TABLE `conductor`
  ADD CONSTRAINT `conductor_ibfk_1` FOREIGN KEY (`Id_persona`) REFERENCES `persona` (`Id_persona`);

--
-- Filtros para la tabla `destino`
--
ALTER TABLE `destino`
  ADD CONSTRAINT `destino_ibfk_1` FOREIGN KEY (`Id_terminal`) REFERENCES `terminal` (`Id_terminal`);

--
-- Filtros para la tabla `direccion`
--
ALTER TABLE `direccion`
  ADD CONSTRAINT `direccion_ibfk_1` FOREIGN KEY (`Id_ciudad`) REFERENCES `ciudad` (`Id_ciudad`),
  ADD CONSTRAINT `direccion_ibfk_2` FOREIGN KEY (`Id_colonia`) REFERENCES `colonia` (`Id_colonia`),
  ADD CONSTRAINT `direccion_ibfk_3` FOREIGN KEY (`Id_CP`) REFERENCES `codigo_postal` (`Id_CP`);

--
-- Filtros para la tabla `efectivo`
--
ALTER TABLE `efectivo`
  ADD CONSTRAINT `efectivo_ibfk_1` FOREIGN KEY (`Id_metodo`) REFERENCES `metodo_pago` (`Id_metodo`);

--
-- Filtros para la tabla `fecha`
--
ALTER TABLE `fecha`
  ADD CONSTRAINT `fecha_ibfk_1` FOREIGN KEY (`Id_mes`) REFERENCES `mes` (`Id_mes`),
  ADD CONSTRAINT `fecha_ibfk_2` FOREIGN KEY (`Id_anio`) REFERENCES `anio` (`Id_anio`);

--
-- Filtros para la tabla `metodo_pago`
--
ALTER TABLE `metodo_pago`
  ADD CONSTRAINT `metodo_pago_ibfk_1` FOREIGN KEY (`Id_fecha`) REFERENCES `fecha` (`Id_fecha`);

--
-- Filtros para la tabla `modelo`
--
ALTER TABLE `modelo`
  ADD CONSTRAINT `modelo_ibfk_1` FOREIGN KEY (`Id_marca`) REFERENCES `marca` (`Id_marca`);

--
-- Filtros para la tabla `origen`
--
ALTER TABLE `origen`
  ADD CONSTRAINT `origen_ibfk_1` FOREIGN KEY (`Id_terminal`) REFERENCES `terminal` (`Id_terminal`);

--
-- Filtros para la tabla `pasajero`
--
ALTER TABLE `pasajero`
  ADD CONSTRAINT `pasajero_ibfk_1` FOREIGN KEY (`Id_persona`) REFERENCES `persona` (`Id_persona`);

--
-- Filtros para la tabla `reembolso`
--
ALTER TABLE `reembolso`
  ADD CONSTRAINT `reembolso_ibfk_1` FOREIGN KEY (`Id_boleto`) REFERENCES `boleto` (`Id_boleto`),
  ADD CONSTRAINT `reembolso_ibfk_2` FOREIGN KEY (`Id_fecha`) REFERENCES `fecha` (`Id_fecha`);

--
-- Filtros para la tabla `ruta`
--
ALTER TABLE `ruta`
  ADD CONSTRAINT `ruta_ibfk_1` FOREIGN KEY (`Id_origen`) REFERENCES `origen` (`Id_origen`),
  ADD CONSTRAINT `ruta_ibfk_2` FOREIGN KEY (`Id_destino`) REFERENCES `destino` (`Id_destino`);

--
-- Filtros para la tabla `rutaautobus`
--
ALTER TABLE `rutaautobus`
  ADD CONSTRAINT `rutaautobus_ibfk_1` FOREIGN KEY (`Id_ruta`) REFERENCES `ruta` (`Id_ruta`),
  ADD CONSTRAINT `rutaautobus_ibfk_2` FOREIGN KEY (`Id_autobus`) REFERENCES `autobus` (`Id_autobus`),
  ADD CONSTRAINT `rutaautobus_ibfk_3` FOREIGN KEY (`Id_fecha`) REFERENCES `fecha` (`Id_fecha`);

--
-- Filtros para la tabla `rutaconductor`
--
ALTER TABLE `rutaconductor`
  ADD CONSTRAINT `rutaconductor_ibfk_1` FOREIGN KEY (`Id_ruta`) REFERENCES `ruta` (`Id_ruta`),
  ADD CONSTRAINT `rutaconductor_ibfk_2` FOREIGN KEY (`Id_conductor`) REFERENCES `conductor` (`Id_conductor`);

--
-- Filtros para la tabla `rutaterminal`
--
ALTER TABLE `rutaterminal`
  ADD CONSTRAINT `rutaterminal_ibfk_1` FOREIGN KEY (`Id_ruta`) REFERENCES `ruta` (`Id_ruta`),
  ADD CONSTRAINT `rutaterminal_ibfk_2` FOREIGN KEY (`Id_terminal`) REFERENCES `terminal` (`Id_terminal`);

--
-- Filtros para la tabla `tarjeta`
--
ALTER TABLE `tarjeta`
  ADD CONSTRAINT `tarjeta_ibfk_1` FOREIGN KEY (`Id_anio`) REFERENCES `anio` (`Id_anio`),
  ADD CONSTRAINT `tarjeta_ibfk_2` FOREIGN KEY (`Id_mes`) REFERENCES `mes` (`Id_mes`),
  ADD CONSTRAINT `tarjeta_ibfk_3` FOREIGN KEY (`Id_metodo`) REFERENCES `metodo_pago` (`Id_metodo`),
  ADD CONSTRAINT `tarjeta_ibfk_4` FOREIGN KEY (`Id_cliente`) REFERENCES `cliente` (`Id_cliente`),
  ADD CONSTRAINT `tarjeta_ibfk_5` FOREIGN KEY (`Id_tipo_tarjeta`) REFERENCES `tipo_tarjeta` (`Id_tipo_tarjeta`);

--
-- Filtros para la tabla `telefonoterminal`
--
ALTER TABLE `telefonoterminal`
  ADD CONSTRAINT `telefonoterminal_ibfk_1` FOREIGN KEY (`Id_terminal`) REFERENCES `terminal` (`Id_terminal`);

--
-- Filtros para la tabla `telefono_persona`
--
ALTER TABLE `telefono_persona`
  ADD CONSTRAINT `telefono_persona_ibfk_1` FOREIGN KEY (`Id_persona`) REFERENCES `persona` (`Id_persona`);

--
-- Filtros para la tabla `terminal`
--
ALTER TABLE `terminal`
  ADD CONSTRAINT `terminal_ibfk_1` FOREIGN KEY (`Id_direccion`) REFERENCES `direccion` (`Id_direccion`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
