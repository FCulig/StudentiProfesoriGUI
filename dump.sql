CREATE TABLE STUDENT( id LONG NOT NULL GENERATED ALWAYS AS IDENTITY, ime VARCHAR(30) NOT NULL, prezime VARCHAR(30) NOT NULL, jmbag VARCHAR(15) NOT NULL, datum_rodjenja DATE NOT NULL, PRIMARY KEY(id) ); 
 
CREATE TABLE PROFESOR( id LONG NOT NULL GENERATED ALWAYS AS IDENTITY, ime VARCHAR(30) NOT NULL, prezime VARCHAR(30) NOT NULL, sifra VARCHAR(10) NOT NULL, titula VARCHAR(50) NOT NULL, PRIMARY KEY(id) ); 
 
CREATE TABLE PREDMET( id LONG NOT NULL GENERATED ALWAYS AS IDENTITY, sifra VARCHAR(10) NOT NULL, naziv VARCHAR(50) NOT NULL, broj_ects_bodova SMALLINT NOT NULL, profesor_id SMALLINT NOT NULL, PRIMARY KEY(id), FOREIGN KEY(profesor_id) REFERENCES PROFESOR(id) ); 
 
CREATE TABLE PREDMET_STUDENT( predmet_id LONG NOT NULL, student_id LONG NOT NULL, PRIMARY KEY(predmet_id, student_id), FOREIGN KEY(predmet_id) REFERENCES PREDMET(id), FOREIGN KEY(student_id) REFERENCES STUDENT(id) ); 
 
 
CREATE TABLE ISPIT( id LONG NOT NULL GENERATED ALWAYS AS IDENTITY, predmet_id LONG NOT NULL, student_id LONG NOT NULL, ocjena SMALLINT NOT NULL, datum_i_vrijeme TIMESTAMP NOT NULL, PRIMARY KEY(id), FOREIGN KEY(predmet_id) REFERENCES PREDMET(id), FOREIGN KEY(student_id) REFERENCES STUDENT(id) ); 
 
CREATE TABLE OBRAZOVNA_USTANOVA( id LONG NOT NULL GENERATED ALWAYS AS IDENTITY, naziv VARCHAR(50) NOT NULL,PRIMARY KEY(id) ); 
 
CREATE TABLE OBRAZOVNA_USTANOVA_ISPIT( obrazovna_ustanova_id LONG NOT NULL, ispit_id LONG NOT NULL, PRIMARY KEY(obrazovna_ustanova_id, ispit_id), FOREIGN KEY(obrazovna_ustanova_id) REFERENCES OBRAZOVNA_USTANOVA(id), FOREIGN KEY(ispit_id) REFERENCES ISPIT(id) ); 
 
CREATE TABLE OBRAZOVNA_USTANOVA_PROFESOR( obrazovna_ustanova_id LONG NOT NULL, profesor_id LONG NOT NULL, PRIMARY KEY(obrazovna_ustanova_id, profesor_id), FOREIGN KEY(obrazovna_ustanova_id) REFERENCES OBRAZOVNA_USTANOVA(id), FOREIGN KEY(profesor_id) REFERENCES PROFESOR(id) ); 
 
CREATE TABLE OBRAZOVNA_USTANOVA_PREDMET( obrazovna_ustanova_id LONG NOT NULL, predmet_id LONG NOT NULL, PRIMARY KEY(obrazovna_ustanova_id, predmet_id), FOREIGN KEY(obrazovna_ustanova_id) REFERENCES OBRAZOVNA_USTANOVA(id), FOREIGN KEY(predmet_id) REFERENCES PREDMET(id) ); 
 
CREATE TABLE OBRAZOVNA_USTANOVA_STUDENT( obrazovna_ustanova_id LONG NOT NULL, student_id LONG NOT NULL, PRIMARY KEY(obrazovna_ustanova_id, student_id), FOREIGN KEY(obrazovna_ustanova_id) REFERENCES OBRAZOVNA_USTANOVA(id), FOREIGN KEY(student_id) REFERENCES STUDENT(id) ); 
 
INSERT INTO PROFESOR(ime, prezime, sifra, titula) VALUES ('Ivan', 'Kovačević', 'P98765', 'Predavač'); INSERT INTO PROFESOR(ime, prezime, sifra, titula) VALUES ('Marija', 'Horvat', 'P2342', 'Viši predavač'); INSERT INTO PROFESOR(ime, prezime, sifra, titula) VALUES ('Marko', 'Župan', 'P72367', 'Profesor visoke škole'); 
 
INSERT INTO STUDENT(ime, prezime, jmbag, datum_rodjenja) VALUES ('Alen', 'Badalić', '024602348923', '1999-12-01'); INSERT INTO STUDENT(ime, prezime, jmbag, datum_rodjenja) VALUES ('Branko', 'Dalenović', '0036387651', '2000-06-11'); INSERT INTO STUDENT(ime, prezime, jmbag, datum_rodjenja) VALUES ('Josipa', 'Marić', '1108293842', '1999-02-28'); INSERT INTO STUDENT(ime, prezime, jmbag, datum_rodjenja) VALUES ('Ivana', 'Nikić', '298347293', '2001-01-12'); 
 
INSERT INTO PREDMET(sifra, naziv, broj_ects_bodova, profesor_id) VALUES ('PR9283', 'Programiranje u jeziku Java', 6, 1); INSERT INTO PREDMET(sifra, naziv, broj_ects_bodova, profesor_id) VALUES ('PR2138', 'Web aplikacije u Javi', 7, 2); INSERT INTO PREDMET(sifra, naziv, broj_ects_bodova, profesor_id) VALUES ('PR98234', 'Nekonvencionalni računalni postupci', 6, 3)INSERT INTO PREDMET_STUDENT(predmet_id, student_id) VALUES (1, 1); INSERT INTO PREDMET_STUDENT(predmet_id, student_id) VALUES (1, 2); INSERT INTO PREDMET_STUDENT(predmet_id, student_id) VALUES (1, 3); INSERT INTO PREDMET_STUDENT(predmet_id, student_id) VALUES (2, 1); INSERT INTO PREDMET_STUDENT(predmet_id, student_id) VALUES (2, 3); INSERT INTO PREDMET_STUDENT(predmet_id, student_id) VALUES (3, 2); INSERT INTO PREDMET_STUDENT(predmet_id, student_id) VALUES (3, 3); 
 
INSERT INTO ISPIT(predmet_id, student_id, ocjena, datum_i_vrijeme) VALUES (1, 1, 5, parsedatetime('28-11-2018 17:00', 'dd-MM-yyyy hh:mm')); INSERT INTO ISPIT(predmet_id, student_id, ocjena, datum_i_vrijeme) VALUES (1, 2, 4, parsedatetime('02-02-2018 18:00', 'dd-MM-yyyy hh:mm')); INSERT INTO ISPIT(predmet_id, student_id, ocjena, datum_i_vrijeme) VALUES (1, 3, 3, parsedatetime('06-07-2018 15:00', 'dd-MM-yyyy hh:mm')); INSERT INTO ISPIT(predmet_id, student_id, ocjena, datum_i_vrijeme) VALUES (2, 1, 4, parsedatetime('01-09-2018 18:00', 'dd-MM-yyyy hh:mm')); INSERT INTO ISPIT(predmet_id, student_id, ocjena, datum_i_vrijeme) VALUES (2, 3, 5, parsedatetime('11-09-2018 16:00', 'dd-MM-yyyy hh:mm')); INSERT INTO ISPIT(predmet_id, student_id, ocjena, datum_i_vrijeme) VALUES (3, 2, 4, parsedatetime('15-09-2018 17:00', 'dd-MM-yyyy hh:mm')); INSERT INTO ISPIT(predmet_id, student_id, ocjena, datum_i_vrijeme) VALUES (3, 3, 5, parsedatetime('22-09-2018 18:00', 'dd-MM-yyyy hh:mm')); 
 
INSERT INTO OBRAZOVNA_USTANOVA(naziv) VALUES ('Fakultet računarstva'); INSERT INTO OBRAZOVNA_USTANOVA(naziv) VALUES ('Tehničko veleučilište'); 