-- 2 - Creacion del Esquema del Grupo--
CREATE SCHEMA TP_DUAL;

-- 3 - Creacion de Tablas, PK y FK --

-- Creacion de la Tabla BandejaNotificaciones --
CREATE TABLE TP_DUAL.bandejaNotificaciones (
	id					BIGINT PRIMARY KEY
);

-- Creacion de la Tabla Maestros --
CREATE TABLE TP_DUAL.maestros (
	id							BIGINT PRIMARY KEY,
	nombre						VARCHAR(255),
	bandejaDeNotificaciones		BIGINT,
	maestro						BIGINT,
	CONSTRAINT fk_bandejaDeNotificaciones_maestro FOREIGN KEY (bandejaDeNotificaciones) REFERENCES TP_DUAL.bandejaNotificaciones(id),
	CONSTRAINT fk_maestro_superior FOREIGN KEY (maestro) REFERENCES TP_DUAL.maestros(id)
);

-- Creacion de la Tabla Ayudantes --
CREATE TABLE TP_DUAL.ayudantes (
	id							BIGINT PRIMARY KEY,
	nombre						VARCHAR(255),
	bandejaDeNotificaciones		BIGINT,
	maestro						BIGINT,
	CONSTRAINT fk_bandejaDeNotificaciones_ayudante FOREIGN KEY (bandejaDeNotificaciones) REFERENCES TP_DUAL.bandejaNotificaciones(id),
	CONSTRAINT fk_maestro_ayudante FOREIGN KEY (maestro) REFERENCES TP_DUAL.maestros(id)
);

-- Creacion de la Tabla Establecimientos --
CREATE TABLE TP_DUAL.establecimientos (
	id					BIGINT PRIMARY KEY,
	nombre				VARCHAR(255),
	provincia			VARCHAR(255),
	municipio			VARCHAR(255),
	localidad			VARCHAR(255),
	direccion			VARCHAR(255),
	telefono			BIGINT,
	email				BIGINT
);

-- Creacion de la Tabla Cursos --
CREATE TABLE TP_DUAL.cursos (
	id					BIGINT PRIMARY KEY,
	grado				VARCHAR(255),
	division			VARCHAR(255),
	ayudante			BIGINT,
	maestro				BIGINT,
	establecimiento		BIGINT,
	egresados			BOOLEAN,
	CONSTRAINT fk_ayudante FOREIGN KEY (ayudante) REFERENCES TP_DUAL.ayudantes(id),
	CONSTRAINT fk_maestro FOREIGN KEY (maestro) REFERENCES TP_DUAL.maestros(id),
	CONSTRAINT fk_establecimiento FOREIGN KEY (establecimiento) REFERENCES TP_DUAL.establecimientos(id)
);

-- Creacion de la Tabla Alumnos --
CREATE TABLE TP_DUAL.alumnos (
	id					BIGINT PRIMARY KEY,
	nombre				VARCHAR(255),
	apellido			VARCHAR(255),
	dni					VARCHAR(255),
	nacimiento			DATE,
	provincia			VARCHAR(255),
	municipio			VARCHAR(255),
	localidad			VARCHAR(255),
	direccion			VARCHAR(255),
	estado				VARCHAR(255),
	curso				BIGINT,
	CONSTRAINT fk_curso FOREIGN KEY (curso) REFERENCES TP_DUAL.cursos(id)
);

-- Creacion de la Tabla Notificaciones --
CREATE TABLE TP_DUAL.notificaciones (
	id							BIGINT PRIMARY KEY,
	titulo						VARCHAR(255),
	cuerpo						VARCHAR(255),
	bandejaDeNotificaciones		BIGINT,	
	CONSTRAINT fk_bandejaDeNotificaciones FOREIGN KEY (bandejaDeNotificaciones) REFERENCES TP_DUAL.bandejaNotificaciones(id)
);