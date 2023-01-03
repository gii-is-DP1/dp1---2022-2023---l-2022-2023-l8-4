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
INSERT INTO achievement(id,name,description,threshold,percentage,trophy,badge_image) VALUES
(1,'Viciado','Si juegas <THRESHOLD> partidas o mas',10.0,90.0,3,'/resources/images/logros/viciado.png'),
(2,'Soldado','Si ganas <THRESHOLD> partidas o mas, en cualquier modo es que eres todo un talentoso.',20.0,70.0,2,'/resources/images/logros/soldado.png'),
(3,'King','Si juegas <THRESHOLD> partidas o mas, en cualquier modo es que eres todo un rey.',100.0,70.0,3,'/resources/images/logros/kinglogo.png'),
(4,'Sensei','Si ganas <THRESHOLD> partidas o mas, en cada modo que eres todo un sensei.',10.0,70.0,1,'/resources/images/logros/sensei logo.png'),
(5,'El Dobble','Si ganas <THRESHOLD> partida, consiguiendo todas las cartas eres el dobble.',1.0,70.0,0,'/resources/images/logo.png');

--Statistics
INSERT INTO statistic(id,total_points,games_played, games_won, games_lost) VALUES 

(1, 80, 3, 2, 1),
(2, 60, 4, 1, 3),
(3, 21, 3, 1, 2),
(4, 103, 1, 1, 0),
(5, 45, 2, 0, 2);

--Players
INSERT INTO players(id,username,register_date,modification_date,last_login,email,birth_date,profile_picture,statistic_id) VALUES 
(1,'pgmarc', '2022-01-01','2022-01-01', '2022-10-01', 'fausto@gmail.com',  '2001-01-01', '/resources/images/logros/viciado.png',1),
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
(3,2),
(3,1),
(3,3),
(4,4),
(2,4),
(3,5),
(5,5);

--Player Game Data

INSERT INTO cards (id, icons) VALUES
(0, 'ABEJA HURON CEBRA CARACOL GALLINA RATA LOBO RENO'),
(1, 'ALPACA PERRO ELEFANTE MARIPOSA LOBO AVISPA CIGUEÑA RANA'),
(2, 'HAMSTER CIGUEÑA OSO CEBRA GATO PAVOREAL KIWI UNICORNIO'),
(3, 'STAR SERPIENTE FLAMENCO CANGURO KIWI RATA RANA GRILLO'),
(4, 'VACA CIGUEÑA ARDILLA CONEJO GAMBA GALLINA RATA PANDA'),
(5, 'STAR ALPACA OSO DELFIN PALOMA CABALLO TORTUGA CARACOL'),
(6, 'CONEJO STAR PEREZOSO RENO ZORRO ARDILLA GAMBA CIGUEÑA'),
(7, 'HAMSTER SINSAJO PAJARO GAMBA LORO GORRINO CARACOL RANA'),
(8, 'GRILLO CANGREJO MARIPOSA ABEJA LORO GATO CABALLO ZORRO'),
(9, 'GORRINO SERPIENTE CIGUEÑA LEON AGUILA DELFIN ABEJA ORCA'),
(10, 'PULPO CEBRA GAMBA PEZ AVISPA CISNE CABALLO SERPIENTE'),
(11, 'LEON HURON HAMSTER CANGREJO PERRO PANDA PEZ STAR'),
(12, 'VACA RANA CONEJO HURON CISNE ORCA GATO PALOMA'),
(13, 'GAMBA FOCA MARIPOSA UNICORNIO RATA TIGRE PALOMA LEON'),
(14, 'OSO AVISPA CONEJO RINOCERONTE RATA CANGREJO PAJARO AGUILA'),
(15, 'HURON PEREZOSO COCODRILLO DELFIN MARIPOSA KIWI PULPO PAJARO'),
(16, 'CANGURO COCODRILO BUHO CIGUEÑA CISNE CARACOL TIGRE CANGREJO'),
(17, 'PALOMA GRILLO OVEJA PINGUINO PAJARO GALLINA CIGUEÑA PEZ'),
(18, 'GATO AVISPA CARACOL LEON PINGUINO PEREZOSO MARIQUITA FLAMENCO'),
(19, 'LORO PEREZOSO CEBRA CANGURO PALOMA BALLENA PERRO AGUILA'),
(20, 'PANDA CONEJO SERPIENTE MARIPOSA PAVOREAL OVEJA CARACOL BALLENA'),
(21, 'STAR FOCA ORCA COCODRILO LORO AVISPA PAVOREAL GALLINA'),
(22, 'BALLENA ELEFANTE MARIQUITA UNICORNIO STAR PAJARO CISNE ABEJA'),
(23, 'TORTUGA PEZ RANA ABEJA TIGRE PAVOREAL PEREZOSO RINOCERONTE'),
(24, 'ALPACA OVEJA KIWI RINOCERONTE LEON RENO LORO CISNE'),
(25, 'FOCA OSO PANDA PEREZOSO GORRINO LOBO CISNE GRILLO'),
(26, 'CABALLO FOCA BALLENA FLAMENCO RINOCERONTE SINSAJO CIGUEÑA HURON'),
(27, 'BUHO MARIQUITA PAVOREAL GRILLO GAMBA ALPACA AGUILA HURON'),
(28, 'PEZ UNICORNIO FLAMENCO LOBO DELFIN CONEJO BUHO LORO'),
(29, 'GORRINO HURON AVISPA TORTUGA CANGURO OVEJA ZORRO UNICORNIO'),
(30, 'DELFIN GATO PANDA RINOCERONTE GALLINA CANGURO GAMBA ELEFANTE'),
(31, 'PALOMA PAVOREAL CANGREJO PULPO RENO ELEFANTE GORRINO FLAMENCO'),
(32, 'TIGRE PULPO STAR LOBO OVEJA SINSAJO GATO AGUILA PULPO'),
(33, 'GRILLO CARACOL UNICORNIO ORCA PERRO RINOCERONTE PULPO ARDILLA'),
(34, 'SERPIENTE MARIQUITA COCODRILO HAMSTER LOBO PALOMA ZORRO RINOCERONTE'),
(35, 'CEBRA PAJARO ALPACA PANDA ZORRO TIGRE FLAMENCO ORCA'),
(36, 'RINOCERONTE CEBRA BUHO PINGUINO MARIPOSA STAR GORRINO VACA'),
(37, 'OSO FLAMENCO PERRO COCODRILO ABEJA VACA GAMBA OVEJA'),
(38, 'ARDILLA RANA FOCA MARIQUITA OVEJA CEBRA CANGREJO DELFIN'),
(39, 'PEREZOSO UNICORNIO SERPIENTE VACA SINSAJO GALLINA ALPACA CANGREJO'),
(40, 'LEON CONEJO CEBRA GRILLO SINSAJO TORTUGA COCODRILO ELEFANTE'),
(41, 'PULPO FOCA PINGUINO CANGURO CONEJO ABEJA HAMSTER ALPACA'),
(42, 'TIGRE GALLINA CABALLO MARIQUITA GORRINO PERRO CONEJO KIWI'),
(43, 'PEREZOSO RATA OVEJA ELEFANTE CABALLO ORCA BUHO HAMSTER'),
(44, 'HAMSTER RENO TIGRE VACA BALLENA AVISPA GRILLO DELFIN'),
(45, 'ELEFANTE AGUILA FOCA PEZ CARACOL KIWI ZORRO VACA'),
(46, 'AGUILA FLAMENCO HAMSTER ARDILLA CISNE MARIPOSA GALLINA TORTUGA'),
(47, 'PAJARO LEON ARDILLA CANGURO PAVOREAL VACA CABALLO LOBO'),
(48, 'SINSAJO ARDILLA ABEJA KIWI BUHO PALOMA AVISPA PANDA'),
(49, 'OSO RANA GALLINA PULPO BUHO BALLENA LEON ZORRO'),
(50, 'SINSAJO ORCA OSO RENO MARIQUITA MARIPOSA PEZ CANGURO'),
(51, 'UNICORNIO CABALLO AGUILA PANDA RENO PINGUINO COCODRILO RANA'),
(52, 'PINGUINO RATA SINSAJO DELFIN PAVOREAL PERRO ZORRO CISNE'),
(53, 'ALPACA RATA BALLENA PEZ ARDILLA GORRINO GATO COCODRILO'),
(54, 'FOCA BUHO SERPIENTE PAJARO RENO TORTUGA GATO PERRO'),
(55, 'CIGUEÑA PANDA MARIQUITA VACA RATA TORTUGA PULPO LORO'),
(56, 'TIGRE OSO SERPIENTE ARDILLA LORO HURON PINGUINO ELEFANTE');
