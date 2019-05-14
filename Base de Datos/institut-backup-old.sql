-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 20-03-2019 a las 10:16:35
-- Versión del servidor: 10.1.38-MariaDB
-- Versión de PHP: 7.3.2

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

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `contenido`
--

CREATE TABLE `contenido` (
  `id` varchar(10) NOT NULL,
  `curso` varchar(10) NOT NULL,
  `codigo` varchar(15) NOT NULL,
  `ensenanza` varchar(2) NOT NULL,
  `nombre_cas` varchar(100) NOT NULL,
  `nombre_val` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
('1', '1', '', 'DAM', '', '', NULL),
('1949539185', '1949539185', '3', '3ESO', 'Tercero', 'Tercer', ' '),
('1949539199', '1949539199', '3', '1ESO', 'Primero', 'Primer', ' '),
('1949539212', '1949539212', '3', '2ESO', 'Segundo', 'Segon', ' '),
('1949539224', '1949539224', '3', '4ESO', 'Cuarto', 'Quart', ' '),
('1949539243', '1949539243', '3', 'PMAR', 'Programa de Mejora del aprendizaje y del rendimiento', 'Programa de millora del aprenentatge i del rendiment', ' '),
('1949539245', '1949539245', '3', '3ESO', '3º ESO - PMAR Ámbitos', '3r ESO - PMAR Àmbits', '1949539243'),
('1949539262', '1949539262', '3', 'PR4', 'Programa de Refuerzo Cuarto', 'Programa de Reforç Quart', ' '),
('1949539264', '1949539264', '3', '4ESO', '4ESO (PR4)', '4ESO (PR4)', '1949539262'),
('1949540387', '1949540387', '4', 'BAC', 'BACHILLERATO DE CIENCIAS', 'BATXILLERAT DE CIÈNCIES', ' '),
('1949540389', '1949540389', '4', '1BAC', 'PRIMERO BACH CIENCIAS ', 'PRIMER BATX CIÈNCIES ', '1949540387'),
('1949540403', '1949540403', '4', '2BAC', 'SEGUNDO BACH CIENCIAS', 'SEGON BATX CIÈNCIES', '1949540387'),
('1949540511', '1949540511', '4', 'BAH', 'BACHILLERATO DE HUMANIDADES Y CIENCIAS SOCIALES', 'BATXILLERAT D\'HUMANITATS I CIÈNCIES SOCIALS', ' '),
('1949540513', '1949540513', '4', '2BAH', 'SEGUNDO BACH HUMANIDADES Y CIENCIAS SOCIALES', 'SEGON BATX HUMANITATS I CIÈNCIES SOCIALS', '1949540511'),
('1949540524', '1949540524', '4', '1BAH', 'PRIMERO BACH HUMANIDADES Y CIENCIAS SOCIALES', 'PRIMER BATX. HUMANITATS I CIÈNCIES SOCIALS', '1949540511'),
('1949542042', '1949542042', '5', '190', 'INFORMÁTICA Y COMUNICACIONES', 'INFORMÀTICA I COMUNICACIONS', ' '),
('1949542044', '1949542044', '5', 'FPB', 'FP BÁSICA', 'FP BÀSICA', '1949542042'),
('1949542067', '1949542067', '5', '97210A', 'INFORMÁTICA DE OFICINA', 'INFORMÀTICA D\'OFICINA', '1949542044'),
('1949542069', '1949542069', '5', '1CFB', 'Primero', 'Primer', '1949542067'),
('1949542076', '1949542076', '5', '2CFB', 'Segundo', 'Segon', '1949542067'),
('1949542114', '1949542114', '5', 'GM', 'GRADO MEDIO', 'GRAU MITJÀ', '1949542042'),
('1949542138', '1949542138', '5', '707103', 'SISTEMAS MICROINFORMÁTICOS Y REDES', 'SISTEMES MICROINFORMÀTICS I XARXES', '1949542114'),
('1949542140', '1949542140', '5', '1CFM', 'Primero', 'Primer', '1949542138'),
('1949542147', '1949542147', '5', '2CFM', 'Segundo', 'Segon', '1949542138'),
('1949542164', '1949542164', '5', 'GS', 'GRADO SUPERIOR', 'GRAU SUPERIOR', '1949542042'),
('1949542195', '1949542195', '5', '836104', 'DESARROLLO DE APLICACIONES MULTIPLATAFORMA', 'DESENROTLLAMENT D\'APLICACIONS MULTIPLATAFORMA', '1949542164'),
('1949542197', '1949542197', '5', '1CFS', 'Primero', 'Primer', '1949542195'),
('1949542204', '1949542204', '5', '2CFS', 'Segundo', 'Segon', '1949542195'),
('1949544249', '1949544249', '5', '001', 'ADMINISTRACIÓN Y GESTIÓN', 'ADMINISTRACIÓ I GESTIÓ', ' '),
('1949544300', '1949544300', '5', 'GM', 'GRADO MEDIO', 'GRAU MITJÀ', '1949544249'),
('1949544331', '1949544331', '5', '472103', 'GESTIÓN ADMINISTRATIVA (LOE)', 'GESTIÓ ADMINISTRATIVA (LOE)', '1949544300'),
('1949544333', '1949544333', '5', '1CFM', 'Primero', 'Primer', '1949544331'),
('1949544340', '1949544340', '5', '2CFM', 'Segundo', 'Segon', '1949544331'),
('1949544364', '1949544364', '5', 'GS', 'GRADO SUPERIOR', 'GRAU SUPERIOR', '1949544249'),
('1949544415', '1949544415', '5', '441104', 'ADMINISTRACIÓN Y FINANZAS (LOE)', 'ADMINISTRACIÓ I FINANCES (LOE)', '1949544364'),
('1949544417', '1949544417', '5', '2CFS', 'Segundo', 'Segon', '1949544415'),
('1949544424', '1949544424', '5', '1CFS', 'Primero', 'Primer', '1949544415'),
('1949545365', '1949545365', '5', '031', 'ELECTRICIDAD Y ELECTRÓNICA', 'ELECTRICITAT I ELECTRÒNICA', ' '),
('1949545367', '1949545367', '5', 'FPB', 'FP BÁSICA', 'FP BÀSICA', '1949545365'),
('1949545369', '1949545369', '5', '95610A', 'ELECTRICIDAD Y ELECTRÓNICA', 'ELECTRICITAT I ELECTRÒNICA', '1949545367'),
('1949545371', '1949545371', '5', '1CFB', 'Primero', 'Primer', '1949545369'),
('1949545378', '1949545378', '5', '2CFB', 'Segundo', 'Segon', '1949545369'),
('1949545683', '1949545683', '5', 'GM', 'GRADO MEDIO', 'GRAU MITJÀ', '1949545365'),
('1949571046', '1949571046', '6', '190', 'INFORMÁTICA Y COMUNICACIONES', 'INFORMÀTICA I COMUNICACIONS', ' ');

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
('1', '1', '1BA.CN.TC.AN.REL', '0', '', '', '', '', 0, '', '', ''),
('10', '', '2BA.CN.TC.AN', '0', '', '', '', '', 0, '', '', ''),
('11', '', '2CFM.GEST.ADMIN', '0', '', '', '', '', 0, '', '', ''),
('12', '', '2CFS.ADMIN.FIN', '0', '', '', '', '', 0, '', '', ''),
('13', '', '2SA.AN.REL.AE.VAL', '0', '', '', '', '', 0, '', '', ''),
('14', '', '2SB.AN.REL.AE.VL', '0', '', '', '', '', 0, '', '', ''),
('15', '', '2SC.AN.REL.PIP', '0', '', '', '', '', 0, '', '', ''),
('16', '', '2SD.AN.REL.AE.PIP', '0', '', '', '', '', 0, '', '', ''),
('17', '', '3SA.AN.REL.AE.VL', '0', '', '', '', '', 0, '', '', ''),
('18', '', '3SB.AN.REL.AE.VAL', '0', '', '', '', '', 0, '', '', ''),
('19', '', '3SC.AN.REL.PIP', '0', '', '', '', '', 0, '', '', ''),
('2', '', '1BB.HUM.AN.REL', '0', '', '', '', '', 0, '', '', ''),
('20', '', '4SA.AN.REL.VAL', '0', '', '', '', '', 0, '', '', ''),
('21', '', '4SB.AN.REL.AE.VAL', '0', '', '', '', '', 0, '', '', ''),
('22', '', 'GUARDIA', '0', '', '', '', '', 0, '', '', ''),
('23', '', 'Necesidades Educativas Especiales', '0', '', '', '', '', 0, '', '', ''),
('24', '', 'REU', '0', '', '', '', '', 0, '', '', ''),
('25', '', 'BIBLIOTECA', '0', '', '', '', '', 0, '', '', ''),
('26', '', 'HORAS DE LIBRE DISPOSICION', '0', '', '', '', '', 0, '', '', ''),
('28', '', 'PAC', '0', '', '', '', '', 0, '', '', ''),
('29', '', '1CFM.SIST.MICRO.XARXES', '0', '', '', '', '', 0, '', '', ''),
('3', '', '1CFM.GEST.ADMIN', '0', '', '', '', '', 0, '', '', ''),
('30', '', '2CFB ELECTRÓNICA', '0', '', '', '', '', 0, '', '', ''),
('31', '', 'FUNCION DIRECTIVA', '0', '', '', '', '', 0, '', '', ''),
('32', '', '2CFM.SIST.MICRO.XARXES', '0', '', '', '', '', 0, '', '', ''),
('33', '', 'APAS', '0', '', '', '', '', 0, '', '', ''),
('34', '', '2CFB INFORMÀTICA D\'OFICINA', '0', '', '', '', '', 0, '', '', ''),
('35', '', '1CFB ELECTRÓNICA', '0', '', '', '', '', 0, '', '', ''),
('36', '', '1CFB INFORMATICA D\'OFICINA', '0', '', '', '', '', 0, '', '', ''),
('37', '', '3ESO PMAR', '0', '', '', '', '', 0, '', '', ''),
('38', '', '1CFS.DES.MULT', '0', '', '', '', '', 0, '', '', ''),
('39', '', '4ESO PR4', '0', '', '', '', '', 0, '', '', ''),
('4', '', '1CFS.ADMIN.FIN', '0', '', '', '', '', 0, '', '', ''),
('40', '', '2BA.HUM.SCO.AN', '0', '', '', '', '', 0, '', '', ''),
('41', '', '2CFS.DES.MULT', '0', '', '', '', '', 0, '', '', ''),
('5', '', '1SA.AN.REL.AE.VAL', '0', '', '', '', '', 0, '', '', ''),
('6', '', '1SB.AN.REL.AE.VAL', '0', '', '', '', '', 0, '', '', ''),
('7', '', '1SC.AN.REL.VAL', '0', '', '', '', '', 0, '', '', ''),
('8', '', '1SD.AN.REL.PIP', '0', '', '', '', '', 0, '', '', ''),
('9', '', '1SE.AN.REL.AE.PIP', '0', '', '', '', '', 0, '', '', '');

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
  `id_asignatura` varchar(10) NOT NULL,
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
  `contenido` varchar(10) NOT NULL,
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
  ADD KEY `id_alumno_rel_2` (`id_alumno`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `cursos-grupos`
--
ALTER TABLE `cursos-grupos`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT;

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
  ADD CONSTRAINT `id_asignatura_rel` FOREIGN KEY (`id_asignatura`) REFERENCES `contenido` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `matricula`
--
ALTER TABLE `matricula`
  ADD CONSTRAINT `id_alumno_rel_2` FOREIGN KEY (`id_alumno`) REFERENCES `alumnos` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
