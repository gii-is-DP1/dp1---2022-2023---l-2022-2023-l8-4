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

--Achivements
INSERT INTO achievements(id,name,description,threshold,percentage,trophy,badge_image) VALUES
(1,'Gamer','If you play <THRESHOLD> games or more in any game mode, you are truly a gamer.',10.0,0.0,3,'/resources/images/logros/viciado.png'),
(2,'Soldier','If you play <THRESHOLD> games or more in any game mode, you are truly a soldier.',20.0,0.0,2,'/resources/images/logros/soldado.png'),
(3,'King','If you play <THRESHOLD> games or more in any game mode, you are truly a king.',100.0,0.0,1,'/resources/images/logros/kinglogo.png'),
(4,'Sensei','If you win <THRESHOLD> games or more in any game mode, you are truly a sensei.',10.0,0.0,1,'/resources/images/logros/sensei logo.png'),
(5,'The Dobble','If you win at least <THRESHOLD> game getting 50 or more points, you are The Dobble.',1.0,0.0,0,'/resources/images/logo.png');

--Statistics
INSERT INTO statistics(id,total_points,games_played, games_won, games_lost) VALUES 

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
(5, 'carzarrei', '2022-11-10', 1, 2, 14);

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

INSERT INTO cards (id, icons) VALUES
(0, 'CABALLO FOCA SINSAJO SERPIENTE PALOMA DELFIN ABEJA ZORRO'),
(1, 'PULPO COCODRILO KIWI ORCA CABALLO GALLINA GATO RATA'),
(2, 'CANGURO LORO RATA FLAMENCO VACA OVEJA PERRO ZORRO'),
(3, 'PULPO PERRO LOBO CISNE UNICORNIO SINSAJO MARIPOSA STAR'),
(4, 'STAR AVISPA HAMSTER CANGURO PALOMA PAJARO PANDA GALLINA'),
(5, 'HURON CABALLO PAVOREAL TORTUGA FLAMENCO HAMSTER CONEJO LOBO'),
(6, 'ARDILLA SERPIENTE GATO PANDA OSO RENO MARIPOSA FLAMENCO'),
(7, 'MARIQUITA CONEJO RENO GALLINA ELEFANTE GAMBA ABEJA PERRO'),
(8, 'GAMBA ORCA HURON PANDA ZORRO LEON CISNE PEZ'),
(9, 'ELEFANTE PULPO CEBRA AVISPA SERPIENTE RANA LORO HURON'),
(10, 'LORO ARDILLA GRILLO AGUILA GAMBA CABALLO STAR RINOCERONTE'),
(11, 'TIGRE LEON CANGURO ELEFANTE BUHO MARIPOSA CABALLO PEREZOSO'),
(12, 'VACA TIGRE GRILLO PANDA DELFIN MARIQUITA PULPO TORTUGA'),
(13, 'FOCA PAJARO GATO TIGRE GAMBA OVEJA LOBO CEBRA'),
(14, 'AVISPA MARIQUITA CANGREJO UNICORNIO OVEJA OSO CABALLO PEZ'),
(15, 'RANA BUHO LOBO RATA ABEJA PANDA CANGREJO AGUILA'),
(16, 'BALLENA PAVOREAL CISNE GATO BUHO PALOMA LORO MARIQUITA'),
(17, 'PAVOREAL UNICORNIO TIGRE GORRINO GALLINA RANA ZORRO ARDILLA'),
(18, 'STAR GATO PINGUINO CANGREJO TORTUGA CIGUEÑA ZORRO ELEFANTE'),
(19, 'RATA GRILLO OSO ELEFANTE GORRINO FOCA HAMSTER CISNE'),
(20, 'STAR DELFIN HURON BUHO RENO GORRINO OVEJA KIWI'),
(21, 'GORRINO SINSAJO GATO VACA AGUILA AVISPA CONEJO LEON'),
(22, 'PINGUINO RANA MARIPOSA ORCA CONEJO OVEJA GRILLO PALOMA'),
(23, 'COCODRILO SINSAJO OVEJA PAVOREAL RINOCERONTE ELEFANTE PANDA CARACOL'),
(24, 'COCODRILO AVISPA GRILLO PEREZOSO BALLENA LOBO RENO ZORRO'),
(25, 'GATO HURON CANGURO UNICORNIO CARACOL ALPACA ABEJA GRILLO'),
(26, 'ABEJA CIGUEÑA OVEJA LEON ARDILLA HAMSTER BALLENA PULPO'),
(27, 'VACA PALOMA ALPACA PEZ ARDILLA KIWI LOBO ELEFANTE'),
(28, 'KIWI CISNE RINOCERONTE ABEJA AVISPA TIGRE FLAMENCO PINGUINO'),
(29, 'PERRO LEON PAVOREAL SERPIENTE PAJARO CANGREJO KIWI GRILLO'),
(30, 'PEREZOSO PAJARO ARDILLA SINSAJO RATA MARIQUITA PINGUINO HURON'),
(31, 'BALLENA CONEJO RATA SERPIENTE PEZ TIGRE CARACOL STAR'),
(32, 'ZORRO AGUILA CEBRA CARACOL MARIPOSA MARIQUITA KIWI HAMSTER'),
(33, 'SERPIENTE MARIQUITA CIGUEÑA ORCA LOBO RINOCERONTE CANGURO GORRINO'),
(34, 'FLAMENCO UNICORNIO ORCA ELEFANTE PAJARO BALLENA AGUILA DELFIN'),
(35, 'PEZ PULPO CANGURO FOCA PAVOREAL PINGUINO RENO AGUILA'),
(36, 'LOBO DELFIN GALLINA LEON CARACOL OSO LORO PINGUINO'),
(37, 'STAR CEBRA PEREZOSO OSO ORCA VACA PAVOREAL ABEJA'),
(38, 'HAMSTER COCODRILO PINGUINO UNICORNIO BUHO GAMBA VACA SERPIENTE'),
(39, 'PERRO TORTUGA BUHO ARDILLA FOCA CARACOL ORCA AVISPA'),
(40, 'GALLINA GRILLO BUHO PEZ FLAMENCO CIGUEÑA SINSAJO CEBRA'),
(41, 'HURON CIGUEÑA PERRO PALOMA TIGRE COCODRILO OSO AGUILA'),
(42, 'GORRINO ABEJA PAJARO PEZ MARIPOSA COCODRILO LORO TORTUGA'),
(43, 'RINOCERONTE PEZ PEREZOSO RANA GATO PERRO HAMSTER DELFIN'),
(44, 'HURON MARIPOSA GALLINA BALLENA CANGREJO VACA RINOCERONTE FOCA'),
(45, 'CISNE CIGUEÑA CABALLO RANA RENO PAJARO VACA CARACOL'),
(46, 'OVEJA AGUILA CISNE TORTUGA ALPACA PEREZOSO GALLINA SERPIENTE'),
(47, 'PANDA ALPACA CEBRA BALLENA CABALLO GORRINO PERRO PINGUINO'),
(48, 'COCODRILO ALPACA FOCA STAR LEON FLAMENCO RANA MARIQUITA'),
(49, 'OSO TORTUGA CANGURO SINSAJO KIWI GAMBA RANA BALLENA'),
(50, 'SINSAJO LORO TIGRE ALPACA RENO HAMSTER ORCA CANGREJO'),
(51, 'CONEJO ZORRO ALPACA RINOCERONTE BUHO PULPO OSO PAJARO'),
(52, 'RENO CEBRA RINOCERONTE TORTUGA LEON PALOMA RATA UNICORNIO'),
(53, 'CARACOL PEREZOSO GAMBA CANGREJO PULPO PALOMA FLAMENCO GORRINO'),
(54, 'LORO CIGUEÑA CONEJO PEREZOSO KIWI PANDA FOCA UNICORNIO'),
(55, 'ALPACA AVISPA GAMBA DELFIN RATA CIGUEÑA PAVOREAL MARIPOSA'),
(56, 'CANGREJO ARDILLA CEBRA COCODRILO CONEJO DELFIN CANGURO CISNE');

--Player Game Data
INSERT INTO player_game_data(id,points,winner,actual_card_id,player_id,game_id) VALUES
(1, 23, false, 0, 1, 1),
(2, 31, false, 1, 2, 1),
(3, 14, false, 2, 2, 2),
(4, 15, false, 3, 3, 2),
(5, 14, false , 4, 3, 3),
(6, 12, false, 5, 2, 3),
(7, 10, false, 6, 1, 3),
(8, 5, false, 7, 5, 3),
(9, 10, false, 8, 4, 4),
(10, 45, true, 9, 2, 4),
(11, 1, false, 10, 5, 5),
(12, 2, false, 11, 3, 5),
(13, 14, true, 12, 1, 5);