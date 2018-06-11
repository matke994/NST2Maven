/*
SQLyog Community
MySQL - 5.7.14 : Database - veleprodaja
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
/*Table structure for table `narudzbenica` */

CREATE TABLE `narudzbenica` (
  `sifraNarudzbenice` int(11) NOT NULL AUTO_INCREMENT,
  `ukupnaVrednost` double DEFAULT NULL,
  `datum` date DEFAULT NULL,
  `radnik` int(11) DEFAULT NULL,
  `pravnoLice` int(9) DEFAULT NULL,
  PRIMARY KEY (`sifraNarudzbenice`),
  KEY `radnik_fk1` (`radnik`),
  KEY `pravnoLice_fk` (`pravnoLice`),
  CONSTRAINT `pravnoLice_fk` FOREIGN KEY (`pravnoLice`) REFERENCES `pravnolice` (`pib`),
  CONSTRAINT `radnik_fk1` FOREIGN KEY (`radnik`) REFERENCES `radnik` (`radnikID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf16 COLLATE=utf16_unicode_ci;

/*Data for the table `narudzbenica` */

insert  into `narudzbenica`(`sifraNarudzbenice`,`ukupnaVrednost`,`datum`,`radnik`,`pravnoLice`) values 
(1,68640,'2017-09-27',1,123456789),
(3,31800,NULL,NULL,NULL);

/*Table structure for table `pravnolice` */

CREATE TABLE `pravnolice` (
  `pib` int(9) NOT NULL,
  `nazivPravnogLica` varchar(255) COLLATE utf16_unicode_ci DEFAULT NULL,
  `maticniBroj` varchar(255) COLLATE utf16_unicode_ci DEFAULT NULL,
  `adresa` varchar(255) COLLATE utf16_unicode_ci DEFAULT NULL,
  `kontakt` varchar(255) COLLATE utf16_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`pib`)
) ENGINE=InnoDB DEFAULT CHARSET=utf16 COLLATE=utf16_unicode_ci;

/*Data for the table `pravnolice` */

insert  into `pravnolice`(`pib`,`nazivPravnogLica`,`maticniBroj`,`adresa`,`kontakt`) values 
(123456789,'Motor doo','12345678','Kralja Petra 6, Jagodina','035656123'),
(542126562,'ABC','45521248','Mije Kovacevica 7, Beograd','011326542'),
(987654321,'Nemac doo','87654321','Cara Dusana 4, Jagodina','035212362');

/*Table structure for table `proizvod` */

CREATE TABLE `proizvod` (
  `proizvodID` int(11) NOT NULL AUTO_INCREMENT,
  `naziv` varchar(255) COLLATE utf16_unicode_ci DEFAULT NULL,
  `stanje` varchar(255) COLLATE utf16_unicode_ci DEFAULT NULL,
  `cena` double DEFAULT NULL,
  `stopaPDV` int(11) DEFAULT NULL,
  `opis` varchar(255) COLLATE utf16_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`proizvodID`),
  KEY `stopaPDV_fk` (`stopaPDV`),
  CONSTRAINT `stopaPDV_fk` FOREIGN KEY (`stopaPDV`) REFERENCES `stopapdv` (`sifraStope`)
) ENGINE=InnoDB AUTO_INCREMENT=94 DEFAULT CHARSET=utf16 COLLATE=utf16_unicode_ci;

/*Data for the table `proizvod` */

insert  into `proizvod`(`proizvodID`,`naziv`,`stanje`,`cena`,`stopaPDV`,`opis`) values 
(8,'auto sijalica','100',1500,2,'sijalica za audi a4'),
(9,'prednja vrata','10',25000,2,'prednja vrata za vw golf 5'),
(10,'zadnja guma','30',7500,2,'zadnja guma dimenzija 195x65x15'),
(11,'stop svetlo','50',3250,2,'stop svetlo vw golf 4'),
(12,'far','20',2400,2,'auto far prednji audi a3'),
(91,'alu felna','16',4500,2,'alu felna 15\"'),
(93,'auspuh','7',4500,2,'zadnja grana audi a4');

/*Table structure for table `racun` */

CREATE TABLE `racun` (
  `brojRacuna` int(11) NOT NULL,
  `ukupnaVrednost` double DEFAULT NULL,
  `datum` date DEFAULT NULL,
  `radnik` int(11) DEFAULT NULL,
  PRIMARY KEY (`brojRacuna`),
  KEY `radnik_fk` (`radnik`),
  CONSTRAINT `radnik_fk` FOREIGN KEY (`radnik`) REFERENCES `radnik` (`radnikID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf16 COLLATE=utf16_unicode_ci;

/*Data for the table `racun` */

insert  into `racun`(`brojRacuna`,`ukupnaVrednost`,`datum`,`radnik`) values 
(1,12500,'2017-08-29',1),
(2,7500,'2017-09-06',2),
(3,68640,'2017-09-27',1);

/*Table structure for table `racunnarudzbenica` */

CREATE TABLE `racunnarudzbenica` (
  `brojRacuna` int(11) NOT NULL,
  `iznos` double DEFAULT NULL,
  `datum` date DEFAULT NULL,
  PRIMARY KEY (`brojRacuna`),
  CONSTRAINT `brojRacuna_fk` FOREIGN KEY (`brojRacuna`) REFERENCES `racun` (`brojRacuna`)
) ENGINE=InnoDB DEFAULT CHARSET=utf16 COLLATE=utf16_unicode_ci;

/*Data for the table `racunnarudzbenica` */

/*Table structure for table `radnik` */

CREATE TABLE `radnik` (
  `radnikID` int(11) NOT NULL,
  `ime` varchar(255) COLLATE utf16_unicode_ci DEFAULT NULL,
  `prezime` varchar(255) COLLATE utf16_unicode_ci DEFAULT NULL,
  `korisnickoIme` varchar(255) COLLATE utf16_unicode_ci DEFAULT NULL,
  `lozinka` varchar(255) COLLATE utf16_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`radnikID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf16 COLLATE=utf16_unicode_ci;

/*Data for the table `radnik` */

insert  into `radnik`(`radnikID`,`ime`,`prezime`,`korisnickoIme`,`lozinka`) values 
(1,'Nikola','Matic','matke994@gmail.com','matke'),
(2,'Marko','Matic','mare','mare');

/*Table structure for table `stavkanarudzbenice` */

CREATE TABLE `stavkanarudzbenice` (
  `sifraStavke` int(11) NOT NULL,
  `sifraNarudzbenice` int(11) DEFAULT NULL,
  `kolicina` int(11) DEFAULT NULL,
  `jm` varchar(255) COLLATE utf16_unicode_ci DEFAULT NULL,
  `cena` double DEFAULT NULL,
  `stopaPDV` int(11) DEFAULT NULL,
  `vrednost` double DEFAULT NULL,
  `proizvod` int(11) DEFAULT NULL,
  PRIMARY KEY (`sifraStavke`),
  KEY `proizvod_fk1` (`proizvod`),
  KEY `sifranarudzbenice_fk` (`sifraNarudzbenice`),
  CONSTRAINT `proizvod_fk1` FOREIGN KEY (`proizvod`) REFERENCES `proizvod` (`proizvodID`),
  CONSTRAINT `sifranarudzbenice_fk` FOREIGN KEY (`sifraNarudzbenice`) REFERENCES `narudzbenica` (`sifraNarudzbenice`)
) ENGINE=InnoDB DEFAULT CHARSET=utf16 COLLATE=utf16_unicode_ci;

/*Data for the table `stavkanarudzbenice` */

insert  into `stavkanarudzbenice`(`sifraStavke`,`sifraNarudzbenice`,`kolicina`,`jm`,`cena`,`stopaPDV`,`vrednost`,`proizvod`) values 
(1,1,2,'kom',25000,20,60000,9),
(2,1,3,'kom',2400,20,8640,12);

/*Table structure for table `stavkaracuna` */

CREATE TABLE `stavkaracuna` (
  `sifraStavke` int(11) NOT NULL,
  `sifraRacuna` int(11) DEFAULT NULL,
  `kolicina` int(11) DEFAULT NULL,
  `jm` varchar(255) COLLATE utf16_unicode_ci DEFAULT NULL,
  `cena` double DEFAULT NULL,
  `stopaPDV` int(11) DEFAULT NULL,
  `vrednostBezPDV` double DEFAULT NULL,
  `vrednostSaPDV` double DEFAULT NULL,
  `proizvod` int(11) DEFAULT NULL,
  PRIMARY KEY (`sifraStavke`),
  KEY `proizvod_fk` (`proizvod`),
  KEY `racun` (`sifraRacuna`),
  CONSTRAINT `proizvod_fk` FOREIGN KEY (`proizvod`) REFERENCES `proizvod` (`proizvodID`),
  CONSTRAINT `racun` FOREIGN KEY (`sifraRacuna`) REFERENCES `racun` (`brojRacuna`)
) ENGINE=InnoDB DEFAULT CHARSET=utf16 COLLATE=utf16_unicode_ci;

/*Data for the table `stavkaracuna` */

/*Table structure for table `stopapdv` */

CREATE TABLE `stopapdv` (
  `sifraStope` int(11) NOT NULL AUTO_INCREMENT,
  `iznos` int(11) DEFAULT NULL,
  `oznaka` varchar(255) COLLATE utf16_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`sifraStope`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf16 COLLATE=utf16_unicode_ci;

/*Data for the table `stopapdv` */

insert  into `stopapdv`(`sifraStope`,`iznos`,`oznaka`) values 
(1,0,'G'),
(2,20,'Đ'),
(3,18,'E');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
