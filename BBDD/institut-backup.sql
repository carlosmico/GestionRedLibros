-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 09-04-2019 a las 17:53:15
-- Versión del servidor: 10.1.38-MariaDB
-- Versión de PHP: 7.3.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `institut`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `alumnos`
--

CREATE TABLE `alumnos` (
  `id` varchar(10) NOT NULL,
  `NIA` varchar(10) DEFAULT NULL,
  `nombre` varchar(100) DEFAULT NULL,
  `apellido1` varchar(50) DEFAULT NULL,
  `apellido2` varchar(50) DEFAULT NULL,
  `fecha_nac` varchar(10) DEFAULT NULL,
  `municipio_nac` varchar(5) DEFAULT NULL,
  `municipio_nac_ext` varchar(5) DEFAULT NULL,
  `provincia_nac` varchar(5) DEFAULT NULL,
  `pais_nac` varchar(5) DEFAULT NULL,
  `nacionalidad` varchar(5) DEFAULT NULL,
  `sexo` varchar(1) DEFAULT NULL,
  `tipo_doc` varchar(1) DEFAULT NULL,
  `documento` varchar(10) DEFAULT NULL,
  `expediente` varchar(5) DEFAULT NULL,
  `libro_escolaridad` varchar(5) DEFAULT NULL,
  `cod_postal` varchar(5) DEFAULT NULL,
  `tipo_via` varchar(5) DEFAULT NULL,
  `domicilio` varchar(100) DEFAULT NULL,
  `numero` varchar(3) DEFAULT NULL,
  `puerta` varchar(2) DEFAULT NULL,
  `escalera` varchar(2) DEFAULT NULL,
  `letra` varchar(2) DEFAULT NULL,
  `piso` varchar(2) DEFAULT NULL,
  `provincia` varchar(5) DEFAULT NULL,
  `municipio` varchar(5) DEFAULT NULL,
  `localidad` varchar(10) DEFAULT NULL,
  `telefono1` varchar(20) DEFAULT NULL,
  `telefono2` varchar(20) DEFAULT NULL,
  `telefono3` varchar(20) DEFAULT NULL,
  `email1` varchar(50) DEFAULT NULL,
  `email2` varchar(50) DEFAULT NULL,
  `sip` varchar(10) DEFAULT NULL,
  `observaciones` varchar(150) DEFAULT NULL,
  `ampa` varchar(1) DEFAULT NULL,
  `seguro` varchar(1) DEFAULT NULL,
  `dictamen` varchar(10) DEFAULT NULL,
  `fecha_resolucion` varchar(10) DEFAULT NULL,
  `informe_psicoped` varchar(10) DEFAULT NULL,
  `informado_posib` varchar(10) DEFAULT NULL,
  `fecha_matricula` varchar(10) DEFAULT NULL,
  `fecha_ingreso_centro` varchar(10) DEFAULT NULL,
  `estado_matricula` varchar(5) DEFAULT NULL,
  `tipo_matricula` varchar(5) DEFAULT NULL,
  `repite` varchar(5) DEFAULT NULL,
  `num_repeticion` varchar(5) DEFAULT NULL,
  `ensenanza` varchar(2) DEFAULT NULL,
  `curso` varchar(15) DEFAULT NULL,
  `grupo` varchar(10) DEFAULT NULL,
  `turno` varchar(2) DEFAULT NULL,
  `linea` varchar(2) DEFAULT NULL,
  `trabaja` varchar(1) DEFAULT NULL,
  `fuera_comunidad` varchar(1) DEFAULT NULL,
  `matricula_parcial` varchar(1) DEFAULT NULL,
  `matricula_condic` varchar(1) DEFAULT NULL,
  `informe_medico` varchar(1) DEFAULT NULL,
  `banco` varchar(4) DEFAULT NULL,
  `sucursal` varchar(4) DEFAULT NULL,
  `digito_control` varchar(2) DEFAULT NULL,
  `cuenta` varchar(12) DEFAULT NULL,
  `modalidad` varchar(5) DEFAULT NULL,
  `iban` varchar(24) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `alumnos`
--

INSERT INTO `alumnos` (`id`, `NIA`, `nombre`, `apellido1`, `apellido2`, `fecha_nac`, `municipio_nac`, `municipio_nac_ext`, `provincia_nac`, `pais_nac`, `nacionalidad`, `sexo`, `tipo_doc`, `documento`, `expediente`, `libro_escolaridad`, `cod_postal`, `tipo_via`, `domicilio`, `numero`, `puerta`, `escalera`, `letra`, `piso`, `provincia`, `municipio`, `localidad`, `telefono1`, `telefono2`, `telefono3`, `email1`, `email2`, `sip`, `observaciones`, `ampa`, `seguro`, `dictamen`, `fecha_resolucion`, `informe_psicoped`, `informado_posib`, `fecha_matricula`, `fecha_ingreso_centro`, `estado_matricula`, `tipo_matricula`, `repite`, `num_repeticion`, `ensenanza`, `curso`, `grupo`, `turno`, `linea`, `trabaja`, `fuera_comunidad`, `matricula_parcial`, `matricula_condic`, `informe_medico`, `banco`, `sucursal`, `digito_control`, `cuenta`, `modalidad`, `iban`) VALUES
('10429470', '10429470', 'CARLOS', 'MICÓ', 'HUERTAS', '26/07/1997', '81', NULL, NULL, NULL, NULL, 'H', NULL, '073226060V', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '665958524', NULL, NULL, 'carlosmico15@gmail.com', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1949542204', '2CFSN', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
('10429497', '10429497', 'JOSÉ', 'SANCHIS', 'BELDA', '16/11/1997', '81', NULL, NULL, NULL, NULL, 'H', NULL, '073226060V', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '665958524', NULL, NULL, 'sanchisbeldajose@gmail.com', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1949542204', '2CFSN', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `contenido`
--

CREATE TABLE `contenido` (
  `id` int(11) NOT NULL,
  `curso` varchar(10) NOT NULL,
  `codigo` varchar(15) NOT NULL,
  `ensenanza` varchar(2) NOT NULL,
  `nombre_cas` varchar(100) NOT NULL,
  `nombre_val` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `contenido`
--

INSERT INTO `contenido` (`id`, `curso`, `codigo`, `ensenanza`, `nombre_cas`, `nombre_val`) VALUES
(1, '1949539264', 'BGLOMCE', '3', 'Biología y Geología', 'Biologia i Geologia'),
(2, '1949539264', 'VLLLOMCE', '3', 'Valenciano: Lengua y Literatura', 'Valencià: Llengua i Literatura'),
(3, '1949539264', 'EFLOMCE', '3', 'Educación Física', 'Educació Física'),
(4, '1949539264', 'FQLOMCE', '3', 'Física y Química', 'Física i Química'),
(5, '1949539264', 'RELLOMCE', '3', 'Religión', 'Religió'),
(6, '1949539264', 'GHLOMCE', '3', 'Geografía e Historia', 'Geografia i Història'),
(7, '1949539264', 'LCLLOMCE', '3', 'Lengua Castellana y Literatura', 'Llengua Castellana i Literatura'),
(8, '1949539264', 'VELOMCE', '3', 'Valores Éticos', 'Valors Ètics'),
(9, '1949539264', 'IAEELOMCE', '3', 'Iniciación a la actividad emprendedora y empresarial', 'Iniciació a l\'activitat emprenedora i empresarial'),
(10, '1949539264', 'MUSLOMCE', '3', 'Música', 'Música'),
(11, '1949539264', 'MACLOMCE', '3', 'Matemáticas orientadas a las enseñanzas académicas', 'Matemàtiques orientades a les ensenyances acadèmiques'),
(12, '1949539264', 'TECLOMCE', '3', 'Tecnología', 'Tecnologia'),
(13, '1949539264', 'SLELOMCE', '3', 'Segunda Lengua Extranjera', 'Segona Llengua Estrangera'),
(14, '1949539264', 'MAPLOMCE', '3', 'Matemáticas orientadas a las enseñanzas aplicadas', 'Matemàtiques orientades a las ensenyances aplicades'),
(15, '1949539264', 'EPVALOMCE', '3', 'Educación plástica, visual y audiovisual', 'Educació plàstica, visual i audiovisual'),
(16, '1949539264', 'PLELOMCE', '3', 'Primera Lengua Extranjera', 'Primera Llengua Estrangera'),
(17, '1949539264', 'CCLOMCE', '3', 'Cultura Clásica', 'Cultura Clàssica'),
(18, '1949539264', 'TUTLOMCE', '3', 'Tutoría', 'Tutoria'),
(19, '1949539264', 'ECOLOMCE', '3', 'Economía', 'Economia'),
(20, '1949539264', 'LLATLOMCE', '3', 'Latín', 'Llatí'),
(21, '1949539264', 'CAAPLOMCE', '3', 'Ciencias aplicadas a la actividad profesional', 'Ciències aplicades a l\'activitat professional'),
(22, '1949539264', 'AEDLOMCE', '3', 'Artes escénicas y Danza', 'Arts escèniques i Dansa'),
(23, '1949539264', 'CCILOMCE', '3', 'Cultura científica', 'Cultura científica'),
(24, '1949539264', 'FILLOMCE', '3', 'Filosofía', 'Filosofia'),
(25, '1949539264', 'TICLOMCE', '3', 'Tecnologías de la información y la comunicación', 'Tecnologies de la informació i la comunicació'),
(26, '1949539264', 'BGLOMCEEO', '3', 'Biología y Geología', 'Biologia i Geologia'),
(27, '1949539264', 'FQLOMCEEO', '3', 'Física y Química', 'Física i Química'),
(28, '1949539264', 'ECOLOMCEEO', '3', 'Economía', 'Economia'),
(29, '1949539264', 'LLATLOMCEEO', '3', 'Latín', 'Llatí'),
(30, '1949539264', 'TECLOMCEEO', '3', 'Tecnología', 'Tecnologia'),
(31, '1949539264', 'CAAPLOMCEEO', '3', 'Ciencias aplicadas a la actividad profesional', 'Ciències aplicades a l\'activitat professional'),
(32, '1949539264', 'IAEELOMCEEO', '3', 'Iniciación a la actividad emprendedora y empresarial', 'Iniciació a l\'activitat emprenedora i empresarial'),
(33, '1949539264', 'TESPLOM', '3', 'Tutoría Específica', 'Tutoria Específica'),
(34, '1949539264', 'TRLOMCE', '3', 'Taller de Refuerzo', 'Taller de Reforç'),
(35, '1949539264', 'TALOMCE', '3', 'Taller de Profundización', 'Taller d\'Aprofundiment'),
(36, '1949539264', 'PILOMCE', '3', 'Proyecto Interdisciplinario', 'Projecte Interdisciplinari'),
(37, '1949539264', 'CCOLOMCE', '3', 'Competencia Comunicativa Oral Primera Lengua Extranjera', 'Competència Comunicativa Oral Primera Llengua Estrangera'),
(38, '1949539245', 'INFLOMCE', '3', 'Informática', 'Informàtica'),
(39, '1949539245', 'EFLOMCE', '3', 'Educación Física', 'Educació Física'),
(40, '1949539245', 'RELLOMCE', '3', 'Religión', 'Religió'),
(41, '1949539245', 'VELOMCE', '3', 'Valores Éticos', 'Valors Ètics'),
(42, '1949539245', 'IAEELOMCE', '3', 'Iniciación a la actividad emprendedora y empresarial', 'Iniciació a l\'activitat emprenedora i empresarial'),
(43, '1949539245', 'IAEELOMCELC', '3', 'Iniciación a la actividad emprendedora y empresarial', 'Iniciació a l\'activitat emprenedora i empresarial'),
(44, '1949539245', 'MUSLOMCE', '3', 'Música', 'Música'),
(45, '1949539245', 'TECLOMCE', '3', 'Tecnología', 'Tecnologia'),
(46, '1949539245', 'SLELOMCE', '3', 'Segunda Lengua Extranjera', 'Segona Llengua Estrangera'),
(47, '1949539245', 'EPVALOMCE', '3', 'Educación plástica, visual y audiovisual', 'Educació plàstica, visual i audiovisual'),
(48, '1949539245', 'SLELOMCELC', '3', 'Segunda Lengua Extranjera', 'Segona Llengua Estrangera'),
(49, '1949539245', 'CCLOMCE', '3', 'Cultura Clásica', 'Cultura Clàssica'),
(50, '1949539245', 'TUTLOMCE', '3', 'Tutoría', 'Tutoria'),
(51, '1949539245', 'CCLOMCELC', '3', 'Cultura Clásica', 'Cultura Clàssica'),
(52, '1949539245', 'TECLOMCELC', '3', 'Tecnología', 'Tecnologia'),
(53, '1949539245', 'ALSLOMQE', '3', 'Ámbito Lingüístico y Social', 'Àmbit Lingüístic i Social'),
(54, '1949539245', 'ACMLOMQE', '3', 'Ámbito Científico y Matemático', 'Àmbit Científic i Matemàtic'),
(55, '1949539245', 'ALELOMQE', '3', 'Ámbito Lenguas Extranjeras', 'Àmbit Llengües Estrangeres'),
(56, '1949539245', 'TRLOMCE', '3', 'Taller de Refuerzo', 'Taller de Reforç'),
(57, '1949539245', 'TALOMCE', '3', 'Taller de Profundización', 'Taller d\'Aprofundiment'),
(58, '1949539245', 'PILOMCE', '3', 'Proyecto Interdisciplinario', 'Projecte Interdisciplinari'),
(59, '1949539245', 'CCOLOMCE', '3', 'Competencia Comunicativa Oral Primera Lengua Extranjera', 'Competència Comunicativa Oral Primera Llengua Estrangera'),
(60, '1949539224', 'BGLOMCE', '3', 'Biología y Geología', 'Biologia i Geologia'),
(61, '1949539224', 'VLLLOMCE', '3', 'Valenciano: Lengua y Literatura', 'Valencià: Llengua i Literatura'),
(62, '1949539224', 'EFLOMCE', '3', 'Educación Física', 'Educació Física'),
(63, '1949539224', 'FQLOMCE', '3', 'Física y Química', 'Física i Química'),
(64, '1949539224', 'RELLOMCE', '3', 'Religión', 'Religió'),
(65, '1949539224', 'GHLOMCE', '3', 'Geografía e Historia', 'Geografia i Història'),
(66, '1949539224', 'LCLLOMCE', '3', 'Lengua Castellana y Literatura', 'Llengua Castellana i Literatura'),
(67, '1949539224', 'VELOMCE', '3', 'Valores Éticos', 'Valors Ètics'),
(68, '1949539224', 'IAEELOMCE', '3', 'Iniciación a la actividad emprendedora y empresarial', 'Iniciació a l\'activitat emprenedora i empresarial'),
(69, '1949539224', 'MUSLOMCE', '3', 'Música', 'Música'),
(70, '1949539224', 'MACLOMCE', '3', 'Matemáticas orientadas a las enseñanzas académicas', 'Matemàtiques orientades a les ensenyances acadèmiques'),
(71, '1949539224', 'TECLOMCE', '3', 'Tecnología', 'Tecnologia'),
(72, '1949539224', 'SLELOMCE', '3', 'Segunda Lengua Extranjera', 'Segona Llengua Estrangera'),
(73, '1949539224', 'MAPLOMCE', '3', 'Matemáticas orientadas a las enseñanzas aplicadas', 'Matemàtiques orientades a las ensenyances aplicades'),
(74, '1949539224', 'EPVALOMCE', '3', 'Educación plástica, visual y audiovisual', 'Educació plàstica, visual i audiovisual'),
(75, '1949539224', 'PLELOMCE', '3', 'Primera Lengua Extranjera', 'Primera Llengua Estrangera'),
(76, '1949539224', 'CCLOMCE', '3', 'Cultura Clásica', 'Cultura Clàssica'),
(77, '1949539224', 'TUTLOMCE', '3', 'Tutoría', 'Tutoria'),
(78, '1949539224', 'ECOLOMCE', '3', 'Economía', 'Economia'),
(79, '1949539224', 'LLATLOMCE', '3', 'Latín', 'Llatí'),
(80, '1949539224', 'CAAPLOMCE', '3', 'Ciencias aplicadas a la actividad profesional', 'Ciències aplicades a l\'activitat professional'),
(81, '1949539224', 'AEDLOMCE', '3', 'Artes escénicas y Danza', 'Arts escèniques i Dansa'),
(82, '1949539224', 'CCILOMCE', '3', 'Cultura científica', 'Cultura científica'),
(83, '1949539224', 'FILLOMCE', '3', 'Filosofía', 'Filosofia'),
(84, '1949539224', 'TICLOMCE', '3', 'Tecnologías de la información y la comunicación', 'Tecnologies de la informació i la comunicació'),
(85, '1949539224', 'BGLOMCEEO', '3', 'Biología y Geología', 'Biologia i Geologia'),
(86, '1949539224', 'FQLOMCEEO', '3', 'Física y Química', 'Física i Química'),
(87, '1949539224', 'ECOLOMCEEO', '3', 'Economía', 'Economia'),
(88, '1949539224', 'LLATLOMCEEO', '3', 'Latín', 'Llatí'),
(89, '1949539224', 'TECLOMCEEO', '3', 'Tecnología', 'Tecnologia'),
(90, '1949539224', 'CAAPLOMCEEO', '3', 'Ciencias aplicadas a la actividad profesional', 'Ciències aplicades a l\'activitat professional'),
(91, '1949539224', 'IAEELOMCEEO', '3', 'Iniciación a la actividad emprendedora y empresarial', 'Iniciació a l\'activitat emprenedora i empresarial'),
(92, '1949539224', 'TRLOMCE', '3', 'Taller de Refuerzo', 'Taller de Reforç'),
(93, '1949539224', 'TALOMCE', '3', 'Taller de Profundización', 'Taller d\'Aprofundiment'),
(94, '1949539224', 'PILOMCE', '3', 'Proyecto Interdisciplinario', 'Projecte Interdisciplinari'),
(95, '1949539224', 'CCOLOMCE', '3', 'Competencia Comunicativa Oral Primera Lengua Extranjera', 'Competència Comunicativa Oral Primera Llengua Estrangera'),
(96, '1949539212', 'VLLLOMCE', '3', 'Valenciano: Lengua y Literatura', 'Valencià: Llengua i Literatura'),
(97, '1949539212', 'INFLOMCE', '3', 'Informática', 'Informàtica'),
(98, '1949539212', 'EFLOMCE', '3', 'Educación Física', 'Educació Física'),
(99, '1949539212', 'FQLOMCE', '3', 'Física y Química', 'Física i Química'),
(100, '1949539212', 'RELLOMCE', '3', 'Religión', 'Religió'),
(101, '1949539212', 'GHLOMCE', '3', 'Geografía e Historia', 'Geografia i Història'),
(102, '1949539212', 'LCLLOMCE', '3', 'Lengua Castellana y Literatura', 'Llengua Castellana i Literatura'),
(103, '1949539212', 'VELOMCE', '3', 'Valores Éticos', 'Valors Ètics'),
(104, '1949539212', 'IAEELOMCELC', '3', 'Iniciación a la actividad emprendedora y empresarial', 'Iniciació a l\'activitat emprenedora i empresarial'),
(105, '1949539212', 'MATLOMCE', '3', 'Matemáticas', 'Matemàtiques'),
(106, '1949539212', 'MUSLOMCE', '3', 'Música', 'Música'),
(107, '1949539212', 'TECLOMCE', '3', 'Tecnología', 'Tecnologia'),
(108, '1949539212', 'EPVALOMCE', '3', 'Educación plástica, visual y audiovisual', 'Educació plàstica, visual i audiovisual'),
(109, '1949539212', 'PLELOMCE', '3', 'Primera Lengua Extranjera', 'Primera Llengua Estrangera'),
(110, '1949539212', 'SLELOMCELC', '3', 'Segunda Lengua Extranjera', 'Segona Llengua Estrangera'),
(111, '1949539212', 'TUTLOMCE', '3', 'Tutoría', 'Tutoria'),
(112, '1949539212', 'CCLOMCELC', '3', 'Cultura Clásica', 'Cultura Clàssica'),
(113, '1949539212', 'TRLOMCE', '3', 'Taller de Refuerzo', 'Taller de Reforç'),
(114, '1949539212', 'TALOMCE', '3', 'Taller de Profundización', 'Taller d\'Aprofundiment'),
(115, '1949539212', 'PILOMCE', '3', 'Proyecto Interdisciplinario', 'Projecte Interdisciplinari'),
(116, '1949539199', 'BGLOMCE', '3', 'Biología y Geología', 'Biologia i Geologia'),
(117, '1949539199', 'VLLLOMCE', '3', 'Valenciano: Lengua y Literatura', 'Valencià: Llengua i Literatura'),
(118, '1949539199', 'INFLOMCE', '3', 'Informática', 'Informàtica'),
(119, '1949539199', 'EFLOMCE', '3', 'Educación Física', 'Educació Física'),
(120, '1949539199', 'RELLOMCE', '3', 'Religión', 'Religió'),
(121, '1949539199', 'RMATLOMCE', '3', 'Refuerzo instrumental Matemáticas', 'Reforç instrumental Matemàtiques'),
(122, '1949539199', 'RLCLLOMCE', '3', 'Refuerzo instrumental Castellano', 'Reforç instrumental Castellà'),
(123, '1949539199', 'GHLOMCE', '3', 'Geografía e Historia', 'Geografia i Història'),
(124, '1949539199', 'RVLLLOMCE', '3', 'Refuerzo instrumental Valenciano', 'Reforç instrumental Valencià'),
(125, '1949539199', 'LCLLOMCE', '3', 'Lengua Castellana y Literatura', 'Llengua Castellana i Literatura'),
(126, '1949539199', 'VELOMCE', '3', 'Valores Éticos', 'Valors Ètics'),
(127, '1949539199', 'IAEELOMCELC', '3', 'Iniciación a la actividad emprendedora y empresarial', 'Iniciació a l\'activitat emprenedora i empresarial'),
(128, '1949539199', 'MATLOMCE', '3', 'Matemáticas', 'Matemàtiques'),
(129, '1949539199', 'MUSLOMCE', '3', 'Música', 'Música'),
(130, '1949539199', 'TECLOMCE', '3', 'Tecnología', 'Tecnologia'),
(131, '1949539199', 'PLELOMCE', '3', 'Primera Lengua Extranjera', 'Primera Llengua Estrangera'),
(132, '1949539199', 'SLELOMCELC', '3', 'Segunda Lengua Extranjera', 'Segona Llengua Estrangera'),
(133, '1949539199', 'TUTLOMCE', '3', 'Tutoría', 'Tutoria'),
(134, '1949539199', 'CCLOMCELC', '3', 'Cultura Clásica', 'Cultura Clàssica'),
(135, '1949539199', 'EPVALOMCELC', '3', 'Educación plástica, visual y audiovisual', 'Educació plàstica, visual i audiovisual'),
(136, '1949539199', 'TRLOMCE', '3', 'Taller de Refuerzo', 'Taller de Reforç'),
(137, '1949539199', 'TALOMCE', '3', 'Taller de Profundización', 'Taller d\'Aprofundiment'),
(138, '1949539199', 'PILOMCE', '3', 'Proyecto Interdisciplinario', 'Projecte Interdisciplinari'),
(139, '1949539185', 'BGLOMCE', '3', 'Biología y Geología', 'Biologia i Geologia'),
(140, '1949539185', 'VLLLOMCE', '3', 'Valenciano: Lengua y Literatura', 'Valencià: Llengua i Literatura'),
(141, '1949539185', 'INFLOMCE', '3', 'Informática', 'Informàtica'),
(142, '1949539185', 'EFLOMCE', '3', 'Educación Física', 'Educació Física'),
(143, '1949539185', 'FQLOMCE', '3', 'Física y Química', 'Física i Química'),
(144, '1949539185', 'RELLOMCE', '3', 'Religión', 'Religió'),
(145, '1949539185', 'GHLOMCE', '3', 'Geografía e Historia', 'Geografia i Història'),
(146, '1949539185', 'LCLLOMCE', '3', 'Lengua Castellana y Literatura', 'Llengua Castellana i Literatura'),
(147, '1949539185', 'VELOMCE', '3', 'Valores Éticos', 'Valors Ètics'),
(148, '1949539185', 'IAEELOMCE', '3', 'Iniciación a la actividad emprendedora y empresarial', 'Iniciació a l\'activitat emprenedora i empresarial'),
(149, '1949539185', 'IAEELOMCELC', '3', 'Iniciación a la actividad emprendedora y empresarial', 'Iniciació a l\'activitat emprenedora i empresarial'),
(150, '1949539185', 'MUSLOMCE', '3', 'Música', 'Música'),
(151, '1949539185', 'MACLOMCE', '3', 'Matemáticas orientadas a las enseñanzas académicas', 'Matemàtiques orientades a les ensenyances acadèmiques'),
(152, '1949539185', 'TECLOMCE', '3', 'Tecnología', 'Tecnologia'),
(153, '1949539185', 'SLELOMCE', '3', 'Segunda Lengua Extranjera', 'Segona Llengua Estrangera'),
(154, '1949539185', 'MAPLOMCE', '3', 'Matemáticas orientadas a las enseñanzas aplicadas', 'Matemàtiques orientades a las ensenyances aplicades'),
(155, '1949539185', 'EPVALOMCE', '3', 'Educación plástica, visual y audiovisual', 'Educació plàstica, visual i audiovisual'),
(156, '1949539185', 'PLELOMCE', '3', 'Primera Lengua Extranjera', 'Primera Llengua Estrangera'),
(157, '1949539185', 'SLELOMCELC', '3', 'Segunda Lengua Extranjera', 'Segona Llengua Estrangera'),
(158, '1949539185', 'CCLOMCE', '3', 'Cultura Clásica', 'Cultura Clàssica'),
(159, '1949539185', 'TUTLOMCE', '3', 'Tutoría', 'Tutoria'),
(160, '1949539185', 'CCLOMCELC', '3', 'Cultura Clásica', 'Cultura Clàssica'),
(161, '1949539185', 'TECLOMCELC', '3', 'Tecnología', 'Tecnologia'),
(162, '1949539185', 'TRLOMCE', '3', 'Taller de Refuerzo', 'Taller de Reforç'),
(163, '1949539185', 'TALOMCE', '3', 'Taller de Profundización', 'Taller d\'Aprofundiment'),
(164, '1949539185', 'PILOMCE', '3', 'Proyecto Interdisciplinario', 'Projecte Interdisciplinari'),
(165, '1949539185', 'CCOLOMCE', '3', 'Competencia Comunicativa Oral Primera Lengua Extranjera', 'Competència Comunicativa Oral Primera Llengua Estrangera'),
(166, '1949540513', 'RELLOMQE', '4', 'Religión', 'Religió'),
(167, '1949540513', 'TUTLOMQE', '4', 'Tutoría', 'Tutoria'),
(168, '1949540513', 'HESLOMQE', '4', 'Historia de España', 'Història d\'Espanya'),
(169, '1949540513', 'LCL2LOMQE', '4', 'Lengua Castellana y Literatura II', 'Llengua Castellana i Literatura II'),
(170, '1949540513', 'PLE2LOMQE', '4', 'Primera lengua extranjera II', 'Primera llengua estrangera II'),
(171, '1949540513', 'VLL2LOMQE', '4', 'Valenciano: lengua y literatura II', 'Valencià: llengua i literatura II'),
(172, '1949540513', 'LLA2LOMQE', '4', 'Latín II', 'Llatí II'),
(173, '1949540513', 'MATCS2LOMQE', '4', 'Matemáticas aplicadas a las Ciencias Sociales II', 'Matemàtiques aplicades a les Ciències Socials II'),
(174, '1949540513', 'GRE2LOMQE', '4', 'Griego II', 'Grec II'),
(175, '1949540513', 'ECOEMLOMQE', '4', 'Economía de la empresa', 'Economia de l\'empresa'),
(176, '1949540513', 'GEOGLOMQE', '4', 'Geografía', 'Geografia'),
(177, '1949540513', 'HARTLOMQE', '4', 'Historia del Arte', 'Història de l\'Art'),
(178, '1949540513', 'HFILLOMQE', '4', 'Historia de la Filosofía', 'Història de la Filosofia'),
(179, '1949540513', 'AMU2LOMQE', '4', 'Análisis musical II', 'Anàlisi musical II'),
(180, '1949540513', 'SLE2LOMQE', '4', 'Segunda lengua extranjera II', 'Segona llengua estrangera II'),
(181, '1949540513', 'TIC2LOMQE', '4', 'Tecnologías de la Información y de la Comunicación II', 'Tecnologies de la Informació i de la Comunicació II'),
(182, '1949540513', 'TECI2LOMQE', '4', 'Tecnología Industrial II', 'Tecnologia Industrial II'),
(183, '1949540513', 'CTMLOMQE', '4', 'Ciencias de la Tierra y del Medio Ambiente', 'Ciències de la Terra i del Medi Ambient'),
(184, '1949540513', 'FAGLOMQE', '4', 'Fundamentos de administración y gestión ', 'Fonaments d\'administració i gestió'),
(185, '1949540513', 'PSILOMQE', '4', 'Psicología', 'Psicologia'),
(186, '1949540513', 'TEGLOMQE', '4', 'Técnicas de expresión graficoplástica', 'Tècniques d\'expressió graficoplàstica'),
(187, '1949540513', 'IMSLOMQE', '4', 'Imagen y Sonido', 'Imatge i So'),
(188, '1949540513', 'FISLOMQEEO', '4', 'Física', 'Física'),
(189, '1949540513', 'QUILOMQEEO', '4', 'Química', 'Química'),
(190, '1949540513', 'DT2LOMQEEO', '4', 'Dibujo Técnico II', 'Dibuix Tècnic II'),
(191, '1949540513', 'BIOLOMQEEO', '4', 'Biología', 'Biologia'),
(192, '1949540513', 'GEOLOMQEEO', '4', 'Geología', 'Geologia'),
(193, '1949540513', 'LLA2LOMQEEO', '4', 'Latín II', 'Llatí II'),
(194, '1949540513', 'GRE2LOMQEEO', '4', 'Griego II', 'Grec II'),
(195, '1949540513', 'MATCS2LOMQEO', '4', 'Matemáticas aplicadas a las CCSS II', 'Matemàtiques aplicades a les CCSS II'),
(196, '1949540513', 'ECOEMLOMQEEO', '4', 'Economía de la empresa', 'Economia de l\'empresa'),
(197, '1949540513', 'GEOGLOMQEEO', '4', 'Geografía', 'Geografia'),
(198, '1949540513', 'HARTLOMQEEO', '4', 'Historia del Arte', 'Història de l\'Art'),
(199, '1949540513', 'HFILLOMQEEO', '4', 'Historia de la Filosofía', 'Història de la Filosofia'),
(200, '1949540513', 'FA2LOMQEEO', '4', 'Fundamentos del Arte II', 'Fonaments de l\'Art II'),
(201, '1949540513', 'CA2LOMQEEO', '4', 'Cultura audiovisual II', 'Cultura audiovisual II'),
(202, '1949540513', 'AELOMQEEO', '4', 'Artes escénicas', 'Arts escèniques'),
(203, '1949540513', 'DISLOMQEEO', '4', 'Diseño', 'Disseny'),
(204, '1949540513', 'HMDLOMQE', '4', 'Historia de la Música y de la Danza', 'Història de la Música i de la Dansa'),
(205, '1949540513', 'DA2LOMQE', '4', 'Dibujo artístico II', 'Dibuix artístic II'),
(206, '1949540513', 'MAT2LOMQEEO', '4', 'Matemáticas II', 'Matemàtiques II'),
(207, '1949540513', 'HFILLOMQEEOB', '4', 'Historia de la Filosofía', 'Història de la Filosofia'),
(208, '1949540513', 'EFSLOMQE', '4', 'Educación Físicodeportiva y Salud', 'Educació Físicoesportiva i Salut'),
(209, '1949540524', 'FILLOMQE', '4', 'Filosofía', 'Filosofia'),
(210, '1949540524', 'MATCS1LOMQE', '4', 'Matemáticas aplicadas a las CCSS I', 'Matemàtiques aplicades a les CCSS I'),
(211, '1949540524', 'ECOLOMQE', '4', 'Economía', 'Economia'),
(212, '1949540524', 'HMCLOMQE', '4', 'Historia del Mundo Contemporáneo', 'Història del Món Contemporani'),
(213, '1949540524', 'LCL1LOMQE', '4', 'Lengua Castellana y Literatura I', 'Llengua Castellana i Literatura I'),
(214, '1949540524', 'LULOMQE', '4', 'Literatura Universal', 'Literatura Universal'),
(215, '1949540524', 'VLL1LOMQE', '4', 'Valenciano: Lengua y Literatura I', 'Valencià: Llengua i Literatura I'),
(216, '1949540524', 'PLE1LOMQE', '4', 'Primera Lengua Extranjera I', 'Primera Llengua Estrangera I'),
(217, '1949540524', 'EFLOMQE', '4', 'Educación Física', 'Educació Física'),
(218, '1949540524', 'AALOMQE', '4', 'Anatomía Aplicada', 'Anatomia Aplicada'),
(219, '1949540524', 'CC1LOMQE', '4', 'Cultura Científica ', 'Cultura Científica '),
(220, '1949540524', 'RELLOMQE', '4', 'Religión', 'Religió'),
(221, '1949540524', 'LLA1LOMQE', '4', 'Latín I', 'Llatí I'),
(222, '1949540524', 'AMU1LOMQE', '4', 'Análisis Musical I', 'Anàlisi Musical I'),
(223, '1949540524', 'GRE1LOMQE', '4', 'Griego I', 'Grec I'),
(224, '1949540524', 'DA1LOMQE', '4', 'Dibujo Artístico I', 'Dibuix Artístic I'),
(225, '1949540524', 'LPMLOMQE', '4', 'Lenguaje y Práctica Musical', 'Llenguatge i Pràctica Musical'),
(226, '1949540524', 'SLE1LOMQE', '4', 'Segunda Lengua Extranjera I', 'Segona Llengua Estrangera I'),
(227, '1949540524', 'MAT1LOMQEEO', '4', 'Matemáticas I', 'Matemàtiques I'),
(228, '1949540524', 'FQLOMQEEO', '4', 'Física y Química', 'Física i Química'),
(229, '1949540524', 'DT1LOMQEEO', '4', 'Dibujo Técnico I', 'Dibuix Tècnic I'),
(230, '1949540524', 'TECI1LOMQE', '4', 'Tecnología Industrial I', 'Tecnologia Industrial I'),
(231, '1949540524', 'LLA1LOMQEEO', '4', 'Latín I', 'Llatí I'),
(232, '1949540524', 'HMCLOMQEEO', '4', 'Historia del Mundo Contemporáneo', 'Història del Món Contemporani'),
(233, '1949540524', 'BGLOMQEEO', '4', 'Biología y Geología', 'Biologia i Geologia'),
(234, '1949540524', 'MCS1LOMQEEO', '4', 'Matemáticas aplicadas a las CCSS I', 'Matemàtiques aplicades a les CCSS I'),
(235, '1949540524', 'GRE1LOMQEEO', '4', 'Griego I', 'Grec I'),
(236, '1949540524', 'TIC1LOMQE', '4', 'Tecnologías de la Información y la Comunicación I', 'Tecnologies de la Informació i la Comunicació I'),
(237, '1949540524', 'ECOLOMQEEO', '4', 'Economía', 'Economia'),
(238, '1949540524', 'LULOMQEEO', '4', 'Literatura Universal', 'Literatura Universal'),
(239, '1949540524', 'CA1LOMQEEO', '4', 'Cultura Audiovisual I', 'Cultura Audiovisual I'),
(240, '1949540524', 'FA1LOMQEEO', '4', 'Fundamentos del Arte I', 'Fonaments de l\'Art I'),
(241, '1949540524', 'VOLLOMQE', '4', 'Volumen', 'Volum'),
(242, '1949540524', 'TUTLOMQE', '4', 'Tutoría', 'Tutoria'),
(243, '1949540389', 'FILLOMQE', '4', 'Filosofía', 'Filosofia'),
(244, '1949540389', 'LCL1LOMQE', '4', 'Lengua Castellana y Literatura I', 'Llengua Castellana i Literatura I'),
(245, '1949540389', 'VLL1LOMQE', '4', 'Valenciano: Lengua y Literatura I', 'Valencià: Llengua i Literatura I'),
(246, '1949540389', 'PLE1LOMQE', '4', 'Primera Lengua Extranjera I', 'Primera Llengua Estrangera I'),
(247, '1949540389', 'MAT1LOMQE', '4', 'Matemáticas I', 'Matemàtiques I'),
(248, '1949540389', 'EFLOMQE', '4', 'Educación Física', 'Educació Física'),
(249, '1949540389', 'FQLOMQE', '4', 'Física y Química', 'Física i Química'),
(250, '1949540389', 'AALOMQE', '4', 'Anatomía Aplicada', 'Anatomia Aplicada'),
(251, '1949540389', 'DT1LOMQE', '4', 'Dibujo Técnico I', 'Dibuix Tècnic I'),
(252, '1949540389', 'CC1LOMQE', '4', 'Cultura Científica ', 'Cultura Científica '),
(253, '1949540389', 'BGLOMQE', '4', 'Biología y Geología', 'Biologia i Geologia'),
(254, '1949540389', 'RELLOMQE', '4', 'Religión', 'Religió'),
(255, '1949540389', 'AMU1LOMQE', '4', 'Análisis Musical I', 'Anàlisi Musical I'),
(256, '1949540389', 'DA1LOMQE', '4', 'Dibujo Artístico I', 'Dibuix Artístic I'),
(257, '1949540389', 'LPMLOMQE', '4', 'Lenguaje y Práctica Musical', 'Llenguatge i Pràctica Musical'),
(258, '1949540389', 'SLE1LOMQE', '4', 'Segunda Lengua Extranjera I', 'Segona Llengua Estrangera I'),
(259, '1949540389', 'DT1LOMQEEO', '4', 'Dibujo Técnico I', 'Dibuix Tècnic I'),
(260, '1949540389', 'TECI1LOMQE', '4', 'Tecnología Industrial I', 'Tecnologia Industrial I'),
(261, '1949540389', 'LLA1LOMQEEO', '4', 'Latín I', 'Llatí I'),
(262, '1949540389', 'HMCLOMQEEO', '4', 'Historia del Mundo Contemporáneo', 'Història del Món Contemporani'),
(263, '1949540389', 'BGLOMQEEO', '4', 'Biología y Geología', 'Biologia i Geologia'),
(264, '1949540389', 'MCS1LOMQEEO', '4', 'Matemáticas aplicadas a las CCSS I', 'Matemàtiques aplicades a les CCSS I'),
(265, '1949540389', 'GRE1LOMQEEO', '4', 'Griego I', 'Grec I'),
(266, '1949540389', 'TIC1LOMQE', '4', 'Tecnologías de la Información y la Comunicación I', 'Tecnologies de la Informació i la Comunicació I'),
(267, '1949540389', 'ECOLOMQEEO', '4', 'Economía', 'Economia'),
(268, '1949540389', 'LULOMQEEO', '4', 'Literatura Universal', 'Literatura Universal'),
(269, '1949540389', 'CA1LOMQEEO', '4', 'Cultura Audiovisual I', 'Cultura Audiovisual I'),
(270, '1949540389', 'FA1LOMQEEO', '4', 'Fundamentos del Arte I', 'Fonaments de l\'Art I'),
(271, '1949540389', 'VOLLOMQE', '4', 'Volumen', 'Volum'),
(272, '1949540389', 'TUTLOMQE', '4', 'Tutoría', 'Tutoria'),
(273, '1949540403', 'RELLOMQE', '4', 'Religión', 'Religió'),
(274, '1949540403', 'TUTLOMQE', '4', 'Tutoría', 'Tutoria'),
(275, '1949540403', 'HESLOMQE', '4', 'Historia de España', 'Història d\'Espanya'),
(276, '1949540403', 'LCL2LOMQE', '4', 'Lengua Castellana y Literatura II', 'Llengua Castellana i Literatura II'),
(277, '1949540403', 'PLE2LOMQE', '4', 'Primera lengua extranjera II', 'Primera llengua estrangera II'),
(278, '1949540403', 'VLL2LOMQE', '4', 'Valenciano: lengua y literatura II', 'Valencià: llengua i literatura II'),
(279, '1949540403', 'MAT2LOMQE', '4', 'Matemáticas II', 'Matemàtiques II'),
(280, '1949540403', 'FISLOMQE', '4', 'Física', 'Física'),
(281, '1949540403', 'QUILOMQE', '4', 'Química', 'Química'),
(282, '1949540403', 'DT2LOMQE', '4', 'Dibujo Técnico II', 'Dibuix Tècnic II'),
(283, '1949540403', 'BIOLOMQE', '4', 'Biología', 'Biologia'),
(284, '1949540403', 'GEOLOMQE', '4', 'Geología', 'Geologia'),
(285, '1949540403', 'AMU2LOMQE', '4', 'Análisis musical II', 'Anàlisi musical II'),
(286, '1949540403', 'SLE2LOMQE', '4', 'Segunda lengua extranjera II', 'Segona llengua estrangera II'),
(287, '1949540403', 'TIC2LOMQE', '4', 'Tecnologías de la Información y de la Comunicación II', 'Tecnologies de la Informació i de la Comunicació II'),
(288, '1949540403', 'TECI2LOMQE', '4', 'Tecnología Industrial II', 'Tecnologia Industrial II'),
(289, '1949540403', 'CTMLOMQE', '4', 'Ciencias de la Tierra y del Medio Ambiente', 'Ciències de la Terra i del Medi Ambient'),
(290, '1949540403', 'FAGLOMQE', '4', 'Fundamentos de administración y gestión ', 'Fonaments d\'administració i gestió'),
(291, '1949540403', 'PSILOMQE', '4', 'Psicología', 'Psicologia'),
(292, '1949540403', 'TEGLOMQE', '4', 'Técnicas de expresión graficoplástica', 'Tècniques d\'expressió graficoplàstica'),
(293, '1949540403', 'IMSLOMQE', '4', 'Imagen y Sonido', 'Imatge i So'),
(294, '1949540403', 'FISLOMQEEO', '4', 'Física', 'Física'),
(295, '1949540403', 'QUILOMQEEO', '4', 'Química', 'Química'),
(296, '1949540403', 'DT2LOMQEEO', '4', 'Dibujo Técnico II', 'Dibuix Tècnic II'),
(297, '1949540403', 'BIOLOMQEEO', '4', 'Biología', 'Biologia'),
(298, '1949540403', 'GEOLOMQEEO', '4', 'Geología', 'Geologia'),
(299, '1949540403', 'LLA2LOMQEEO', '4', 'Latín II', 'Llatí II'),
(300, '1949540403', 'GRE2LOMQEEO', '4', 'Griego II', 'Grec II'),
(301, '1949540403', 'MATCS2LOMQEO', '4', 'Matemáticas aplicadas a las CCSS II', 'Matemàtiques aplicades a les CCSS II'),
(302, '1949540403', 'ECOEMLOMQEEO', '4', 'Economía de la empresa', 'Economia de l\'empresa'),
(303, '1949540403', 'GEOGLOMQEEO', '4', 'Geografía', 'Geografia'),
(304, '1949540403', 'HARTLOMQEEO', '4', 'Historia del Arte', 'Història de l\'Art'),
(305, '1949540403', 'HFILLOMQEEO', '4', 'Historia de la Filosofía', 'Història de la Filosofia'),
(306, '1949540403', 'FA2LOMQEEO', '4', 'Fundamentos del Arte II', 'Fonaments de l\'Art II'),
(307, '1949540403', 'CA2LOMQEEO', '4', 'Cultura audiovisual II', 'Cultura audiovisual II'),
(308, '1949540403', 'AELOMQEEO', '4', 'Artes escénicas', 'Arts escèniques'),
(309, '1949540403', 'DISLOMQEEO', '4', 'Diseño', 'Disseny'),
(310, '1949540403', 'HMDLOMQE', '4', 'Historia de la Música y de la Danza', 'Història de la Música i de la Dansa'),
(311, '1949540403', 'DA2LOMQE', '4', 'Dibujo artístico II', 'Dibuix artístic II'),
(312, '1949540403', 'HFILLOMQEEOB', '4', 'Historia de la Filosofía', 'Història de la Filosofia'),
(313, '1949540403', 'EFSLOMQE', '4', 'Educación Físicodeportiva y Salud', 'Educació Físicoesportiva i Salut'),
(314, '1949542147', 'TU02CF', '5', 'Tutoría Segundo', 'Tutoria Segon'),
(315, '1949542147', 'CV0002', '5', 'Inglés Técnico II-M / Horario reservado para la docencia en inglés', 'Anglés Tècnic II-M / Horari reservat per a la docència en anglés'),
(316, '1949542147', '0230', '5', 'Empresa e iniciativa emprendedora', 'Empresa i iniciativa emprenedora'),
(317, '1949542147', '0228', '5', 'Aplicaciones web', 'Aplicacions web'),
(318, '1949542147', '0226', '5', 'Seguridad informática', 'Seguretat informàtica'),
(319, '1949542147', '0224', '5', 'Sistemas operativos en red', 'Sistemes operatius en xarxa'),
(320, '1949542147', '0231', '5', 'Formación en Centros de Trabajo', 'Formació en Centres de Treball'),
(321, '1949542147', '0227', '5', 'Servicios en red', 'Servicis en xarxa'),
(322, '1949542140', 'TU01CF', '5', 'Tutoría Primero', 'Tutoria Primer'),
(323, '1949542140', 'CV0001', '5', 'Inglés Técnico I-M / Horario reservado para la docencia en inglés', 'Anglés Tècnic I-M / Horari reservat per a la docència en anglés'),
(324, '1949542140', '0225', '5', 'Redes locales', 'Xarxes locals'),
(325, '1949542140', '0223', '5', 'Aplicaciones ofimáticas', 'Aplicacions ofimàtiques'),
(326, '1949542140', '0221', '5', 'Montaje y mantenimiento de equipos', 'Muntatge i manteniment d\'equips'),
(327, '1949542140', '0229', '5', 'Formación y orientación laboral', 'Formació i orientació laboral'),
(328, '1949542140', '0222', '5', 'Sistemas operativos monopuesto', 'Sistemes operatius monolloc'),
(329, '1949542076', 'TU02CF', '5', 'Tutoría Segundo', 'Tutoria Segon'),
(330, '1949542076', '3012e', '5', 'Lengua Extranjera II', 'Llengua Estrangera II'),
(331, '1949542076', '3012', '5', 'Comunicación y Sociedad II', 'Comunicació i Societat II'),
(332, '1949542076', '3012s', '5', 'Ciencias Sociales II', 'Ciències Socials II'),
(333, '1949542076', '3012cv', '5', 'Castellano-Valenciano II', 'Castellà-Valencià II'),
(334, '1949542076', 'CV0006', '5', 'Formación y Orientación Laboral II', 'Formació i Orientació Laboral II'),
(335, '1949542076', '3019', '5', 'Ciencias Aplicadas II', 'Ciències Aplicades II'),
(336, '1949542076', '3016', '5', 'Instalación y mantenimiento de redes para transmisión de datos', 'Instal·lació i manteniment de xarxes per a transmissió de dades'),
(337, '1949542076', '3030', '5', 'Operaciones auxiliares para la configuración y la explotación', 'Operacions auxiliars per a la configuració i l\'explotació'),
(338, '1949542076', '3033', '5', 'Formación en centros de trabajo', 'Formació en centres de treball'),
(339, '1949542069', 'TU01CF', '5', 'Tutoría Primero', 'Tutoria Primer'),
(340, '1949542069', '3031', '5', 'Ofimática y archivo de documentos', 'Ofimàtica i arxiu de documents'),
(341, '1949542069', '3029', '5', 'Montaje y mantenimiento de sistemas y componentes informáticos', 'Muntatge i manteniment de sistemes i components informàtics'),
(342, '1949542069', 'CV0005', '5', 'Formación y orientación laboral ', 'Formació i orientació laboral '),
(343, '1949542069', '3009', '5', 'Ciencias Aplicadas I', 'Ciències aplicades I'),
(344, '1949542069', '3011', '5', 'Comunicación y Sociedad I', 'Comunicació i societat I'),
(345, '1949542069', '3011s', '5', 'Ciencias Sociales ', 'Ciències Socials '),
(346, '1949542069', '3011e', '5', 'Lengua Extranjera', 'Llengua Estrangera'),
(347, '1949542069', '3011cv', '5', 'Castellano-Valenciano', 'Castellà-Valencià'),
(348, '1949542204', 'TU02CF', '5', 'Tutoría Segundo', 'Tutoria Segon'),
(349, '1949542204', 'CV0004', '5', 'Inglés Técnico II-S / Horario reservado para la docencia en inglés', 'Anglés Tècnic II-S / Horari reservat per a la docència en anglés'),
(350, '1949542204', '0495', '5', 'Formación en centros de trabajo', 'Formació en centres de treball'),
(351, '1949542204', '0492', '5', 'Proyecto de desarrollo de aplicaciones multiplataforma', 'Projecte de desenrotllament d\'aplicacions multiplataforma'),
(352, '1949542204', '0494', '5', 'Empresa e iniciativa emprendedora', 'Empresa i iniciativa emprenedora'),
(353, '1949542204', '0491', '5', 'Sistemas de gestión empresarial', 'Sistemes de gestió empresarial'),
(354, '1949542204', '0490', '5', 'Programación de servicios y procesos', 'Programació de servicis i processos'),
(355, '1949542204', '0489', '5', 'Programación multimedia y dispositivos móviles', 'Programació multimèdia i dispositius mòbils'),
(356, '1949542204', '0488', '5', 'Desarrollo de interfaces', 'Desenrotllament d\'interfícies'),
(357, '1949542204', '0486', '5', 'Acceso a datos', 'Accés a dades'),
(358, '1949542197', 'TU01CF', '5', 'Tutoría Primero', 'Tutoria Primer'),
(359, '1949542197', 'CV0003', '5', 'Inglés Técnico I-S / Horario reservado para la docencia en inglés', 'Inglés Técnico I-S / Horario reservado para la docencia en inglés'),
(360, '1949542197', '0373', '5', 'Lenguajes de marcas y sistemas de gestión de información', 'Llenguatges de marques i sistemes de gestió d\'informació'),
(361, '1949542197', '0487', '5', 'Entornos de desarrollo', 'Entorns de desenrotllament'),
(362, '1949542197', '0485', '5', 'Programación', 'Programació'),
(363, '1949542197', '0484', '5', 'Bases de Datos', 'Bases de Dades'),
(364, '1949542197', '0483', '5', 'Sistemas informáticos', 'Sistemes informàtics'),
(365, '1949542197', '0493', '5', 'Formación y orientación laboral', 'Formació i orientació laboral'),
(366, '1949545378', 'TU02CF', '5', 'Tutoría Segundo', 'Tutoria Segon'),
(367, '1949545378', '3018', '5', 'Formación en centros de trabajo', 'Formació en centres de treball'),
(368, '1949545378', '3018s', '5', 'Formación en centros de trabajo (UF2)', 'Formació en centres de treball (UF2)'),
(369, '1949545378', '3012e', '5', 'Lengua Extranjera II', 'Llengua Estrangera II'),
(370, '1949545378', '3012', '5', 'Comunicación y Sociedad II', 'Comunicació i Societat II'),
(371, '1949545378', '3012s', '5', 'Ciencias Sociales II', 'Ciències Socials II'),
(372, '1949545378', '3012cv', '5', 'Castellano-Valenciano II', 'Castellà-Valencià II'),
(373, '1949545378', 'CV0006', '5', 'Formación y Orientación Laboral II', 'Formació i Orientació Laboral II'),
(374, '1949545378', '3019', '5', 'Ciencias Aplicadas II', 'Ciències Aplicades II'),
(375, '1949545378', '3016', '5', 'Instalación y mantenimiento de redes para transmisión de datos', 'Instal·lació i manteniment de xarxes per a transmissió de dades'),
(376, '1949545378', '3014', '5', 'Instalaciones de telecomunicaciones', 'Instal·lacions de telecomunicacions'),
(377, '1949545371', 'TU01CF', '5', 'Tutoría Primero', 'Tutoria Primer'),
(378, '1949545371', 'CV0005', '5', 'Formación y orientación laboral ', 'Formació i orientació laboral '),
(379, '1949545371', '3015', '5', 'Equipos eléctricos y electrónicos', 'Equips elèctrics i electrònics.'),
(380, '1949545371', '3013', '5', 'Instalaciones eléctricas y domóticas', 'Instal.lacions elèctriques i domòtiques'),
(381, '1949545371', '3009', '5', 'Ciencias Aplicadas I', 'Ciències aplicades I'),
(382, '1949545371', '3011', '5', 'Comunicación y Sociedad I', 'Comunicació i societat I'),
(383, '1949545371', '3011s', '5', 'Ciencias Sociales ', 'Ciències Socials '),
(384, '1949545371', '3011e', '5', 'Lengua Extranjera', 'Llengua Estrangera'),
(385, '1949545371', '3011cv', '5', 'Castellano-Valenciano', 'Castellà-Valencià'),
(386, '1949545371', '3018p', '5', 'Formación en centros de trabajo (UF1)', 'Formació en centres de treball (UF1)'),
(387, '1949544417', 'TU02CF', '5', 'Tutoría Segundo', 'Tutoria Segon'),
(388, '1949544417', 'CV0004', '5', 'Inglés Técnico II-S / Horario reservado para la docencia en inglés', 'Anglés Tècnic II-S / Horari reservat per a la docència en anglés'),
(389, '1949544417', '0655', '5', 'Gestión logística y comercial', 'Gestió logística i comercial'),
(390, '1949544417', '0657', '5', 'Proyecto de Administración y Finanzas', 'Projecte d\'Administració i finances'),
(391, '1949544417', '0652', '5', 'Gestión de recursos humanos', 'Gestió de recursos humans'),
(392, '1949544417', '0653', '5', 'Gestión financiera', 'Gestió financera '),
(393, '1949544417', '0656', '5', 'Simulación empresarial', 'Simulació empresarial'),
(394, '1949544417', '0660', '5', 'Formación en Centros de Trabajo', 'Formació en Centres de Treball'),
(395, '1949544417', '0654', '5', 'Contabilidad y fiscalidad', 'Comptabilitat i fiscalitat'),
(396, '1949544424', 'TU01CF', '5', 'Tutoría Primero', 'Tutoria Primer'),
(397, '1949544424', 'CV0003', '5', 'Inglés Técnico I-S / Horario reservado para la docencia en inglés', 'Inglés Técnico I-S / Horario reservado para la docencia en inglés'),
(398, '1949544424', '0179', '5', 'Inglés', 'Anglés'),
(399, '1949544424', '0651', '5', 'Comunicación y atención al cliente', 'Comunicació i atenció al client'),
(400, '1949544424', '0658', '5', 'Formación y orientación laboral', 'Formació i orientació laboral'),
(401, '1949544424', '0650', '5', 'Proceso integral de la actividad comercial', 'Procés integral de l\'activitat comercial'),
(402, '1949544424', '0648', '5', 'Recursos humanos y responsabilidad social corporativa', 'Recursos humans i responsabilitat social corporativa '),
(403, '1949544424', '0649', '5', 'Ofimática y proceso de la información', 'Ofimàtica i procés de la informació'),
(404, '1949544424', '0647', '5', 'Gestión de la documentación jurídica y empresarial', 'Gestió de la documentació jurídica i empresarial'),
(405, '1949544340', 'TU02CF', '5', 'Tutoría Segundo', 'Tutoria Segon'),
(406, '1949544340', 'CV0002', '5', 'Inglés Técnico II-M / Horario reservado para la docencia en inglés', 'Anglés Tècnic II-M / Horari reservat per a la docència en anglés'),
(407, '1949544340', '0446', '5', 'Empresa en el aula', 'Empresa en l\'aula'),
(408, '1949544340', '0443', '5', 'Tratamiento de la documentación contable', 'Tractament de la documentació comptable'),
(409, '1949544340', '0442', '5', 'Operaciones administrativas de recursos humanos', 'Operacions administratives de recursos humans'),
(410, '1949544340', '0451', '5', 'Formación en centros de trabajo', 'Formació en centres de treball'),
(411, '1949544340', '0448', '5', 'Operaciones auxiliares de gestión de tesorería', 'Operacions auxiliars de gestió de tresoreria'),
(412, '1949544333', 'TU01CF', '5', 'Tutoría Primero', 'Tutoria Primer'),
(413, '1949544333', 'CV0001', '5', 'Inglés Técnico I-M / Horario reservado para la docencia en inglés', 'Anglés Tècnic I-M / Horari reservat per a la docència en anglés'),
(414, '1949544333', '0437', '5', 'Comunicación empresarial y atención al cliente', 'Comunicació empresarial i atenció al client'),
(415, '1949544333', '0449', '5', 'Formación y orientación laboral', 'Formació i orientació laboral'),
(416, '1949544333', '0441', '5', 'Técnica contable', 'Tècnica comptable'),
(417, '1949544333', '0439', '5', 'Empresa y administración', 'Empresa i administració'),
(418, '1949544333', '0438', '5', 'Operaciones administrativa de compra-venta', 'Operacions administrativa de compra-venda'),
(419, '1949544333', '0440', '5', 'Tratamiento informático de la información ', 'Tractament informàtic de la informació '),
(420, '1949544333', '0156', '5', 'Inglés', 'Anglés');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cursos`
--

CREATE TABLE `cursos` (
  `id` varchar(10) NOT NULL,
  `codigo` varchar(15) NOT NULL,
  `ensenanza` varchar(2) NOT NULL,
  `abreviatura` varchar(10) NOT NULL,
  `nombre_cas` varchar(100) NOT NULL,
  `nombre_val` varchar(100) NOT NULL,
  `idPadre` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `cursos`
--

INSERT INTO `cursos` (`id`, `codigo`, `ensenanza`, `abreviatura`, `nombre_cas`, `nombre_val`, `idPadre`) VALUES
('1949539185', '1949539185', '3', '3ESO', 'Tercero', 'Tercer', ' '),
('1949539199', '1949539199', '3', '1ESO', 'Primero', 'Primer', ' '),
('1949539212', '1949539212', '3', '2ESO', 'Segundo', 'Segon', ' '),
('1949539224', '1949539224', '3', '4ESO', 'Cuarto', 'Quart', ' '),
('1949539243', '1949539243', '3', 'PMAR', 'Programa de Mejora del aprendizaje y del rendimiento', 'Programa de millora del aprenentatge i del rendiment', ' '),
('1949539245', '1949539245', '3', '3ESO', '3ï¿½ ESO - PMAR ï¿½mbitos', '3r ESO - PMAR ï¿½mbits', '1949539243'),
('1949539262', '1949539262', '3', 'PR4', 'Programa de Refuerzo Cuarto', 'Programa de Reforï¿½ Quart', ' '),
('1949539264', '1949539264', '3', '4ESO', '4ESO (PR4)', '4ESO (PR4)', '1949539262'),
('1949540387', '1949540387', '4', 'BAC', 'BACHILLERATO DE CIENCIAS', 'BATXILLERAT DE CIï¿½NCIES', ' '),
('1949540389', '1949540389', '4', '1BAC', 'PRIMERO BACH CIENCIAS ', 'PRIMER BATX CIï¿½NCIES ', '1949540387'),
('1949540403', '1949540403', '4', '2BAC', 'SEGUNDO BACH CIENCIAS', 'SEGON BATX CIï¿½NCIES', '1949540387'),
('1949540511', '1949540511', '4', 'BAH', 'BACHILLERATO DE HUMANIDADES Y CIENCIAS SOCIALES', 'BATXILLERAT D\'HUMANITATS I CIï¿½NCIES SOCIALS', ' '),
('1949540513', '1949540513', '4', '2BAH', 'SEGUNDO BACH HUMANIDADES Y CIENCIAS SOCIALES', 'SEGON BATX HUMANITATS I CIï¿½NCIES SOCIALS', '1949540511'),
('1949540524', '1949540524', '4', '1BAH', 'PRIMERO BACH HUMANIDADES Y CIENCIAS SOCIALES', 'PRIMER BATX. HUMANITATS I CIï¿½NCIES SOCIALS', '1949540511'),
('1949542042', '1949542042', '5', '190', 'INFORMï¿½TICA Y COMUNICACIONES', 'INFORMï¿½TICA I COMUNICACIONS', ' '),
('1949542044', '1949542044', '5', 'FPB', 'FP Bï¿½SICA', 'FP Bï¿½SICA', '1949542042'),
('1949542067', '1949542067', '5', '97210A', 'INFORMï¿½TICA DE OFICINA', 'INFORMï¿½TICA D\'OFICINA', '1949542044'),
('1949542069', '1949542069', '5', '1CFB', 'Primero', 'Primer', '1949542067'),
('1949542076', '1949542076', '5', '2CFB', 'Segundo', 'Segon', '1949542067'),
('1949542114', '1949542114', '5', 'GM', 'GRADO MEDIO', 'GRAU MITJï¿½', '1949542042'),
('1949542138', '1949542138', '5', '707103', 'SISTEMAS MICROINFORMï¿½TICOS Y REDES', 'SISTEMES MICROINFORMï¿½TICS I XARXES', '1949542114'),
('1949542140', '1949542140', '5', '1CFM', 'Primero', 'Primer', '1949542138'),
('1949542147', '1949542147', '5', '2CFM', 'Segundo', 'Segon', '1949542138'),
('1949542164', '1949542164', '5', 'GS', 'GRADO SUPERIOR', 'GRAU SUPERIOR', '1949542042'),
('1949542195', '1949542195', '5', '836104', 'DESARROLLO DE APLICACIONES MULTIPLATAFORMA', 'DESENROTLLAMENT D\'APLICACIONS MULTIPLATAFORMA', '1949542164'),
('1949542197', '1949542197', '5', '1CFS', 'Primero', 'Primer', '1949542195'),
('1949542204', '1949542204', '5', '2CFS', 'Segundo', 'Segon', '1949542195'),
('1949544249', '1949544249', '5', '001', 'ADMINISTRACIï¿½N Y GESTIï¿½N', 'ADMINISTRACIï¿½ I GESTIï¿½', ' '),
('1949544300', '1949544300', '5', 'GM', 'GRADO MEDIO', 'GRAU MITJï¿½', '1949544249'),
('1949544331', '1949544331', '5', '472103', 'GESTIï¿½N ADMINISTRATIVA (LOE)', 'GESTIï¿½ ADMINISTRATIVA (LOE)', '1949544300'),
('1949544333', '1949544333', '5', '1CFM', 'Primero', 'Primer', '1949544331'),
('1949544340', '1949544340', '5', '2CFM', 'Segundo', 'Segon', '1949544331'),
('1949544364', '1949544364', '5', 'GS', 'GRADO SUPERIOR', 'GRAU SUPERIOR', '1949544249'),
('1949544415', '1949544415', '5', '441104', 'ADMINISTRACIï¿½N Y FINANZAS (LOE)', 'ADMINISTRACIï¿½ I FINANCES (LOE)', '1949544364'),
('1949544417', '1949544417', '5', '2CFS', 'Segundo', 'Segon', '1949544415'),
('1949544424', '1949544424', '5', '1CFS', 'Primero', 'Primer', '1949544415'),
('1949545365', '1949545365', '5', '031', 'ELECTRICIDAD Y ELECTRï¿½NICA', 'ELECTRICITAT I ELECTRï¿½NICA', ' '),
('1949545367', '1949545367', '5', 'FPB', 'FP Bï¿½SICA', 'FP Bï¿½SICA', '1949545365'),
('1949545369', '1949545369', '5', '95610A', 'ELECTRICIDAD Y ELECTRï¿½NICA', 'ELECTRICITAT I ELECTRï¿½NICA', '1949545367'),
('1949545371', '1949545371', '5', '1CFB', 'Primero', 'Primer', '1949545369'),
('1949545378', '1949545378', '5', '2CFB', 'Segundo', 'Segon', '1949545369'),
('1949545683', '1949545683', '5', 'GM', 'GRADO MEDIO', 'GRAU MITJï¿½', '1949545365'),
('1949571046', '1949571046', '6', '190', 'INFORMï¿½TICA Y COMUNICACIONES', 'INFORMï¿½TICA I COMUNICACIONS', ' ');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cursos-grupos`
--

CREATE TABLE `cursos-grupos` (
  `id` int(10) NOT NULL,
  `grupo` varchar(10) NOT NULL,
  `ensenanza` varchar(2) NOT NULL,
  `curso` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ejemplares`
--

CREATE TABLE `ejemplares` (
  `id` varchar(10) NOT NULL,
  `codigo` varchar(100) NOT NULL,
  `id_libro` varchar(100) NOT NULL,
  `estado` int(11) NOT NULL,
  `prestado` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `grupos`
--

CREATE TABLE `grupos` (
  `id` varchar(10) NOT NULL,
  `codigo` varchar(10) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `ensenanza` varchar(2) NOT NULL,
  `linea` varchar(2) NOT NULL,
  `turno` varchar(2) NOT NULL,
  `modalidad` varchar(5) NOT NULL,
  `aula` varchar(15) NOT NULL,
  `capacidad` smallint(6) NOT NULL,
  `tutor_ppal` varchar(10) NOT NULL,
  `tutor_sec` varchar(10) NOT NULL,
  `oficial` varchar(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `grupos`
--

INSERT INTO `grupos` (`id`, `codigo`, `nombre`, `ensenanza`, `linea`, `turno`, `modalidad`, `aula`, `capacidad`, `tutor_ppal`, `tutor_sec`, `oficial`) VALUES
('1BACA', '1BACA', '1BA.CT.AN.REL.AE', '4', '3', 'D', 'COM', '1BA.CT.AN.', 30, '022540223Q', ' ', 'S'),
('1BACB', '1BACB', '1BB.HUM.AN.REL', '4', '3', 'D', 'COM', '1BB.HUM.AN', 30, '024388381D', ' ', 'S'),
('1CFBE', '1CFBE', '1 FP Bàsica Electronica', '5', '3', 'D', 'COM', ' ', 15, '020784196Q', ' ', 'S'),
('1CFBIO', '1CFBIO', '1 FP Bàsica Inf. Oficina', '5', '3', 'D', 'COM', ' ', 18, '020437036H', ' ', 'S'),
('1CFMA', '1CFMA', '1.CM.ADMTVO.PIP', '5', '3', 'D', 'COM', ' ', 30, '052660245C', ' ', 'S'),
('1CFMN', '1CFMN', '1.CM.INFOR.PIP', '5', '3', 'D', 'COM', '1.CM.SIS.I', 25, '020816980W', ' ', 'S'),
('1CFSF', '1CFSF', '1.CS.AD.FIN.PIP', '5', '3', 'D', 'COM', '1.CS.AD.FI', 31, '020422492X', ' ', 'S'),
('1CFSN', '1CFSN', '1.CS.DES.MULTIP.', '5', '3', 'D', 'COM', '1.CS.INF', 24, '020432847S', ' ', 'S'),
('1ESOA', '1ESOA', '1A.AN.REL.AE.PEV', '3', '2', 'D', 'COM', ' ', 35, '020786939E', ' ', 'S'),
('1ESOB', '1ESOB', '1B.AN.REL.AE.PIP.PEV', '3', '2', 'D', 'COM', ' ', 35, '020820137P', ' ', 'S'),
('1ESOC', '1ESOC', '1C.AN.REL.PIP', '3', '3', 'D', 'COM', ' ', 35, '020429313T', ' ', 'S'),
('1ESOD', '1ESOD', '1D.AN.REL.AE.PIP', '3', '3', 'D', 'COM', ' ', 35, '020832270C', ' ', 'S'),
('1ESOE', '1ESOE', '1E.AN.FR.REL.AE.PIP', '3', '3', 'D', 'COM', '1E.AN.FR.R', 30, '020828476K', ' ', 'S'),
('2BACA', '2BACA', '2BA.CIENT', '4', '3', 'D', 'COM', ' ', 35, '052646181D', ' ', 'S'),
('2BAHB', '2BAHB', '2BB.HUM.SOC', '4', '3', 'D', 'COM', ' ', 35, '020816939F', ' ', 'S'),
('2CFBE', '2CFBE', '2 FP Bàsica Electrònica', '5', '3', 'D', 'COM', '2CFBE', 25, '073908934K', ' ', 'S'),
('2CFBN', '2CFBN', '2 FP Bàsica Informàtica', '5', '3', 'D', 'COM', '2CFBN', 18, '052730192R', ' ', 'S'),
('2CFMA', '2CFMA', '2.CM.GES.ADM.PIP', '5', '3', 'D', 'COM', ' ', 30, '020781229Q', ' ', 'S'),
('2CFMN', '2CFMN', '2.CM.SIS.INF.PIP', '5', '3', 'D', 'COM', '2.CM.SIS.I', 20, '073776124J', ' ', 'S'),
('2CFSF', '2CFSF', '2.CS.AD.FIN.PIP', '5', '3', 'D', 'COM', '2.CS.AD.FI', 30, '042995716E', ' ', 'S'),
('2CFSN', '2CFSN', '2.CS.DES.MULTIP.', '5', '3', 'D', 'COM', ' ', 24, '052660712G', ' ', 'S'),
('2ESO-PAC', '2ESO-PAC', '2ESOPAC', '3', '3', 'D', 'COM', ' ', 0, ' ', ' ', 'N'),
('2ESOA', '2ESOA', '2A.AN.REL.VE', '3', '2', 'D', 'COM', ' ', 35, '020787797Y', ' ', 'S'),
('2ESOB', '2ESOB', '2B.AN.REL.VE.PEV', '3', '2', 'D', 'COM', ' ', 35, '020458654Q', ' ', 'S'),
('2ESOC', '2ESOC', '2C.AN.REL.PIP', '3', '3', 'D', 'COM', '2C.AN.FRA.', 35, '033458717L', ' ', 'S'),
('2ESOD', '2ESOD', '2D.AN.REL.VE.PIP', '3', '3', 'D', 'COM', ' ', 38, '052733883N', ' ', 'S'),
('3ESOA', '3ESOA', '3A.AN.REL.AE.PEV', '3', '2', 'D', 'COM', ' ', 35, '020454778G', ' ', 'S'),
('3ESOB', '3ESOB', '3B.AN.REL.AE.PEV', '3', '2', 'D', 'COM', '3B.AN.REL.', 35, '020791945Z', ' ', 'S'),
('3ESOC', '3ESOC', '3C.AN.FR.REL.AE.PIP', '3', '3', 'D', 'COM', '3C.AN.FR.R', 35, '020778755A', ' ', 'S'),
('3ESOPMAR', '3ESOPMAR', '3C.AN.FR.REL.VE.PIP', '3', '3', 'D', 'COM', ' ', 25, '020782928J', ' ', 'S'),
('4ESOA', '4ESOA', '4A.AN.REL.AE.PEV', '3', '2', 'D', 'COM', ' ', 37, '020792416W', ' ', 'S'),
('4ESOB', '4ESOB', '4B.AN.FR.REL.AE.PIP.PEV', '3', '3', 'D', 'COM', ' ', 35, '052682739C', '020038542E', 'S'),
('4ESOC', '4ESOC', '4C.AN.FR.REL.AE.PIP.PEV', '3', '2', 'D', 'COM', ' ', 30, '020807415M', '020038542E', 'S'),
('4ESOPR', '4ESOPR', '4PR.AN.PEV.PIP', '3', '3', 'D', 'COM', ' ', 0, ' ', ' ', 'N'),
('4ESOPR4', '4ESOPR4', '4PR.AN.PEP.PIV', '3', '2', 'D', 'COM', ' ', 30, ' ', ' ', 'S');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `historial`
--

CREATE TABLE `historial` (
  `id` int(11) NOT NULL,
  `id_inventario` varchar(10) NOT NULL,
  `id_alumno` varchar(10) NOT NULL,
  `curso_escolar` int(11) NOT NULL,
  `estado_inicial` int(11) NOT NULL,
  `estado_final` int(11) NOT NULL,
  `fecha_inicial` date NOT NULL,
  `fecha_final` date NOT NULL,
  `observaciones` varchar(500) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `libros`
--

CREATE TABLE `libros` (
  `id` varchar(10) NOT NULL,
  `codigo` varchar(50) NOT NULL,
  `id_asignatura` int(11) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `ISBN` varchar(50) NOT NULL,
  `unidades` int(11) NOT NULL,
  `obsoleto` tinyint(1) NOT NULL,
  `precio` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `matricula`
--

CREATE TABLE `matricula` (
  `id` int(11) NOT NULL,
  `curso_escolar` int(11) NOT NULL,
  `id_alumno` varchar(10) NOT NULL,
  `ensenanza` varchar(2) NOT NULL,
  `curso` varchar(10) NOT NULL,
  `contenido` int(11) NOT NULL,
  `idioma` varchar(25) NOT NULL,
  `tipo_basico` varchar(50) NOT NULL,
  `tipo_predom` varchar(50) NOT NULL,
  `acis` varchar(25) NOT NULL,
  `fec_ini_acis` date NOT NULL,
  `fec_fin_acis` date NOT NULL,
  `cur_ref_acis` varchar(25) NOT NULL,
  `curso_pendiente` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `matricula`
--

INSERT INTO `matricula` (`id`, `curso_escolar`, `id_alumno`, `ensenanza`, `curso`, `contenido`, `idioma`, `tipo_basico`, `tipo_predom`, `acis`, `fec_ini_acis`, `fec_fin_acis`, `cur_ref_acis`, `curso_pendiente`) VALUES
(1, 2018, '10429497', '5', '1949542204', 350, ' ', 'N', ' ', 'N', '2000-01-01', '2000-01-01', ' ', ' '),
(2, 2018, '10429497', '5', '1949542204', 351, ' ', 'N', ' ', 'N', '2000-01-01', '2000-01-01', ' ', ' '),
(3, 2018, '10429497', '5', '1949542204', 352, ' ', 'N', 'C', 'N', '2000-01-01', '2000-01-01', ' ', ' '),
(4, 2018, '10429497', '5', '1949542204', 353, ' ', 'N', ' ', 'N', '2000-01-01', '2000-01-01', ' ', ' '),
(5, 2018, '10429497', '5', '1949542204', 354, ' ', 'N', ' ', 'N', '2000-01-01', '2000-01-01', ' ', ' '),
(6, 2018, '10429497', '5', '1949542204', 355, ' ', 'N', ' ', 'N', '2000-01-01', '2000-01-01', ' ', ' '),
(7, 2018, '10429497', '5', '1949542204', 356, ' ', 'N', ' ', 'N', '2000-01-01', '2000-01-01', ' ', ' '),
(8, 2018, '10429497', '5', '1949542204', 357, ' ', 'N', ' ', 'N', '2000-01-01', '2000-01-01', ' ', ' '),
(9, 2018, '10429497', '5', '1949542204', 314, ' ', 'N', ' ', 'N', '2000-01-01', '2000-01-01', ' ', ' '),
(10, 2018, '10429497', '5', '1949542204', 349, ' ', 'N', ' ', 'N', '2000-01-01', '2000-01-01', ' ', ' '),
(11, 2018, '10429470', '5', '1949542204', 350, ' ', 'N', ' ', 'N', '2000-01-01', '2000-01-01', ' ', ' '),
(12, 2018, '10429470', '5', '1949542204', 351, ' ', 'N', ' ', 'N', '2000-01-01', '2000-01-01', ' ', ' '),
(13, 2018, '10429470', '5', '1949542204', 352, ' ', 'N', 'C', 'N', '2000-01-01', '2000-01-01', ' ', ' '),
(14, 2018, '10429470', '5', '1949542204', 353, ' ', 'N', ' ', 'N', '2000-01-01', '2000-01-01', ' ', ' '),
(15, 2018, '10429470', '5', '1949542204', 354, ' ', 'N', ' ', 'N', '2000-01-01', '2000-01-01', ' ', ' '),
(16, 2018, '10429470', '5', '1949542204', 355, ' ', 'N', ' ', 'N', '2000-01-01', '2000-01-01', ' ', ' '),
(17, 2018, '10429470', '5', '1949542204', 356, ' ', 'N', ' ', 'N', '2000-01-01', '2000-01-01', ' ', ' '),
(18, 2018, '10429470', '5', '1949542204', 357, ' ', 'N', ' ', 'N', '2000-01-01', '2000-01-01', ' ', ' '),
(19, 2018, '10429470', '5', '1949542204', 314, ' ', 'N', ' ', 'N', '2000-01-01', '2000-01-01', ' ', ' '),
(20, 2018, '10429470', '5', '1949542204', 349, ' ', 'N', ' ', 'N', '2000-01-01', '2000-01-01', ' ', ' ');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `alumnos`
--
ALTER TABLE `alumnos`
  ADD PRIMARY KEY (`id`),
  ADD KEY `curso` (`curso`),
  ADD KEY `grupo` (`grupo`);

--
-- Indices de la tabla `contenido`
--
ALTER TABLE `contenido`
  ADD PRIMARY KEY (`id`),
  ADD KEY `curso_rel` (`curso`);

--
-- Indices de la tabla `cursos`
--
ALTER TABLE `cursos`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `cursos-grupos`
--
ALTER TABLE `cursos-grupos`
  ADD PRIMARY KEY (`id`),
  ADD KEY `grupo` (`grupo`),
  ADD KEY `curso` (`curso`);

--
-- Indices de la tabla `ejemplares`
--
ALTER TABLE `ejemplares`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_libro_rel` (`id_libro`);

--
-- Indices de la tabla `grupos`
--
ALTER TABLE `grupos`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `historial`
--
ALTER TABLE `historial`
  ADD KEY `id_inventario_rel` (`id_inventario`),
  ADD KEY `id_alumno_rel` (`id_alumno`);

--
-- Indices de la tabla `libros`
--
ALTER TABLE `libros`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_asignatura_rel` (`id_asignatura`);

--
-- Indices de la tabla `matricula`
--
ALTER TABLE `matricula`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_alumno_rel_2` (`id_alumno`),
  ADD KEY `id_contenido_rel` (`contenido`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `contenido`
--
ALTER TABLE `contenido`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=421;

--
-- AUTO_INCREMENT de la tabla `cursos-grupos`
--
ALTER TABLE `cursos-grupos`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `matricula`
--
ALTER TABLE `matricula`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `alumnos`
--
ALTER TABLE `alumnos`
  ADD CONSTRAINT `alumnos_ibfk_1` FOREIGN KEY (`curso`) REFERENCES `cursos` (`id`) ON DELETE NO ACTION,
  ADD CONSTRAINT `alumnos_ibfk_2` FOREIGN KEY (`grupo`) REFERENCES `grupos` (`id`) ON DELETE NO ACTION;

--
-- Filtros para la tabla `contenido`
--
ALTER TABLE `contenido`
  ADD CONSTRAINT `curso_rel` FOREIGN KEY (`curso`) REFERENCES `cursos` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `cursos-grupos`
--
ALTER TABLE `cursos-grupos`
  ADD CONSTRAINT `cursos-grupos_ibfk_1` FOREIGN KEY (`grupo`) REFERENCES `grupos` (`id`) ON DELETE NO ACTION,
  ADD CONSTRAINT `cursos-grupos_ibfk_2` FOREIGN KEY (`curso`) REFERENCES `cursos` (`id`) ON DELETE NO ACTION;

--
-- Filtros para la tabla `ejemplares`
--
ALTER TABLE `ejemplares`
  ADD CONSTRAINT `id_libro_rel` FOREIGN KEY (`id_libro`) REFERENCES `libros` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `historial`
--
ALTER TABLE `historial`
  ADD CONSTRAINT `id_alumno_rel` FOREIGN KEY (`id_alumno`) REFERENCES `alumnos` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `id_inventario_rel` FOREIGN KEY (`id_inventario`) REFERENCES `ejemplares` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `libros`
--
ALTER TABLE `libros`
  ADD CONSTRAINT `id_contenido_r` FOREIGN KEY (`id_asignatura`) REFERENCES `contenido` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `matricula`
--
ALTER TABLE `matricula`
  ADD CONSTRAINT `id_alumno_rel_2` FOREIGN KEY (`id_alumno`) REFERENCES `alumnos` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `id_contenido_rel` FOREIGN KEY (`contenido`) REFERENCES `contenido` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
