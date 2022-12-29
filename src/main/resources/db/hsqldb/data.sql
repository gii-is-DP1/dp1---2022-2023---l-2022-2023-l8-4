-- Users
INSERT INTO users(username,password,enabled) VALUES ('admin1','4dm1n',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (1,'admin1','admin');

INSERT INTO users(username,password,enabled) VALUES ('pgmarc','abc', TRUE);
INSERT INTO authorities(id,username,authority) VALUES (4,'pgmarc','admin');

INSERT INTO users(username,password,enabled) VALUES ('carbersor','123', TRUE);
INSERT INTO authorities(id,username,authority) VALUES (5,'carbersor','admin');

INSERT INTO users(username,password,enabled) VALUES ('fermatgom','admin1234', TRUE);
INSERT INTO authorities(id,username,authority) VALUES (6,'fermatgom','admin');

INSERT INTO users(username,password,enabled) VALUES ('pedlopruz','pedlopruz2002', TRUE);
INSERT INTO authorities(id,username,authority) VALUES (7,'pedlopruz','admin');

INSERT INTO users(username,password,enabled) VALUES ('carzarrei','chocolate', TRUE);
INSERT INTO authorities(id,username,authority) VALUES (8,'carzarrei','admin');

INSERT INTO users(username,password,enabled) VALUES ('dangalmar','kaka', TRUE);
INSERT INTO authorities(id,username,authority) VALUES (9,'dangalmar','admin');

--Achivements
INSERT INTO achievement(id,name,description,threshold,percentage,trophy,acquire_date,badge_image) VALUES
(1,'Viciado','Si juegas <THRESHOLD> partidas o mas',10.0,90.0,3,'2022-01-01','/resources/images/logros/viciado.png'),
(2,'Soldado','Si ganas <THRESHOLD> partidas o mas, en cualquier modo es que eres todo un talentoso.',20.0,70.0,2,'2022-01-01','/resources/images/logros/soldado.png'),
(3,'King','Si juegas <THRESHOLD> partidas o mas, en cualquier modo es que eres todo un rey.',100.0,70.0,3,'2022-01-01','/resources/images/logros/kinglogo.png'),
(4,'Sensei','Si ganas <THRESHOLD> partidas o mas, en cada modo que eres todo un sensei.',10.0,70.0,1,'2022-01-15','/resources/images/logros/sensei logo.png'),
(5,'El Dobble','Si ganas <THRESHOLD> partida, consiguiendo todas las cartas eres el dobble.',1.0,70.0,0,'2022-01-10','/resources/images/logo.png');

--Statistics
INSERT INTO statistic(id,total_points,games_played, games_won, games_lost) VALUES 

(1, 80, 3, 2, 1),
(2, 60, 4, 1, 3),
(3, 21, 3, 1, 2),
(4, 103, 1, 1, 0),
(5, 45, 2, 0, 2);

--Players
INSERT INTO players(id,username,register_date,modification_date,last_login,email,birth_date,profile_picture,statistic_id) VALUES 
(1,'pgmarc', '2022-01-01','2022-01-01', '2022-10-01', 'fausto@gmail.com',  '2001-01-01', 'resources/images/logros/viciado.png',1),
(2,'carbersor',  '2022-02-02','2022-02-03', '2022-10-01', 'rukisro@gmail.com',  '1992-01-01', '/resources/images/logros/viciado.png',2),
(3,'fermatgom',   '2022-02-02','2022-02-03', '2022-10-01','sergio@gmail.com',  '1975-01-01', '/resources/images/logros/viciado.png',3),
(4,'pedlopruz',   '2022-02-02','2022-02-02', '2022-10-01',  'rocioalberca@gmail.com',  '2002-01-01', '/resources/images/logros/viciado.png',4),
(5,'carzarrei',  '2022-04-01','2022-01-01', '2022-10-01',  'vic@gmail.com',  '2000-01-01', '/resources/images/logros/viciado.png',5);

--Games
INSERT INTO games(id,creator,date,game_mode, game_state, game_code) VALUES 

(1, 'pgmarc', '2022-11-04', 0, 1, 10),
(2, 'carbersor', '2022-11-04', 0, 0, 11),
(3, 'fermatgom', '2022-11-06', 1, 1, 12),
(4, 'pedlopruz', '2022-11-09', 0, 2, 13),
(5, 'carzarrei', '2022-11-10', 2, 2, 14);

--Player games
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

--Player achievements
INSERT INTO players_achievements(achievement_id,player_id) VALUES 
(1,1),
(1,2),
(1,4),
(2,2),
(2,3),
(3,3),
(3,2),
(3,1),
(3,3),
(4,4),
(1,4),
(2,4),
(4,4),
(3,3),
(4,4),
(1,4),
(2,4),
(4,4),
(3,5),
(5,5);

--Player Game Data

-- Creating deck of cards
INSERT INTO cards (id, icons) VALUES
(0, 'AVISPA CEBRA ABEJA CIGUEÑA ZORRO LOBO BUHO CANGURO'),
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
