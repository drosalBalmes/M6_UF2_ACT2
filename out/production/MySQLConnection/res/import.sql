# -------------------------------------------------------------------------------------------
-- # Creació base de dades i taules
CREATE DATABASE IF NOT EXISTS Cinema;
CREATE TABLE IF NOT EXISTS Director(
    idDirector INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(255) NOT NULL,
    any_naixement INT NOT NULL,
    pais VARCHAR(50) NOT NULL
);
CREATE TABLE IF NOT EXISTS Films(
    idFilm      INT AUTO_INCREMENT PRIMARY KEY,
    titol       VARCHAR(125) NOT NULL ,
    DataEstrena DATE         NOT NULL ,
    pais        VARCHAR(50)  NOT NULL ,
    idDirector  INT,
    CONSTRAINT FK_directorFilm
        FOREIGN KEY (idDirector) REFERENCES Director (idDirector)
);
# -------------------------------------------------------------------------------------------
-- # Inserir Directors
INSERT INTO Director(nom, any_naixement, pais) VALUES ('Steven Spielberg',1946,'Estats Units');
INSERT INTO Director(nom, any_naixement, pais) VALUES ('George Lucas',1944,'Estats Units');
INSERT INTO Director(nom, any_naixement, pais) VALUES ('Gareth Edwards',1975,'Regne Unit');
# ---------------------------------------------------------------------------------------------
-- # Inserir Pelicules
-- # yymmdd
INSERT INTO Films(titol, DataEstrena, pais, idDirector) VALUES ('E.T. the Extra-Terrestrial','1982-6-11','Estats Units',1);
INSERT INTO Films(titol, DataEstrena, pais, idDirector) VALUES ('Jurassic Park','1993-6-9','Estats Units',1);
INSERT INTO Films(titol, DataEstrena, pais, idDirector) VALUES ('Star Wars: Episode IV - A New Hope','1977-5-25','Estats Units',2);
INSERT INTO Films(titol, DataEstrena, pais, idDirector) VALUES ('Raiders of the Lost Ark','1981-6-12','Estats Units',2);
INSERT INTO Films(titol, DataEstrena, pais, idDirector) VALUES ('Godzilla','2014-5-16','Estats Units',3);
INSERT INTO Films(titol, DataEstrena, pais, idDirector) VALUES ('Rogue One: A Star Wars Story','2016-12-15','Estats Units',3);

-- #4
-- #Si, no es necesari posar not null