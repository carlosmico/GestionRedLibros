-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 14-05-2019 a las 20:48:06
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
  `idPadre` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
  `id` varchar(100) NOT NULL,
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

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `historial`
--

CREATE TABLE `historial` (
  `id` int(11) NOT NULL,
  `id_ejemplar` varchar(100) DEFAULT NULL,
  `id_alumno` varchar(10) NOT NULL,
  `curso_escolar` int(11) NOT NULL,
  `estado_inicial` int(11) NOT NULL,
  `estado_final` int(11) DEFAULT NULL,
  `fecha_inicial` datetime NOT NULL,
  `fecha_final` datetime DEFAULT NULL,
  `observaciones` varchar(500) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `libros`
--

CREATE TABLE `libros` (
  `id` varchar(50) NOT NULL,
  `codigo` varchar(50) NOT NULL,
  `id_contenido` int(11) NOT NULL,
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
  `fec_ini_acis` datetime NOT NULL,
  `fec_fin_acis` datetime NOT NULL,
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
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_inventario_rel` (`id_ejemplar`),
  ADD KEY `id_alumno_rel` (`id_alumno`);

--
-- Indices de la tabla `libros`
--
ALTER TABLE `libros`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_asignatura_rel` (`id_contenido`);

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
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `cursos-grupos`
--
ALTER TABLE `cursos-grupos`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `historial`
--
ALTER TABLE `historial`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `matricula`
--
ALTER TABLE `matricula`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

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
  ADD CONSTRAINT `id_ejemplar_rel` FOREIGN KEY (`id_ejemplar`) REFERENCES `ejemplares` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `libros`
--
ALTER TABLE `libros`
  ADD CONSTRAINT `id_contenido_r` FOREIGN KEY (`id_contenido`) REFERENCES `contenido` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

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
