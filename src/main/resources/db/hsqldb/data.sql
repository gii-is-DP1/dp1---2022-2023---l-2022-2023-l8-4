-- One admin user, named admin1 with passwor 4dm1n and authority admin
INSERT INTO users(username,password,enabled) VALUES ('admin1','4dm1n',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (1,'admin1','admin');
-- One owner user, named owner1 with passwor 0wn3r
INSERT INTO users(username,password,enabled) VALUES ('owner1','0wn3r',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (2,'owner1','owner');
-- One vet user, named vet1 with passwor v3t
INSERT INTO users(username,password,enabled) VALUES ('vet1','v3t',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (3,'vet1','veterinarian');


INSERT INTO users(username,password,enabled) VALUES ('pgmarc','p4gmarc', TRUE);
INSERT INTO authorities(id,username,authority) VALUES (4,'pgmarc','admin');

INSERT INTO users(username,password,enabled) VALUES ('carbersor','123', TRUE);
INSERT INTO authorities(id,username,authority) VALUES (5,'carbersor','admin');

INSERT INTO users(username,password,enabled) VALUES ('fermatgom','admin1234', TRUE);
INSERT INTO authorities(id,username,authority) VALUES (6,'fermatgom','admin');

INSERT INTO users(username,password,enabled) VALUES ('pedlopruz','pedlopruz2002', TRUE);
INSERT INTO authorities(id,username,authority) VALUES (7,'pedlopruz','admin');

INSERT INTO users(username,password,enabled) VALUES ('carzarrei','chocolate', TRUE);
INSERT INTO authorities(id,username,authority) VALUES (8,'carzarrei','admin');


INSERT INTO vets(id, first_name,last_name) VALUES (1, 'James', 'Carter');
INSERT INTO vets(id, first_name,last_name) VALUES (2, 'Helen', 'Leary');
INSERT INTO vets(id, first_name,last_name) VALUES (3, 'Linda', 'Douglas');
INSERT INTO vets(id, first_name,last_name) VALUES (4, 'Rafael', 'Ortega');
INSERT INTO vets(id, first_name,last_name) VALUES (5, 'Henry', 'Stevens');
INSERT INTO vets(id, first_name,last_name) VALUES (6, 'Sharon', 'Jenkins');

INSERT INTO specialties VALUES (1, 'radiology');
INSERT INTO specialties VALUES (2, 'surgery');
INSERT INTO specialties VALUES (3, 'dentistry');

INSERT INTO vet_specialties VALUES (2, 1);
INSERT INTO vet_specialties VALUES (3, 2);
INSERT INTO vet_specialties VALUES (3, 3);
INSERT INTO vet_specialties VALUES (4, 2);
INSERT INTO vet_specialties VALUES (5, 1);

INSERT INTO types VALUES (1, 'cat');
INSERT INTO types VALUES (2, 'dog');
INSERT INTO types VALUES (3, 'lizard');
INSERT INTO types VALUES (4, 'snake');
INSERT INTO types VALUES (5, 'bird');
INSERT INTO types VALUES (6, 'hamster');
INSERT INTO types VALUES (7, 'turtle');

INSERT INTO owners VALUES (1, 'George', 'Franklin', '110 W. Liberty St.', 'Madison', '6085551023', 'owner1');
INSERT INTO owners VALUES (2, 'Betty', 'Davis', '638 Cardinal Ave.', 'Sun Prairie', '6085551749', 'owner1');
INSERT INTO owners VALUES (3, 'Eduardo', 'Rodriquez', '2693 Commerce St.', 'McFarland', '6085558763', 'owner1');
INSERT INTO owners VALUES (4, 'Harold', 'Davis', '563 Friendly St.', 'Windsor', '6085553198', 'owner1');
INSERT INTO owners VALUES (5, 'Peter', 'McTavish', '2387 S. Fair Way', 'Madison', '6085552765', 'owner1');
INSERT INTO owners VALUES (6, 'Jean', 'Coleman', '105 N. Lake St.', 'Monona', '6085552654', 'owner1');
INSERT INTO owners VALUES (7, 'Jeff', 'Black', '1450 Oak Blvd.', 'Monona', '6085555387', 'owner1');
INSERT INTO owners VALUES (8, 'Maria', 'Escobito', '345 Maple St.', 'Madison', '6085557683', 'owner1');
INSERT INTO owners VALUES (9, 'David', 'Schroeder', '2749 Blackhawk Trail', 'Madison', '6085559435', 'owner1');
INSERT INTO owners VALUES (10, 'Carlos', 'Estaban', '2335 Independence La.', 'Waunakee', '6085555487', 'owner1');

INSERT INTO owners VALUES (11, 'Pedro', 'Gonzalez', 'C. Gomez de la Lama', 'Camas', '684000708', 'pgmarc');
INSERT INTO owners VALUES (12, 'Carlos', 'Bermejo', 'C. Juan de Mariana', 'Sevilla', '638026321', 'carbersor');
INSERT INTO owners VALUES (13, 'Fernando José', 'Mateos Gómez', 'C. España', 'Sevilla', '618587795', 'fermatgom');
INSERT INTO owners VALUES (14, 'Pedro', 'Lopez Ruz', 'C. Las Prietas', 'Montilla', '693776919', 'pedlopruz');
INSERT INTO owners VALUES (15, 'Carlos', 'Zarzuela Reina', 'C. Condesa', 'Sevilla', '601060413', 'carzarrei');


INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (1, 'Leo', '2010-09-07', 1, 1);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (2, 'Basil', '2012-08-06', 6, 2);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (3, 'Rosy', '2011-04-17', 2, 3);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (4, 'Jewel', '2010-03-07', 2, 3);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (5, 'Iggy', '2010-11-30', 3, 4);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (6, 'George', '2010-01-20', 4, 5);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (7, 'Samantha', '2012-09-04', 1, 6);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (8, 'Max', '2012-09-04', 1, 6);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (9, 'Lucky', '2011-08-06', 5, 7);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (10, 'Mulligan', '2007-02-24', 2, 8);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (11, 'Freddy', '2010-03-09', 5, 9);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (12, 'Lucky', '2010-06-24', 2, 10);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (13, 'Sly', '2012-06-08', 1, 10);


INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (14, 'Pepita', '2011-01-07', 7, 11);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (15, 'Canepi', '2014-01-30', 2, 12);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (16, 'Simeon', '2011-02-27', 2, 13);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (17, 'Rockie', '2017-02-12', 2, 5);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (18, 'Ted', '2012-06-10', 1, 15);


INSERT INTO visits(id,pet_id,visit_date,description) VALUES (1, 7, '2022-01-01', 'rabies shot');
INSERT INTO visits(id,pet_id,visit_date,description) VALUES (2, 8, '2022-01-02', 'rabies shot');
INSERT INTO visits(id,pet_id,visit_date,description) VALUES (3, 8, '2022-01-03', 'neutered');
INSERT INTO visits(id,pet_id,visit_date,description) VALUES (4, 7, '2022-01-04', 'spayed');


--Players
INSERT INTO players(id,username,register_date,modification_date,last_login,email,birth_date,profile_picture) VALUES 
(1,'pgmarc', '2022-01-01','2022-01-01', '2022-10-01', 'fausto@gmail.com',  '2001-01-01', ''),
(2,'carbersor',  '2022-02-02','2022-02-03', '2022-10-01', 'rukisro@gmail.com',  '1992-01-01', ''),
(3,'fermatgom',   '2022-02-02','2022-02-03', '2022-10-01','sergio@gmail.com',  '1975-01-01', ''),
(4,'pedlopruz',   '2022-02-02','2022-02-02', '2022-10-01',  'rocioalberca@gmail.com',  '2002-01-01', ''),
(5,'carzarrei',  '2022-04-01','2022-01-01', '2022-10-01',  'vic@gmail.com',  '2000-01-01', '');

--Games

INSERT INTO games(id,date,game_mode, game_code) VALUES 

(1, '2022-11-04', 0, 10),
(2, '2022-11-04', 0, 11),
(3, '2022-11-06', 1, 12),
(4, '2022-11-09', 0, 13),
(5, '2022-11-10', 2, 14);

INSERT INTO players_games(game_id,player_id) VALUES 
(1,1),
(1,2),
(2,2),
(2,3),
(3,3),
(3,2),
(3,1),
(3,5),
(4,4),
(4,2),
(5,5),
(5,3),
(5,1);

-- Creating deck of cards
INSERT INTO cards (id, icons) VALUES (0, 'AVISPA CEBRA ABEJA CIGUEÑA ZORRO LOBO BUHO CANGURO'),
(1, 'CANGURO ORCA PEREZOSO TORTUGA CARACOL GALLINA CANGREJO PINGUINO'),
(2, 'MARIQUITA RENO CARACOL COCODRILO CONEJO PAVOREAL DELFIN BUHO'),
(3, 'GORRINO PEZ ALPACA RENO CANGURO VACA PERRO PAJARO'),
(4, 'VACA CIGUEÑA ARDILLA CONEJO GAMBA GALLINA RATA PANDA'),
(5, 'FLAMENCO RINOCERONTE KIWI CANGURO SINSAJO DELFIN HURON GAMBA'),
(6, 'RINOCERONTE UNICORNIO OVEJA CANGREJO COCODRILO AVISPA ALPACA RATA'),
(7, 'RANA VACA LOBO CANGREJO TIGRE HURON HAMSTER PAVOREAL'),
(8, 'SERPIENTE CANGREJO PEZ BUHO ELEFANTE KIWI LEON ARDILLA'),
(9, 'ABEJA OSO PINGUINO LEON HURON ALPACA CABALLO CONEJO'),
(10, 'FOCA ALPACA ARDILLA RANA SALTAMONTES FLAMENCO CARACOL ZORRO'),
(11, 'SINSAJO CEBRA PAVOREAL ALPACA MARIPOSA SERPIENTE GALLINA GATO'),
(12, 'LOBO ARDILLA UNICORNIO MARIPOSA PALOMA PERRO PINGUINO DELFIN'),
(13, 'MARIPOSA TIGRE PEZ TORTUGA OSO GAMBA COCODRILO ZORRO'),
(14, 'GATO GAMBA FOCA ABEJA PERRO MARIQUITA LORO CANGREJO'),
(15, 'SALTAMONTES CIGUEÑA ORCA COCODRILO HURON PERRO SERPIENTE STAR'),
(16, 'HURON AVISPA CISNE TORTUGA RENO ARDILLA PULPO GATO'),
(17, 'ABEJA SINSAJO HAMSTER BALLENA COCODRILO PEREZOSO ARDILLA GORRINO'),
(18, 'PULPO UNICORNIO TIGRE FOCA CANGREJO CONEJO SERPIENTE BALLENA'),
(19, 'DELFIN ORCA GATO BALLENA VACA LEON ZORRO OVEJA'),
(20, 'PAJARO ZORRO SINSAJO STAR CANGREJO PALOMA CISNE CONEJO'),
(21, 'OSO VACA FLAMENCO PEREZOSO SERPIENTE AVISPA PALOMA MARIQUITA'),
(22, 'LORO VACA CABALLO UNICORNIO SALTAMONTES SINSAJO BUHO TORTUGA'),
(23, 'BALLENA RANA GALLINA RINOCERONTE BUHO CISNE PERRO OSO'),
(24, 'RENO ELEFANTE OSO FOCA LOBO SINSAJO ORCA RATA'),
(25, 'CONEJO LORO GORRINO MARIPOSA RANA KIWI AVISPA ORCA'),
(26, 'CEBRA PINGUINO KIWI VACA AGUILA CISNE FOCA COCODRILO'),
(27, 'CEBRA PULPO CANGREJO SALTAMONTES GORRINO DELFIN OSO PANDA'),
(28, 'PULPO LORO LOBO GALLINA COCODRILO PAJARO FLAMENCO LEON'),
(29, 'CEBRA PEREZOSO UNICORNIO RENO LEON STAR RANA GAMBA'),
(30, 'TIGRE GATO STAR GORRINO PINGUINO FLAMENCO RATA BUHO'),
(31, 'PEREZOSO PERRO PULPO RATA KIWI CABALLO PAVOREAL ZORRO'),
(32, 'ELEFANTE PINGUINO GAMBA SALTAMONTES PAVOREAL AVISPA PAJARO BALLENA'),
(33, 'MARIQUITA BALLENA TORTUGA STAR KIWI ALPACA PANDA LOBO'),
(34, 'MARIQUITA ORCA CEBRA RINOCERONTE ARDILLA TIGRE CABALLO PAJARO'),
(35, 'GAMBA ORCA BUHO PALOMA ALPACA HAMSTER PULPO AGUILA'),
(36, 'RATA PALOMA HURON BALLENA PEZ CEBRA LORO CARACOL'),
(37, 'STAR AGUILA LORO PAVOREAL CANGURO OSO OVEJA ARDILLA'),
(38, 'LOBO SALTAMONTES GATO CONEJO PEREZOSO AGUILA RINOCERONTE PEZ'),
(39, 'LORO CISNE ALPACA ELEFANTE DELFIN TIGRE CIGUEÑA PEREZOSO'),
(40, 'ZORRO HAMSTER PANDA RENO LORO PINGUINO SERPIENTE RINOCERONTE'),
(41, 'PALOMA SALTAMONTES RENO OVEJA ABEJA KIWI GALLINA TIGRE'),
(42, 'FOCA HAMSTER DELFIN CABALLO PEZ GALLINA STAR AVISPA'),
(43, 'OVEJA FLAMENCO HAMSTER TORTUGA PERRO ELEFANTE CEBRA CONEJO'),
(44, 'CARACOL VACA RINOCERONTE STAR ABEJA MARIPOSA ELEFANTE PULPO'),
(45, 'SALTAMONTES RATA CANGURO CISNE LEON MARIPOSA MARIQUITA HAMSTER'),
(46, 'UNICORNIO ZORRO MARIQUITA ELEFANTE GORRINO AGUILA GALLINA HURON'),
(47, 'OVEJA MARIPOSA PANDA HURON PAJARO FOCA PEREZOSO BUHO'),
(48, 'SINSAJO CIGUEÑA PEZ PINGUINO PULPO RANA OVEJA MARIQUITA'),
(49, 'GORRINO SERPIENTE CISNE LOBO GAMBA CARACOL OVEJA CABALLO'),
(50, 'CARACOL KIWI GATO OSO UNICORNIO PAJARO HAMSTER CIGUEÑA'),
(51, 'ABEJA PAJARO SERPIENTE TORTUGA DELFIN RATA AGUILA RANA'),
(52, 'RINOCERONTE FLAMENCO TORTUGA LEON PALOMA CIGUEÑA GORRINO FOCA'),
(53, 'ELEFANTE GATO PANDA CABALLO RANA COCODRILO CANGURO PALOMA'),
(54, 'ORCA PEZ PAVOREAL AVISPA CISNE FLAMENCO PANDA UNICORNIO'),
(55, 'CANGREJO MARIPOSA FLAMENCO BALLENA RENO AGUILA CABALLO CIGUEÑA'),
(56, 'CARACOL AGUILA PERRO AVISPA PANDA TIGRE SINSAJO LEON');
