-- One admin user, named admin1 with passwor 4dm1n and authority admin
INSERT INTO users(username,password,enabled) VALUES ('admin1','4dm1n',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (1,'admin1','admin');
-- One owner user, named owner1 with passwor 0wn3r
INSERT INTO users(username,password,enabled) VALUES ('owner1','0wn3r',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (2,'owner1','owner');
-- One vet user, named vet1 with passwor v3t
INSERT INTO users(username,password,enabled) VALUES ('vet1','v3t',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (3,'vet1','veterinarian');

--INSERT INTO owners VALUES (1, 'George', 'Franklin', '110 W. Liberty St.', 'Madison', '6085551023', 'owner1');
--INSERT INTO owners VALUES (2, 'Betty', 'Davis', '638 Cardinal Ave.', 'Sun Prairie', '6085551749', 'owner1');
--INSERT INTO owners VALUES (3, 'Eduardo', 'Rodriquez', '2693 Commerce St.', 'McFarland', '6085558763', 'owner1');
--INSERT INTO owners VALUES (4, 'Harold', 'Davis', '563 Friendly St.', 'Windsor', '6085553198', 'owner1');
--INSERT INTO owners VALUES (5, 'Peter', 'McTavish', '2387 S. Fair Way', 'Madison', '6085552765', 'owner1');
--INSERT INTO owners VALUES (6, 'Jean', 'Coleman', '105 N. Lake St.', 'Monona', '6085552654', 'owner1');
--INSERT INTO owners VALUES (7, 'Jeff', 'Black', '1450 Oak Blvd.', 'Monona', '6085555387', 'owner1');
--INSERT INTO owners VALUES (8, 'Maria', 'Escobito', '345 Maple St.', 'Madison', '6085557683', 'owner1');
--INSERT INTO owners VALUES (9, 'David', 'Schroeder', '2749 Blackhawk Trail', 'Madison', '6085559435', 'owner1');
--INSERT INTO owners VALUES (10, 'Carlos', 'Estaban', '2335 Independence La.', 'Waunakee', '6085555487', 'owner1');

--INSERT INTO owners VALUES (11, 'Pedro', 'Gonzalez', 'C. Gomez de la Lama', 'Camas', '684000708', 'pgmarc');
--INSERT INTO owners VALUES (12, 'Carlos', 'Bermejo', 'C. Juan de Mariana', 'Sevilla', '638026321', 'carbersor');
--INSERT INTO owners VALUES (13, 'Fernando José', 'Mateos Gómez', 'C. España', 'Sevilla', '618587795', 'fermatgom');
--INSERT INTO owners VALUES (14, 'Pedro', 'Lopez Ruz', 'C. Las Prietas', 'Montilla', '693776919', 'owner1');
--INSERT INTO owners VALUES (15, 'Carlos', 'Zarzuela Reina', 'C. Condesa', 'Sevilla', '601060413', 'carzarrei');

INSERT INTO jugador VALUES(0, 'Pedro', 'pedlopruz', 'pedlopruz2002', 'email', '2022-10-25', '2022-10-25', '2022-10-25','2002-12-10', 'https://bit.ly/certifiedGamer')




