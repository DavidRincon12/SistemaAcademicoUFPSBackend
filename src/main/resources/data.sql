-- Insertar roles
INSERT INTO rol (nombre, permisos) VALUES ('Administrador', 'ALL');
INSERT INTO rol (nombre, permisos) VALUES ('Docente', 'READ_WRITE');
INSERT INTO rol (nombre, permisos) VALUES ('Estudiante', 'READ');
INSERT INTO rol (nombre, permisos) VALUES ('Invitado', 'LIMITED');

-- Insertar personas
INSERT INTO persona (
    nombre, contrasena, fecha_registro, tipo_documento, numero_documento,
    direccion, telefono, correo, fecha_nacimiento, estado_civil,
    nacionalidad, datos_profesionales, genero, codigo, rol_id
) VALUES (
    'Ana', '12345', '2024-03-31', 'CC', '1002003001', 'Cra 45 #22', '3134567890', 'ana@example.com',
    '1999-12-31', 'Soltera', 'Colombiana', 'Ingeniera', 'Femenino', 'P001',
    (SELECT id FROM rol WHERE nombre = 'Estudiante')
);

INSERT INTO persona (
    nombre, contrasena, fecha_registro, tipo_documento, numero_documento,
    direccion, telefono, correo, fecha_nacimiento, estado_civil,
    nacionalidad, datos_profesionales, genero, codigo, rol_id
) VALUES (
    'Carlos', '12345', '2024-03-31', 'CC', '1002003002', 'Cra 45 #22', '3134567891', 'carlos@example.com',
    '1990-05-12', 'Soltero', 'Colombiano', 'Profesor', 'Masculino', 'P002',
    (SELECT id FROM rol WHERE nombre = 'Docente')
);

-- Insertar docentes (relacionados con personas previamente creadas o nuevas)

-- Persona 1: Carlos (ya insertado arriba)
INSERT INTO docente (
    correo_institucional, tipo, persona_id
) VALUES (
    'carlos.prof@ufps.edu.co', 'Tiempo Completo',
    (SELECT id FROM persona WHERE id = 2)
);

-- Persona 2: María
INSERT INTO persona (
    nombre, contrasena, fecha_registro, tipo_documento, numero_documento,
    direccion, telefono, correo, fecha_nacimiento, estado_civil,
    nacionalidad, datos_profesionales, genero, codigo, rol_id
) VALUES (
    'Maria', '54321', '2024-03-30', 'TI', '1002003003', 'Calle 10 #5-20', '3123456789', 'maria@example.com',
    '1985-06-15', 'Casada', 'Colombiana', 'Magister en Educación', 'Femenino', 'P003',
    (SELECT id FROM rol WHERE nombre = 'Docente')
);

INSERT INTO docente (
    correo_institucional, tipo, persona_id
) VALUES (
    'maria.prof@ufps.edu.co', 'Catedrático',
    (SELECT id FROM persona WHERE nombre = 'Maria')
);

-- Persona 3: Juan
INSERT INTO persona (
    nombre, contrasena, fecha_registro, tipo_documento, numero_documento,
    direccion, telefono, correo, fecha_nacimiento, estado_civil,
    nacionalidad, datos_profesionales, genero, codigo, rol_id
) VALUES (
    'Juan', '98765', '2024-03-29', 'CC', '1002003004', 'Av. Principal #10', '3112233445', 'juan@example.com',
    '1978-11-03', 'Casado', 'Colombiano', 'PhD en Ciencias', 'Masculino', 'P004',
    (SELECT id FROM rol WHERE nombre = 'Docente')
);

INSERT INTO docente (
    correo_institucional, tipo, persona_id
) VALUES (
    'juan.prof@ufps.edu.co', 'Tiempo Completo',
    (SELECT id FROM persona WHERE nombre = 'Juan')
);

-- Carlos (Docente id = 1)
INSERT INTO HORARIOASESORIA (dia_semana, hora_inicio, hora_fin, docente_id)
VALUES 
('MONDAY', '08:00:00', '10:00:00', 1),
('WEDNESDAY', '14:00:00', '16:00:00', 1);

-- María (Docente id = 2)
INSERT INTO HORARIOASESORIA (dia_semana, hora_inicio, hora_fin, docente_id)
VALUES 
('FRIDAY', '09:00:00', '11:00:00', 2),
('TUESDAY', '13:30:00', '15:00:00', 2);

-- Juan (Docente id = 3)
INSERT INTO HORARIOASESORIA (dia_semana, hora_inicio, hora_fin, docente_id)
VALUES 
('THURSDAY', '10:00:00', '12:00:00', 3),
('MONDAY', '15:00:00', '17:00:00', 3);