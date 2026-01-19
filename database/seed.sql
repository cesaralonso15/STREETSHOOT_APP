USE streetshoot;

INSERT INTO USUARIO (email, nick, hash_password, nivel, exp)
VALUES
('ivan@test.com', 'Ivan', '1234', 1, 0),
('hector@test.com', 'Hector', '1234', 1, 0),
('sergio@test.com', 'Sergio', '1234', 1, 0);

INSERT INTO ZONA (nombre, ciudad, tipo, lat, lng, radio_metros, activa)
VALUES
('Parque Central', 'Madrid', 'parque', 40.416775, -3.703790, 150, TRUE),
('Plaza Mayor', 'Madrid', 'plaza', 40.415363, -3.707398, 120, TRUE),
('Campo Norte', 'Madrid', 'campo', 40.430000, -3.690000, 200, TRUE);

INSERT INTO STREETSPOT (zona_id, nombre, lat, lng, radio_metros, activo)
VALUES
(1, 'Escaleras rojas', 40.416900, -3.703900, 20, TRUE),
(1, 'Muro grafiti', 40.417100, -3.703600, 25, TRUE),
(2, 'Fuente antigua', 40.415400, -3.707500, 15, TRUE);

INSERT INTO RETO (retador_id, rival_id, zona_id, modo, estado, fecha_hora)
VALUES
(1, 2, 1, 'asincrono', 'pendiente', NOW());

INSERT INTO NOTIFICACION (usuario_id, tipo, payload)
VALUES
(2, 'reto', 'Ivan te ha retado en Parque Central');
