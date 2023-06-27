-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`usuario` (
  `id` VARCHAR(15) NOT NULL,
  `nombre_usuario` VARCHAR(15) NULL,
  `correo` VARCHAR(30) NULL,
  `nombre` VARCHAR(20) NULL,
  `pais` VARCHAR(20) NULL,
  `contraseña` VARCHAR(15) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Perfil`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Perfil` (
  `id` VARCHAR(15) NOT NULL,
  `descripcion` VARCHAR(150) NULL,
  `tipo_usuario` VARCHAR(20) NULL,
  `usuario_id` VARCHAR(15) NOT NULL,
  PRIMARY KEY (`id`, `usuario_id`),
  INDEX `fk_Perfil_usuario1_idx` (`usuario_id` ASC) VISIBLE,
  CONSTRAINT `fk_Perfil_usuario1`
    FOREIGN KEY (`usuario_id`)
    REFERENCES `mydb`.`usuario` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Privilegio`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Privilegio` (
  `id` VARCHAR(15) NOT NULL,
  `nombre` VARCHAR(20) NULL,
  `descripcion` VARCHAR(150) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Consultas`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Consultas` (
  `id` INT NOT NULL,
  `fecha` DATE NULL,
  `hora` VARCHAR(10) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Receta`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Receta` (
  `id` VARCHAR(15) NOT NULL,
  `nombre` VARCHAR(50) NULL,
  `descripcion` VARCHAR(150) NULL,
  `tiempo_coccion` VARCHAR(20) NULL,
  `tiempo_total` VARCHAR(20) NULL,
  `tiempo_preparacion` VARCHAR(20) NULL,
  `instrucciones` VARCHAR(400) NULL,
  `imagen_receta` JSON NULL,
  `porciones` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Destacada`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Destacada` (
  `id` VARCHAR(15) NOT NULL,
  `fecha_inicio` DATE NULL,
  `fecha_final` DATE NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`ocasion`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`ocasion` (
  `id` INT NOT NULL,
  `situacion` VARCHAR(20) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`complejidad`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`complejidad` (
  `id` INT NOT NULL,
  `nivel` VARCHAR(10) NULL,
  `Receta_id` VARCHAR(15) NOT NULL,
  PRIMARY KEY (`id`, `Receta_id`),
  INDEX `fk_complejidad_Receta1_idx` (`Receta_id` ASC) VISIBLE,
  CONSTRAINT `fk_complejidad_Receta1`
    FOREIGN KEY (`Receta_id`)
    REFERENCES `mydb`.`Receta` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`categoria`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`categoria` (
  `id` INT NOT NULL,
  `nombre` VARCHAR(30) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`ingredientes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`ingredientes` (
  `id` INT NOT NULL,
  `nombre` VARCHAR(50) NULL,
  `descripcion` VARCHAR(50) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`medidas`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`medidas` (
  `id` INT NOT NULL,
  `nombre` VARCHAR(15) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Privilegio_has_Perfil`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Privilegio_has_Perfil` (
  `Privilegio_id` VARCHAR(15) NOT NULL,
  `Perfil_id` VARCHAR(15) NOT NULL,
  PRIMARY KEY (`Privilegio_id`, `Perfil_id`),
  INDEX `fk_Privilegio_has_Perfil_Perfil1_idx` (`Perfil_id` ASC) VISIBLE,
  INDEX `fk_Privilegio_has_Perfil_Privilegio_idx` (`Privilegio_id` ASC) VISIBLE,
  CONSTRAINT `fk_Privilegio_has_Perfil_Privilegio`
    FOREIGN KEY (`Privilegio_id`)
    REFERENCES `mydb`.`Privilegio` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Privilegio_has_Perfil_Perfil1`
    FOREIGN KEY (`Perfil_id`)
    REFERENCES `mydb`.`Perfil` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`usuario_has_Consultas`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`usuario_has_Consultas` (
  `usuario_id` VARCHAR(15) NOT NULL,
  `Consultas_id` INT NOT NULL,
  PRIMARY KEY (`usuario_id`, `Consultas_id`),
  INDEX `fk_usuario_has_Consultas_Consultas1_idx` (`Consultas_id` ASC) VISIBLE,
  INDEX `fk_usuario_has_Consultas_usuario1_idx` (`usuario_id` ASC) VISIBLE,
  CONSTRAINT `fk_usuario_has_Consultas_usuario1`
    FOREIGN KEY (`usuario_id`)
    REFERENCES `mydb`.`usuario` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_usuario_has_Consultas_Consultas1`
    FOREIGN KEY (`Consultas_id`)
    REFERENCES `mydb`.`Consultas` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Consultas_has_Receta`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Consultas_has_Receta` (
  `Consultas_id` INT NOT NULL,
  `Receta_id` VARCHAR(15) NOT NULL,
  PRIMARY KEY (`Consultas_id`, `Receta_id`),
  INDEX `fk_Consultas_has_Receta_Receta1_idx` (`Receta_id` ASC) VISIBLE,
  INDEX `fk_Consultas_has_Receta_Consultas1_idx` (`Consultas_id` ASC) VISIBLE,
  CONSTRAINT `fk_Consultas_has_Receta_Consultas1`
    FOREIGN KEY (`Consultas_id`)
    REFERENCES `mydb`.`Consultas` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Consultas_has_Receta_Receta1`
    FOREIGN KEY (`Receta_id`)
    REFERENCES `mydb`.`Receta` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Receta_has_Destacada`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Receta_has_Destacada` (
  `Receta_id` VARCHAR(15) NOT NULL,
  `Destacada_id` VARCHAR(15) NOT NULL,
  PRIMARY KEY (`Receta_id`, `Destacada_id`),
  INDEX `fk_Receta_has_Destacada_Destacada1_idx` (`Destacada_id` ASC) VISIBLE,
  INDEX `fk_Receta_has_Destacada_Receta1_idx` (`Receta_id` ASC) VISIBLE,
  CONSTRAINT `fk_Receta_has_Destacada_Receta1`
    FOREIGN KEY (`Receta_id`)
    REFERENCES `mydb`.`Receta` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Receta_has_Destacada_Destacada1`
    FOREIGN KEY (`Destacada_id`)
    REFERENCES `mydb`.`Destacada` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`ocasion_has_Receta`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`ocasion_has_Receta` (
  `ocasion_id` INT NOT NULL,
  `Receta_id` VARCHAR(15) NOT NULL,
  PRIMARY KEY (`ocasion_id`, `Receta_id`),
  INDEX `fk_ocasion_has_Receta_Receta1_idx` (`Receta_id` ASC) VISIBLE,
  INDEX `fk_ocasion_has_Receta_ocasion1_idx` (`ocasion_id` ASC) VISIBLE,
  CONSTRAINT `fk_ocasion_has_Receta_ocasion1`
    FOREIGN KEY (`ocasion_id`)
    REFERENCES `mydb`.`ocasion` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ocasion_has_Receta_Receta1`
    FOREIGN KEY (`Receta_id`)
    REFERENCES `mydb`.`Receta` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`categoria_has_Receta`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`categoria_has_Receta` (
  `categoria_id` INT NOT NULL,
  `Receta_id` VARCHAR(15) NOT NULL,
  PRIMARY KEY (`categoria_id`, `Receta_id`),
  INDEX `fk_categoria_has_Receta_Receta1_idx` (`Receta_id` ASC) VISIBLE,
  INDEX `fk_categoria_has_Receta_categoria1_idx` (`categoria_id` ASC) VISIBLE,
  CONSTRAINT `fk_categoria_has_Receta_categoria1`
    FOREIGN KEY (`categoria_id`)
    REFERENCES `mydb`.`categoria` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_categoria_has_Receta_Receta1`
    FOREIGN KEY (`Receta_id`)
    REFERENCES `mydb`.`Receta` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`usuario_has_Receta`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`usuario_has_Receta` (
  `usuario_id` VARCHAR(15) NOT NULL,
  `Receta_id` VARCHAR(15) NOT NULL,
  `fecha_voto` DATE NULL,
  PRIMARY KEY (`usuario_id`, `Receta_id`),
  INDEX `fk_usuario_has_Receta_Receta1_idx` (`Receta_id` ASC) VISIBLE,
  INDEX `fk_usuario_has_Receta_usuario1_idx` (`usuario_id` ASC) VISIBLE,
  CONSTRAINT `fk_usuario_has_Receta_usuario1`
    FOREIGN KEY (`usuario_id`)
    REFERENCES `mydb`.`usuario` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_usuario_has_Receta_Receta1`
    FOREIGN KEY (`Receta_id`)
    REFERENCES `mydb`.`Receta` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Receta_has_Receta`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Receta_has_Receta` (
  `Receta_id` VARCHAR(15) NOT NULL,
  `Receta_id1` VARCHAR(15) NOT NULL,
  PRIMARY KEY (`Receta_id`, `Receta_id1`),
  INDEX `fk_Receta_has_Receta_Receta2_idx` (`Receta_id1` ASC) VISIBLE,
  INDEX `fk_Receta_has_Receta_Receta1_idx` (`Receta_id` ASC) VISIBLE,
  CONSTRAINT `fk_Receta_has_Receta_Receta1`
    FOREIGN KEY (`Receta_id`)
    REFERENCES `mydb`.`Receta` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Receta_has_Receta_Receta2`
    FOREIGN KEY (`Receta_id1`)
    REFERENCES `mydb`.`Receta` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Receta_has_medidas`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Receta_has_medidas` (
  `Receta_id` VARCHAR(15) NOT NULL,
  `medidas_id` INT NOT NULL,
  `ingredientes_id` INT NOT NULL,
  PRIMARY KEY (`Receta_id`, `medidas_id`, `ingredientes_id`),
  INDEX `fk_Receta_has_medidas_medidas1_idx` (`medidas_id` ASC) VISIBLE,
  INDEX `fk_Receta_has_medidas_Receta1_idx` (`Receta_id` ASC) VISIBLE,
  INDEX `fk_Receta_has_medidas_ingredientes1_idx` (`ingredientes_id` ASC) VISIBLE,
  CONSTRAINT `fk_Receta_has_medidas_Receta1`
    FOREIGN KEY (`Receta_id`)
    REFERENCES `mydb`.`Receta` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Receta_has_medidas_medidas1`
    FOREIGN KEY (`medidas_id`)
    REFERENCES `mydb`.`medidas` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Receta_has_medidas_ingredientes1`
    FOREIGN KEY (`ingredientes_id`)
    REFERENCES `mydb`.`ingredientes` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
