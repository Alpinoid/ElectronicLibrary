SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `LibraryDB` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `LibraryDB` ;

-- -----------------------------------------------------
-- Table `LibraryDB`.`Publishers`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `LibraryDB`.`Publishers` (
  `PublisherID` INT NOT NULL AUTO_INCREMENT ,
  `PublisherName` VARCHAR(64) NOT NULL ,
  PRIMARY KEY (`PublisherID`) ,
  UNIQUE INDEX `PublishersName_UNIQUE` (`PublisherName` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `LibraryDB`.`Authors`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `LibraryDB`.`Authors` (
  `AuthorID` INT NOT NULL AUTO_INCREMENT ,
  `AuthorName` VARCHAR(64) NOT NULL ,
  PRIMARY KEY (`AuthorID`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `LibraryDB`.`Books`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `LibraryDB`.`Books` (
  `BookID` INT NOT NULL AUTO_INCREMENT ,
  `BookName` VARCHAR(128) NOT NULL ,
  `BookDescription` TEXT NULL ,
  `BookImage` BLOB NULL ,
  `BookPublisher` INT NOT NULL ,
  `BookPublishDate` DATE NULL ,
  PRIMARY KEY (`BookID`) ,
  INDEX `idPublishers_idx` (`BookPublisher` ASC) ,
  CONSTRAINT `idPublishers`
    FOREIGN KEY (`BookPublisher` )
    REFERENCES `LibraryDB`.`Publishers` (`PublisherID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `LibraryDB`.`AuthorsOfBooks`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `LibraryDB`.`AuthorsOfBooks` (
  `AuthorsOfBooksID` INT NOT NULL AUTO_INCREMENT ,
  `AuthorsID` INT NULL ,
  `BooksID` INT NULL ,
  PRIMARY KEY (`AuthorsOfBooksID`) ,
  INDEX `idAuthors_idx` (`AuthorsID` ASC) ,
  INDEX `idBooks_idx` (`BooksID` ASC) ,
  CONSTRAINT `idAuthors`
    FOREIGN KEY (`AuthorsID` )
    REFERENCES `LibraryDB`.`Authors` (`AuthorID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `idBooksForAuthors`
    FOREIGN KEY (`BooksID` )
    REFERENCES `LibraryDB`.`Books` (`BookID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `LibraryDB`.`Tags`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `LibraryDB`.`Tags` (
  `TagID` INT NOT NULL AUTO_INCREMENT ,
  `TagName` VARCHAR(32) NOT NULL ,
  PRIMARY KEY (`TagID`) ,
  UNIQUE INDEX `TagsName_UNIQUE` (`TagName` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `LibraryDB`.`TagsOfBooks`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `LibraryDB`.`TagsOfBooks` (
  `TagsOfBooksID` INT NOT NULL AUTO_INCREMENT ,
  `BooksID` INT NULL ,
  `TagsID` INT NULL ,
  PRIMARY KEY (`TagsOfBooksID`) ,
  INDEX `idBooks_idx` (`BooksID` ASC) ,
  INDEX `idTags_idx` (`TagsID` ASC) ,
  CONSTRAINT `idBooksForTags`
    FOREIGN KEY (`BooksID` )
    REFERENCES `LibraryDB`.`Books` (`BookID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `idTags`
    FOREIGN KEY (`TagsID` )
    REFERENCES `LibraryDB`.`Tags` (`TagID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `LibraryDB`.`FilesOfBook`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `LibraryDB`.`FilesOfBook` (
  `FileID` INT NOT NULL AUTO_INCREMENT ,
  `BookID` INT NOT NULL ,
  `FileName` VARCHAR(256) NOT NULL ,
  `FileType` VARCHAR(16) NOT NULL ,
  `File` BLOB NOT NULL ,
  PRIMARY KEY (`FileID`) ,
  INDEX `idBoos_idx` (`BookID` ASC) ,
  CONSTRAINT `idBooks`
    FOREIGN KEY (`BookID` )
    REFERENCES `LibraryDB`.`Books` (`BookID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `LibraryDB`.`Users`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `LibraryDB`.`Users` (
  `username` VARCHAR(50) NOT NULL ,
  `password` VARCHAR(50) NOT NULL ,
  `enabled` TINYINT(1) NOT NULL ,
  PRIMARY KEY (`username`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `LibraryDB`.`Authorities`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `LibraryDB`.`Authorities` (
  `username` VARCHAR(50) NOT NULL ,
  `authority` VARCHAR(50) NOT NULL ,
  INDEX `fk_authorities_users_idx` (`username` ASC) ,
  CONSTRAINT `fk_authorities_users`
    FOREIGN KEY (`username` )
    REFERENCES `LibraryDB`.`Users` (`username` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

USE `LibraryDB` ;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
