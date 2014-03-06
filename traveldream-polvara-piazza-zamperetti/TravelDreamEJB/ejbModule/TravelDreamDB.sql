
SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

DROP SCHEMA IF EXISTS TravelDreamDB;
CREATE SCHEMA `TravelDreamDB` DEFAULT CHARACTER SET utf8 ;
USE `TravelDreamDB` ;

-- -----------------------------------------------------
-- Table `TravelDreamDB`.`Utente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `TravelDreamDB`.`Utente` (
  `Mail` VARCHAR(255) NOT NULL,
  `Password` VARCHAR(255) NOT NULL,
  `Nome` VARCHAR(255) NULL DEFAULT NULL,
  `Cognome` VARCHAR(255) NULL DEFAULT NULL,
  `NumCartaCredito` MEDIUMTEXT NULL DEFAULT NULL,
  `IndirizzoFatturazione` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`Mail`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `TravelDreamDB`.`PacchettoPredefinito`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `TravelDreamDB`.`PacchettoPredefinito` (
  `Id` INT NOT NULL AUTO_INCREMENT,
  `Luogo` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`Id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `TravelDreamDB`.`PacchettoPersonalizzato`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `TravelDreamDB`.`PacchettoPersonalizzato` (
  `Id` INT NOT NULL AUTO_INCREMENT,
  `Luogo` VARCHAR(255) NOT NULL,
  `MailClienteCarrello` VARCHAR(255) NOT NULL,
  `Acquistato` TINYINT(1) NOT NULL,
  PRIMARY KEY (`Id`),
  INDEX `MailClienteCarrello_idx` (`MailClienteCarrello` ASC),
  CONSTRAINT `FK MailClienteCarrello`
    FOREIGN KEY (`MailClienteCarrello`)
    REFERENCES `TravelDreamDB`.`Utente` (`Mail`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `TravelDreamDB`.`Volo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `TravelDreamDB`.`Volo` (
  `IdVolo` INT NOT NULL AUTO_INCREMENT,
  `LuogoPartenza` VARCHAR(255) NOT NULL,
  `LuogoDestinazione` VARCHAR(255) NOT NULL,
  `Data` DATETIME NOT NULL,
  `Prezzo` FLOAT NOT NULL,
  `Valido` TINYINT(1) NOT NULL,
  PRIMARY KEY (`IdVolo`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `TravelDreamDB`.`Hotel`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `TravelDreamDB`.`Hotel` (
  `IdHotel` INT NOT NULL AUTO_INCREMENT,
  `Nome` VARCHAR(255) NOT NULL,
  `Luogo` VARCHAR(255) NOT NULL,
  `Indirizzo` VARCHAR(255) NOT NULL,
  `Prezzo` FLOAT NOT NULL,
  `Valido` TINYINT(1) NOT NULL,
  PRIMARY KEY (`IdHotel`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `TravelDreamDB`.`Escursione`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `TravelDreamDB`.`Escursione` (
  `IdEscursione` INT NOT NULL AUTO_INCREMENT,
  `Nome` VARCHAR(255) NOT NULL,
  `Luogo` VARCHAR(255) NOT NULL,
  `Durata` TIME NOT NULL,
  `Data` DATETIME NOT NULL,
  `Prezzo` FLOAT NOT NULL,
  `Valido` TINYINT(1) NOT NULL,
  PRIMARY KEY (`IdEscursione`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `TravelDreamDB`.`VoliPers`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `TravelDreamDB`.`VoliPers` (
  `Pacchetto` INT NOT NULL,
  `IdVolo` INT NOT NULL,
  `InGiftList` TINYINT(1) NOT NULL,
  `Acquistato` TINYINT(1) NOT NULL,
  `Regalato` TINYINT(1) NOT NULL,
  PRIMARY KEY (`Pacchetto`, `IdVolo`),
  INDEX `IdVolo_idx` (`IdVolo` ASC),
  CONSTRAINT `FK IdPacchettoVoliPers`
    FOREIGN KEY (`Pacchetto`)
    REFERENCES `TravelDreamDB`.`PacchettoPersonalizzato` (`Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK IdVoloPers`
    FOREIGN KEY (`IdVolo`)
    REFERENCES `TravelDreamDB`.`Volo` (`IdVolo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `TravelDreamDB`.`HotelsPers`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `TravelDreamDB`.`HotelsPers` (
  `Pacchetto` INT NOT NULL,
  `IdHotel` INT NOT NULL,
  `InGiftList` TINYINT(1) NOT NULL,
  `Acquistato` TINYINT(1) NOT NULL,
  `Regalato` TINYINT(1) NOT NULL,
  PRIMARY KEY (`Pacchetto`, `IdHotel`),
  INDEX `IdHotel_idx` (`IdHotel` ASC),
  CONSTRAINT `FK IdPacchettoHotPers`
    FOREIGN KEY (`Pacchetto`)
    REFERENCES `TravelDreamDB`.`PacchettoPersonalizzato` (`Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK IdHotelPers`
    FOREIGN KEY (`IdHotel`)
    REFERENCES `TravelDreamDB`.`Hotel` (`IdHotel`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `TravelDreamDB`.`EscursioniPers`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `TravelDreamDB`.`EscursioniPers` (
  `Pacchetto` INT NOT NULL,
  `IdEscursione` INT NOT NULL,
  `InGiftList` TINYINT(1) NOT NULL,
  `Acquistato` TINYINT(1) NOT NULL,
  `Regalato` TINYINT(1) NOT NULL,
  PRIMARY KEY (`Pacchetto`, `IdEscursione`),
  INDEX `IdEscursione_idx` (`IdEscursione` ASC),
  CONSTRAINT `FK IdPacchettoEscPers`
    FOREIGN KEY (`Pacchetto`)
    REFERENCES `TravelDreamDB`.`PacchettoPersonalizzato` (`Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK IdEscursionePers`
    FOREIGN KEY (`IdEscursione`)
    REFERENCES `TravelDreamDB`.`Escursione` (`IdEscursione`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `TravelDreamDB`.`VoliPred`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `TravelDreamDB`.`VoliPred` (
  `Pacchetto` INT NOT NULL,
  `IdVolo` INT NOT NULL,
  PRIMARY KEY (`Pacchetto`, `IdVolo`),
  INDEX `IdVolo_idx` (`IdVolo` ASC),
  CONSTRAINT `FK IdPacchettoVoliPred`
    FOREIGN KEY (`Pacchetto`)
    REFERENCES `TravelDreamDB`.`PacchettoPredefinito` (`Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK IdVoloPred`
    FOREIGN KEY (`IdVolo`)
    REFERENCES `TravelDreamDB`.`Volo` (`IdVolo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `TravelDreamDB`.`HotelsPred`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `TravelDreamDB`.`HotelsPred` (
  `Pacchetto` INT NOT NULL,
  `IdHotel` INT NOT NULL,
  PRIMARY KEY (`Pacchetto`, `IdHotel`),
  INDEX `IdHotel_idx` (`IdHotel` ASC),
  CONSTRAINT `FK IdPacchettoHotPred`
    FOREIGN KEY (`Pacchetto`)
    REFERENCES `TravelDreamDB`.`PacchettoPredefinito` (`Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK IdHotelPred`
    FOREIGN KEY (`IdHotel`)
    REFERENCES `TravelDreamDB`.`Hotel` (`IdHotel`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `TravelDreamDB`.`EscursioniPred`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `TravelDreamDB`.`EscursioniPred` (
  `Pacchetto` INT NOT NULL,
  `IdEscursione` INT NOT NULL,
  PRIMARY KEY (`Pacchetto`, `IdEscursione`),
  INDEX `IdEscursione_idx` (`IdEscursione` ASC),
  CONSTRAINT `FK IdPacchettoEscPred`
    FOREIGN KEY (`Pacchetto`)
    REFERENCES `TravelDreamDB`.`PacchettoPredefinito` (`Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK IdEscursionePred`
    FOREIGN KEY (`IdEscursione`)
    REFERENCES `TravelDreamDB`.`Escursione` (`IdEscursione`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `TravelDreamDB`.`GruppoUtente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `TravelDreamDB`.`GruppoUtente` (
  `Mail` VARCHAR(255) NOT NULL,
  `Gruppo` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`Mail`, `Gruppo`),
  INDEX `FK nomeGruppo_idx` (`Gruppo` ASC),
  CONSTRAINT `FK emailUtente`
    FOREIGN KEY (`Mail`)
    REFERENCES `TravelDreamDB`.`Utente` (`Mail`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Insertions
-- -----------------------------------------------------

INSERT Utente VALUE ("admin@gmail.com", "8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918", "Ademino", "Ademini", "0", "Via tal dei tali");
INSERT GruppoUtente VALUE ("admin@gmail.com", "IMPIEGATO");
-- password di admin: "admin" 

INSERT Utente VALUE ("user@gmail.com", "04f8996da763b7a969b1028ee3007569eaf3a635486ddab211d512c85b9df8fb", "Usero", "Useri", "1", "Via Tizio");
INSERT GruppoUtente VALUE ("user@gmail.com", "CLIENTE");
-- password di user: "user"

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
