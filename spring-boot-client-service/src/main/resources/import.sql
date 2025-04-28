
/* AGREGAR INFORMACION*/

-- Insertar datos en la tabla persona
INSERT INTO persona (nombre, genero, edad, identificacion, direccion, telefono, tipo_persona) VALUES
('Jose Lema', 'Masculino', 30, '1234567890', 'Otavalo sn y principal', '098254785', 'CLIENTE'),
('Marianela Montalvo', 'Femenino', 28, '0987654321', 'Amazonas y NNUU', '097548965', 'CLIENTE'),
('Juan Osorio', 'Masculino', 35, '1122334455', '13 junio y Equinoccial', '098874587', 'CLIENTE');

-- Insertar datos en la tabla cliente
INSERT INTO cliente (contrasena, estado, id) VALUES
('1234', TRUE, 1),
('5678', TRUE, 2),
('1245', TRUE, 3); 